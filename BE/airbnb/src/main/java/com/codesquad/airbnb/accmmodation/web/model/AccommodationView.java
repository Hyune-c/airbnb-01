package com.codesquad.airbnb.accmmodation.web.model;

import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.accmmodation.data.Coordinate;
import com.codesquad.airbnb.accmmodation.data.Image;
import com.codesquad.airbnb.accmmodation.data.Price;
import com.codesquad.airbnb.accmmodation.data.Review;
import com.codesquad.airbnb.accmmodation.data.type.AccommodationType;
import com.codesquad.airbnb.accmmodation.data.type.ImageType;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccommodationView {

  private final Long id;
  private final String name;
  private final Integer guests;
  private final AccommodationType type;
  private final String location;
  private final Coordinate coordinate;
  private final Price price;
  private final List<Image> images;
  private final float avgStar;

  @Builder
  public AccommodationView(Accommodation accommodation) {
    this.id = accommodation.getId();
    this.name = accommodation.getName();
    this.guests = accommodation.getGuests();
    this.type = accommodation.getType();
    this.location = accommodation.getLocation();
    this.coordinate = accommodation.getCoordinate();
    this.price = accommodation.getPrice();
    this.images = accommodation.getImages(ImageType.MAIN);

    float temp = 0;
    Set<Review> reviews = accommodation.getReviews();
    for (Review review : reviews) {
      temp += review.getStar();
    }
    avgStar = temp / reviews.size();
  }
}
