package ru.tututu.trains.model.schedule;

import java.sql.Time;

public class TripPointResponse {
    public int getTripPointId() {
        return tripPointId;
    }

    public String getLocalityAndStationName() {
        return localityAndStationName;
    }

    public void setLocalityAndStationName(String localityAndStationName) {
        this.localityAndStationName = localityAndStationName;
    }

    public void setTripPointId(int tripPointId) {
        this.tripPointId = tripPointId;
    }

    private int tripPointId;
    private Time arrivalTime;
    private Time departureTime;
    private String localityAndStationName;

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
}
