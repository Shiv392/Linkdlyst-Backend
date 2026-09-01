package com.example.Linkdlyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LinkdlystApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkdlystApplication.class, args);
	}

}
