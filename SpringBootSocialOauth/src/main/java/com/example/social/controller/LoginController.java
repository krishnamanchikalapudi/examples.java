package com.example.social.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

	private static final String authorizationRequestBaseUri = "oauth2/authorize-client";
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping("/")
	public String home(Model model) {
		return getLoginPage(model);
	}

	@GetMapping("/oauth_login")
	public String getLoginPage(Model model) {
		Iterable<ClientRegistration> clientRegistrations = null;
		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
		if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
			clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
		}

		clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
				authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
		model.addAttribute("urls", oauth2AuthenticationUrls);

		return "oauth_login";
	}

	@GetMapping("/loginSuccess")
	public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {

		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

		String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

		if (!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
					Map.class);
			/*
			 * Google Response {sub=109686743314382038325, name=Krishna, given_name=Krishna,
			 * picture=https://lh6.googleusercontent.com/-PG4qRO43IBY/AAAAAAAAAAI/
			 * AAAAAAAAmLk/EYBcPXXy0L8/photo.jpg, email=krishna.manchikalapudi@gmail.com,
			 * email_verified=true, locale=en}
			 */

			/*
			 * Facebook response <200,{id=10159010915818508, name=Krishna Manchikalapudi,
			 * email=krishna.manchikalapudi@gmail.com},[ETag:
			 * ""f5c9386ac30d6c477497061e192d74cba0d4d769"",
			 * x-app-usage:"{"call_count":0,"total_cputime":0,"total_time":0}",
			 * Content-Type:"application/json", facebook-api-version:"v3.3",
			 * Strict-Transport-Security:"max-age=15552000; preload", Pragma:"no-cache",
			 * x-fb-rev:"1000917516", Access-Control-Allow-Origin:"*",
			 * Cache-Control:"private, no-cache, no-store, must-revalidate",
			 * x-fb-trace-id:"Fp/dP0n5B9V", x-fb-request-id:"AQeG_NWMO95rjcBm_e5N_RY",
			 * Expires:"Sat, 01 Jan 2000 00:00:00 GMT", X-FB-Debug:
			 * "OWcBJERnAw2TSJyxFEfMnf7WHMEvhus13tgajo5Z+gVLg2fR4Ek6cU8ykoABYPypRtwRuKvk/N6i+Ce4lQAh9w==",
			 * Date:"Mon, 08 Jul 2019 23:37:20 GMT", Connection:"keep-alive",
			 * Content-Length:"106"]>
			 * 
			 */

			/*
			 * Github reponse: OpenId response: <200,{login=krishnamanchikalapudi,
			 * id=1148983, node_id=MDQ6VXNlcjExNDg5ODM=,
			 * avatar_url=https://avatars2.githubusercontent.com/u/1148983?v=4,
			 * gravatar_id=, url=https://api.github.com/users/krishnamanchikalapudi,
			 * html_url=https://github.com/krishnamanchikalapudi,
			 * followers_url=https://api.github.com/users/krishnamanchikalapudi/followers,
			 * following_url=https://api.github.com/users/krishnamanchikalapudi/following{/
			 * other_user},
			 * gists_url=https://api.github.com/users/krishnamanchikalapudi/gists{/gist_id},
			 * starred_url=https://api.github.com/users/krishnamanchikalapudi/starred{/owner
			 * }{/repo},
			 * subscriptions_url=https://api.github.com/users/krishnamanchikalapudi/
			 * subscriptions,
			 * organizations_url=https://api.github.com/users/krishnamanchikalapudi/orgs,
			 * repos_url=https://api.github.com/users/krishnamanchikalapudi/repos,
			 * events_url=https://api.github.com/users/krishnamanchikalapudi/events{/privacy
			 * }, received_events_url=https://api.github.com/users/krishnamanchikalapudi/
			 * received_events, type=User, site_admin=false, name=Krishna, company=null,
			 * blog=https://www.linkedin.com/in/krishnamanchikalapudi, location=94111,
			 * email=null, hireable=true, bio=More information at
			 * https://www.linkedin.com/in/krishnamanchikalapudi, public_repos=4,
			 * public_gists=0, followers=4, following=1, created_at=2011-10-24T18:40:30Z,
			 * updated_at=2019-07-08T23:45:01Z, private_gists=0, total_private_repos=2,
			 * owned_private_repos=2, disk_usage=213436, collaborators=0,
			 * two_factor_authentication=false, plan={name=free, space=976562499,
			 * collaborators=0, private_repos=10000}},[Date:"Mon, 08 Jul 2019 23:45:12 GMT",
			 * Content-Type:"application/json; charset=utf-8", Content-Length:"1632",
			 * Server:"GitHub.com", Status:"200 OK", X-RateLimit-Limit:"5000",
			 * X-RateLimit-Remaining:"4998", X-RateLimit-Reset:"1562633112",
			 * Cache-Control:"private, max-age=60, s-maxage=60",
			 * Vary:"Accept, Authorization, Cookie, X-GitHub-OTP", "Accept-Encoding",
			 * ETag:""7b20d3dfe05741e6d01e55009cece38a"",
			 * Last-Modified:"Mon, 08 Jul 2019 23:45:01 GMT", X-OAuth-Scopes:"read:user",
			 * X-Accepted-OAuth-Scopes:"", X-OAuth-Client-Id:"6285563044aa0597bc2a",
			 * X-GitHub-Media-Type:"github.v3", Access-Control-Expose-
			 * Headers:"ETag, Link, Location, Retry-After, X-GitHub-OTP, X-RateLimit-Limit, X-RateLimit-Remaining, X-RateLimit-Reset, X-OAuth-Scopes, X-Accepted-OAuth-Scopes, X-Poll-Interval, X-GitHub-Media-Type"
			 * , Access-Control-Allow-Origin:"*",
			 * Strict-Transport-Security:"max-age=31536000; includeSubdomains; preload",
			 * X-Frame-Options:"deny", X-Content-Type-Options:"nosniff",
			 * X-XSS-Protection:"1; mode=block",
			 * Referrer-Policy:"origin-when-cross-origin, strict-origin-when-cross-origin",
			 * Content-Security-Policy:"default-src 'none'",
			 * X-GitHub-Request-Id:"D028:9240:A6E9F7:C91DDC:5D23D588"]>
			 * 
			 */
			System.out.println("OpenId response: " + response.toString());
			Map userAttributes = response.getBody();
			model.addAttribute("name", userAttributes.get("name"));
			model.addAttribute("oauth2sys", client.getClientRegistration().getClientName());

			model.addAttribute("email", userAttributes.get("email"));

		}

		return "loginSuccess";
	}

}
