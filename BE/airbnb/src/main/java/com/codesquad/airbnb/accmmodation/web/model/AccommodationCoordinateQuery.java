package com.codesquad.airbnb.accmmodation.web.model;

import com.codesquad.airbnb.accmmodation.data.Coordinate;
import java.util.Objects;
import lombok.Getter;

@Getter
public class AccommodationCoordinateQuery {

  private Coordinate leftTop;
  private Coordinate rightBottom;
  private Integer pageCount = 0;

  public AccommodationCoordinateQuery(Double leftTopLatitude, Double leftTopLongitude,
      Double rightBottomLatitude, Double rightBottomLongitude, Integer pageCount) {
    leftTop = Coordinate.builder()
        .latitude(leftTopLatitude)
        .longitude(leftTopLongitude)
        .build();
    rightBottom = Coordinate.builder()
        .latitude(rightBottomLatitude)
        .longitude(rightBottomLongitude)
        .build();
    this.pageCount = (Objects.isNull(pageCount)) ? this.pageCount : pageCount;
  }
}
