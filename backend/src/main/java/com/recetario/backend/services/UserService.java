package com.recetario.backend.services;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recetario.backend.dtos.CredentialsDto;
import com.recetario.backend.dtos.SignUpDto;
import com.recetario.backend.dtos.UserDto;
import com.recetario.backend.entities.User;
import com.recetario.backend.exceptions.AppException;
import com.recetario.backend.mappers.UserMapper;
import com.recetario.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;

	public UserDto findByLogin(String login) {
		User user = userRepository.findByLogin(login)
				.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
		return userMapper.toUserDto(user);
	}

	public UserDto login(CredentialsDto credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.getLogin())
				.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
			return userMapper.toUserDto(user);
		}

		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UserDto register(SignUpDto userDto) {
		Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());
		if (optionalUser.isPresent())
			throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);

		User user = userMapper.signUpToUser(userDto);
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
		User savaedUser = userRepository.save(user);
		
		return userMapper.toUserDto(savaedUser);
	}
}
