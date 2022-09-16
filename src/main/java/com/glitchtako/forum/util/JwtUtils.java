package com.glitchtako.forum.util;

import com.glitchtako.forum.model.dto.UserDetailsDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

  @Value("${AUTH_SECRET}")
  private String jwtSecret;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(user.getUsername())
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(31, ChronoUnit.DAYS)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(jwtSecret)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateJwtToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
