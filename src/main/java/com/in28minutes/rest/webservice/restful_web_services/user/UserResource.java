package com.in28minutes.rest.webservice.restful_web_services.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserResource {

	private UserDaoService service;
	
	//생성자 주입(Constructor Injection)
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<User>retrieveAllUsers(){
		return service.findAll();
	}
	
	// http://localhost:8080/users
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		User user =  service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id: "+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		//링크를 반환하기 위한 코드
		entityModel.add(link.withRel("all-users"));
		//링크를 entityModel 객체에 추가
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		//일반적인 저장이면 200 상태
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(savedUser.getId())
									.toUri();
		//사용자가 생성되면 201상태 반환
		//생성된 리소스의 location 값을 응답값 헤더에 넣어서 반환
		//POST로 생성된 location을 GET으로 요청 가능 
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		service.deleteById(id);
	}
	
	
	
}
