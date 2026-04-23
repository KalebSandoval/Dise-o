package interfaces;

import dtos.CategoriaDTO;
import dtos.EventoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define la lógica de negocio para la gestión de eventos.
 *
 * Se encarga de validar reglas antes de interactuar con la DAO.
 *
 * @author Kaleb
 */
public interface IEventoBO {

    EventoDTO guardarEvento(EventoDTO evento) throws NegocioException;

    List<EventoDTO> obtenerEventosPorCategoria(CategoriaDTO categoria);

    EventoDTO obtenerEventoPorId(Long id) throws NegocioException;
}
