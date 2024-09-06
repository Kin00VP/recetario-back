package com.recetario.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recetario.backend.dtos.SignUpDto;
import com.recetario.backend.dtos.UserDto;
import com.recetario.backend.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toUserDto(User user);

	@Mapping(target = "password", ignore = true)
	User signUpToUser(SignUpDto userDto);
}
