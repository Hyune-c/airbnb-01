package com.codesquad.airbnb.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

  public static List<LocalDate> makeTargetDates(LocalDate checkIn, LocalDate checkOut) {
    List<LocalDate> targetDates = new ArrayList<>();
    for (LocalDate targetDate = checkIn; targetDate.isBefore(checkOut.plusDays(1));
        targetDate = targetDate.plusDays(1)) {
      targetDates.add(targetDate);
    }
    return targetDates;
  }
}
