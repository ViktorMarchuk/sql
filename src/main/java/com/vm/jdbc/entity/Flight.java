package com.vm.jdbc.entity;

import java.util.Objects;

public class Flight {
    private int id;
    private String flightNumber;
    private String departureDate;
    private int departureAirportCode;
    private String arrivalDate;
    private int arrivalAirportCode;
    private int aircraftId;
    private FlightStatus flightStatus;

    public Flight() {
    }

    public Flight(int id, String flightNumber, String departureDate, int departureAirportCode,
                  String arrivalDate, int arrivalAirportCode,
                  int aircraftId, FlightStatus flightStatus) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.flightStatus = flightStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(int departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(int arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && departureAirportCode == flight.departureAirportCode && arrivalAirportCode == flight.arrivalAirportCode && aircraftId == flight.aircraftId && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(arrivalDate, flight.arrivalDate) && flightStatus == flight.flightStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureDate, departureAirportCode, arrivalDate, arrivalAirportCode, aircraftId, flightStatus);
    }

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", flightNumber='" + flightNumber + '\'' +
               ", departureDate='" + departureDate + '\'' +
               ", departureAirportCode=" + departureAirportCode +
               ", arrivalDate='" + arrivalDate + '\'' +
               ", arrivalAirportCode=" + arrivalAirportCode +
               ", aircraftId=" + aircraftId +
               ", flightStatus=" + flightStatus +
               '}';
    }
}
