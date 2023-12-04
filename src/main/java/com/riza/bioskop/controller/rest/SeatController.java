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
import com.riza.bioskop.repository.impl.postgres.entity.Seat;
import com.riza.bioskop.service.SeatService;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {
  private final SeatService seatService;

  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<List<Seat>>> get(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Entry<Integer, List<Seat>> result = seatService.getSeats(page, size);
    return ResponseEntity.ok(new PaginationResponse<>("Success", result.getValue(), result.getKey()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponse<Seat>> detail(@PathVariable Integer id) {
    Seat result = seatService.getDetailSeat(id);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @PostMapping
  public ResponseEntity<DataResponse<Seat>> create(@RequestBody Seat seat) {
    Seat result = seatService.createNew(seat.getTheater().getId(), seat.getSeatNumber());
    return ResponseEntity.ok(new DataResponse<>("Success created", result));
  }

  @PutMapping
  public ResponseEntity<DataResponse<Seat>> update(@RequestBody Seat seat) {
    Seat result = seatService.updateSeat(seat);
    return ResponseEntity.ok(new DataResponse<>("Data updated", result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Integer id) {
    seatService.deleteSeatOfId(id);
    return ResponseEntity.ok(new BaseResponse("Successfully removed"));
  }

}
