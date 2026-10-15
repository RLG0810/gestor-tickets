package com.rafaela.gestor_tickets.config;

import com.rafaela.gestor_tickets.model.*;
import com.rafaela.gestor_tickets.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatosPrueba implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final TicketRepository ticketRepository;
    private final ComentarioRepository comentarioRepository;
    private final NotificacionRepository notificacionRepository;

    public DatosPrueba(UsuarioRepository usuarioRepository,
                       TicketRepository ticketRepository,
                       ComentarioRepository comentarioRepository,
                       NotificacionRepository notificacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ticketRepository = ticketRepository;
        this.comentarioRepository = comentarioRepository;
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            return; // ya hay datos, no duplicar
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Usuario ana = new Usuario();
        ana.setNombre("Ana");
        ana.setEmail("ana@empresa.com");
        ana.setPassword(encoder.encode("1234"));
        ana.setRol(Rol.USUARIO);
        usuarioRepository.save(ana);

        Usuario carlos = new Usuario();
        carlos.setNombre("Carlos");
        carlos.setEmail("carlos@empresa.com");
        carlos.setPassword(encoder.encode("1234"));
        carlos.setRol(Rol.ADMIN);
        usuarioRepository.save(carlos);

        Ticket t1 = new Ticket();
        t1.setTitulo("No funciona la impresora");
        t1.setDescripcion("La impresora de la planta 2 no responde desde esta mañana.");
        t1.setFechaCreacion(LocalDateTime.now());
        t1.setEstado(Estado.ABIERTO);
        t1.setPrioridad(Prioridad.MEDIA);
        t1.setUsuarioCreador(ana);
        ticketRepository.save(t1);

        Ticket t2 = new Ticket();
        t2.setTitulo("El portátil no enciende");
        t2.setDescripcion("Pantalla negra al pulsar el botón de encendido.");
        t2.setFechaCreacion(LocalDateTime.now());
        t2.setEstado(Estado.EN_PROGRESO);
        t2.setPrioridad(Prioridad.ALTA);
        t2.setUsuarioCreador(ana);
        t2.setAdminAsignado(carlos);
        ticketRepository.save(t2);

        Ticket t3 = new Ticket();
        t3.setTitulo("Acceso a la carpeta compartida");
        t3.setDescripcion("No puedo abrir la carpeta de Contabilidad.");
        t3.setFechaCreacion(LocalDateTime.now());
        t3.setEstado(Estado.ABIERTO);
        t3.setPrioridad(Prioridad.BAJA);
        t3.setUsuarioCreador(ana);
        ticketRepository.save(t3);

        Comentario c1 = new Comentario();
        c1.setTicket(t2);
        c1.setAutor(carlos);
        c1.setMensaje("Me lo llevo yo. ¿Puedes probar con otro cargador?");
        c1.setFecha(LocalDateTime.now());
        comentarioRepository.save(c1);

        Notificacion n1 = new Notificacion();
        n1.setUsuarioDestino(ana);
        n1.setTicketRelacionado(t2);
        n1.setMensaje("Carlos ha comentado en tu ticket \"El portátil no enciende\"");
        n1.setFecha(LocalDateTime.now());
        n1.setLeida(false);
        notificacionRepository.save(n1);
    }
}
