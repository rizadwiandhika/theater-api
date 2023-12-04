package com.riza.bioskop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.riza.bioskop.common.constant.RatingEnum;
import com.riza.bioskop.common.error.RestrictedAgeException;
import com.riza.bioskop.common.error.TheaterFullyBookedException;
import com.riza.bioskop.repository.CustomerRepository;
import com.riza.bioskop.repository.FilmRepository;
import com.riza.bioskop.repository.SeatRepository;
import com.riza.bioskop.repository.TheaterRepository;
import com.riza.bioskop.repository.TicketRepository;
import com.riza.bioskop.repository.impl.postgres.entity.Customer;
import com.riza.bioskop.repository.impl.postgres.entity.Film;
import com.riza.bioskop.repository.impl.postgres.entity.Seat;
import com.riza.bioskop.repository.impl.postgres.entity.Theater;
import com.riza.bioskop.repository.impl.postgres.entity.Ticket;
import com.riza.bioskop.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService {
  private final TicketRepository ticketRepository;
  private final TheaterRepository theaterRepository;
  private final FilmRepository filmRepository;
  private final CustomerRepository customerRepository;
  private final SeatRepository seatRepository;
  private final Map<RatingEnum, Integer> minimumRatingAge;

  public TheaterServiceImpl(TheaterRepository theaterRepository, FilmRepository filmRepository,
      CustomerRepository customerRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
    this.theaterRepository = theaterRepository;
    this.filmRepository = filmRepository;
    this.customerRepository = customerRepository;
    this.seatRepository = seatRepository;
    this.ticketRepository = ticketRepository;

    minimumRatingAge = new HashMap<>();
    minimumRatingAge.put(RatingEnum.A, 0);
    minimumRatingAge.put(RatingEnum.BO, 13);
    minimumRatingAge.put(RatingEnum.R, 18);
    minimumRatingAge.put(RatingEnum.D, 21);
  }

  @Override
  public Theater createNew(Integer filmId, String theaterNumber) {
    Film film = filmRepository.findById(filmId);
    return theaterRepository.insert(new Theater(theaterNumber, film));
  }

  @Override
  public void deleteTheaterOfId(Integer id) {
    theaterRepository.deleteById(id);
  }

  @Override
  public Theater getDetailTheater(Integer id) {
    return theaterRepository.findById(id);
  }

  @Override
  public Entry<Integer, List<Theater>> getTheaters(Integer page, Integer size) {
    return theaterRepository.findAll(page, size);
  }

  @Override
  public Theater updateTheater(Theater theater) {
    return theaterRepository.update(theater);
  }

  @Override
  @Transactional
  public Ticket reserveTicket(Integer seatId, Integer customerId) {
    Seat seat = seatRepository.findById(seatId);
    Theater theater = seat.getTheater();
    Customer customer = customerRepository.findById(customerId);

    // Check customer age before continuing the purchase
    if (customer.getAge() < minimumRatingAge.get(theater.getFilm().getRating().getCode())) {
      throw RestrictedAgeException.because("You cannot watch this movie because of your age");
    }

    // Check seat availability
    if (theater.getStock() < 1) {
      throw TheaterFullyBookedException.because("Theater is fully booked");
    }

    theater.setStock(theater.getStock() - 1);
    theaterRepository.update(theater);

    // Create ticket
    return ticketRepository.insert(new Ticket(seat, customer));
  }
}
