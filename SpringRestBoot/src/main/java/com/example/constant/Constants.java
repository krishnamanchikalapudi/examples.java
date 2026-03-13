package com.example.constant;

public final class Constants {
	private Constants() {
		// restrict instantiation
	}

	public static final String VERSION = "v1";
	public static final String URL_HOME = ("/"+ VERSION);
	public static final String URL_BY_ID = (URL_HOME + "/{id}");

	public static final String RTN_HOME = "Welcome to Spring Rest + Boot example";

	public static final String APPLICATION_TOON_VALUE = "application/vnd.toon+json"; // A common convention for custom formats
    public static final MediaType APPLICATION_TOON = MediaType.valueOf(APPLICATION_TOON_VALUE);
}
