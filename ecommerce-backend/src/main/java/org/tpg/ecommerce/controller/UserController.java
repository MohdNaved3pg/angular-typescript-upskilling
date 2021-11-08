package org.tpg.ecommerce.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tpg.ecommerce.dtos.LoginDTO;
import org.tpg.ecommerce.model.Token;
import org.tpg.ecommerce.model.User;
import org.tpg.ecommerce.repository.UserRepository;
import org.tpg.ecommerce.service.UserService;

@Controller
@RequestMapping("users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<User> getById(@RequestHeader Map<String, String> headers) {
		
		Optional<Integer> userId = extractFromHeaders(headers, HttpHeaders.AUTHORIZATION)
									.flatMap(userService::getUserIdFromToken);
		if(!userId.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return userId.flatMap(repository::findById)
			.map(ResponseEntity::ok)
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("register")
	public ResponseEntity<Object> registerUser(@RequestBody User user) {
		repository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("login")
	public ResponseEntity<Token> login(@RequestBody LoginDTO loginDto) {
		
		try {
			Token token = userService.authenticateUser(loginDto.getUsername(), loginDto.getPassword());
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	private Optional<String> extractFromHeaders(Map<String, String> headers, String headerKey) {
		Optional<String> headerValue = headers.entrySet().stream()
				.filter(es -> headerKey.equalsIgnoreCase(es.getKey()))
				.map(es -> es.getValue())
				.findFirst();
		return headerValue;
	}
}
