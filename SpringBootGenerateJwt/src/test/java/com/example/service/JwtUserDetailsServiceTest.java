package com.example.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("JwtUserDetailsService Tests")
class JwtUserDetailsServiceTest {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Test
	@DisplayName("loadUserByUsername returns UserDetails for known user 'javainuse'")
	void testLoadUserByUsername_KnownUser() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("javainuse");
		assertNotNull(userDetails);
		assertEquals("javainuse", userDetails.getUsername());
	}

	@Test
	@DisplayName("loadUserByUsername password is encoded (not plain text)")
	void testLoadUserByUsername_PasswordEncoded() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("javainuse");
		// BCrypt encoded passwords start with $2a$ or $2b$
		assertTrue(userDetails.getPassword().startsWith("$2"));
	}

	@Test
	@DisplayName("loadUserByUsername throws UsernameNotFoundException for unknown user")
	void testLoadUserByUsername_UnknownUser() {
		assertThrows(UsernameNotFoundException.class,
				() -> userDetailsService.loadUserByUsername("unknownuser"));
	}

	@Test
	@DisplayName("loadUserByUsername account is enabled for known user")
	void testLoadUserByUsername_AccountEnabled() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("javainuse");
		assertTrue(userDetails.isEnabled());
		assertTrue(userDetails.isAccountNonExpired());
		assertTrue(userDetails.isCredentialsNonExpired());
		assertTrue(userDetails.isAccountNonLocked());
	}
}
