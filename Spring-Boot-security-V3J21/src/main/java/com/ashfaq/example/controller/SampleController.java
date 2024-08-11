package com.ashfaq.example.controller;

import java.util.Base64;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

//Getting the username from the api calls
	@GetMapping("/home")
	public String handleWelcome() {
		return "home";
	}

	@GetMapping("/admin/home")
	public String handleAdminHome() {
		return "home_admin";
	}

	@GetMapping("/user/home")
	public String handleUserHome(@RequestHeader("Authorization") String authHeader) {

//		// Get the authenticated user from @RequestHeader
		String username = extractUsernameFromAuthHeader(authHeader);
		System.out.println("Username from Header: " + username);

//        OR 

		// Get the authenticated user from SecurityContextHolder
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usernamefromContext = authentication.getName();

		// Log the username
		System.out.println("User '{}' is creating a car" + usernamefromContext);

		// logs
//        Username from Header: seller
//     User  is creating a car 'seller'

		return "home_user";
	}

	//
	private String extractUsernameFromAuthHeader(String authHeader) {
		if (authHeader != null && authHeader.startsWith("Basic ")) {
			// Decode the Base64 encoded username:password
			String base64Credentials = authHeader.substring(6);
			String credentials = new String(Base64.getDecoder().decode(base64Credentials));
			// Split the credentials to get the username
			return credentials.split(":")[0];
		}
		return null;
	}

}
