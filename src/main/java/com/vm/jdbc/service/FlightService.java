package com.vm.jdbc.service;

import com.vm.jdbc.dao.FlightDao;
import com.vm.jdbc.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {

    }

    public static FlightService getInstance() {
        return INSTANCE;

    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream().map(flight -> new FlightDto(flight.getId(),
                "Arrival airport code: %s  Departure airport code: %s   Aircraft ID: %s   Status: %s".formatted(flight.getArrivalAirportCode(),
                flight.getDepartureAirportCode(),flight.getAircraftId(),
                flight.getFlightStatus()))).collect(Collectors.toList());
    }
}
