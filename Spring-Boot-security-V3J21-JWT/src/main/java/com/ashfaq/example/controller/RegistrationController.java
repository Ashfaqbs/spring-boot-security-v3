package com.ashfaq.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.config.CustomUserDetailService;
import com.ashfaq.example.config.services.JWTService;
import com.ashfaq.example.dao.UserRepository;
import com.ashfaq.example.model.AppUser;
import com.ashfaq.example.model.LoginForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public")
public class RegistrationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService userDetailService;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserRepository myUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// this api should be accessable to all
	@PostMapping("/register")
	public AppUser createUser(@RequestBody AppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		AppUser appUser = myUserRepository.save(user);
		return appUser;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<String> authAndGetTokenold(@RequestBody LoginForm loginFormuser) {

		Authentication authenticationResult = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginFormuser.username(), loginFormuser.password()));

//		log.info(authenticationResult.toString() + "authenticationResult ************");
		
		if (authenticationResult.isAuthenticated()) {
			// we need to return JWT token

			UserDetails userDetails = userDetailService.loadUserByUsername(loginFormuser.username());
//			log.info(loginFormuser.username() + "User found in database ************");

			String jwtToken = jwtService.generateToken(userDetails);// this functions takes Userdetails param and we
			// have loginform with username and pwd ,
			// so we will use CusotmuserDetailService funciont which takes username
			// validates and returns Userdetails

			return ResponseEntity.ok().body("Token: " + jwtToken);

		} else {

			throw new RuntimeException("Invalid username or password" + loginFormuser.username());// this wont work as if auth is failed 
			//directly we get 401 error

		}

	}

}