package com.in28minutes.rest.webservice.restful_web_services.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
		
	@Id
	@GeneratedValue
	private Integer id; //id가 null이면 todo를 삽입하고 id가 기존에 있는 값이면 업데이트
					// id가 null을 가질 수 있을려면 래퍼클래스인 int에서 Integer로 변경해줘야한다.
	private String username;
	
	@Size(min=10, message="10글자 이상 입력하세요")
	private String description;
	
	private LocalDate targetDate;
	private boolean done;
	
	//기본생성자 없으면 에러남
	public Todo() {
		
	}
	
	public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}


	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}

	
	
	
}
