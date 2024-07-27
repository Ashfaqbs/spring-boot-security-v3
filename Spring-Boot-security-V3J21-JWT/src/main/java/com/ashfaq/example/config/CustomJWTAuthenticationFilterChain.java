package com.ashfaq.example.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ashfaq.example.config.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomJWTAuthenticationFilterChain extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	// this method will be called for every request but we have to get it from
	// OncePerRequestFilter class

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		//so in every req we see if we have a authheader and if we dont have we will continue and wont check  anything 
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);
			// if the header is not present or not start with Bearer then we ignore the
			// request and continue
			return;

		}
		// if the authHeader contains Bearer then we will get the JWTtoken
		String jwtToken = authHeader.substring(7); // as in header it will be like Bearer 'token' so 6 words and 1 space
		// now from the token we have to get userName

		String username = jwtService.getUsernameFromJWTToken(jwtToken);

		// if the user is not null and not logged in , if he is already logged or
		// authenticated no need to authenticate again with JWT tokn
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// validate the user from our userdetails Service as this will check in DB
			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

			if (userDetails != null && jwtService.validateToken(jwtToken)) {

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						username, // user name
						userDetails.getPassword(), // password
						userDetails.getAuthorities());// roles of the user
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//by doing this 
				//we can see the details of the user like his IP , and track user for spam check , or DDOS attack **Details of the client

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}
		filterChain.doFilter(request, response);// one above filter is done then to proceed with the next respective filter

	}

}
