package com.example.ReservationSalleMateriel.service;

import com.example.ReservationSalleMateriel.Repository.ReservMatRepository;
import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.model.ReservMat;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservMatService {

    @Autowired
    private ReservMatRepository reservMatRepository;

    @Autowired
    private ReservSalleService reservSalleService;

    public List<ReservMat> getAllReservMat() {return reservMatRepository.findAll();}

    public Optional<ReservMat> getReservMatById(Integer id) {return reservMatRepository.findById(id);}

    public ReservMat newReservationMat(ReservMat reservation, Enseignant enseignant) {

        List<ReservSalle> reservSalles = reservSalleService.getReservationsByEnseignant(enseignant);
        for (ReservSalle reservSalle : reservSalles){
            if (reservSalle.getDate().equals(reservation.getDate()) && reservSalle.getHoraire().equals(reservation.getHoraire())){
                reservation.setState("NEW");
                reservation.setCree_Le(LocalDate.now());
                return reservMatRepository.save(reservation);
            }
        }
        return null;
    }

    public List<ReservMat> getReservationsByEnseignant(Enseignant enseignant) {
        return reservMatRepository.findAll().stream()
                .filter(reservation -> reservation.getEnseignantId().equals(enseignant) && (reservation.getState().equals("NEW") || reservation.getState().equals("IN PROGRESS")))
                .collect(Collectors.toList());
    }

    public ReservMat updateReservationMat(Integer id, ReservMat reservMatDetails) {
        // 1. Find the existing entity by its ID
        Optional<ReservMat> optionalReservMat = reservMatRepository.findById(id);

        if (optionalReservMat.isPresent()) {
            ReservMat existingReservMat = optionalReservMat.get();

            // 2. Update its properties with the new details
            existingReservMat.setDate(reservMatDetails.getDate());
            existingReservMat.setHoraire(reservMatDetails.getHoraire());
            existingReservMat.setState(reservMatDetails.getState());
            existingReservMat.setMatId(reservMatDetails.getMatId());
            existingReservMat.setEnseignantId(reservMatDetails.getEnseignantId());

            // 3. Save the updated entity
            return reservMatRepository.save(existingReservMat);
        } else {
            // Handle the case where the reservSalle is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Material reservation with id " + id + " not found.");
            // Or return null;
        }
    }

    public void onGoingReservation(){
        List<ReservMat> reservMats = getAllReservMat();

        for (ReservMat reservMat : reservMats){
            if (reservMat.getDate().equals(LocalDate.now()) &&
                    LocalTime.parse(reservMat.getHoraire().split("-")[0]).isBefore(LocalTime.now()) &&
                    LocalTime.parse(reservMat.getHoraire().split("-")[1]).isAfter(LocalTime.now())){
                reservMat.setState("IN PROGRESS");
            }
        }
    }

    public void archievedReservation(){
        List<ReservMat> reservMats = getAllReservMat();

        for (ReservMat reservMat : reservMats){
            if (reservMat.getDate().isBefore(LocalDate.now()) ||
                    (reservMat.getDate().equals(LocalDate.now()) &&
                            LocalTime.parse(reservMat.getHoraire().split("-")[1]).isBefore(LocalTime.now()))){
                reservMat.setState("ARCHIEVED");
            }
        }
    }

    public void cancelReservation(int reservationId) {
        ReservMat reservation = reservMatRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if(reservation.getState().equals("NEW")) {
            reservation.setState("CANCELLED");
        }
        reservMatRepository.save(reservation);
    }
}