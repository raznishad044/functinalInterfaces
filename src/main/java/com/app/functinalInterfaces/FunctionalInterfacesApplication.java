package com.app.functinalInterfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FunctionalInterfacesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FunctionalInterfacesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FunctionalInterfacesApplication.class, args);
	}

}
