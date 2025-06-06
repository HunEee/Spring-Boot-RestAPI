package com.in28minutes.rest.webservice.restful_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// JWT 적용을 위해 꺼줌
// @Configuration 
public class BasicSpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  
		//CORS 활성화
        //http.cors(withDefaults());
        //http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        
		//1) 모든 요청이 인증되도록 함 => CORS 활성화를 위해 주석처리함
		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
												.anyRequest().authenticated());
        
		//2) 기본인증: 웹페이지를 보여주지 않지만 팝업창으로 인증을 요청 
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
		);
		
		//3) CSRF 해제
		http.csrf().disable();
		
		return http.build();
	}
	
}
