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

import com.riza.bioskop.controller.dto.request.ReserveTicketRequest;
import com.riza.bioskop.controller.dto.response.BaseResponse;
import com.riza.bioskop.controller.dto.response.DataResponse;
import com.riza.bioskop.controller.dto.response.PaginationResponse;
import com.riza.bioskop.repository.impl.postgres.entity.Theater;
import com.riza.bioskop.repository.impl.postgres.entity.Ticket;
import com.riza.bioskop.service.TheaterService;

@RestController
@RequestMapping("/api/v1/theaters")
public class TheaterController {

  private final TheaterService theaterService;

  public TheaterController(TheaterService theaterService) {
    this.theaterService = theaterService;
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<List<Theater>>> get(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Entry<Integer, List<Theater>> result = theaterService.getTheaters(page, size);
    return ResponseEntity.ok(new PaginationResponse<>("Success", result.getValue(), result.getKey()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponse<Theater>> detail(@PathVariable Integer id) {
    Theater result = theaterService.getDetailTheater(id);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @PostMapping
  public ResponseEntity<DataResponse<Theater>> create(@RequestBody Theater theater) {
    Theater result = theaterService.createNew(theater.getFilm().getId(), theater.getTheaterNumber());
    return ResponseEntity.ok(new DataResponse<>("Success created", result));
  }

  @PutMapping
  public ResponseEntity<DataResponse<Theater>> update(@RequestBody Theater theater) {
    Theater result = theaterService.updateTheater(theater);
    return ResponseEntity.ok(new DataResponse<>("Data updated", result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Integer id) {
    theaterService.deleteTheaterOfId(id);
    return ResponseEntity.ok(new BaseResponse("Successfully removed"));
  }

  @PostMapping("/reserve")
  public ResponseEntity<DataResponse<Ticket>> reserveTicket(@RequestBody ReserveTicketRequest request) {
    Ticket ticket = theaterService.reserveTicket(request.getSeatId(), request.getCustomerId());
    return ResponseEntity.ok(new DataResponse<>("Ticket created", ticket));
  }
}
