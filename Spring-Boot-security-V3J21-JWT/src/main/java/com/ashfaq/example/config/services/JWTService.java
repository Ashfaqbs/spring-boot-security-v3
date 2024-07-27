package com.ashfaq.example.config.services;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private static final String SECRET_KEY = "220A1EC50F1FCF5D547C8A78E0F459120780BB374FC9E1B90FCFCE3F1D14D6381733878836A5414C0A0B1250363144BE9B928F54304BD0D0CEEBE0E966E79779";
	private static final long VALIDITY_OF_TOKEN = TimeUnit.MINUTES.toMillis(30);//30 mins conversion to millis

	public String generateToken(UserDetails userDetails) {

		// claims metadata of the token basically issuer name, site .....
		Map<String, Object> claims = new HashMap<>();
		claims.put("issuer link", "www.mywebsite.com");
		claims.put("issuer name", "Ashfaq");

		// token generation
		return Jwts.builder().
				claims(claims).//issuer of the token details we can pass here as well
				subject(userDetails.getUsername())// subject name will be username
				.issuedAt(Date.from(Instant.now())).
				expiration(Date.from(Instant.now().plusMillis(VALIDITY_OF_TOKEN)))// 30 mins is the expiration of the token
				.signWith(getSecretKey())// provide the secret key
				.compact(); // convert the token into String
		
	}

	// take the secretKey which was in String format and return it as Secret Key format
	private SecretKey getSecretKey() {
		// we will take our Secret key and decode it
		byte[] decode = Base64.getDecoder().decode(SECRET_KEY);
		SecretKey secretKey = Keys.hmacShaKeyFor(decode);
		return secretKey;
	}

	public String getUsernameFromJWTToken(String jwtToken) {
		Claims claims = getClaims(jwtToken);
		return claims.getSubject(); // as subject name will be username , we gave while token generation
	}

	private Claims getClaims(String jwtToken) {
		return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(jwtToken).getPayload();//if we see in the JWT.io we can see the payload there will be details
	}

	
	//Validate Token like is token expired or not
	public boolean validateToken(String jwtToken) {
		Claims claims = getClaims(jwtToken);
		
		
		
		return claims.getExpiration().after(Date.from( Instant.now()));//simple check like the expiration date is after current date so its in future
	}

}
