package com.example.movie_theater.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTGenerator {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    public String generatorToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }
    public String getUsernameFromJWT(String token){
        return getClaimsFromJWT(token).getSubject();
    }

    public List<String> getRolesFromJWT(String token){
        return getClaimsFromJWT(token).get("roles", List.class);
    }
    public Claims getClaimsFromJWT(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Token đã hết hạn", e);
        } catch (MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Token không hợp lệ", e);
        } catch (SignatureException e) {
            throw new AuthenticationCredentialsNotFoundException("Chữ ký không hợp lệ", e);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Lỗi xác thực token", e);
        }
    }
}
