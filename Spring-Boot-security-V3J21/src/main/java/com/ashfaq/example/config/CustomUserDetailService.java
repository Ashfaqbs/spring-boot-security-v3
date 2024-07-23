package com.ashfaq.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashfaq.example.dao.UserRepository;
import com.ashfaq.example.model.AppUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> findbyUsername = userRepository.findByUsername(username);
		if (findbyUsername.isPresent()) {
			AppUser appUser = findbyUsername.get();

			log.info(appUser + "User found in database");
			// if the user is present we have to convert our user to userdetails class and
			// return it
			// as you can see earlier we returned a userdetails object
//		UserDetails - >	import org.springframework.security.core.userdetails.UserDetails; this we have to return  it
//		User - > 	import org.springframework.security.core.userdetails.User; this is the builder class we have to use it and this returns a userdetails object

			return User.builder().username(appUser.getUsername()).password(appUser.getPassword())
//					.roles(appUser.getRole().split(","))//***IMP**this takes a list , but we have created roles by string variable so how will do is 
			// we ill give the data in coma separated like ADMIN,USER.....
//					OR we can create a seperate func
					.roles(getRoles(appUser.getRole())).build();

		} else {

			throw new UsernameNotFoundException(username + "User not found");
		}
	}

	private String[] getRoles(String roles) {

		if (roles == null) {
			return new String[] { "USER" };
		}

		return roles.split(",");
	}

}
