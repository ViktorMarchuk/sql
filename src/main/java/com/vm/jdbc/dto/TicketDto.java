package com.vm.jdbc.dto;

public record TicketDto(Long id, int flightId, int seat, String name) {
}
