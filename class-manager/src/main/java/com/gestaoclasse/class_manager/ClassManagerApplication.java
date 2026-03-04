package com.gestaoclasse.class_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClassManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassManagerApplication.class, args);
	}

}
