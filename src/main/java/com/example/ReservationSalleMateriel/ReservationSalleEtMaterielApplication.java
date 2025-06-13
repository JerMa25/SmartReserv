package com.example.ReservationSalleMateriel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = "com.example.ReservationSalleMateriel.model")
@EnableJpaRepositories(basePackages = "com.example.ReservationSalleMateriel.Repository")


public class ReservationSalleEtMaterielApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationSalleEtMaterielApplication.class, args);
	}

}
