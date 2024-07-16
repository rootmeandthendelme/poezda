package ru.tututu.trains.entity;

public class Platform {
    private int id;
    private int num;
    private int stationId;

    public Platform(int id, int num, int stationId) {
        this.id = id;
        this.num = num;
        this.stationId = stationId;
    }

    public Platform() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}
