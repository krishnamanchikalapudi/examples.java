package com.example.security.util;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.model.Person;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Validates a JWT token using the secret configured in application.yml.
 */
@Component
public class JwtTokenValidator {

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Parses the JWT token and returns a Person extracted from its claims,
	 * or null if the token is invalid or missing required properties.
	 *
	 * @param token the JWT token to parse
	 * @return Person object or null if token is invalid
	 */
	public Person parseToken(String token) {
		Person u = null;
		try {
			Claims body = Jwts.parser()
					.verifyWith(getSigningKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();

			u = new Person();
			u.setUsername(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			u.setRole((String) body.get("role"));

		} catch (JwtException e) {
			e.printStackTrace();
		}
		return u;
	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
