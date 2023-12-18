package com.vm.jdbc.utils;

import com.vm.jdbc.dao.FlightDao;
import com.vm.jdbc.dao.TicketDao;
import com.vm.jdbc.dto.TicketFilter;
import com.vm.jdbc.entity.Flight;
import com.vm.jdbc.entity.FlightStatus;

public class JdbcNewRunner {
    public static void main(String[] args) {
        var ticketDao = TicketDao.getInstance();
        var flightDao = FlightDao.getInstance();

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
//        Flight flight = new Flight(141, "AAAR-3873", "2023-12-23", 1430,
//                "2023-12-25", 1209, 45, FlightStatus.ARRIVED);
//        System.out.println(flightDao.update(flight));
//        System.out.println(flightDao.save(flight));
//        System.out.println(flightDao.delete(259));
      //  System.out.println(flightDao.findAll());
        System.out.println(flightDao.findById(141).get());


    }
}
