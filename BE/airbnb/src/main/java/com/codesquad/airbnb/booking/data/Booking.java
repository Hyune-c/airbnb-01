package com.codesquad.airbnb.booking.data;

import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.booking.web.model.BookingCommand;
import com.codesquad.airbnb.user.data.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Boolean isCanceled;

  @Column
  private String user;

  @Column
  private LocalDate checkIn;

  @Column
  private LocalDate checkOut;

  @Column
  private Integer personCount;

  @Column
  private Long totalPrice;

  @OneToOne
  @JoinColumn(name = "accommodation_id")
  private Accommodation accommodation;

  @Builder
  public Booking(User user, Accommodation accommodation, BookingCommand command) {
    this.isCanceled = false;
    this.user = user.getEmail();
    this.checkIn = command.getCheckIn();
    this.checkOut = command.getCheckOut();
    this.personCount = command.getPersonCount();
    this.accommodation = accommodation;
    this.totalPrice = calculateTotalPrice(accommodation, command);
  }

  private long calculateTotalPrice(Accommodation accommodation, BookingCommand command) {
    long days = ChronoUnit.DAYS.between(checkIn, checkOut);
    return command.getPersonCount() * days * accommodation.getPrice().getPrice();
  }

  public boolean isRightUser(User loginUser) {
    return user.equals(loginUser.getEmail());
  }

  public void cancel() {
    accommodation.cancel(checkIn, checkOut);
    isCanceled = true;
  }
}
