package objetosNegocio;

import Entitys.Evento;
//import adapters.EventoAdapter;
import daos.EventoDAO;
import dtos.EventoDTO;
import excepciones.NegocioException;
import interfaces.IEventoBO;
import interfaces.IEventoDAO;
import java.util.List;

/**
 * Objeto de negocio para Evento. Implementa patrón Singleton.
 */
/*public class EventoBO implements IEventoBO {
    
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
    public EventoDTO guardarEvento(EventoDTO evento) throws NegocioException {
        if(!validarDatos(evento)){
            throw new NegocioException("Evento inválido.");
        }
        return EventoAdapter.entidadADTO(eventoDAO.guardar(EventoAdapter.dtoAEntidad(evento)));
    }
    
    @Override
    public List<EventoDTO> obtenerEventos() {
        return EventoAdapter.listaDTOs(eventoDAO.buscarTodos());
    }
    
    @Override
    public EventoDTO obtenerEventoPorId(Long id) throws NegocioException {
        return EventoAdapter.entidadADTO(eventoDAO.buscarPorId(id));
    }
    
    public boolean validarDatos(EventoDTO eventoDTO) {
        // 1. Validar que el objeto no sea nulo
        if (eventoDTO == null) {
            return false;
        }

        // 2. Validar Nombre (No nulo, no vacío y aplicar trim/lowercase)
        if (eventoDTO.getNombreEvento() == null || eventoDTO.getNombreEvento().isBlank()) {
            return false;
        }
        // Formateo simple
        eventoDTO.setNombreEvento(eventoDTO.getNombreEvento().trim().toLowerCase());

        // 3. Validar Ubicación
        if (eventoDTO.getUbicacion() == null || eventoDTO.getUbicacion().isBlank()) {
            return false;
        }
        eventoDTO.setUbicacion(eventoDTO.getUbicacion().trim().toLowerCase());

        // 4. Validar Categoría y Estado (Enums)
        if (eventoDTO.getCategoriaEvento() == null || eventoDTO.getEstadoEvento() == null) {
            return false;
        }

        // 5. Validar Fecha (Que no sea nula)
        if (eventoDTO.getFechaHora() == null) {
            return false;
        }

        // 6. Formatear campos opcionales (Información)
        if (eventoDTO.getInformacionEvento() != null) {
            eventoDTO.setInformacionEvento(eventoDTO.getInformacionEvento().trim().toLowerCase());
        }
        
        return true; // Si pasó todos los filtros
    }
}*/
