package com.ashfaq.example;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import java.security.KeyPair;
//import java.security.PrivateKey;
//import java.security.PublicKey;

@Slf4j
public class JWTSecretMakerTest {

	@Test
	public void generateSecretKey() {

		SecretKey secretKey = Jwts.SIG.HS512.key().build();// HMAC-SHA-512 Algorithm
		String encodedKey = DatatypeConverter.printHexBinary(secretKey.getEncoded());
		log.info("encodedKey Secret Key : " + encodedKey);
//		2024-07-24T10:20:11.684+05:30  INFO 84340 --- [Spring-Boot-security-V3J21] [           main] com.ashfaq.example.JWTSecretMakerTest   
//		: encodedKey Secret Key : 220A1EC50F1FCF5D547C8A78E0F459120780BB374FC9E1B90FCFCE3F1D14D6381733878836A5414C0A0B1250363144BE9B928F54304BD0D0CEEBE0E966E79779

	}

	// OTHER ALGO approach
//	 HMAC (Symmetric Key Algorithms: HS256, HS384, HS512)
//	For HMAC algorithms, you can use Keys.secretKeyFor(SignatureAlgorithm).

//	 public static void generateHMACKey() {
//	        // Generate a secret key for HS512
//	        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//	        String encodedKey = DatatypeConverter.printHexBinary(secretKey.getEncoded());
//	        System.out.println("HS512 Secret Key: " + encodedKey);
//	    }

//	 RSA (Asymmetric Key Algorithms: RS256, RS384, RS512) For RSA algorithms, you need to generate a key pair (private and public keys).

//	 public static void generateRSAKeyPair() {
//	        // Generate an RSA key pair
//	        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS512);
//	        PrivateKey privateKey = keyPair.getPrivate();
//	        PublicKey publicKey = keyPair.getPublic();
//
//	        String encodedPrivateKey = DatatypeConverter.printHexBinary(privateKey.getEncoded());
//	        String encodedPublicKey = DatatypeConverter.printHexBinary(publicKey.getEncoded());
//
//	        System.out.println("RS512 Private Key: " + encodedPrivateKey);
//	        System.out.println("RS512 Public Key: " + encodedPublicKey);
//	    }

	
	
//	 Elliptic Curve (Asymmetric Key Algorithms: ES256, ES384, ES512) For Elliptic Curve algorithms, you also need to generate a key pair.

//	 public static void generateECKeyPair() {
//	        // Generate an EC key pair
//	        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES512);
//	        PrivateKey privateKey = keyPair.getPrivate();
//	        PublicKey publicKey = keyPair.getPublic();
//
//	        String encodedPrivateKey = DatatypeConverter.printHexBinary(privateKey.getEncoded());
//	        String encodedPublicKey = DatatypeConverter.printHexBinary(publicKey.getEncoded());
//
//	        System.out.println("ES512 Private Key: " + encodedPrivateKey);
//	        System.out.println("ES512 Public Key: " + encodedPublicKey);
//	    }
//	Key Points
//	HMAC Algorithms: Simple secret key generation, suitable for symmetric encryption.
//	RSA and EC Algorithms: Key pair generation, suitable for asymmetric encryption.
//	Usage: The Jwts class can be used for building and signing tokens with any supported algorithm.
}
