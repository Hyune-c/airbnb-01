package com.codesquad.airbnb.accmmodation.data;

import com.codesquad.airbnb.accmmodation.data.type.ImageType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Image {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ImageType type;

  @Column(nullable = false)
  private String url;

  @Builder
  public Image(ImageType type, String url) {
    this.type = type;
    this.url = url;
  }
}
