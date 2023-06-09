package com.lihao.bookstore.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class TokenUtils {
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";
    private final String AUTHORITIES = "AUTH";

    @Value("${auth.expiration}")
    private Long TOKEN_VALIDITY = 604800L;

    @Value("${auth.secret}")
    private String TOKEN_SECRET;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        claims.put(AUTHORITIES, userDetails.getAuthorities());

        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
    private Claims getClaims(String token){
        Claims claims;

        try{
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            claims = null;
        }
        return claims;
    }

    private boolean isTokenExpired(String token){
        Date expirationDate = getClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }
}
