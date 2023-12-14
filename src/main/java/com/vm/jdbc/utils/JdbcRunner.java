package com.vm.jdbc.utils;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
//        String sql = """
//                select * from flight  ;
//                """;
//        try (Connection connection = ConnectionManager.get()) {
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            while (result.next()) {
//                System.out.println(result.getObject("status"));
//            }
//        }
          System.out.println(getTicketsByFlightId(141L));
//        System.out.println(getFlightsBetween(LocalDate.of(2023, 12, 04).atStartOfDay(),
//                LocalDate.of(2023, 12, 16).atStartOfDay()));
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
                preparedStatement.setTimestamp(1,timestampStart);
                preparedStatement.setTimestamp(2,timestampEnd);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    flightBeetwen.add(resultSet.getString(2));
                }
            }
        }
        return flightBeetwen;
    }
}
