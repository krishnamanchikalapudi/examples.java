package com.example.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("JwtTokenUtil Tests")
class JwtTokenUtilTest {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private UserDetails userDetails;
	private String token;

	@BeforeEach
	void setUp() {
		userDetails = new User("testuser", "password", new ArrayList<>());
		token = jwtTokenUtil.generateToken(userDetails);
	}

	@Test
	@DisplayName("generateToken returns a non-null, non-empty token")
	void testGenerateToken() {
		assertNotNull(token);
		assertFalse(token.isEmpty());
	}

	@Test
	@DisplayName("getUsernameFromToken extracts correct username")
	void testGetUsernameFromToken() {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		assertEquals("testuser", username);
	}

	@Test
	@DisplayName("getExpirationDateFromToken returns future date")
	void testGetExpirationDateFromToken() {
		Date expiration = jwtTokenUtil.getExpirationDateFromToken(token);
		assertNotNull(expiration);
		assertTrue(expiration.after(new Date()));
	}

	@Test
	@DisplayName("validateToken returns true for correct user")
	void testValidateToken_Valid() {
		Boolean valid = jwtTokenUtil.validateToken(token, userDetails);
		assertTrue(valid);
	}

	@Test
	@DisplayName("validateToken returns false for wrong username")
	void testValidateToken_WrongUser() {
		UserDetails otherUser = new User("otheruser", "password", new ArrayList<>());
		Boolean valid = jwtTokenUtil.validateToken(token, otherUser);
		assertFalse(valid);
	}

	@Test
	@DisplayName("generateToken produces different tokens for different users")
	void testGenerateToken_DifferentUsers() {
		UserDetails anotherUser = new User("anotheruser", "pass", new ArrayList<>());
		String anotherToken = jwtTokenUtil.generateToken(anotherUser);
		assertNotEquals(token, anotherToken);
	}

	@Test
	@DisplayName("JWT_TOKEN_VALIDITY constant is 5 hours in seconds")
	void testTokenValidity() {
		assertEquals(5 * 60 * 60, JwtTokenUtil.JWT_TOKEN_VALIDITY);
	}
}
