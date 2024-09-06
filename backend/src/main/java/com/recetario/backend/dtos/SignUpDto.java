package com.recetario.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SignUpDto {
	
	private String firstName;
	private String lastName;
	private String login;
	private char[] password;
	
}
