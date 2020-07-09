package com.codesquad.airbnb.accmmodation.web.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

@Getter
public class AccommodationQuery {

  private String location;

  @ApiModelProperty(value = "체크 인 일자", example = "2020-06-17")
  @FutureOrPresent
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkIn = LocalDate.of(2020, 1, 1);

  @ApiModelProperty(value = "체크 아웃 일자", example = "2020-06-20")
  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkOut = LocalDate.of(2020, 12, 31);

  private Long priceMin = 0L;
  private Long priceMax = 1000000L;
  private Integer guests = 4;
  private Integer pageCount = 0;

  public AccommodationQuery(String location, LocalDate checkIn, LocalDate checkOut, Long priceMin,
      Long priceMax, Integer guests, Integer pageCount) {
    if (ObjectUtils.isEmpty(location.trim())) {
      throw new RuntimeException("지명은 검색 필수 조건 입니다");
    }

    this.location = location;
    this.checkIn = (Objects.isNull(checkIn)) ? this.checkIn : checkIn;
    this.checkOut = (Objects.isNull(checkOut)) ? this.checkOut : checkOut;
    this.priceMin = (Objects.isNull(priceMin)) ? this.priceMin : priceMin;
    this.priceMax = (Objects.isNull(priceMax)) ? this.priceMax : priceMax;
    this.guests = (Objects.isNull(guests)) ? this.guests : guests;
    this.pageCount = (Objects.isNull(pageCount)) ? this.pageCount : pageCount;
  }
}
