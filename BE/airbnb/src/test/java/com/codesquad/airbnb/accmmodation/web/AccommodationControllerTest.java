package com.codesquad.airbnb.accmmodation.web;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codesquad.airbnb.accmmodation.business.AccommodationService;
import com.codesquad.airbnb.accmmodation.data.Accommodation;
import com.codesquad.airbnb.accmmodation.data.Coordinate;
import com.codesquad.airbnb.accmmodation.data.Price;
import com.codesquad.airbnb.accmmodation.data.type.AccommodationType;
import com.codesquad.airbnb.accmmodation.web.model.DetailAccommodationView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AccommodationController.class)
class AccommodationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccommodationService accommodationService;

  @Test
  public void 디테일_숙소_조회_응답_포맷_테스트() throws Exception {
    // given
    Long id = 1l;
    DetailAccommodationView dto = DetailAccommodationView.builder()
        .accommodation(Accommodation.builder()
            .id(id)
            .name("오토 캠핑장")
            .type(AccommodationType.CAMPING)
            .location("새덕굴길")
            .coordinate(Coordinate.builder().longitude(35.31).latitude(23.14).build())
            .price(Price.builder().price(34500L).build())
            .build())
        .build();

    // when
    when(accommodationService.detailAccommodation(id))
        .thenReturn(dto);

    // then
    mockMvc.perform(get("/api/accommodations/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.name", is(dto.getName())))
        .andExpect(jsonPath("$.location", is(dto.getLocation())));
  }
}
