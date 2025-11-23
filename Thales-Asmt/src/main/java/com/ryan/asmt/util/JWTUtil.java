package com.ryan.asmt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class JWTUtil {

	public static final String issuer = "Ryan Le";
	public static final String subject = "Access Token";
	public static final String audience = "Thales users";
	
	/**
	 * Create a JWT token
	 * 
	 * @param signingKey secret key to encrypt/decrypt jwt
	 * @param lifeTime	 duration (minutes) before jwt expired
	 * @param claims	 customized claims
	 * @return JWT String
	 */
	public static String generateJWT(String signingKey, int lifeTime, Map<String, Object> claims) {
		Calendar c = Calendar.getInstance();
		Date issuedAt = c.getTime();
		c.add(Calendar.MINUTE, lifeTime);
		Date expiration = c.getTime();
		
		JwtBuilder builder = Jwts.builder();
		builder.setIssuer(issuer);
		builder.setSubject(subject);
		builder.setAudience(audience);
		builder.setExpiration(expiration);
		builder.setNotBefore(issuedAt);
		builder.setIssuedAt(issuedAt);
		builder.setId(UUID.randomUUID().toString());
		if (claims != null && !claims.isEmpty()) {
			claims.forEach((key, value) -> {
				builder.claim(key, value);
			});
		}
		builder.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(signingKey));
		
		String jwt = builder.compact();
		return jwt;
	}
	
	/**
	 * Decrypt a JWT token to get its payload
	 * 
	 * @param jwt		 JWT String
	 * @param signingKey secret key to encrypt/decrypt jwt
	 * @return
	 */
	public static Claims parseJWT(String jwt, String signingKey) {
		Jws<Claims> jws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
		Claims claims = jws.getBody();
		return claims;
	}
	
}
