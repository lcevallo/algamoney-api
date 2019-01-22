package com.example.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}
	
	
	/*
	
	//Para cualquier requisicion
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurerAdapter() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:8000"); 
			}
		};
	}
	
	*/
}
