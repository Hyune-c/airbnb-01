package com.codesquad.airbnb.user.business;

import com.codesquad.airbnb.config.AuthProperties;
import com.codesquad.airbnb.util.JwtUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

  private final AuthProperties authProperties;

  public HttpHeaders login() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

    UriComponents builder = UriComponentsBuilder
        .fromHttpUrl(authProperties.getAuthorizeUrl())
        .queryParam("client_id", authProperties.getClientId())
        .queryParam("scope", "user")
        .build(false);

    headers.setLocation(builder.toUri());

    return headers;
  }

  public MultiValueMap<String, String> callbackHeader(String authorizationCode) {
    String accessToken = requestAccessToken(authorizationCode);
    List<LinkedHashMap<String, String>> emails = requestEmails(accessToken);

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    Map<String, String> header = new HashMap<>();
    header.put("Authorization", JwtUtil.create(emails.get(0)));
    headers.setAll(header);

    return headers;
  }

  private String requestAccessToken(String authorizationCode) {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    Map<String, String> header = new HashMap<>();
    header.put("Accept", "application/json");
    headers.setAll(header);

    MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
    Map<String, String> requestPayload = new HashMap<>();
    requestPayload.put("client_id", authProperties.getClientId());
    requestPayload.put("client_secret", authProperties.getClientSecret());
    requestPayload.put("code", authorizationCode);
    requestPayloads.setAll(requestPayload);

    HttpEntity<MultiValueMap> request = new HttpEntity<>(requestPayloads, headers);
    ResponseEntity<HashMap> response = new RestTemplate()
        .postForEntity(authProperties.getAccessTokenUrl(), request, HashMap.class);

    Map<String, String> map = response.getBody();

    return Optional.of(map.get("access_token"))
        .orElseThrow(() -> new RuntimeException("JWT TOKEN 생성에 실패하였습니다"));
  }

  private List requestEmails(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
        new MediaType("application", "json", StandardCharsets.UTF_8));
    headers.add("Authorization", "bearer " + accessToken);

    ResponseEntity<List> response = new RestTemplate().exchange(
        authProperties.getUserEmailUrl(), HttpMethod.GET, new HttpEntity<String>(headers),
        List.class);

    return response.getBody();
  }
}
