package com.rafaela.gestor_tickets.repository;

import com.rafaela.gestor_tickets.model.Notificacion;
import com.rafaela.gestor_tickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByUsuarioDestinoOrderByFechaDesc(Usuario usuarioDestino);

    long countByUsuarioDestinoAndLeidaFalse(Usuario usuarioDestino);
}