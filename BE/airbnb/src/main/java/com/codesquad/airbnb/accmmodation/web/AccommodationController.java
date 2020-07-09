package com.codesquad.airbnb.accmmodation.web;

import com.codesquad.airbnb.accmmodation.business.AccommodationService;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationCoordinateQuery;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationQuery;
import com.codesquad.airbnb.accmmodation.web.model.AccommodationView;
import com.codesquad.airbnb.accmmodation.web.model.DetailAccommodationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "prod")
@RequiredArgsConstructor
@RequestMapping("/api/accommodations")
@RestController
public class AccommodationController {

  private final AccommodationService accommodationService;

  @ApiImplicitParam(name = "location", value = "지역명", required = true)
  @ApiOperation(value = "검색 조건에 맞는 숙소 리스트를 가져옵니다")
  @GetMapping("/s/{location}")
  public List<AccommodationView> accommodations(AccommodationQuery query) {
    return accommodationService.accommodation(query);
  }

  @ApiOperation(value = "좌표 조건에 맞는 숙소 리스트를 가져옵니다", notes = "http://localhost:8080/api/accommodations/s?leftTopLatitude=34&leftTopLongitude=126.5&rightBottomLatitude=34.65&rightBottomLongitude=127")
  @GetMapping("/s")
  public List<AccommodationView> accommodationsCoordinate(AccommodationCoordinateQuery query) {
    return accommodationService.accommodationsCoordinate(query);
  }

  @ApiImplicitParam(name = "location", value = "지역명", required = true)
  @ApiOperation(value = "검색 조건에 맞는 숙소의 가격 정보만 가져옵니다")
  @GetMapping("/p/{location}")
  public List<Long> accommodationsPrice(AccommodationQuery query) {
    return accommodationService.accommodationsPrice(query);
  }

  @ApiImplicitParam(name = "id", value = "전체 리스트에서 가져오는 숙소 ID", required = true)
  @ApiOperation(value = "특정 숙소의 상세 정보를 가져옵니다")
  @GetMapping("/{id}")
  public DetailAccommodationView detailAccommodation(@PathVariable Long id) {
    return accommodationService.detailAccommodation(id);
  }
}
