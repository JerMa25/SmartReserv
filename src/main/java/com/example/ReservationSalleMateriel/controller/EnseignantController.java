package com.example.ReservationSalleMateriel.controller; // Adjust package as needed

import com.example.ReservationSalleMateriel.service.EnseignantService;
import com.example.ReservationSalleMateriel.model.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://192.168.1.135:3000")
@RestController // Mark this class as a Spring REST Controller
@RequestMapping("/api/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    @Autowired
    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    @GetMapping("/{formation}")
    public ResponseEntity<List<Enseignant>> getEnseignantByFormation(@PathVariable String formation){
        List<Enseignant> enseignants = enseignantService.getEnseignantByFormation(formation);
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<Enseignant> getEnseignantByMatricule(@PathVariable String matricule) {
        // Here, the controller calls the service layer
        Optional<Enseignant> enseignant = enseignantService.getEnseignantById(matricule);

        if (enseignant.isPresent()) {
            return new ResponseEntity<>(enseignant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant createdEnseignant = enseignantService.createEnseignant(enseignant);
        return new ResponseEntity<>(createdEnseignant, HttpStatus.CREATED);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable String matricule, @RequestBody Enseignant enseignantDetails) {
        try {
            Enseignant updatedEnseignant = enseignantService.updateEnseignant(matricule, enseignantDetails);
            return new ResponseEntity<>(updatedEnseignant, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or a more specific error
        }
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable String matricule) {
        enseignantService.deleteEnseignant(matricule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginEnseignant(@RequestBody Enseignant loginRequest) {

        String matricule = loginRequest.getMatricule();
        String password = loginRequest.getPassword();

        return enseignantService.getEnseignantById(matricule)
                .filter(e -> e.getPassword().equals(password))
                .map(e -> ResponseEntity.ok("Login successful for " + e.getNom()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

}