package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.model.ReservMat;
import com.example.ReservationSalleMateriel.service.ReservMatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservationMat")
public class ReservMatController {

    @Autowired
    private ReservMatService reservMatService;

    @PostMapping("/newReservMat")
    public ResponseEntity<ReservMat> createReservMat(@RequestBody ReservMat reservation) {

        ReservMat reserv = reservMatService.newReservationMat(reservation, reservation.getEnseignantId());
        return new ResponseEntity<>(reserv, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservMat> getReservationById(@PathVariable int id) {
        reservMatService.archievedReservation();
        reservMatService.onGoingReservation();
        Optional<ReservMat> reservation = reservMatService.getReservMatById(id);
        if (reservation.isPresent()) {
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<ReservMat>> getReservationsByEnseignant(@PathVariable Enseignant enseignantId) {
        reservMatService.archievedReservation();
        reservMatService.onGoingReservation();
        List<ReservMat> reservations = reservMatService.getReservationsByEnseignant(enseignantId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {
        reservMatService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }
}