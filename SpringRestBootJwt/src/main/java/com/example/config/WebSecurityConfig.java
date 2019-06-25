package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtAuthenticationEntryPoint;
import com.example.security.JwtAuthenticationProvider;
import com.example.security.JwtAuthenticationSuccessHandler;
import com.example.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {

		return new ProviderManager(Arrays.asList(authenticationProvider));
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManager());
		authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()
				// All urls must be authenticated (filter for token always fires (/**)
				.authorizeRequests().anyRequest().authenticated().and()
				// Call our errorHandler if authentication/authorisation fails
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // .and()
		// Custom JWT based security filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();
	}
}
