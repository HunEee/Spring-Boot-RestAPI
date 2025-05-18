package com.in28minutes.rest.webservice.restful_web_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // 모든 것에 대한 활성화
						.allowedMethods("*") // 모든 메서드 허용(GET,PUT,POST)
						.allowedOrigins("http://localhost:3000") // 어디서 오는 요청인지
						.allowCredentials(true);
			}
		};
	}
}
