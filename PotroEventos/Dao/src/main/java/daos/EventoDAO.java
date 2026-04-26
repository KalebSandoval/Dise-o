package daos;

import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import Entitys.ENUMS.EstadoEvento;
import Entitys.ENUMS.TipoEventoP;
import Entitys.ENUMS.TipoUbicacionP;
import Entitys.Evento;
import Entitys.Ubicacion;
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
        eventos.add(new Evento(1L, new Categoria(1L, CategoriaEvento.FUTBOL, ""), "Itson vs Itesca", "Potritos vs Pichones", LocalDateTime.now(), new Ubicacion(1L, "Estadio MAYE", 500, TipoUbicacionP.AIRELIBRE), EstadoEvento.ACTIVO, "/imgCate/futbol.png", false, TipoEventoP.ITSON, 500));
        eventos.add(new Evento(2L, new Categoria(2L, CategoriaEvento.ARTE, ""), "Exposición de arte", "Exposiciones de pinturas y dibujos del alumnado ITSON", LocalDateTime.now(), new Ubicacion(2L, "Potro Pasillo", 200, TipoUbicacionP.AIRELIBRE), EstadoEvento.ACTIVO, "", true, TipoEventoP.PÚBLICO, 200));
        eventos.add(new Evento(3L, new Categoria(3L, CategoriaEvento.BASQUETBOL, ""), "Itson vs Unison", "Potritos vs Búhos", LocalDateTime.now(), new Ubicacion(3L, "Arena ITSON", 3000, TipoUbicacionP.CERRADA), EstadoEvento.ACTIVO, "", false, TipoEventoP.PÚBLICO, 3000));
        eventos.add(new Evento(3L, new Categoria(3L, CategoriaEvento.NATACION, ""), "Wicked: En el Agua Mataron al Elphaba", "La icónica historia que ha arrazado en Broadway llega hasta ITSON pero con un giro inesperado: Está ambientada bajo el agua! Podrá nuestra bruja malvada y verde salvar el Reino de OZ antes de ahogarse o que se le caiga el maquillaje? (o ambas!). Puesta en escena organizada por estudiantes ITSON y para la comunidad POTROS!", LocalDateTime.now(), new Ubicacion(4L, "Teatro ITSON", 1000, TipoUbicacionP.CERRADA), EstadoEvento.ACTIVO, "", true, TipoEventoP.ITSON, 1000));     
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
    public List<Evento> buscarTodosCategoria(Categoria categoria) {
        List<Evento> lista = new ArrayList<>(); 
        for(Evento e : eventos){
            if(e.getCategoriaEvento().getNombre() == categoria.getNombre() && e.getEstadoEvento() == EstadoEvento.ACTIVO && e.getDisponibilidad() != 0){
                lista.add(e);
            }
        }
        return lista;
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
