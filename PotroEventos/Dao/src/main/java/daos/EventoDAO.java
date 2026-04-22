package daos;

import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import Entitys.ENUMS.EstadoEvento;
import Entitys.Evento;
import interfaces.IEventoDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación mock de la DAO de Evento.
 *
 * Simula persistencia en memoria utilizando entidades.
 *
 * @author Kaleb
 */
public class EventoDAO implements IEventoDAO {

    private final List<Evento> eventos = new ArrayList<>();

    public EventoDAO() {

        Categoria cat = new Categoria(1L, "img.png", CategoriaEvento.FUTBOL);

        eventos.add(new Evento(
                1L,
                cat,
                "Final Liga MX",
                "Gran final",
                LocalDateTime.now().plusDays(5),
                "Estadio Azteca",
                EstadoEvento.ACTIVO,
                "evento.png"
        ));
    }

    @Override
    public Evento buscarPorId(Long id) {
        return eventos.stream()
                .filter(e -> e.getIdEvento().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Evento> buscarTodos() {
        return eventos;
    }

    @Override
    public Evento guardar(Evento evento) {
        evento.setIdEvento((long) (eventos.size() + 1));
        eventos.add(evento);
        return evento;
    }
}
