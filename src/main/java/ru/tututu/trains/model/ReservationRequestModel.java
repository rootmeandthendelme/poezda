package ru.tututu.trains.model;

public class ReservationRequestModel {
    private int seatId;
    private int startPointId;
    private int endPointId;
    private String passport;
    private String fullName;

    public ReservationRequestModel(int seatId, int startPointId, int endPointId, String passport, String fullName) {
        this.seatId = seatId;
        this.startPointId = startPointId;
        this.endPointId = endPointId;
        this.passport = passport;
        this.fullName = fullName;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getStartPointId() {
        return startPointId;
    }

    public void setStartPointId(int startPointId) {
        this.startPointId = startPointId;
    }

    public int getEndPointId() {
        return endPointId;
    }

    public void setEndPointId(int endPointId) {
        this.endPointId = endPointId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
