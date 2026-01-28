package com.example.api;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.Constants;
import com.example.model.Person;

@RestController
public class PersonApi {

	@GetMapping(Constants.URL_HOME)
	public String home() {
		return "Welcome to Spring Rest + Boot example";
	}

	@GetMapping(value = { Constants.URL_PERSON_BY_ID }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Person> id(@PathVariable String id) {
		Person p = new Person(id, "Krishna");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("CurrentTime", (new Date().toString()));
		return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(p);
	}
}
