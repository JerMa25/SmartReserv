package com.example.ReservationSalleMateriel.service;

import com.example.ReservationSalleMateriel.Repository.OrdinateurPortableRepository;
import com.example.ReservationSalleMateriel.Repository.VideoProjRepository;
import com.example.ReservationSalleMateriel.model.Ordinateur;
import com.example.ReservationSalleMateriel.model.VideoProj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoProjService {

    @Autowired
    private VideoProjRepository videoProjRepository;

    public List<VideoProj> getAllVideoProjs() {
        return videoProjRepository.findAll();
    }

    public Optional<VideoProj> getVideoProjById(Integer id) {
        // Here's where you use findById()
        return videoProjRepository.findById(id);
    }

    public VideoProj createVideoProj(VideoProj videoProj) {
        // Use save() for creating new entities
        return videoProjRepository.save(videoProj);
    }

    public VideoProj updateVideoProj(Integer id, VideoProj videoProjDetails) {
        // 1. Find the existing entity by its ID
        Optional<VideoProj> optionalVideoProj = videoProjRepository.findById(id);

        if (optionalVideoProj.isPresent()) {
            VideoProj existingVideoProj = optionalVideoProj.get();

            // 2. Update its properties with the new details
            existingVideoProj.setId(videoProjDetails.getId());
            existingVideoProj.setNom(videoProjDetails.getNom());
            existingVideoProj.setMarque(videoProjDetails.getMarque());
            existingVideoProj.setModel(videoProjDetails.getModel());
            existingVideoProj.setResolution(videoProjDetails.getResolution());
            existingVideoProj.setType_de_lampe(videoProjDetails.getType_de_lampe());
            existingVideoProj.setFrequence(videoProjDetails.getFrequence());

            // 3. Save the updated entity
            return videoProjRepository.save(existingVideoProj);
        } else {
            // Handle the case where the classroom is not found
            // You might throw a custom exception, return null, or Optional.empty()
            throw new RuntimeException("Enseignant with matricule " + id + " not found.");
            // Or return null;
        }
    }

    public void deleteVideoProj(Integer id) {
        videoProjRepository.deleteById(id);
    }


}
