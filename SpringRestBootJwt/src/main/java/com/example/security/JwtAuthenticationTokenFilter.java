package com.example.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.model.JwtAuthenticationToken;
import com.example.security.exception.JwtTokenMissingException;

/**
 * Filter that orchestrates authentication by using supplied JWT token
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	@Value("${jwt.header}")
	private String tokenHeader;

	public JwtAuthenticationTokenFilter() {
		super("/v1/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String header = request.getHeader(this.tokenHeader);

		if (header == null || !header.startsWith("Bearer ")) {
			throw new JwtTokenMissingException("No JWT token found in request headers");
		}

		String authToken = header.substring(7);
		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
