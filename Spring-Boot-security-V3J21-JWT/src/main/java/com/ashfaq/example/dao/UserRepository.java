package com.ashfaq.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	// we need to create a function which should be able to find a user by username and some time user exists or not so better return in optional
	Optional<AppUser> findByUsername(String username);
	

}
