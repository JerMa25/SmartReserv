package com.example.ReservationSalleMateriel.service;

import com.example.ReservationSalleMateriel.Repository.ClassroomRepository;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.model.SalleDeCours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ReservSalleService reservSalleService;

    public List<SalleDeCours> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<SalleDeCours> getClassroomById(Integer id) {
        // Here's where you use findById()
        return classroomRepository.findById(id);
    }

    public SalleDeCours createClassroom(SalleDeCours salleDeCours) {
        // Use save() for creating new entities
        return classroomRepository.save(salleDeCours);
    }

    public SalleDeCours updateClassroom(Integer id, SalleDeCours salleDeCoursDetails) {
        // 1. Find the existing entity by its ID
        Optional<SalleDeCours> optionalSalleDeCours = classroomRepository.findById(id);

        if (optionalSalleDeCours.isPresent()) {
            SalleDeCours existingSalleDeCours = optionalSalleDeCours.get();

            // 2. Update its properties with the new details
            existingSalleDeCours.setId(salleDeCoursDetails.getId());
            existingSalleDeCours.setNbPlaces(salleDeCoursDetails.getNbPlaces());

            // 3. Save the updated entity
            return classroomRepository.save(existingSalleDeCours);
        } else {
            // Handle the case where the classroom is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Classroom with id " + id + " not found.");
            // Or return null;
        }
    }

    public void deleteClassroom(Integer matricule) {
        classroomRepository.deleteById(matricule);
    }

    public List<SalleDeCours> availableClassrooms(LocalDate Date, String horaire) {
        List<ReservSalle> reservSalles = reservSalleService.getAllReservSalle();
        List<SalleDeCours> classrooms = getAllClassrooms();
        List<SalleDeCours> salleDisponibles = new ArrayList<>();

        for (ReservSalle reservSalle : reservSalles) {
            for (SalleDeCours classroom : classrooms) {
                if (reservSalle.getSalleId().equals(classroom) && reservSalle.getHoraire().equals(horaire) && reservSalle.getDate().equals(Date)) {
                    continue;
                }
                salleDisponibles.add(classroom);
            }
        }
        return salleDisponibles;
    }

    public List<SalleDeCours> filterByNbPlaces(LocalDate Date, String horaire, int nbPlaces) {

        List<SalleDeCours> salleDisponibles = availableClassrooms(Date, horaire);
        return salleDisponibles.stream()
                               .filter(x -> x.getNbPlaces() <= nbPlaces)
                               .toList();
    }
}