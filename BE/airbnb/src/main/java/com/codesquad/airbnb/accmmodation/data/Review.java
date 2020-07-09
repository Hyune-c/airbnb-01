package com.codesquad.airbnb.accmmodation.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Embeddable
public class Review {

  private Integer star;

  private String content;

  private String authorEmail;

  private String url;

  @CreatedDate
  private LocalDate localDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Review review = (Review) o;
    return Objects.equals(star, review.star) &&
        Objects.equals(content, review.content) &&
        Objects.equals(authorEmail, review.authorEmail) &&
        Objects.equals(url, review.url) &&
        Objects.equals(localDate, review.localDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(star, content, authorEmail, url, localDate);
  }
}
