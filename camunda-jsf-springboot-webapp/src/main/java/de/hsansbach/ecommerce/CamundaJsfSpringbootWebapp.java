package de.hsansbach.ecommerce;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableProcessApplication
public class CamundaJsfSpringbootWebapp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CamundaJsfSpringbootWebapp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CamundaJsfSpringbootWebapp.class);
	}
}
