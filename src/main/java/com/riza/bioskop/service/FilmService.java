package com.riza.bioskop.service;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.repository.impl.postgres.entity.Film;

public interface FilmService {

  Entry<Integer, List<Film>> getFilms(Integer page, Integer size);

  Film getDetailFilm(Integer id);

  Film createNew(Integer ratingId, String title, Integer duration, Date showDate, Integer price);

  Film updateFilm(Film film);

  void removeFilmOfId(Integer id);
}
