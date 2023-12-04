package com.riza.bioskop.service;

import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.repository.impl.postgres.entity.Seat;

public interface SeatService {

  Entry<Integer, List<Seat>> getSeats(Integer page, Integer size);

  Seat getDetailSeat(Integer id);

  Seat createNew(Integer theaterId, String seatNumber);

  Seat updateSeat(Seat seat);

  void deleteSeatOfId(Integer id);

}
