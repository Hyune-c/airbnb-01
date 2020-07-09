package com.codesquad.airbnb.user.web;

import com.codesquad.airbnb.user.business.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "prod")
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
@RestController
public class AuthController {

  private final AuthService authService;

  @ApiOperation(value = "로그인을 요청합니다")
  @GetMapping("/token")
  public ResponseEntity<String> token() {
    HttpHeaders redirectHeader = authService.login();
    return new ResponseEntity<>(redirectHeader, HttpStatus.SEE_OTHER);
  }

  @ApiOperation(value = "Git 의 CallBack Url 로 Client 에서 접근하는 API 가 아닙니다", hidden = true)
  @GetMapping("/callback")
  public ResponseEntity<String> callback(@PathParam("code") String code) {
    return new ResponseEntity<>(authService.callbackHeader(code), HttpStatus.OK);
  }
}
