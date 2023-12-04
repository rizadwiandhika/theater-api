package com.riza.bioskop.service;

import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.common.constant.RatingEnum;
import com.riza.bioskop.repository.impl.postgres.entity.Rating;

public interface RatingService {

  Entry<Integer, List<Rating>> getRatings(Integer page, Integer size);

  Rating getDetailRating(Integer id);

  Rating createNew(RatingEnum code, String description);

  Rating updateRating(Rating rating);

  void deleteRatingOfId(Integer id);

}
