package com.riza.bioskop.service.impl;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.riza.bioskop.common.constant.RatingEnum;
import com.riza.bioskop.repository.RatingRepository;
import com.riza.bioskop.repository.impl.postgres.entity.Rating;
import com.riza.bioskop.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  public RatingServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public Rating createNew(RatingEnum code, String description) {
    return ratingRepository.insert(new Rating(code, description));
  }

  @Override
  public void deleteRatingOfId(Integer id) {
    ratingRepository.deleteById(id);

  }

  @Override
  public Rating getDetailRating(Integer id) {
    return ratingRepository.findById(id);
  }

  @Override
  public Entry<Integer, List<Rating>> getRatings(Integer page, Integer size) {
    return ratingRepository.findAll(page, size);
  }

  @Override
  public Rating updateRating(Rating rating) {
    return ratingRepository.update(rating);
  }

}
