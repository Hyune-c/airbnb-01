package com.codesquad.airbnb.booking.business;

import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.accmmodation.data.AccommodationRepository;
import com.codesquad.airbnb.booking.data.Booking;
import com.codesquad.airbnb.booking.data.BookingRepository;
import com.codesquad.airbnb.booking.web.model.BookingCommand;
import com.codesquad.airbnb.booking.web.model.BookingView;
import com.codesquad.airbnb.user.data.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final AccommodationRepository accommodationRepository;

  public BookingView booking(BookingCommand command, User user) {
    Accommodation accommodation = accommodationRepository.findById(command.getAccommodationId())
        .orElseThrow(NoSuchElementException::new);
    accommodation.book(command.getCheckIn(), command.getCheckOut());

    Booking booking = Booking.builder()
        .user(user)
        .accommodation(accommodation)
        .command(command)
        .build();

    Booking savedBooking = bookingRepository.save(booking);

    return BookingView.builder()
        .booking(savedBooking)
        .build();
  }

  public List<BookingView> show(User user) {
    return bookingRepository.findBookingsByUser(user.getEmail()).stream()
        .map(booking -> BookingView.builder()
            .booking(booking)
            .build())
        .collect(Collectors.toList());
  }

  public BookingView cancel(Long bookingId, User loginUser) {
    Booking foundBooking = bookingRepository.findById(bookingId)
        .orElseThrow(NoSuchElementException::new);

    if (!foundBooking.isRightUser(loginUser)) {
      throw new RuntimeException("예약자 정보가 틀립니다");
    }

    foundBooking.cancel();

    Booking canceledBooking;
    canceledBooking = bookingRepository.save(foundBooking);

    return BookingView.builder()
        .booking(canceledBooking)
        .build();
  }
}
