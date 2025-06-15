package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.model.VideoProj;
import com.example.ReservationSalleMateriel.service.VideoProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Mark this class as a Spring REST Controller
@RequestMapping("/api/videoProj")
public class VideoProjController {

    private final VideoProjService videoProjService;

    @Autowired
    public VideoProjController(VideoProjService videoProjService) {
        this.videoProjService = videoProjService;
    }

    @GetMapping
    public ResponseEntity<List<VideoProj>> getAllVideoProjs() {
        List<VideoProj> videoProjs = videoProjService.getAllVideoProjs();
        return new ResponseEntity<>(videoProjs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoProj> getVideoProjById(@PathVariable Integer id) {
        // Here, the controller calls the service layer
        Optional<VideoProj> videoProj = videoProjService.getVideoProjById(id);

        if (videoProj.isPresent()) {
            return new ResponseEntity<>(videoProj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addVideoProj")
    public ResponseEntity<VideoProj> createVideoProj(@RequestBody VideoProj videoProj) {
        VideoProj createdVideoProj = videoProjService.createVideoProj(videoProj);
        return new ResponseEntity<>(createdVideoProj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoProj> updateVideoProj(@PathVariable Integer id, @RequestBody VideoProj videoProjDetails) {
        try {
            VideoProj updatedVideoProj = videoProjService.updateVideoProj(id, videoProjDetails);
            return new ResponseEntity<>(updatedVideoProj, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or a more specific error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoProj(@PathVariable Integer id) {
        videoProjService.deleteVideoProj(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }
}
