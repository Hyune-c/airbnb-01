package com.codesquad.airbnb.accmmodation.web;

import com.codesquad.airbnb.accmmodation.business.AccommodationService;
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

  @ApiImplicitParam(name = "id", value = "전체 리스트에서 가져오는 숙소 ID", required = true)
  @ApiOperation(value = "특정 숙소의 상세 정보를 가져옵니다")
  @GetMapping("/{id}")
  public DetailAccommodationView detailAccommodation(@PathVariable Long id) {
    return accommodationService.detailAccommodation(id);
  }
}
