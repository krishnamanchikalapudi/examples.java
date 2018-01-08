package com.example.constant;

public final class Constants {
	private Constants() {
		// restrict instantiation
	}

	public static final String URL_HOME = "/";
	public static final String URL_HEALTH = "/health";
	public static final String URL_PERSON = "/person";
	public static final String URL_PERSON_BY_ID = (URL_PERSON + "/{id}");

}
