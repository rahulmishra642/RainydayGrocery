package com.rainyday.grocery.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import com.rainyday.grocery.exception.JwtTokenMalformedException;
import com.rainyday.grocery.exception.JwtTokenMissingException;
import com.rainyday.grocery.models.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public UserInfo getUser(final String token) {

        try {

            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            UserInfo user = new UserInfo();
            user.setUserName(body.getSubject());
            Set<String> roles = Arrays.asList(body.get("roles").toString().split(",")).stream().map(String::new)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
            return user;

        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;

    }


    public String generateToken(UserInfo userInfo) {

        Claims claims = Jwts.claims().setSubject(userInfo.getUserName());
        claims.put("roles", userInfo.getRoles());
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }


    public void validateToken(final String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}
