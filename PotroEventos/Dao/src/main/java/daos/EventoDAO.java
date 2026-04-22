package daos;

import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import Entitys.ENUMS.EstadoEvento;
import Entitys.Evento;
import interfaces.IEventoDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación mock de la DAO de Evento. Simula persistencia en memoria
 * utilizando una lista.
 *
 * * @author Kaleb
 */
public class EventoDAO implements IEventoDAO {

    // Lista estática para que los datos se mantengan entre diferentes instancias del DAO
    private static List<Evento> eventos = new ArrayList<>();

    // Bloque estático para inicializar con unos cuantos registros de prueba
    static {
        eventos.add(new Evento(1L, new Categoria(1L, CategoriaEvento.FUTBOL, ""), "Itson vs Itesca", "Potritos vs pichones", LocalDateTime.now(), "Cancha de futbol rápido ITSON", EstadoEvento.ACTIVO, ""));
        eventos.add(new Evento(2L, new Categoria(2L, CategoriaEvento.ARTE, ""), "Exposición de arte", "Exposiciones de pinturas y dibujos del alumnado ITSON", LocalDateTime.now(), "Potropasillo", EstadoEvento.ACTIVO, ""));
        eventos.add(new Evento(3L, new Categoria(3L, CategoriaEvento.BASQUETBOL, ""), "Itson vs Unison", "Potritos vs buhos", LocalDateTime.now(), "Arena ITSON", EstadoEvento.ACTIVO, ""));
    }

    @Override
    public Evento buscarPorId(Long id) {
        for (Evento e : eventos) {
            if (e.getIdEvento().equals(id)) {
                return e;
            }
        }
        return null; // Si no lo encuentra
    }

    @Override
    public List<Evento> buscarTodos() {
        return new ArrayList<>(eventos); // Retornamos una copia para proteger la lista original
    }

    @Override
    public Evento guardar(Evento evento) {
        // Si el evento ya existe (por ID), lo removemos para "actualizarlo"
        if (evento.getIdEvento() != null) {
            eventos.removeIf(e -> e.getIdEvento().equals(evento.getIdEvento()));
        } else {
            // Si es nuevo, le asignamos un ID básico basado en el tamaño
            evento.setIdEvento((long) (eventos.size() + 1));
        }

        eventos.add(evento);
        return evento;
    }
}
