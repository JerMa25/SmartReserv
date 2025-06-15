package com.example.ReservationSalleMateriel.service; // Adjust package as needed

import com.example.ReservationSalleMateriel.Repository.EnseignantRepository;
import com.example.ReservationSalleMateriel.model.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Important for findById

@Service
public class EnseignantService{

    @Autowired
    private EnseignantRepository enseignantRepository;

    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public Optional<Enseignant> getEnseignantById(String matricule) {
        // Here's where you use findById()
        return enseignantRepository.findById(matricule);
    }

    public Enseignant createEnseignant(Enseignant enseignant) {
        // Use save() for creating new entities
        return enseignantRepository.save(enseignant);
    }

    public Enseignant updateEnseignant(String matricule, Enseignant enseignantDetails) {
        // 1. Find the existing entity by its ID
        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(matricule);

        if (optionalEnseignant.isPresent()) {
            Enseignant existingEnseignant = optionalEnseignant.get();

            // 2. Update its properties with the new details
            existingEnseignant.setMatricule(enseignantDetails.getMatricule());
            existingEnseignant.setNom(enseignantDetails.getNom());
            existingEnseignant.setPrenom(enseignantDetails.getPrenom());
            existingEnseignant.setAge(enseignantDetails.getAge());
            existingEnseignant.setSex(enseignantDetails.getSex());
            existingEnseignant.setTel(enseignantDetails.getTel());
            existingEnseignant.setMail(enseignantDetails.getMail());
            existingEnseignant.setFormation(enseignantDetails.getFormation());

            // 3. Save the updated entity
            return enseignantRepository.save(existingEnseignant);
        } else {
            // Handle the case where the Enseignant is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Enseignant with matricule " + matricule + " not found.");
            // Or return null;
        }
    }

    public List<Enseignant> getEnseignantByFormation(String formation){
        return enseignantRepository.findByFormation(formation);
    }

    public void deleteEnseignant(String matricule) {
        enseignantRepository.deleteById(matricule);
    }
}