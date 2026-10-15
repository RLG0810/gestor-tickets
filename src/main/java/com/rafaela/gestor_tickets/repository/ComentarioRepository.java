package com.rafaela.gestor_tickets.repository;

import com.rafaela.gestor_tickets.model.Comentario;
import com.rafaela.gestor_tickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTicketOrderByFechaAsc(Ticket ticket);
}