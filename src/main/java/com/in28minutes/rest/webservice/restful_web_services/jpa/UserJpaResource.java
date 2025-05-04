package com.in28minutes.rest.webservice.restful_web_services.jpa;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservice.restful_web_services.user.Post;
import com.in28minutes.rest.webservice.restful_web_services.user.User;
import com.in28minutes.rest.webservice.restful_web_services.user.UserDaoService;
import com.in28minutes.rest.webservice.restful_web_services.user.UserNotFoundException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserJpaResource {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	//생성자 주입(Constructor Injection)
	public UserJpaResource(UserRepository repository,PostRepository postRepository) {
		this.userRepository=repository;
		this.postRepository=postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User>retrieveAllUsers(){
		return userRepository.findAll();
		//select u1_0.id,u1_0.birth_date,u1_0.name from user_details u1_0
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		//일반적인 저장이면 200 상태
		User savedUser = userRepository.save(user);
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
	
	// http://localhost:8080/users
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		Optional<User> user =  userRepository.findById(id);
		//select u1_0.id,u1_0.birth_date,u1_0.name from user_details u1_0 where u1_0.id=?
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		//링크를 반환하기 위한 코드
		entityModel.add(link.withRel("all-users"));
		//링크를 entityModel 객체에 추가
		return entityModel;
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePoatsForUser(@PathVariable int id){
		Optional<User> user =  userRepository.findById(id);
		//먼저 사용자의 id로 사용자를 가져오고 그 다음 게시물의 세부정보를 가져옴 -> 두 개의 쿼리를 날림
		//user.getPosts()를 호출하는 시점에 지연로딩으로 인해 Post에 대한 SELECT가 추가로 실행
		//select u1_0.id,u1_0.birth_date,u1_0.name from user_details u1_0 where u1_0.id=? 
		//select p1_0.user_id,p1_0.id,p1_0.description from post p1_0 where p1_0.user_id=?
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}
		return user.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user =  userRepository.findById(id);
		//select u1_0.id,u1_0.birth_date,u1_0.name from user_details u1_0 where u1_0.id=?
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}
		post.setUser(user.get());
		Post SavedPost = postRepository.save(post);
		//select next value for post_seq
		//insert into post (description,user_id,id) values (?,?,?)
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(SavedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	


	

	

	
	
	
}
