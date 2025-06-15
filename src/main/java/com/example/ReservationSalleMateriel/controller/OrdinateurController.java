package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.model.Ordinateur;
import com.example.ReservationSalleMateriel.service.OrdinateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Mark this class as a Spring REST Controller
@RequestMapping("/api/ordinateur")
public class OrdinateurController {

    private final OrdinateurService ordinateurService;

    @Autowired
    public OrdinateurController(OrdinateurService ordinateurService) {
        this.ordinateurService = ordinateurService;
    }

    @GetMapping
    public ResponseEntity<List<Ordinateur>> getAllOrdinateurs() {
        List<Ordinateur> ordinateurs = ordinateurService.getAllOrdinateurs();
        return new ResponseEntity<>(ordinateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordinateur> getOrdinateurById(@PathVariable Integer id) {
        // Here, the controller calls the service layer
        Optional<Ordinateur> ordinateur = ordinateurService.getOrdinateurById(id);

        if (ordinateur.isPresent()) {
            return new ResponseEntity<>(ordinateur.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addOrdinateur")
    public ResponseEntity<Ordinateur> createOrdinateur(@RequestBody Ordinateur ordinateur) {
        Ordinateur createdOrdinateur = ordinateurService.createOrdinateur(ordinateur);
        return new ResponseEntity<>(createdOrdinateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordinateur> updateOrdinateur(@PathVariable Integer id, @RequestBody Ordinateur ordinateurDetails) {
        try {
            Ordinateur updatedOrdinateur = ordinateurService.updateOrdinateur(id, ordinateurDetails);
            return new ResponseEntity<>(updatedOrdinateur, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or a more specific error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdinateur(@PathVariable Integer id) {
        ordinateurService.deleteOrdinateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }
}
