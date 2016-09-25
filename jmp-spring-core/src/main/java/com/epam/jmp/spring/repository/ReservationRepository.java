package com.epam.jmp.spring.repository;

import com.epam.jmp.spring.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByDate(LocalDate date);
}
