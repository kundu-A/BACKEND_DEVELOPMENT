package com.arpankundu.journalApp.services;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
    @Autowired
    private UserRepo userRepository;

private String secretKey="";
	
	public JwtService(){
		try {
			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=keyGen.generateKey();
			secretKey=Base64.getUrlEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}	
	}

	//Generate Access token
	public String generateToken(String username) {
		Map<String, Object> claims=new HashMap<>();
		
		return Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1*60*60*1000))
				.and()
				.signWith(getKey())
				.compact();
	}

	//Validate Access Token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	//Generate Refresh Token
	public String generateRefreshToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))// 7 days expiration
				.signWith(getKey())
				.compact();
	}

	//Validate Refresh Token
	public boolean validateRefreshToken(String token) {
		return !isTokenExpired(token);
	}

	private SecretKey getKey() {
		byte keyBytes[]=Base64.getUrlDecoder().decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		try {
			return extractClaim(token, Claims::getSubject);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return  null;
		}
	}

	private<T> T extractClaim(String token,Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
					.verifyWith(getKey())
				.build().parseSignedClaims(token).getPayload();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	private Date extractIssueTime(String token) {
		return extractClaim(token, Claims::getIssuedAt);
	}
	public boolean isRefreshTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}
