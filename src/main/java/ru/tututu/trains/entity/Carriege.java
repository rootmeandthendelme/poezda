package ru.tututu.trains.entity;

public class Carriege {
    private int id;
    private int carriegeNumber;
    private int trainId;

    public int getCarriegeTypeId() {
        return carriegeTypeId;
    }

    public void setCarriegeTypeId(int carriegeTypeId) {
        this.carriegeTypeId = carriegeTypeId;
    }

    private int carriegeTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarriegeNumber() {
        return carriegeNumber;
    }

    public void setCarriegeNumber(int carriegeNumber) {
        this.carriegeNumber = carriegeNumber;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
