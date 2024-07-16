package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Comfort;
import ru.tututu.trains.entity.Train;
import ru.tututu.trains.entity.TrainType;
import ru.tututu.trains.model.TrainResponse;
import ru.tututu.trains.repo.ComfortRepo;
import ru.tututu.trains.repo.TrainRepo;
import ru.tututu.trains.repo.TrainTypeRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TrainsService {
    @Autowired
    private final TrainRepo trainRepo;

    @Autowired
    private final ComfortRepo comfortRepo;

    @Autowired
    private final TrainTypeRepo trainTypeRepo;

    public TrainsService(TrainRepo trainRepo, ComfortRepo comfortRepo, TrainTypeRepo trainTypeRepo) {
        this.trainRepo = trainRepo;
        this.comfortRepo = comfortRepo;
        this.trainTypeRepo = trainTypeRepo;
    }

    public TrainResponse getTrainInformation(int trainId) throws SQLException {
        Optional<Train> train = trainRepo.getTrainById(trainId);
        if(train.isEmpty())
            return new TrainResponse();

        List<String> comforts = comfortRepo.getAllComfortByTrainId(trainId).stream().map(Comfort::getValue).toList();
        TrainType trainType = trainTypeRepo.getTrainTypeById(train.get().getTrainTypeId()).orElse(new TrainType());

        return aggregateTrainInfo(train.get(), comforts, trainType);
    }

    private TrainResponse aggregateTrainInfo(Train train, List<String> comforts, TrainType trainType){
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainId(train.getId());
        trainResponse.setTrainName(train.getName());
        trainResponse.setComfort(comforts);
        trainResponse.setTrainType(trainType.getType());
        return trainResponse;
    }
}
