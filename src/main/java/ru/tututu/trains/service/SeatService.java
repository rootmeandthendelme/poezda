package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Carriege;
import ru.tututu.trains.model.schedule.AvailableSeat;
import ru.tututu.trains.repo.CarriegeRepo;
import ru.tututu.trains.repo.CarriegeTypeRepo;
import ru.tututu.trains.repo.SeatRepo;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private final CarriegeRepo carriegeRepo;

    @Autowired
    private final CarriegeTypeRepo carriegeTypeRepo;

    @Autowired
    private final SeatRepo seatRepo;

    public SeatService(CarriegeRepo carriegeRepo, CarriegeTypeRepo carriegeTypeRepo, SeatRepo seatRepo) {
        this.carriegeRepo = carriegeRepo;
        this.carriegeTypeRepo = carriegeTypeRepo;
        this.seatRepo = seatRepo;
    }

    public List<AvailableSeat> getNotReservedSeats(int tripId, int trainId, Time departureTime, Time arrivalTime) throws SQLException {
        List<AvailableSeat> seats = new ArrayList<>();
        for(Carriege carriege: carriegeRepo.getCarriegesByTrainId(trainId)){
            AvailableSeat availableSeat = new AvailableSeat();
            availableSeat.setAvailableSeatList(seatRepo.getNotReservedSeats(tripId, carriege.getId(), departureTime, arrivalTime));
            availableSeat.setCarriegeId(carriege.getId());
            availableSeat.setCarriegeType(carriegeTypeRepo.getCarriegeTypeById(carriege.getCarriegeTypeId()).getType());
            seats.add(availableSeat);
        }
        return seats;
    }
}
