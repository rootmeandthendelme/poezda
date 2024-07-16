package ru.tututu.trains.entity;

public class Seat {
    private int id;
    private int seatNumber;
    private int carriegeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getCarriegeId() {
        return carriegeId;
    }

    public void setCarriegeId(int carriegeId) {
        this.carriegeId = carriegeId;
    }
}
