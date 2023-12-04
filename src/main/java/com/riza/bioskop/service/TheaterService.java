package com.riza.bioskop.service;

import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.repository.impl.postgres.entity.Theater;
import com.riza.bioskop.repository.impl.postgres.entity.Ticket;

public interface TheaterService {
  Entry<Integer, List<Theater>> getTheaters(Integer page, Integer size);

  Theater getDetailTheater(Integer id);

  Theater createNew(Integer filmId, String theaterNumber);

  Theater updateTheater(Theater theater);

  void deleteTheaterOfId(Integer id);

  Ticket reserveTicket(Integer seatId, Integer customerId);
}
