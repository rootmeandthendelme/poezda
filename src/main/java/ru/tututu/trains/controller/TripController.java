package ru.tututu.trains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tututu.trains.model.schedule.TripInformationResponse;
import ru.tututu.trains.service.TripService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController()
public class TripController {

    @Autowired
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips/search")
    public List<TripInformationResponse> getAvailableTrips(
            @RequestParam("date") Optional<String> date,
            @RequestParam("trainName") Optional<String> trainName,
            @RequestParam("startPoint") String startPoint,
            @RequestParam("endPoint") String endPoint
    ) throws SQLException {
        return tripService.getTripsSchedule(date, trainName, startPoint, endPoint);
    }
}
