package com.vm.jdbc.dto;

import com.vm.jdbc.entity.FlightStatus;

public record FlightFilter(int aircraftId,
                           FlightStatus flightStatus,
                           int limit,
                           int offset) {
}
