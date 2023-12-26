package com.vm.jdbc.service;

import com.vm.jdbc.dao.TicketDao;
import com.vm.jdbc.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private final static TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(int flightId) {
        return ticketDao
                .findAllByFlightId(flightId)
                .stream().map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlight().getId(),
                        ticket.getSeat(),
                        ticket.getName()))
                .collect(Collectors.toList());
    }
}
