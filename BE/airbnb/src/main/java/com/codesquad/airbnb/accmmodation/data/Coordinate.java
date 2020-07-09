package com.codesquad.airbnb.accmmodation.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Coordinate {

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @Builder
  public Coordinate(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
