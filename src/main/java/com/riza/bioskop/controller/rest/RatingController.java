package com.riza.bioskop.controller.rest;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riza.bioskop.controller.dto.response.BaseResponse;
import com.riza.bioskop.controller.dto.response.DataResponse;
import com.riza.bioskop.controller.dto.response.PaginationResponse;
import com.riza.bioskop.repository.impl.postgres.entity.Rating;
import com.riza.bioskop.service.RatingService;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
  private final RatingService ratingService;

  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<List<Rating>>> get(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Entry<Integer, List<Rating>> result = ratingService.getRatings(page, size);
    return ResponseEntity.ok(new PaginationResponse<>("Success", result.getValue(), result.getKey()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponse<Rating>> detail(@PathVariable Integer id) {
    Rating result = ratingService.getDetailRating(id);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @PostMapping
  public ResponseEntity<DataResponse<Rating>> create(@RequestBody Rating rating) {
    Rating result = ratingService.createNew(rating.getCode(), rating.getDescription());
    return ResponseEntity.ok(new DataResponse<>("Success created", result));
  }

  @PutMapping
  public ResponseEntity<DataResponse<Rating>> update(@RequestBody Rating rating) {
    Rating result = ratingService.updateRating(rating);
    return ResponseEntity.ok(new DataResponse<>("Data updated", result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Integer id) {
    ratingService.deleteRatingOfId(id);
    return ResponseEntity.ok(new BaseResponse("Successfully removed"));
  }
}
