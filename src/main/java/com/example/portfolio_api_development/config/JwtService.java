package com.example.portfolio_api_development.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final static String SECRET_KEY = "QmFzZTY0IGVuY29kaW5nIHNjaGVtZXMgYXJlIGNvbW1vbmx5IHVzZWQgd2hlbiB0aGVyZSBpcyBhIG5lZWQgdG8gZW5jb2RlIGJpbmFyeSBkYXRhLCBlc3BlY2lhbGx5IHdoZW4gdGhhdCBkYXRhIG5lZWRzIHRvIGJlIHN0b3JlZCBhbmQgdHJhbnNmZXJyZWQgb3ZlciBtZWRpYSB0aGF0IGFyZSBkZXNpZ25lZCB0byBkZWFsIHdpdGggdGV4dC4gVGhpcyBlbmNvZGluZyBoZWxwcyB0byBlbnN1cmUgdGhhdCB0aGUgZGF0YSByZW1haW5zIGludGFjdCB3aXRob3V0IG1vZGlmaWNhdGlvbiBkdXJpbmcgdHJhbnNwb3J0LiBCYXNlNjQgaXMgdXNlZCBjb21tb25seSBpbiBhIG51bWJlciBvZiBhcHBsaWNhdGlvbnMgaW5jbHVkaW5nIGVtYWlsIHZpYSBNSU1FLCBhcyB3ZWxsIGFzIHN0b3JpbmcgY29tcGxleCBkYXRh";
    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            logger.error("Failed to extract username from token: {}", e.getMessage());
            throw e;
        }
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        try {
            final String username = extractUsername(token);
            boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
            logger.info("Token validation result for username {}: {}", username, isValid);
            return isValid;
        } catch (Exception e) {
            logger.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (Exception e) {
            logger.error("Failed to check token expiration: {}", e.getMessage());
            throw e;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Failed to extract claims from token: {}", e.getMessage());
            throw e;
        }
    }
    private Key getSigninKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            logger.error("Failed to generate signing key: {}", e.getMessage());
            throw e;
        }
    }
}
