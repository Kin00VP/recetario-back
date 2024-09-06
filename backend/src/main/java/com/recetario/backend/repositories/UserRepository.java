package com.recetario.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.recetario.backend.entities.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
}
