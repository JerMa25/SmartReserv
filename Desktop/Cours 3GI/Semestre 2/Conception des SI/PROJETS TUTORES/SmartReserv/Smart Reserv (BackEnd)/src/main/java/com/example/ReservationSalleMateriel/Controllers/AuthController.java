package com.example.ReservationSalleMateriel.Controllers;

import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.Repository.EnseignantRepository;
import com.example.ReservationSalleMateriel.Security.JwtUtil;
import com.example.ReservationSalleMateriel.Message.AuthRequest;
import com.example.ReservationSalleMateriel.Message.AuthResponse;
import com.example.ReservationSalleMateriel.Message.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private EnseignantRepository enseignantRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Response<Map<String,Object>>> login(@RequestBody AuthRequest request) {
        Optional<Enseignant> enseignantOptional = enseignantRepo.findByEmail(request.getEmail());

        if (enseignantOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response<>( "Email not found", null));
        }

        Enseignant enseignant = enseignantOptional.get();

        if (enseignant.getMatricule()!=request.getMatricule()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response<>( "Invalid matricule", null));
        }

        String token = jwtUtil.generateToken(enseignant);
        return ResponseEntity.ok(new Response<>( "Login successful", Map.of("token",token,"enseignant",enseignant)));
    }
}
