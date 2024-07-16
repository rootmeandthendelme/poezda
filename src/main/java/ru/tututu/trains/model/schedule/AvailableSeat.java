package ru.tututu.trains.model.schedule;

import ru.tututu.trains.entity.Seat;

import java.util.List;

public class AvailableSeat {
    private int carriegeId;
    private String carriegeType;
    private int countAvailableSeats;
    private List<Seat> availableSeatList;

    public int getCarriegeId() {
        return carriegeId;
    }

    public void setCarriegeId(int carriegeId) {
        this.carriegeId = carriegeId;
    }

    public String getCarriegeType() {
        return carriegeType;
    }

    public void setCarriegeType(String carriegeType) {
        this.carriegeType = carriegeType;
    }

    public int getCountAvailableSeats() {
        return countAvailableSeats;
    }

    public List<Seat> getAvailableSeatList() {
        return availableSeatList;
    }

    public void setAvailableSeatList(List<Seat> availableSeatList) {
        this.availableSeatList = availableSeatList;
        this.countAvailableSeats = availableSeatList.size();
    }
}
