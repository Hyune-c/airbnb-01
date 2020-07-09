package com.codesquad.airbnb.booking.web.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
@Getter
public class BookingCommand {

  @ApiModelProperty(value = "숙소 ID", required = true, example = "2")
  @NotNull
  private final Long accommodationId;

  @ApiModelProperty(value = "체크 인 일자", required = true, example = "2020-06-17")
  @FutureOrPresent
  private final LocalDate checkIn;

  @ApiModelProperty(value = "체크 아웃 일자", required = true, example = "2020-06-20")
  @Future
  private final LocalDate checkOut;

  @ApiModelProperty(value = "투숙객 수", required = true, example = "2")
  @Range(min = 1, max = 9)
  private final Integer personCount;
}
