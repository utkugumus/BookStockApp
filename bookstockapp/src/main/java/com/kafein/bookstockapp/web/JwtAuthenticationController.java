package com.kafein.bookstockapp.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kafein.bookstockapp.config.JwtTokenUtil;
import com.kafein.bookstockapp.model.JwtResponse;
import com.kafein.bookstockapp.model.User;
import com.kafein.bookstockapp.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		User user = userService.findByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.doGenerateToken(user.getUsername());

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<URI> creataUser(@RequestBody User user) {
		try {
			userService.save(user);
			String name = user.getUsername();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(name)
					.toUri();
			
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}