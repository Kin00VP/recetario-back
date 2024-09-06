package com.recetario.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CredentialsDto {
	
	private String login;
	private char[] password;
}
