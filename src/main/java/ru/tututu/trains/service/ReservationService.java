package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Reservation;
import ru.tututu.trains.model.ReservationRequestModel;
import ru.tututu.trains.repo.ReservationRepo;

import java.sql.SQLException;

@Service
public class ReservationService {
    @Autowired
    private final ReservationRepo reservationRepo;

    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
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
        reservationRepo.deleteReservation(id);
    }
}
