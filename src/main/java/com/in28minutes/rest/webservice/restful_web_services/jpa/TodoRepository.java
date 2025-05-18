package com.in28minutes.rest.webservice.restful_web_services.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservice.restful_web_services.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUsername(String username);
	
}
