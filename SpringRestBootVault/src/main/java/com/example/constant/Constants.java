package com.example.constant;

public final class Constants {
	private Constants() {
		// restrict instantiation
	}

	public static final String URL_HOME = "/v1/";
	public static final String URL_BY_ID = (URL_HOME + "/{id}");

	public static final String VAULT_SECRET = "secret/springrestbootvault";
	
}
