package ru.tututu.trains.entity;

import java.sql.Date;

public class Trip {
    private int id;
    private Date travelDate;
    private int trainId;

    public Trip() {
    }

    public Trip(int id, Date travelDate, int trainId) {
        this.id = id;
        this.travelDate = travelDate;
        this.trainId = trainId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
