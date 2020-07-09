package com.codesquad.airbnb.booking.web;

import com.codesquad.airbnb.booking.business.BookingService;
import com.codesquad.airbnb.booking.web.model.BookingCommand;
import com.codesquad.airbnb.booking.web.model.BookingView;
import com.codesquad.airbnb.user.data.User;
import com.codesquad.airbnb.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "prod")
@RequiredArgsConstructor
@RequestMapping("/api/booking")
@RestController
public class BookingController {

  private final BookingService bookingService;

  @ApiOperation(value = "숙소를 예약합니다", notes = "Authorization 이 없으면 anonymous@gmail.com 으로 강제됩니다")
  @PostMapping
  public BookingView booking(@RequestBody @Valid BookingCommand command,
      HttpServletRequest htpHttpServletRequest) {
    User user = findUser(htpHttpServletRequest.getHeader("Authorization"));
    return bookingService.booking(command, user);
  }

  @ApiOperation(value = "예약된 숙소 정보를 가져옵니다", notes = "Authorization 이 없으면 anonymous@gmail.com 으로 강제됩니다")
  @GetMapping
  public List<BookingView> show(HttpServletRequest htpHttpServletRequest) {
    User user = findUser(htpHttpServletRequest.getHeader("Authorization"));
    return bookingService.show(user);
  }

  @ApiOperation(value = "특정 예약을 취소합니다", notes = "Authorization 이 없으면 anonymous@gmail.com 으로 강제됩니다")
  @PutMapping("/{bookingId}")
  public BookingView cancel(@PathVariable Long bookingId,
      HttpServletRequest htpHttpServletRequest) {
    User user = findUser(htpHttpServletRequest.getHeader("Authorization"));
    return bookingService.cancel(bookingId, user);
  }

  private User findUser(String jwt) {
    return (Objects.isNull(jwt))
        ? User.builder()
        .email("anonymous@gmail.com")
        .build()
        : User.builder()
            .email(JwtUtil.parseEmail(jwt))
            .build();
  }
}
