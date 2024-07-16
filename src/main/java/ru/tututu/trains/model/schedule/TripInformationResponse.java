package ru.tututu.trains.model.schedule;

import ru.tututu.trains.model.TrainResponse;

import java.util.Date;
import java.util.List;

public class TripInformationResponse {
    private int tripId;
    private Date tripDate;
    private TrainResponse train;
    private List<TripPointResponse> route;
    private List<AvailableSeat> availableSeats;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public TrainResponse getTrain() {
        return train;
    }

    public void setTrain(TrainResponse train) {
        this.train = train;
    }

    public List<TripPointResponse> getRoute() {
        return route;
    }

    public void setRoute(List<TripPointResponse> route) {
        this.route = route;
    }

    public List<AvailableSeat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<AvailableSeat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
