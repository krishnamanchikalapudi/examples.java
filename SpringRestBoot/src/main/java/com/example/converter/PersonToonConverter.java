package com.example.converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.example.model.Person;

/**
 * Custom HttpMessageConverter for media type "text/toon".
 * Format: key=value pairs delimited by "|", e.g. id=42|firstName=Krishna
 */
public class PersonToonConverter extends AbstractHttpMessageConverter<Person> {

	public static final MediaType TEXT_TOON = MediaType.valueOf("text/toon");

	public PersonToonConverter() {
		super(TEXT_TOON);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	@Override
	protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String body = new String(inputMessage.getBody().readAllBytes(), StandardCharsets.UTF_8);
		Person p = new Person(null, null);
		for (String part : body.split("\\|")) {
			String[] kv = part.split("=", 2);
			if (kv.length == 2) {
				switch (kv[0].trim()) {
					case "id" -> p.setId(kv[1].trim());
					case "firstName" -> p.setFirstName(kv[1].trim());
					case "lastName" -> p.setLastName(kv[1].trim());
					case "phoneNumber" -> p.setPhoneNumber(kv[1].trim());
				}
			}
		}
		return p;
	}

	@Override
	protected void writeInternal(Person person, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(person.getId());
		if (person.getFirstName() != null) sb.append("|firstName=").append(person.getFirstName());
		if (person.getLastName() != null) sb.append("|lastName=").append(person.getLastName());
		if (person.getPhoneNumber() != null) sb.append("|phoneNumber=").append(person.getPhoneNumber());
		outputMessage.getBody().write(sb.toString().getBytes(StandardCharsets.UTF_8));
	}

}
