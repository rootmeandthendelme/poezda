package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Reservation;
import ru.tututu.trains.entity.TripPoint;
import ru.tututu.trains.exceptions.NotFoundException;
import ru.tututu.trains.model.ReservationRequestModel;
import ru.tututu.trains.repo.ReservationRepo;
import ru.tututu.trains.repo.TripPointRepo;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationService {
    @Autowired
    private final ReservationRepo reservationRepo;

    @Autowired
    private final TripPointRepo tripPointRepo;

    public ReservationService(ReservationRepo reservationRepo, TripPointRepo tripPointRepo) {
        this.reservationRepo = reservationRepo;
        this.tripPointRepo = tripPointRepo;
    }

    public Reservation createReservation(ReservationRequestModel reservationRequestModel, int userId) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setSeatId(reservationRequestModel.getSeatId());
        reservation.setStartPointId(reservationRequestModel.getStartPointId());
        reservation.setEndPointId(reservationRequestModel.getEndPointId());
        reservation.setPassport(reservationRequestModel.getPassport());
        reservation.setFullName(reservationRequestModel.getFullName());

        return reservationRepo.createReservation(reservation);
    }

    public void deleteReservation(int id) throws SQLException {
        Optional<Reservation> reservation = reservationRepo.findReservationById(id);
        if(reservation.isEmpty())
            throw new NotFoundException("reservation with id " + id + " not found");

        TripPoint tripPoint = tripPointRepo.getTripById(reservation.get().getStartPointId()).get();
        if(LocalTime.now().plusHours(2).isAfter(tripPoint.getDepartureTime().toLocalTime())){
            throw new RuntimeException("could not cancel reservation");
        }

        reservationRepo.deleteReservation(id);
    }
}
