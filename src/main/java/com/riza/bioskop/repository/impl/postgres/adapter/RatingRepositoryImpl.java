package com.riza.bioskop.repository.impl.postgres.adapter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.repository.RatingRepository;
import com.riza.bioskop.repository.impl.postgres.driver.RatingJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Rating;

@Repository
public class RatingRepositoryImpl implements RatingRepository {
  private static final String RATING_NOT_FOUD_ID = "Rating not foud for ID: ";
  private final RatingJpa ratingJpa;

  public RatingRepositoryImpl(RatingJpa ratingJpa) {
    this.ratingJpa = ratingJpa;
  }

  @Override
  public Rating insert(Rating entity) {
    entity.setId(null);
    return ratingJpa.save(entity);
  }

  @Override
  public void deleteById(Integer id) throws NotFoundException {
    ratingJpa.findById(id).orElseThrow(() -> NotFoundException.because(RATING_NOT_FOUD_ID + id));
    ratingJpa.deleteById(id);
  }

  @Override
  public Entry<Integer, List<Rating>> findAll(Integer page, Integer size) {
    Page<Rating> result = ratingJpa.findAll(PageRequest.of(page, size));
    return new SimpleEntry<>(result.getTotalPages(), result.getContent());
  }

  @Override
  public Rating findById(Integer id) throws NotFoundException {
    return ratingJpa.findById(id).orElseThrow(() -> NotFoundException.because(RATING_NOT_FOUD_ID + id));
  }

  @Override
  public Rating update(Rating entity) throws NotFoundException {
    Integer id = entity.getId();
    ratingJpa.findById(id).orElseThrow(() -> NotFoundException.because(RATING_NOT_FOUD_ID + id));
    return ratingJpa.save(entity);
  }

}
