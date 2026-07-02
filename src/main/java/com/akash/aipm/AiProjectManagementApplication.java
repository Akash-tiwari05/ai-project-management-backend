package com.akash.aipm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AiProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiProjectManagementApplication.class, args);
	}

}
