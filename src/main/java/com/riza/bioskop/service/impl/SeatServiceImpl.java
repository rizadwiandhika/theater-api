package com.riza.bioskop.service.impl;

import java.util.List;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.riza.bioskop.repository.SeatRepository;
import com.riza.bioskop.repository.TheaterRepository;
import com.riza.bioskop.repository.impl.postgres.entity.Seat;
import com.riza.bioskop.repository.impl.postgres.entity.Theater;
import com.riza.bioskop.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

  private final SeatRepository seatRepository;
  private final TheaterRepository theaterRepository;

  public SeatServiceImpl(SeatRepository seatRepository, TheaterRepository theaterRepository) {
    this.seatRepository = seatRepository;
    this.theaterRepository = theaterRepository;
  }

  @Override
  @Transactional
  public Seat createNew(Integer theaterId, String seatNumber) {
    Theater theater = theaterRepository.findById(theaterId);
    theater.setStock(theater.getStock() + 1);
    theaterRepository.update(theater);
    return seatRepository.insert(new Seat(seatNumber, theater));
  }

  @Override
  public void deleteSeatOfId(Integer id) {
    seatRepository.deleteById(id);
  }

  @Override
  public Seat getDetailSeat(Integer id) {
    return seatRepository.findById(id);
  }

  @Override
  public Entry<Integer, List<Seat>> getSeats(Integer page, Integer size) {
    return seatRepository.findAll(page, size);
  }

  @Override
  public Seat updateSeat(Seat seat) {
    return seatRepository.update(seat);
  }

}
