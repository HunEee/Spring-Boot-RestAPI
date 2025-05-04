package com.in28minutes.rest.webservice.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersion0fPerson() {
		return new PersonV1("Bob Chailie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersion0fPerson() {
		return new PersonV2(new Name("Bob","Chailie"));
	}
	
	//http://localhost:8080/person?version=1
	@GetMapping(path="/person",params="version=1")
	public PersonV1 getFirstVersion0fPersonRequestParameter() {
		return new PersonV1("Bob Chailie");
	}
	
	//http://localhost:8080/person?version=2
	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersion0fPersonRequestParameter() {
		return new PersonV2(new Name("Bob","Chailie"));
	}
	
	//Request 헤더
	@GetMapping(path="/person/header",headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersion0fPersonRequestHeader() {
		return new PersonV1("Bob Chailie");
	}
	
	@GetMapping(path="/person/header",headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersion0fPersonRequestHeader() {
		return new PersonV2(new Name("Bob","Chailie"));
	}
	
	//Accept 헤더
	@GetMapping(path="/person/accept",produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersion0fPersonAcceptHeader() {
		return new PersonV1("Bob Chailie");
	}
	
	@GetMapping(path="/person/accept",produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersion0fPersonAcceptHeader() {
		return new PersonV2(new Name("Bob","Chailie"));
	}
	
}
