package com.example.ReservationSalleMateriel.controller;

import com.example.ReservationSalleMateriel.service.ReservSalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planning")
public class PlanningControlleur {

    @Autowired
    ReservSalleService reservSalleService;

}
