package ru.tututu.trains.entity;

public class Reservation {
    private int id;
    private int seatId;
    private int userId;
    private int startPointId;
    private int endPointId;
    private String passport;
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
