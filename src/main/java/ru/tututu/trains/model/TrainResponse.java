package ru.tututu.trains.model;

import java.util.List;

public class TrainResponse {
    private int trainId;
    private String trainName;
    private String trainType;
    private List<String> comfort;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public List<String> getComfort() {
        return comfort;
    }

    public void setComfort(List<String> comfort) {
        this.comfort = comfort;
    }
}
