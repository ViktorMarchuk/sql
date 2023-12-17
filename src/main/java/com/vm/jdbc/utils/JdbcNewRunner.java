package com.vm.jdbc.utils;

import com.vm.jdbc.dao.TicketDao;

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
//        var filter = new TicketFilter("Elena Metla", 0, 5, 0);
//        System.out.println(ticketDao.findAllByFilter(filter));
//        System.out.println(flightDao.findAll());

    }
}
