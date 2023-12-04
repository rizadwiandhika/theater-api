package com.riza.bioskop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.riza.bioskop.repository.FilmRepository;
import com.riza.bioskop.repository.RatingRepository;
import com.riza.bioskop.repository.impl.postgres.entity.Film;
import com.riza.bioskop.repository.impl.postgres.entity.Rating;
import com.riza.bioskop.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {
  private final FilmRepository filmRepository;
  private final RatingRepository ratingRepository;

  public FilmServiceImpl(FilmRepository filmRepository, RatingRepository ratingRepository) {
    this.filmRepository = filmRepository;
    this.ratingRepository = ratingRepository;
  }

  @Override
  public Film createNew(Integer ratingId, String title, Integer duration, Date showDate, Integer price) {
    Rating rating = ratingRepository.findById(ratingId);
    return filmRepository.insert(new Film(title, duration, showDate, price, rating));
  }

  @Override
  public Film getDetailFilm(Integer id) {
    return filmRepository.findById(id);
  }

  @Override
  public Entry<Integer, List<Film>> getFilms(Integer page, Integer size) {
    return filmRepository.findAll(page, size);
  }

  @Override
  public void removeFilmOfId(Integer id) {
    filmRepository.deleteById(id);

  }

  @Override
  public Film updateFilm(Film film) {
    return filmRepository.update(film);
  }

}
