package com.codesquad.airbnb.accmmodation.data.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccommodationType {

  HOTEL("HOTEL", "01", "호텔"), MOTEL("MOTEL", "02", "모텔"), BNB("BNB", "03", "민박"), CONDO("CONDO",
      "04", "콘도"), PENSION("PENSION", "05", "민박/팬션"), HOSTEL("HOSTEL", "06", "수련관/유스호스텔"), CAMPING(
      "CAMPING", "07", "캠핑장"), HAPPYHOTEL("HAPPYHOTEL", "08", "행복호텔");

  private final String key;
  private final String code;
  private final String title;
}
