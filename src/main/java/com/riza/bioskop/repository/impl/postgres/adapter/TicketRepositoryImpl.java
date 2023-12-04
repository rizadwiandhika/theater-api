package com.riza.bioskop.repository.impl.postgres.adapter;

import org.springframework.stereotype.Repository;

import com.riza.bioskop.repository.TicketRepository;
import com.riza.bioskop.repository.impl.postgres.driver.TicketJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Ticket;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
  private final TicketJpa ticketJpa;

  public TicketRepositoryImpl(TicketJpa ticketJpa) {
    this.ticketJpa = ticketJpa;
  }

  @Override
  public Ticket insert(Ticket ticket) {
    ticket.setId(null);
    return ticketJpa.save(ticket);
  }

}
