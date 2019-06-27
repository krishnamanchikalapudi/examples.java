package com.example.constant;

public final class Constants {
	private Constants() {
		// restrict instantiation
	}

	public static final String VERSION = "v1";
	public static final String URL_HOME = ("/" + VERSION);
	public static final String URL_LIVE_STATUS = ("/liveStatus");
	public static final String URL_BY_ID = (URL_HOME + "/{id}");

	public static final String RTN_HOME = "Welcome to Spring Rest + Boot example";
}
