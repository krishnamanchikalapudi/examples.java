package com.example.api.test;

import org.junit.Test;

import com.example.model.Person;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * convenience class to generate a token for testing your requests. Make sure
 * the used secret here matches the on in your application.yml
 */
public class JwtTokenGeneratorTest {

	private String jwtSecret = "example-secret-key";

	@Test
	public void testGenerateToken() throws Exception {

		Person user = new Person();
		user.setId(123L);
		user.setUsername("Krishna");
		user.setRole("admin");

		try {
			Person u = new Person();
			Claims claims = Jwts.claims().setSubject(u.getUsername());
			claims.put("userId", u.getId() + "");
			claims.put("role", u.getRole());

			String authToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
			System.out.println("*************** GENERATE TOKEN ***********************\n\n" + authToken
					+ "\n\n**************************************");
		} catch (JwtException e) {
			// Simply print the exception and null will be returned for the userDto
			e.printStackTrace();
		}

	}

	@Test

	public void testDisplayAuthToken() throws Exception {
		String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ";
		Person u = null;
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();

			u = new Person();
			u.setUsername(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			u.setRole((String) body.get("role"));

			System.out.println("*************** Auth TOKEN ***********************\n\n" + u.toString()
					+ "\n\n**************************************");
		} catch (JwtException e) {
			// Simply print the exception and null will be returned for the userDto
			e.printStackTrace();
		}
	}
}
