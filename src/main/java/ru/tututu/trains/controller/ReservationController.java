package ru.tututu.trains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.tututu.trains.entity.Reservation;
import ru.tututu.trains.model.ReservationRequestModel;
import ru.tututu.trains.security.UserPrincipal;
import ru.tututu.trains.service.ReservationService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public @ResponseBody List<Reservation> reserve(@RequestBody @Validated List<ReservationRequestModel> reservations) {
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = Math.toIntExact(userPrincipal.getUserId());
        List<Reservation> response = new ArrayList<>();

        for(ReservationRequestModel reservationRequestModel: reservations){
            try {
                response.add(reservationService.createReservation(reservationRequestModel, userId));
            } catch (SQLException e) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), "sql error with msg - " + e.getMessage());
            }
        }

        return response;
    }

    @DeleteMapping("/reservations/{id}")
    public void reserve(@PathVariable int id) throws SQLException {
        reservationService.deleteReservation(id);
    }
}
