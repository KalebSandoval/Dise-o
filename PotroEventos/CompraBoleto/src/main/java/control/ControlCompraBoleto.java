package control;

import dtos.*;
import dtos.ENUMS.EstadoAsientoDTO;
import dtos.ENUMS.EstadoEventoDTO;
import Entitys.*;
import dtos.ENUMS.CategoriaEventoDTO;
import objetosNegocio.*;
import excepciones.CompraBoletoException;
import interfaces.IAsientoBO;
import interfaces.IAsientoEventoBO;
import interfaces.ICategoriaBO;
import interfaces.IEventoBO;
import interfaces.IReservacionBO;
import interfaces.ISeccionBO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//import objetosNegocio.EventoBO;

/**
 * Controlador de caso de uso para la compra de boletos. Orquesta la
 * comunicación con los BOs y transforma Entidades a DTOs.
 *
 * * @author Kaleb
 */
public class ControlCompraBoleto {

    // Dependencias a la capa de Negocio (BOs)
    private final IEventoBO eventoBO;
    private final ISeccionBO seccionBO;
    private final IAsientoBO asientoBO;
    private final IAsientoEventoBO asientoEventoBO;
    private final IReservacionBO reservacionBO;
    private final ICategoriaBO categoriaBO;

    public ControlCompraBoleto() {
        this.eventoBO = EventoBO.getInstance();
        this.seccionBO = SeccionBO.getInstance();
        this.asientoBO = AsientoBO.getInstance();
        this.asientoEventoBO = AsientoEventoBO.getInstance();
        this.reservacionBO = ReservacionBO.getInstance();
        this.categoriaBO = CategoriaBO.getInstance();
    }

    /**
     * Obtiene el evento desde el BO y lo convierte a EventoDTO.
     */
    public EventoDTO obtenerInformacionEvento(Long idEvento) throws CompraBoletoException {
        try {
            EventoDTO e = eventoBO.obtenerEventoPorId(idEvento);
            if (e == null) {
                throw new CompraBoletoException("El evento con ID " + idEvento + " no fue encontrado.");
            }
            return e;

            // Conversión de Entidad a DTO usando tu constructor
//            CategoriaDTO catDTO = new CategoriaDTO(e.getCategoriaDTO().getIdCategoria(), e.getCategoriaDTO().getUrlImagen(), CategoriaEventoDTO.valueOf(e.getCategoriaDTO().getNombreCategoria().name()));
//
//            return new EventoDTO(
//                    e.getIdEvento(),
//                    catDTO,
//                    e.getNombreEvento(),
//                    e.getInformacionEvento(),
//                    e.getFechaHora(),
//                    e.getUbicacion(),
//                    EstadoEventoDTO.valueOf(e.getEstadoEvento().name()), // Asumiendo mapeo de Enum
//                    e.getUrlImagen()
//            );

        } catch (Exception ex) {
            throw new CompraBoletoException("Error al obtener la información del evento: " + ex.getMessage());
        }
    }

    /**
     * Obtiene las secciones de un evento y las convierte a SeccionDTO.
     */
    public List<SeccionDTO> obtenerSeccionesEvento(Long idEvento) throws CompraBoletoException {
        try {
            List<SeccionDTO> secciones = seccionBO.consultarSeccionesPorEvento(idEvento);
//            List<SeccionDTO> seccionesDTO = new ArrayList<>();
//
//            for (Seccion s : secciones) {
//                seccionesDTO.add(new SeccionDTO(
//                        s.getIdSeccion(),
//                        s.getNombre(),
//                        s.getCapacidad(),
//                        s.getPrecioBase()
//                ));
//            }
            return secciones;
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar las secciones: " + ex.getMessage());
        }
    }

    /**
     * Obtiene los estados de los asientos (ocupados/disponibles) y los
     * convierte a AsientoEventoDTO.
     */
    public List<AsientoEventoDTO> obtenerOcupacionEvento(Long idEvento) throws CompraBoletoException {
        try {
            List<AsientoEventoDTO> estados = asientoEventoBO.consultarEstadosPorEvento(idEvento);
            return estados;
//            return estados.stream().map(ae -> new AsientoEventoDTO(
//                    ae.getReservacion() != null ? ae.getReservacion().getIdReservacion() : null,
//                    EstadoAsientoDTO.valueOf(ae.getEstado().name()),
//                    ae.getAsiento().getIdAsiento(),
//                    ae.getEvento().getIdEvento()
//            )).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar la ocupación del evento: " + ex.getMessage());
        }
    }

    /**
     * Obtiene el catálogo físico de asientos y lo convierte a AsientoDTO.
     */
    public List<AsientoDTO> obtenerCatalogoAsientos() throws CompraBoletoException {
        try {
            List<AsientoDTO> asientos = asientoBO.consultarTodosLosAsientos();

            return asientos.stream().map(a -> new AsientoDTO(
                    a.getIdAsiento(),
                    a.getFila(),
                    a.getNumero(),
                    a.getIdSeccion()
            )).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar el catálogo de asientos: " + ex.getMessage());
        }
    }
    
    public List<EventoDTO> obtenerEventosCategoria(CategoriaDTO categoria) throws CompraBoletoException {
        try{
            return eventoBO.obtenerEventosPorCategoria(categoria);
        } catch(Exception ex){
            throw new CompraBoletoException("Error al cargar eventos por categoría: " + ex.getMessage());
        }
    }
    
    public boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException {
        try{
            return reservacionBO.agregarReservacion(reservacion);
        } catch(Exception ex){
            throw new CompraBoletoException("Error al agregar la reservación: " + ex.getMessage());
        }
    }
    
    public List<ReservacionDTO> consultarReservacionUsuario(Long idUsuario) throws CompraBoletoException {
        try {
            return reservacionBO.obtenerReservacionesUsuario(idUsuario);
        } catch(Exception ex){
            throw new CompraBoletoException("Error al consultar las reservaciones: " + ex.getMessage());
        }
    }
    
    public List<CategoriaDTO> consultarCategorias() throws CompraBoletoException {
        try{
            return categoriaBO.consultarCategorias();
        } catch(Exception ex){
            throw new CompraBoletoException("Error al consultar las reservaciones: " + ex.getMessage());
        }
    }
}
