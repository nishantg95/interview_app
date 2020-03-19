package com.accenture.inteview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.accenture.inteview.configuration.HibernateSearchConfig;

@SpringBootApplication
@Import(HibernateSearchConfig.class)
public class InterviewBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewBackendApplication.class, args);
	}
}
