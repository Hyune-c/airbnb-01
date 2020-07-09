package com.codesquad.airbnb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AuthProperties {

  @Value("${auth.clientId}")
  private String clientId;

  @Value("${auth.clientSecret}")
  private String clientSecret;

  @Value("${url.authorize}")
  private String authorizeUrl;

  @Value("${url.accessToken}")
  private String accessTokenUrl;

  @Value("${url.userEmail}")
  private String userEmailUrl;
}
