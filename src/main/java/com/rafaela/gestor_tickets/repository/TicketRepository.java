package com.rafaela.gestor_tickets.repository;

import com.rafaela.gestor_tickets.model.Estado;
import com.rafaela.gestor_tickets.model.Ticket;
import com.rafaela.gestor_tickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUsuarioCreador(Usuario usuarioCreador);

    List<Ticket> findByAdminAsignadoIsNull();

    List<Ticket> findByEstado(Estado estado);
}