package com.riza.bioskop.repository;

import com.riza.bioskop.repository.impl.postgres.entity.Film;

public interface FilmRepository extends CrudPaginationRepository<Film, Integer> {

}
