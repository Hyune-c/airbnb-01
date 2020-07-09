package com.codesquad.airbnb.accmmodation.business;

import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.accmmodation.data.AccommodationRepository;
import com.codesquad.airbnb.accmmodation.data.Price;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationCoordinateQuery;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationQuery;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationView;
import com.codesquad.airbnb.accmmodation.web.model.DetailAccommodationView;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccommodationService {

  private final AccommodationRepository accommodationRepository;
  private final int PAGE_ROW_COUNT = 10;

  public List<AccommodationView> accommodation(AccommodationQuery query) {
    Price minPrice = Price.builder().price(query.getPriceMin()).build();
    Price maxPrice = Price.builder().price(query.getPriceMax()).build();
    PageRequest requestPage = PageRequest.of(query.getPageCount(), PAGE_ROW_COUNT);

    Page<Accommodation> accommodations =
        accommodationRepository.findByLocationContainingAndPriceBetweenAndGuestsGreaterThanEqual(
            query.getLocation(), minPrice, maxPrice, query.getGuests(), requestPage);

    return accommodations.stream().filter(accommodation ->
        accommodation.isPossibleBookedDates(query.getCheckIn(), query.getCheckOut()))
        .map(AccommodationView::new)
        .collect(Collectors.toList());
  }

  public List<Long> accommodationsPrice(AccommodationQuery query) {
    Price minPrice = Price.builder().price(query.getPriceMin()).build();
    Price maxPrice = Price.builder().price(query.getPriceMax()).build();
    PageRequest requestPage = PageRequest.of(query.getPageCount(), Integer.MAX_VALUE);

    Page<Accommodation> accommodations =
        accommodationRepository.findByLocationContainingAndPriceBetweenAndGuestsGreaterThanEqual(
            query.getLocation(), minPrice, maxPrice, query.getGuests(), requestPage);

    return accommodations.stream().filter(accommodation ->
        accommodation.isPossibleBookedDates(query.getCheckIn(), query.getCheckOut()))
        .map(accommodation -> accommodation.getPrice().getPrice())
        .collect(Collectors.toList());
  }

  public List<AccommodationView> accommodationsCoordinate(AccommodationCoordinateQuery query) {
    PageRequest requestPage = PageRequest.of(query.getPageCount(), Integer.MAX_VALUE);
    Page<Accommodation> accommodations = accommodationRepository
        .findByCoordinate_LatitudeBetweenAndCoordinate_LongitudeBetween(
            query.getRightBottom().getLatitude(), query.getLeftTop().getLatitude(),
            query.getLeftTop().getLongitude(), query.getRightBottom().getLongitude(),
            requestPage);

    return accommodations.stream().map(AccommodationView::new).collect(Collectors.toList());
  }

  public DetailAccommodationView detailAccommodation(Long id) {
    Accommodation accommodation = accommodationRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);
    return new DetailAccommodationView(accommodation);
  }
}
