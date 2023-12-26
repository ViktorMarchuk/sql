package com.vm.jdbc.utils;

import com.vm.jdbc.dao.FlightDao;
import com.vm.jdbc.dao.TicketDao;
import com.vm.jdbc.dao.UserDao;
import com.vm.jdbc.dto.FlightFilter;
import com.vm.jdbc.dto.TicketFilter;
import com.vm.jdbc.entity.*;

import java.time.LocalDate;

public class JdbcNewRunner {
    public static void main(String[] args) {
        var ticketDao = TicketDao.getInstance();
        var flightDao = FlightDao.getInstance();
        var userDao = UserDao.getInstance();

//        Ticket ticket = ticketDao.save(new Ticket(296L, "ROO649", "Stagodt Kot",
//                17L, 47, BigDecimal.valueOf(645.6)));
//        System.out.println(ticket);

//        Boolean deleteAccept = ticketDao.delete(296L);
//        System.out.println(deleteAccept);
        //System.out.println(ticketDao.findAll());
//        System.out.println(ticketDao.findById(47L).get());
//        Ticket ticket = ticketDao.findById(47L).get();
//        ticket.setCost(BigDecimal.valueOf(90000));
//        System.out.println(ticketDao.update(ticket));
//        var filter = new TicketFilter(null, 56, 5, 0);
//        System.out.println(ticketDao.findAllByFilter(filter));
//        Flight flight = new Flight(1490, "BY-111", "2023-12-21", 1430,
//                "2023-12-25", 1209, 45, FlightStatus.SCHEDULED);
//        System.out.println(flightDao.update(flight));
//        System.out.println(flightDao.save(flight));
//        System.out.println(flightDao.delete(259));
        //  System.out.println(flightDao.findAll());
//        System.out.println(flightDao.findById(141).get());
//        System.out.println(flightDao.findBYFilter(new FlightFilter(0,null,6,3)));
//        System.out.println(ticketDao.findById(24L).get());
        System.out.println(userDao.save(new User(23, "Piter", LocalDate.of(1977, 12, 23), "23",
                "10", Role.ADMIN, Gender.MALE)));
    }
}
