package com.example.api;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.Constants;
import com.example.model.Person;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class RestControllerApi {

	@RequestMapping(value = { Constants.URL_HOME }, method = RequestMethod.GET)
	public String home() {
		return "Welcome to Spring Rest + Boot example";
	}

	@RequestMapping(value = { Constants.URL_BY_ID }, method = RequestMethod.GET, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<Person> jsonId(@PathVariable String id, @RequestHeader HttpHeaders reqHeaders) {
		System.out.println("USER: content-type: " + reqHeaders.getContentType().toString());

		return id(id, reqHeaders);
	}

	private ResponseEntity<Person> id(String id, HttpHeaders reqHeaders) {
		MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<String, String>();
		responseHeaders.set("status", "" + HttpStatus.OK.value());
		responseHeaders.set("CurrentTime", (new Date().toString()));

		Person p = new Person(Long.parseLong((String) id), "Krishna");
		return new ResponseEntity<Person>(p, responseHeaders, HttpStatus.OK);
	}

}
