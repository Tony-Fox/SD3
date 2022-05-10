package com.example.sd2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sd2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sd2Application.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
//						.allowedOrigins("*")
//						.allowedHeaders("*");
//			}
//		};
//	}











}
