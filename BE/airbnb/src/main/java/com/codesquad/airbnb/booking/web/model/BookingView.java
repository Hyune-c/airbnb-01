package com.codesquad.airbnb.booking.web.model;

import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.booking.data.Booking;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookingView {

  private final Long id;
  private final Boolean isCanceled;
  private final LocalDate checkIn;
  private final LocalDate checkOut;
  private final Integer personCount;
  private final Long totalPrice;
  private final Accommodation accommodation;

  @Builder
  public BookingView(Booking booking) {
    this.id = booking.getId();
    this.isCanceled = booking.getIsCanceled();
    this.checkIn = booking.getCheckIn();
    this.checkOut = booking.getCheckOut();
    this.personCount = booking.getPersonCount();
    this.totalPrice = booking.getTotalPrice();
    this.accommodation = booking.getAccommodation();
  }
}
