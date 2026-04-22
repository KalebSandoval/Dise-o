package objetosNegocio;

import Entitys.Evento;
import daos.EventoDAO;
import dtos.EventoDTO;
import excepciones.NegocioException;
import interfaces.IEventoBO;
import interfaces.IEventoDAO;
import java.util.List;

/**
 * Objeto de negocio para Evento. Implementa patrón Singleton.
 */
public class EventoBO implements IEventoBO {

    private static EventoBO instancia;
    private final IEventoDAO eventoDAO;

    private EventoBO() {
        this.eventoDAO = new EventoDAO();
    }

    public static EventoBO getInstance() {
        if (instancia == null) {
            instancia = new EventoBO();
        }
        return instancia;
    }

    @Override
    public EventoDTO crearEvento(EventoDTO evento) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EventoDTO> obtenerEventos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EventoDTO obtenerEventoPorId(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEvento(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
