package com.in28minutes.rest.webservice.restful_web_services.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservice.restful_web_services.jpa.TodoRepository;

@RestController
public class TodoJpaResource {

	private TodoService todoService;
	
	private TodoRepository todoRepository;
	
	public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username){
		//return todoService.findByUsername(username);
		return todoRepository.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id){
		// return todoService.findById(id);
		return todoRepository.findById(id).get();
	}
	
	//삭제
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
		//todoService.deleteById(id);
		todoRepository.deleteById(id);
		return ResponseEntity.noContent().build(); //no content라는 상태를 리턴
	}
	
	//수정
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){
		//todoService.updateTodo(todo);
		todoRepository.save(todo);
		return todo; // 업데이트 된 Todo를 리턴
	}
	
	//생성
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
		todo.setUsername(username);
		todo.setId(null);
		return todoRepository.save(todo);

//		Todo createdTodo = todoService.addTodo(username, todo.getDescription(),todo.getTargetDate(),todo.isDone());
//		return createdTodo; // 업데이트 된 Todo를 리턴
	}
	
}
