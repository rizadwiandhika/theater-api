package com.riza.bioskop.repository;

import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.common.error.NotFoundException;

public interface CrudPaginationRepository<T, ID> {
  Entry<Integer, List<T>> findAll(Integer page, Integer size);

  T findById(ID id) throws NotFoundException;

  T insert(T entity);

  T update(T entity) throws NotFoundException;

  void deleteById(ID id) throws NotFoundException;
}
