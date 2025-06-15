package com.example.ReservationSalleMateriel.service;

import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.Repository.ReservSalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservSalleService {

    @Autowired
    private ReservSalleRepository reservSalleRepository;

    public List<ReservSalle> getAllReservSalle() {return reservSalleRepository.findAll();}

    public Optional<ReservSalle> getReservSalleById(Integer id) {return reservSalleRepository.findById(id);}

    public ReservSalle newReservationSalle(ReservSalle reservation) {
        reservation.setState("NEW");
        reservation.setCree_Le(LocalDate.now());
        return reservSalleRepository.save(reservation);
    }

    public List<ReservSalle> getReservationsByEnseignant(Enseignant enseignant) {
        return reservSalleRepository.findAll().stream()
                .filter(reservation -> reservation.getEnseignantId().equals(enseignant) && (reservation.getState().equals("NEW") || reservation.getState().equals("IN PROGRESS")))
                .collect(Collectors.toList());
    }

    public ReservSalle updateReservationSalle(Integer id, ReservSalle reservSalleDetails) {
        // 1. Find the existing entity by its ID
        Optional<ReservSalle> optionalReservSalle = reservSalleRepository.findById(id);

        if (optionalReservSalle.isPresent()) {
            ReservSalle existingReservSalle = optionalReservSalle.get();

            // 2. Update its properties with the new details
            existingReservSalle.setDate(reservSalleDetails.getDate());
            existingReservSalle.setHoraire(reservSalleDetails.getHoraire());
            existingReservSalle.setState(reservSalleDetails.getState());
            existingReservSalle.setSalleId(reservSalleDetails.getSalleId());
            existingReservSalle.setEnseignantId(reservSalleDetails.getEnseignantId());

            // 3. Save the updated entity
            return reservSalleRepository.save(existingReservSalle);
        } else {
            // Handle the case where the reservSalle is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Classroom reservation with id " + id + " not found.");
            // Or return null;
        }
    }

    public void onGoingReservation(){
        List<ReservSalle> reservSalles = getAllReservSalle();

        for (ReservSalle reservSalle : reservSalles){
            if (reservSalle.getDate().equals(LocalDate.now()) &&
                    LocalTime.parse(reservSalle.getHoraire().split("-")[0]).isBefore(LocalTime.now()) &&
                    LocalTime.parse(reservSalle.getHoraire().split("-")[1]).isAfter(LocalTime.now())){
                        reservSalle.setState("IN PROGRESS");
            }
        }
    }

    public void archievedReservation(){
        List<ReservSalle> reservSalles = getAllReservSalle();

        for (ReservSalle reservSalle : reservSalles){
            if (reservSalle.getDate().isBefore(LocalDate.now()) ||
                    (reservSalle.getDate().equals(LocalDate.now()) &&
                            LocalTime.parse(reservSalle.getHoraire().split("-")[1]).isBefore(LocalTime.now()))){
                                reservSalle.setState("ARCHIEVED");
            }
        }
    }

    public void cancelReservation(int reservationId) {
        ReservSalle reservation = reservSalleRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if(reservation.getState().equals("NEW")) {
            reservation.setState("CANCELLED");
        }
        reservSalleRepository.save(reservation);
    }
}