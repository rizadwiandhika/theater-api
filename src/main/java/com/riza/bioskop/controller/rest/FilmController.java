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

import com.riza.bioskop.controller.dto.response.BaseResponse;
import com.riza.bioskop.controller.dto.response.DataResponse;
import com.riza.bioskop.controller.dto.response.PaginationResponse;
import com.riza.bioskop.repository.impl.postgres.entity.Film;
import com.riza.bioskop.service.FilmService;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {
  private final FilmService filmService;

  public FilmController(FilmService filmService) {
    this.filmService = filmService;
  }

  @PostMapping
  public ResponseEntity<DataResponse<Film>> create(@RequestBody Film film) {
    Film result = filmService.createNew(film.getRating().getId(), film.getTitle(), film.getDuration(),
        film.getShowDate(), film.getPrice());

    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<List<Film>>> getFilms(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Entry<Integer, List<Film>> result = filmService.getFilms(page, size);

    return ResponseEntity.ok(new PaginationResponse<>("Success", result.getValue(), result.getKey()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponse<Film>> getDetailFilm(
      @PathVariable Integer id) {
    Film result = filmService.getDetailFilm(id);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @PutMapping
  public ResponseEntity<DataResponse<Film>> update(@RequestBody Film film) {
    Film result = filmService.updateFilm(film);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(
      @PathVariable Integer id) {
    filmService.removeFilmOfId(id);
    return ResponseEntity.ok(new BaseResponse("Data deleted"));
  }
}
