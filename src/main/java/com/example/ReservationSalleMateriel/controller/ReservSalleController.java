package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.service.ReservSalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservationSalle")
public class ReservSalleController {

    @Autowired
    private ReservSalleService reservSalleService;

    @PostMapping("/newReservSalle")
    public ResponseEntity<ReservSalle> createReservSalle(@RequestBody ReservSalle reservation) {
        ReservSalle reserv = reservSalleService.newReservationSalle(reservation);
        return new ResponseEntity<>(reserv, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservSalle> getReservationById(@PathVariable int id) {
        reservSalleService.archievedReservation();
        reservSalleService.onGoingReservation();
        Optional<ReservSalle> reservation = reservSalleService.getReservSalleById(id);
        if (reservation.isPresent()) {
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<ReservSalle>> getReservationsByEnseignant(@PathVariable Enseignant enseignantId) {
        reservSalleService.archievedReservation();
        reservSalleService.onGoingReservation();
        List<ReservSalle> reservations = reservSalleService.getReservationsByEnseignant(enseignantId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {
        reservSalleService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }
}