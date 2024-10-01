package com.orazov.solva_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SolvaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolvaAssignmentApplication.class, args);
	}

}
