package com.codesquad.airbnb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiredMinute}")
  private Long expiredMinute;
}
