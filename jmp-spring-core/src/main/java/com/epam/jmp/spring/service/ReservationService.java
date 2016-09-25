package com.epam.jmp.spring.service;


import com.epam.jmp.spring.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    void createOrUpdateReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> findReservationsByDate(LocalDate date);
    List<Reservation> reservationAll();
    void removeReservationById(Long id);
}
