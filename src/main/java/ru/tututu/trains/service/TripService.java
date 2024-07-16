package ru.tututu.trains.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Trip;
import ru.tututu.trains.model.schedule.AvailableSeat;
import ru.tututu.trains.model.schedule.TripInformationResponse;
import ru.tututu.trains.model.schedule.TripPointResponse;
import ru.tututu.trains.repo.PlatformRepo;
import ru.tututu.trains.repo.TripRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private final PlatformRepo platformRepo;

    @Autowired
    private final TripRepo tripRepo;

    @Autowired
    private final ObjectMapper mapper;

    @Autowired
    private final TrainsService trainsService;

    @Autowired
    private final TripPointService tripPointService;

    @Autowired
    private final PlatformService platformService;

    @Autowired
    private final SeatService seatService;

    public TripService(PlatformRepo platformRepo, TripRepo tripRepo, ObjectMapper mapper, TrainsService trainsService, TripPointService tripPointService, PlatformService platformService, SeatService seatService) {
        this.platformRepo = platformRepo;
        this.tripRepo = tripRepo;
        this.mapper = mapper;
        this.trainsService = trainsService;
        this.tripPointService = tripPointService;
        this.platformService = platformService;
        this.seatService = seatService;
    }

    public List<TripInformationResponse> getTripsSchedule(Optional<String> date, Optional<String> trainName, String startPoint, String endPoint) throws SQLException {
        Object[] departurePlatforms = platformService.getPlatformsArrayByLocalityName(startPoint);
        Object[] arrivalPlatforms = platformService.getPlatformsArrayByLocalityName(endPoint);
        List<TripInformationResponse> responses = new ArrayList<>();

        for(Trip trip: tripRepo.getAllTripsByParameters(departurePlatforms,arrivalPlatforms,trainName, date)){
            TripInformationResponse tripInformationResponse = new TripInformationResponse();
            tripInformationResponse.setTripDate(trip.getTravelDate());
            tripInformationResponse.setTripId(trip.getId());
            tripInformationResponse.setTrain(trainsService.getTrainInformation(trip.getTrainId()));
            List<TripPointResponse> route = tripPointService.getRouteByTripId(trip.getId(), startPoint, endPoint);
            tripInformationResponse.setRoute(route);
            tripInformationResponse.setAvailableSeats(getAvailableSeats(trip.getId(), trip.getTrainId(), route));
            responses.add(tripInformationResponse);
        }

        return responses;
    }

    private List<AvailableSeat> getAvailableSeats(int tripId, int trainId, List<TripPointResponse> route) throws SQLException {
        TripPointResponse first = route.get(0);
        TripPointResponse last = route.get(route.size()-1);

        return seatService.getNotReservedSeats(
                tripId,
                trainId,
                first.getArrivalTime(),
                last.getArrivalTime()
        );
    }
}
