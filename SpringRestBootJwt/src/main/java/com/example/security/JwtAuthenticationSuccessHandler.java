package com.example.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Defines where to go after successful login. In this implementation just make
 * sure nothing is done (REST API constrains no pages)
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		// Do do anything specific here
	}

}
