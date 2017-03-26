package de.hsansbach.ecommerce;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableProcessApplication
public class CamundaJsfSpringbootWebappApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CamundaJsfSpringbootWebappApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CamundaJsfSpringbootWebappApplication.class);
	}
}
