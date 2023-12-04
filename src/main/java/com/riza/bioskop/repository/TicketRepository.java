package com.riza.bioskop.repository;

import com.riza.bioskop.repository.impl.postgres.entity.Ticket;

public interface TicketRepository {
  Ticket insert(Ticket ticket);
}
