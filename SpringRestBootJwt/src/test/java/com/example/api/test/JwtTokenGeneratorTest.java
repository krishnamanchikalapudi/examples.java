package com.example.api.test;

import com.example.model.Person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Generates and validates JWT tokens for testing.
 * Ensure jwt.secret in application.yml matches the key used here.
 */
@DisplayName("JWT Token Generator Tests")
class JwtTokenGeneratorTest {

	// Base64-encoded 512-bit key matching application.yml
	private static final String JWT_SECRET =
			"U3ByaW5nQm9vdDRKd3RTZWNyZXRLZXlGb3JUZXN0aW5nVGhpc0lzQTUxMkJpdExvbmdTZWNyZXRLZXlYWFhYWA==";

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
	}

	@Test
	@DisplayName("Generate JWT token for a user and print it")
	void testGenerateToken() {
		Person user = new Person();
		user.setId(123L);
		user.setUsername("Krishna");
		user.setRole("admin");

		Claims claims = Jwts.claims()
				.subject(user.getUsername())
				.add("userId", String.valueOf(user.getId()))
				.add("role", user.getRole())
				.build();

		String authToken = Jwts.builder()
				.claims(claims)
				.signWith(getSigningKey(), Jwts.SIG.HS512)
				.compact();

		assertNotNull(authToken);
		assertFalse(authToken.isEmpty());
		System.out.println("Generated Token:\n" + authToken);
	}

	@Test
	@DisplayName("Parse a valid JWT token and extract user details")
	void testParseToken() {
		Person user = new Person();
		user.setId(456L);
		user.setUsername("TestUser");
		user.setRole("role");

		Claims createdClaims = Jwts.claims()
				.subject(user.getUsername())
				.add("userId", String.valueOf(user.getId()))
				.add("role", user.getRole())
				.build();

		String token = Jwts.builder()
				.claims(createdClaims)
				.signWith(getSigningKey(), Jwts.SIG.HS512)
				.compact();

		Claims parsed = Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

		Person parsedUser = new Person();
		parsedUser.setUsername(parsed.getSubject());
		parsedUser.setId(Long.parseLong((String) parsed.get("userId")));
		parsedUser.setRole((String) parsed.get("role"));

		assertEquals("TestUser", parsedUser.getUsername());
		assertEquals(456L, parsedUser.getId());
		assertEquals("role", parsedUser.getRole());
	}

	@Test
	@DisplayName("Parsing a tampered token throws JwtException")
	void testTamperedToken_ThrowsException() {
		String tamperedToken = "eyJhbGciOiJIUzUxMiJ9.tampered.signature";
		assertThrows(JwtException.class, () ->
				Jwts.parser()
						.verifyWith(getSigningKey())
						.build()
						.parseSignedClaims(tamperedToken)
		);
	}
}
