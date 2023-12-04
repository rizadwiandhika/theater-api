package com.riza.bioskop.repository.impl.postgres.driver;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riza.bioskop.repository.impl.postgres.entity.Rating;

public interface RatingJpa extends JpaRepository<Rating, Integer> {

}
