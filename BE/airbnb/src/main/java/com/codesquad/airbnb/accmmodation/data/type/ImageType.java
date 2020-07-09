package com.codesquad.airbnb.accmmodation.data.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageType {

  MAIN("MAIN", "메인"), DETAIL("DETAIL", "디테일"), BATH("BATH", "화장실");

  private final String key;
  private final String title;
}
