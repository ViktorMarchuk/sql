package com.vm.jdbc.utils;


import com.vm.jdbc.dao.TicketDao;
import com.vm.jdbc.entity.Ticket;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getInstance();
//        ticketDao.save(new Ticket
//                (43L, "Ol74664", "Skott", 23L, 18, BigDecimal.valueOf(46.3)));

//        ticketDao.delete(111l);


//          System.out.println(getTicketsByFlightId(141L));
//        System.out.println(getFlightsBetween(LocalDate.of(2023, 12, 04).atStartOfDay(),
//                LocalDate.of(2023, 12, 16).atStartOfDay()));
       // System.out.println(ticketDao.getTicket());
    }

    public static List<String> getTicketsByFlightId(Long flightId) throws SQLException {
        List<String> tikets = new ArrayList<>();
        String sql = """ 
                select * from ticket where flight_id=%s ;
                """.formatted(flightId);
        try (Connection connection = ConnectionManager.get()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                tikets.add(result.getString("name"));
            }
        }
        return tikets;
    }

    public static List<String> getFlightsBetween(LocalDateTime start, LocalDateTime end) throws SQLException {
        List<String> flightBeetwen = new ArrayList<>();
        String sql = """
                select * from flight where departure_date between ? and ?;
                """;
        try (Connection connection = ConnectionManager.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                Timestamp timestampStart = Timestamp.valueOf(start);
                Timestamp timestampEnd = Timestamp.valueOf(end);
                preparedStatement.setTimestamp(1, timestampStart);
                preparedStatement.setTimestamp(2, timestampEnd);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    flightBeetwen.add(resultSet.getString(2));
                }
            }
        }
        return flightBeetwen;
    }
}
