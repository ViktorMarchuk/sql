package com.vm.jdbc.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String passportNumber;
    private String name;
    private Long flightId;
    private int seat;
    private BigDecimal cost;

    public Ticket() {
    }

    public Ticket(Long id, String passportNumber, String name, Long flightId, int seat, BigDecimal cost) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.name = name;
        this.flightId = flightId;
        this.seat = seat;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return seat == ticket.seat && Objects.equals(id, ticket.id) && Objects.equals(passportNumber, ticket.passportNumber) && Objects.equals(name, ticket.name) && Objects.equals(flightId, ticket.flightId) && Objects.equals(cost, ticket.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportNumber, name, flightId, seat, cost);
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "id=" + id +
               ", passportNumber='" + passportNumber + '\'' +
               ", name='" + name + '\'' +
               ", flightId=" + flightId +
               ", seat=" + seat +
               ", cost=" + cost +
               '}';
    }
}

