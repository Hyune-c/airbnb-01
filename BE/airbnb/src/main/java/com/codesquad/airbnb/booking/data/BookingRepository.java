package com.codesquad.airbnb.booking.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findBookingsByUser(String userEmail);
}
