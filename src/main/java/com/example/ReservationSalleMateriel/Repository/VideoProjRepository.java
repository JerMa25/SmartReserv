package com.example.ReservationSalleMateriel.Repository;

import com.example.ReservationSalleMateriel.model.VideoProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoProjRepository extends JpaRepository<VideoProj, Integer> {

    List<VideoProj> findAll();
    Optional<VideoProj> findById(Integer id);
    VideoProj save(VideoProj videoProj);
    void deleteById(Integer id);
}
