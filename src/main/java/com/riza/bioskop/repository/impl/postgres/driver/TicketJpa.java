package com.riza.bioskop.repository.impl.postgres.driver;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riza.bioskop.repository.impl.postgres.entity.Ticket;

public interface TicketJpa extends JpaRepository<Ticket, Integer> {

}
