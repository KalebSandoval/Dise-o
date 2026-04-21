package negocio;

import Entitys.AsientoEvento;
import Entitys.Evento;
import Entitys.Usuario;
import Entitys.ENUMS.EstadoAsiento;
import Entitys.ENUMS.EstadoEvento;
import adapters.UsuarioAdapter;
import interfaz.IControlOperaciones;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class ControlOperaciones implements IControlOperaciones {

    private List<Evento> eventos;
    private List<Usuario> usuarios;
    private List<AsientoEvento> asientosEvento;

    public ControlOperaciones() {
        this.eventos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.asientosEvento = new ArrayList<>();
    }

    @Override
    public List<Evento> obtenerEventosDisponibles() {

        List<Evento> disponibles = new ArrayList<>();

        for (Evento evento : eventos) {

            if (evento != null
                    && evento.getEstadoEvento() == EstadoEvento.ACTIVO
                    && evento.getFechaHora() != null
                    && evento.getFechaHora().isAfter(LocalDateTime.now())) {

                disponibles.add(evento);
            }
        }

        return disponibles;
    }

    @Override
    public Evento obtenerEvento(Long idEvento) {

        if (idEvento == null) {
            throw new IllegalArgumentException("El id del evento no puede ser nulo");
        }

        for (Evento evento : eventos) {
            if (evento != null
                    && evento.getIdEvento() != null
                    && evento.getIdEvento().equals(idEvento)) {

                return evento;
            }
        }

        return null;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario obtenerUsuario(Long idUsuario) {

        if (idUsuario == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }

        for (Usuario usuario : usuarios) {
            if (usuario != null
                    && usuario.getIdUsuario() != null
                    && usuario.getIdUsuario().equals(idUsuario)) {

                return usuario;
            }
        }

        return null;
    }

    @Override
    public List<AsientoEvento> obtenerAsientosEvento(Long idEvento) {

        if (idEvento == null) {
            throw new IllegalArgumentException("El id del evento no puede ser nulo");
        }

        List<AsientoEvento> lista = new ArrayList<>();

        for (AsientoEvento asientoEvento : asientosEvento) {

            if (asientoEvento != null
                    && asientoEvento.getEvento() != null
                    && asientoEvento.getEvento().getIdEvento() != null
                    && asientoEvento.getEvento().getIdEvento().equals(idEvento)) {

                lista.add(asientoEvento);
            }
        }

        return lista;
    }

    @Override
    public boolean validarDisponibilidad(Long idEvento, Long idAsiento) {

        if (idEvento == null) {
            throw new IllegalArgumentException("El id del evento no puede ser nulo");
        }

        if (idAsiento == null) {
            throw new IllegalArgumentException("El id del asiento no puede ser nulo");
        }

        for (AsientoEvento asientoEvento : asientosEvento) {

            if (asientoEvento != null
                    && asientoEvento.getEvento() != null
                    && asientoEvento.getAsiento() != null
                    && asientoEvento.getEvento().getIdEvento().equals(idEvento)
                    && asientoEvento.getAsiento().getIdAsiento().equals(idAsiento)) {

                return asientoEvento.getEstadoAsiento() == EstadoAsiento.DISPONIBLE;
            }
        }

        return false;
    }

    @Override
    public void agregarEvento(Evento evento) {

        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }

        if (evento.getNombreEvento() == null || evento.getNombreEvento().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }

        eventos.add(evento);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        usuarios.add(usuario);
    }

    @Override
    public void agregarAsientoEvento(AsientoEvento asientoEvento) {

        if (asientoEvento == null) {
            throw new IllegalArgumentException("El asientoEvento no puede ser nulo");
        }

        asientosEvento.add(asientoEvento);
    }
}
