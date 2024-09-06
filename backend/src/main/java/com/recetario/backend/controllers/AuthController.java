package com.recetario.backend.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recetario.backend.config.UserAuthProvider;
import com.recetario.backend.dtos.CredentialsDto;
import com.recetario.backend.dtos.SignUpDto;
import com.recetario.backend.dtos.UserDto;
import com.recetario.backend.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

	@Autowired
	private final UserService userService;
	@Autowired
	private final UserAuthProvider userAuthProvider;

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user.getLogin()));
		return ResponseEntity.ok(user);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
		UserDto user = userService.register(signUpDto);
		user.setToken(userAuthProvider.createToken(user.getLogin()));

		return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
	}
}
