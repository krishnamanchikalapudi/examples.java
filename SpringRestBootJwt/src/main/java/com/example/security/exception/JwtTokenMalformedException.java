package com.example.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be parsed
 */
public class JwtTokenMalformedException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}
}
