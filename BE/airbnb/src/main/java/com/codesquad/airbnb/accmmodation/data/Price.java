package com.codesquad.airbnb.accmmodation.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Price {

  @Column(nullable = false)
  private Long price;

  @Builder
  public Price(Long price) {
    this.price = price;
  }
}
