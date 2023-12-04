package com.riza.bioskop.repository.impl.postgres.adapter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.repository.SeatRepository;
import com.riza.bioskop.repository.impl.postgres.driver.SeatJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Seat;

@Repository
public class SeatRepositoryImpl implements SeatRepository {
  private static final String SEAT_NOT_FOUND_ID = "Seat not found for ID: ";
  private final SeatJpa seatJpa;

  public SeatRepositoryImpl(SeatJpa seatJpa) {
    this.seatJpa = seatJpa;
  }

  @Override
  public Seat insert(Seat entity) {
    entity.setId(null);
    return seatJpa.save(entity);
  }

  @Override
  public void deleteById(Integer id) throws NotFoundException {
    seatJpa.findById(id).orElseThrow(() -> NotFoundException.because(SEAT_NOT_FOUND_ID + id));
    seatJpa.deleteById(id);
  }

  @Override
  public Entry<Integer, List<Seat>> findAll(Integer page, Integer size) {
    Page<Seat> result = seatJpa.findAll(PageRequest.of(page, size));
    return new SimpleEntry<>(result.getTotalPages(), result.getContent());
  }

  @Override
  public Seat findById(Integer id) throws NotFoundException {
    return seatJpa.findById(id).orElseThrow(() -> NotFoundException.because(SEAT_NOT_FOUND_ID + id));
  }

  @Override
  public Seat update(Seat entity) throws NotFoundException {
    Integer id = entity.getId();
    seatJpa.findById(id).orElseThrow(() -> NotFoundException.because(SEAT_NOT_FOUND_ID + id));
    return seatJpa.save(entity);
  }

}
