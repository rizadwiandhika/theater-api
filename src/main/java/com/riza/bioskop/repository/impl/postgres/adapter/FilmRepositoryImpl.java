package com.riza.bioskop.repository.impl.postgres.adapter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.repository.FilmRepository;
import com.riza.bioskop.repository.impl.postgres.driver.FilmJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Film;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

  private static final String FILM_NOT_FOUND_ID = "Film not found for ID: ";
  private final FilmJpa filmJpa;

  public FilmRepositoryImpl(FilmJpa filmJpa) {
    this.filmJpa = filmJpa;
  }

  @Override
  public Film insert(Film entity) {
    entity.setId(null);
    return filmJpa.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    if (!filmJpa.findById(id).isPresent()) {
      throw NotFoundException.because(FILM_NOT_FOUND_ID + id);
    }
    filmJpa.deleteById(id);
  }

  @Override
  public Entry<Integer, List<Film>> findAll(Integer page, Integer size) {
    Page<Film> result = filmJpa.findAll(PageRequest.of(page, size));
    return new SimpleEntry<>(result.getTotalPages(), result.getContent());
  }

  @Override
  public Film findById(Integer id) {
    return filmJpa.findById(id).orElseThrow(() -> NotFoundException.because(FILM_NOT_FOUND_ID + id));

  }

  @Override
  public Film update(Film entity) throws NotFoundException {
    Integer id = entity.getId();
    filmJpa.findById(id).orElseThrow(() -> NotFoundException.because(FILM_NOT_FOUND_ID + id));
    return filmJpa.save(entity);
  }

}
