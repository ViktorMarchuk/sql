package com.vm.jdbc.dao;

import com.vm.jdbc.dto.FlightFilter;
import com.vm.jdbc.entity.Flight;
import com.vm.jdbc.entity.FlightStatus;
import com.vm.jdbc.exceptions.DaoException;
import com.vm.jdbc.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDao implements Dao<Integer, Flight> {
    private final static FlightDao INSTANCE = new FlightDao();
    private static final String SQL_DELETE = """
            DELETE FROM  flight
            WHERE id=?
            """;
    private static final String SQL_SAVE = """
            INSERT INTO flight (id,flight_number,departure_date,departure_airport_code,arrival_date,
            arrival_airport_code,aircraft_id,status)
            VALUES (?,?,?,?,?,?,?,?)
            """;
    private static final String SQL_UPDATE = """
            UPDATE flight 
            SET flight_number=?,departure_date=?,departure_airport_code=?,arrival_date=?,arrival_airport_code=?,
            aircraft_id=?,status=?
            WHERE id=?       
            """;
    private static final String SQL_FIND_ALL = """
            SELECT id,flight_number,departure_date,departure_airport_code,arrival_date,
            arrival_airport_code,aircraft_id,status 
            FROM flight
                        """;
    private static final String SQL_FIND_BY_ID = """
            SELECT id,flight_number,departure_date,departure_airport_code,arrival_date,
                        arrival_airport_code,aircraft_id,status
                        FROM flight
                        WHERE id=?
            """;

    private FlightDao() {
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Flight flight) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, flight.getFlightNumber());
            statement.setString(2, flight.getDepartureDate());
            statement.setInt(3, flight.getDepartureAirportCode());
            statement.setString(4, flight.getArrivalDate());
            statement.setInt(5, flight.getArrivalAirportCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setObject(7, flight.getFlightStatus(), Types.OTHER);
            statement.setInt(8, flight.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_FIND_ALL);
            var resulSet = statement.executeQuery();
            while (resulSet.next()) {
                list.add(buildFlight(resulSet)
                );
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }


    @Override
    public Optional<Flight> findById(Integer id) {
        Flight flight = null;
        Connection connection = ConnectionManager.get();
        try (var statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                flight = buildFlight(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(flight);
    }


    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Flight save(Flight flight) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, flight.getId());
            statement.setString(2, flight.getFlightNumber());
            statement.setString(3, flight.getDepartureDate());
            statement.setInt(4, flight.getDepartureAirportCode());
            statement.setString(5, flight.getArrivalDate());
            statement.setInt(6, flight.getArrivalAirportCode());
            statement.setInt(7, flight.getAircraftId());
            statement.setObject(8, flight.getFlightStatus(), Types.OTHER);
            statement.executeUpdate();
            var result = statement.getGeneratedKeys();
            if (result.next()) {
                flight.setId(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return flight;
    }

    public Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(resultSet.getInt("id"),
                resultSet.getString("flight_number"),
                resultSet.getString("departure_date"),
                resultSet.getInt("departure_airport_code"),
                resultSet.getString("arrival_date"),
                resultSet.getInt("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                FlightStatus.valueOf(String.valueOf(resultSet.getObject("status")))
        );
    }

    public List<Flight> findBYFilter(FlightFilter flightFilter) {
        List<Flight> flights = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (flightFilter.aircraftId() != 0) {
            parameters.add(flightFilter.aircraftId());
            whereSql.add("aircraft_id=?");
        }
        if (flightFilter.flightStatus() != null) {
            parameters.add(flightFilter.flightStatus());
            whereSql.add("status=?");
        }
        parameters.add(flightFilter.limit());
        parameters.add(flightFilter.offset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parameters.size()>2 ?" WHERE " : "",
                " LIMIT ? OFFSET ?"
        ));
        try (Connection connection = ConnectionManager.get()) {
            String sql = SQL_FIND_ALL + where;
            var statement = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.size(); i++) {
                if(parameters.get(i) instanceof FlightStatus)
                statement.setObject(i + 1, parameters.get(i),Types.OTHER);
                else {
                    statement.setObject(i + 1, parameters.get(i));
                }
            }
            System.out.println(statement);
            var result = statement.executeQuery();
            while (result.next()) {
                flights.add(buildFlight(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
}
