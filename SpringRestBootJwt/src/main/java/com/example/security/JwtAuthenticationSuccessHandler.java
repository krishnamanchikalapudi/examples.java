package com.example.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * No-op success handler for REST API (no page redirect needed).
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		System.out.println("\n\t\t  ---- JWT Success ---- ");
		System.out.println("name: " + authentication.getName());
		System.out.println("tostring: " + authentication.toString());
		System.out.println("\n\t\t  -------- ");
	}
}
