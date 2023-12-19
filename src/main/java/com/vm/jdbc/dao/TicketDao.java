package com.vm.jdbc.dao;

import com.vm.jdbc.dto.TicketFilter;
import com.vm.jdbc.entity.Flight;
import com.vm.jdbc.entity.FlightStatus;
import com.vm.jdbc.entity.Ticket;
import com.vm.jdbc.exceptions.DaoException;
import com.vm.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements Dao<Long, Ticket> {

    private final static TicketDao INSTANCE = new TicketDao();
    private static final String SQL_SAVE = """
            insert into ticket (id, passport_number, name, flight_id, seat, cost) VALUES 
                        (?,?,?,?,?,?)
            """;
    private static final String SQL_DELETE = """
            delete from ticket where id=?
            """;
    private static final String SQL_FIND_ALL = """
            SELECT t.id, t.passport_number, t.name, t.flight_id, t.seat, t.cost,f.flight_id,f.departure_date,
            f.departure_airport_code,f.arrival_date,f.arrival_airport_code,f.aircraft_id,f.status   
            FROM ticket t
            JOIN flight f on f.id=t.flight_id
            """;
    private static final String SQL_FIND_BY_ID = """
        SELECT t.id, t.passport_number, t.name, t.flight_id, t.seat, t.cost,
        f.flight_number, f.departure_date, f.departure_airport_code,
        f.arrival_date, f.arrival_airport_code, f.aircraft_id, f.status
        FROM ticket t
        JOIN flight f ON f.id = t.flight_id
        WHERE t.id = ?
        """;

    private static final String SQL_UPDATE = """
            update ticket set passport_number=?,name=?,flight_id=?,seat=?,cost=? 
            where id =?
            """;

    private TicketDao() {
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public Ticket save(Ticket ticket) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, ticket.getId());
            statement.setString(2, ticket.getPassportNumber());
            statement.setString(3, ticket.getName());
            statement.setLong(4, ticket.getFlight().getId());
            statement.setInt(5, ticket.getSeat());
            statement.setBigDecimal(6, ticket.getCost());
            statement.executeUpdate();
            var resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                ticket.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return ticket;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll() {
        List<Ticket> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_FIND_ALL);
            var result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        buildTicket(result)
                );
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    public Optional<Ticket> findById(Long id) {
        Ticket ticket = null;
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                ticket = buildTicket(result);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(ticket);
    }

    private Ticket buildTicket(ResultSet result) throws SQLException {
        var flight = new Flight(result.getInt("flight_id"),
                result.getString("flight_number"),
                result.getString("departure_date"),
                result.getInt("departure_airport_code"),
                result.getString("arrival_date"),
                result.getInt("arrival_airport_code"),
                result.getInt("aircraft_id"),
                FlightStatus.valueOf(String.valueOf(result.getObject("status")))
        );
        return new Ticket(
                result.getLong("id"),
                result.getString("passport_number"),
                result.getString("name"),
                flight,
                result.getInt("seat"),
                result.getBigDecimal("cost")
        );
    }

    public boolean update(Ticket ticket) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, ticket.getPassportNumber());
            statement.setString(2, ticket.getName());
            statement.setLong(3, ticket.getFlight().getId());
            statement.setInt(4, ticket.getSeat());
            statement.setBigDecimal(5, ticket.getCost());
            statement.setLong(6, ticket.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> findAllByFilter(TicketFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.name() != null) {
            parameters.add(filter.name());
            whereSql.add("name = ?");
        }
        if (filter.seat() != 0) {
            parameters.add(filter.seat());
            whereSql.add("seat = ?");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                "WHERE ",
                " LIMIT  ? OFFSET  ? "
        ));

        List<Ticket> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.get()) {
            String sql = SQL_FIND_ALL + where;
            var statement = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(statement);
            var result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        buildTicket(result)
                );
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }
}
