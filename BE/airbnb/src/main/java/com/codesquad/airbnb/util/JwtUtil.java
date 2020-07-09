package com.codesquad.airbnb.util;


import com.codesquad.airbnb.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtUtil {

  private static JwtProperties staticJwtProperties;
  private final JwtProperties jwtProperties;

  public static String create(Map<String, String> claims) {
    Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    Instant expiration = issuedAt
        .plus(staticJwtProperties.getExpiredMinute(), ChronoUnit.MINUTES);
    JwtBuilder jwt = Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setIssuedAt(Date.from(issuedAt))
        .setExpiration(Date.from(expiration));
    for (String key : claims.keySet()) {
      jwt.claim(key, claims.get(key));
    }

    return jwt.signWith(SignatureAlgorithm.HS256, generateKey()).compact();
  }

  private static byte[] generateKey() {
    return staticJwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
  }

  public static String parseEmail(String jwt) {
    Claims claims = Jwts.parser()
        .setSigningKey(generateKey())
        .parseClaimsJws(jwt)
        .getBody();

    return claims.get("email").toString();
  }

  @PostConstruct
  private void initStaticJwtProperties() {
    staticJwtProperties = this.jwtProperties;
  }
}
