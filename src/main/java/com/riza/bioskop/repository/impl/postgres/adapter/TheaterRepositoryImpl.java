package com.riza.bioskop.repository.impl.postgres.adapter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.repository.TheaterRepository;
import com.riza.bioskop.repository.impl.postgres.driver.TheaterJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Theater;

@Repository
public class TheaterRepositoryImpl implements TheaterRepository {

  private static final String THEATER_NOT_FOUND_ID = "Theater not found for ID: ";
  private final TheaterJpa theaterJpa;

  public TheaterRepositoryImpl(TheaterJpa theaterJpa) {
    this.theaterJpa = theaterJpa;
  }

  @Override
  public Theater insert(Theater entity) {
    entity.setId(null);
    return theaterJpa.save(entity);
  }

  @Override
  public void deleteById(Integer id) throws NotFoundException {
    theaterJpa.findById(id).orElseThrow(() -> NotFoundException.because(THEATER_NOT_FOUND_ID + id));
    theaterJpa.deleteById(id);
  }

  @Override
  public Entry<Integer, List<Theater>> findAll(Integer page, Integer size) {
    Page<Theater> result = theaterJpa.findAll(PageRequest.of(page, size));
    return new SimpleEntry<>(result.getTotalPages(), result.getContent());
  }

  @Override
  public Theater findById(Integer id) throws NotFoundException {
    return theaterJpa.findById(id).orElseThrow(() -> NotFoundException.because(THEATER_NOT_FOUND_ID + id));
  }

  @Override
  public Theater update(Theater entity) throws NotFoundException {
    Integer id = entity.getId();
    theaterJpa.findById(id).orElseThrow(() -> NotFoundException.because(THEATER_NOT_FOUND_ID + id));
    return theaterJpa.save(entity);
  }

}
