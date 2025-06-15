package com.example.ReservationSalleMateriel.service;

import com.example.ReservationSalleMateriel.Repository.OrdinateurPortableRepository;
import com.example.ReservationSalleMateriel.model.Ordinateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdinateurService {

    @Autowired
    private OrdinateurPortableRepository ordinateurRepository;

    public List<Ordinateur> getAllOrdinateurs() {
        return ordinateurRepository.findAll();
    }

    public Optional<Ordinateur> getOrdinateurById(Integer id) {
        // Here's where you use findById()
        return ordinateurRepository.findById(id);
    }

    public Ordinateur createOrdinateur(Ordinateur ordinateur) {
        // Use save() for creating new entities
        return ordinateurRepository.save(ordinateur);
    }

    public Ordinateur updateOrdinateur(Integer id, Ordinateur ordinateurDetails) {
        // 1. Find the existing entity by its ID
        Optional<Ordinateur> optionalOrdinateur = ordinateurRepository.findById(id);

        if (optionalOrdinateur.isPresent()) {
            Ordinateur existingOrdinateur = optionalOrdinateur.get();

            // 2. Update its properties with the new details
            existingOrdinateur.setId(ordinateurDetails.getId());
            existingOrdinateur.setNom(ordinateurDetails.getNom());
            existingOrdinateur.setMarque(ordinateurDetails.getMarque());
            existingOrdinateur.setModel(ordinateurDetails.getModel());
            existingOrdinateur.setProcesseur(ordinateurDetails.getProcesseur());
            existingOrdinateur.setStockage(ordinateurDetails.getStockage());
            existingOrdinateur.setMemoire(ordinateurDetails.getMemoire());

            // 3. Save the updated entity
            return ordinateurRepository.save(existingOrdinateur);
        } else {
            // Handle the case where the classroom is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Ordinateur with id " + id + " not found.");
            // Or return null;
        }
    }

    public void deleteOrdinateur(Integer id) {
        ordinateurRepository.deleteById(id);
    }


}
