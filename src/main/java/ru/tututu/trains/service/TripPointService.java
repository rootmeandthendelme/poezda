package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.*;
import ru.tututu.trains.model.schedule.TripPointResponse;
import ru.tututu.trains.repo.LocalityRepo;
import ru.tututu.trains.repo.PlatformRepo;
import ru.tututu.trains.repo.StationRepo;
import ru.tututu.trains.repo.TripPointRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripPointService {
    @Autowired
    private final TripPointRepo tripPointRepo;

    @Autowired
    private final StationRepo stationRepo;

    @Autowired
    private final LocalityRepo localityRepo;

    @Autowired
    private final PlatformService platformService;

    public TripPointService(TripPointRepo tripPointRepo, StationRepo stationRepo, LocalityRepo localityRepo, PlatformService platformService) {
        this.tripPointRepo = tripPointRepo;
        this.stationRepo = stationRepo;
        this.localityRepo = localityRepo;
        this.platformService = platformService;
    }

    public List<TripPointResponse> getRouteByTripId(int tripId, String departureLocality, String arrivalLocality) throws SQLException {
        List<TripPointResponse> tripPointResponses = new ArrayList<>();
        for(TripPoint tripPoint: getTripPoints(tripId, departureLocality, arrivalLocality)){
            TripPointResponse resp = new TripPointResponse();
            resp.setTripPointId(tripPoint.getId());
            resp.setArrivalTime(tripPoint.getArrivalTime());
            resp.setDepartureTime(tripPoint.getDepartureTime());
            resp.setLocalityAndStationName(getLocalityAndStationName(tripPoint.getPlatformId()));
            tripPointResponses.add(resp);
        }
        return tripPointResponses;
    }

    private List<TripPoint> getTripPoints(int tripId, String departureLocality, String arrivalLocality) throws SQLException{
        Object[] departurePlatforms = platformService.getPlatformsArrayByLocalityName(departureLocality);
        Object[] arrivalPlatforms = platformService.getPlatformsArrayByLocalityName(arrivalLocality);

        return tripPointRepo.getRouteByTripId(tripId, departurePlatforms, arrivalPlatforms);
    }

    private String getLocalityAndStationName(int platformId) throws SQLException {
        Optional<Station> station = stationRepo.getStationById(platformService.getPlatformById(platformId).getStationId());

        if(station.isEmpty())
            return "";

        Locality locality = localityRepo.getLocalityById(station.get().getLocalityId()).orElse(new Locality());

        return locality.getName() + " - " + station.get().getName();
    }

}
