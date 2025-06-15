package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.model.SalleDeCours;
import com.example.ReservationSalleMateriel.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController // Mark this class as a Spring REST Controller
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<SalleDeCours>> getAllClassrooms() {
        List<SalleDeCours> salleDeCours = classroomService.getAllClassrooms();
        return new ResponseEntity<>(salleDeCours, HttpStatus.OK);
    }

    @GetMapping("/disponible")
    public ResponseEntity<List<SalleDeCours>> availableClassrooms(
            @RequestParam("date") LocalDate date,
            @RequestParam("horaire") String horaire) {

        List<SalleDeCours> salleDeCours = classroomService.availableClassrooms(date, horaire);
        return new ResponseEntity<>(salleDeCours, HttpStatus.OK);
    }

    @GetMapping("/disponible/{nbPlaces}")
    public ResponseEntity<List<SalleDeCours>> filterByNbPlaces(
            @RequestParam("date") LocalDate date,
            @RequestParam("horaire") String horaire,
            @PathVariable("nbPlaces") int nbPlaces) {

        List<SalleDeCours> salleDeCours = classroomService.filterByNbPlaces(date, horaire, nbPlaces);
        return new ResponseEntity<>(salleDeCours, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleDeCours> getClassroomById(@PathVariable Integer id) {
        // Here, the controller calls the service layer
        Optional<SalleDeCours> classroom = classroomService.getClassroomById(id);

        if (classroom.isPresent()) {
            return new ResponseEntity<>(classroom.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addClassroom")
    public ResponseEntity<SalleDeCours> createSalleDeCours(@RequestBody SalleDeCours salleDeCours) {
        SalleDeCours createdClassroom = classroomService.createClassroom(salleDeCours);
        return new ResponseEntity<>(createdClassroom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalleDeCours> updateClassroom(@PathVariable Integer id, @RequestBody SalleDeCours salleDeCoursDetails) {
        try {
            SalleDeCours updatedClassroom = classroomService.updateClassroom(id, salleDeCoursDetails);
            return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or a more specific error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Integer id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }
}
