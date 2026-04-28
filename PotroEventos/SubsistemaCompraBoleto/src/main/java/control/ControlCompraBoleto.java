package control;

import dtos.*;
import objetosNegocio.*;
import excepciones.CompraBoletoException;
import interfaces.IAsientoBO;
import interfaces.IAsientoEventoBO;
import interfaces.ICategoriaBO;
import interfaces.IEventoBO;
import interfaces.IReservacionBO;
import interfaces.ISeccionBO;
import interfaz.IControlCompraBoleto;
import java.util.List;
import java.util.stream.Collectors;
//import objetosNegocio.EventoBO;

/**
 * Controlador de caso de uso para la compra de boletos. Orquesta la
 * comunicación con los BOs y transforma Entidades a DTOs.
 *
 * * @author Kaleb
 */
public class ControlCompraBoleto implements IControlCompraBoleto {

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
    @Override
    public EventoDTO obtenerInformacionEvento(Long idEvento) throws CompraBoletoException {
        try {
            EventoDTO e = eventoBO.obtenerEventoPorId(idEvento);
            if (e == null) {
                throw new CompraBoletoException("El evento con ID " + idEvento + " no fue encontrado.");
            }
            return e;

        } catch (Exception ex) {
            throw new CompraBoletoException("Error al obtener la información del evento: " + ex.getMessage());
        }
    }

    /**
     * Obtiene las secciones de un evento y las convierte a SeccionDTO.
     */
    @Override
    public List<SeccionDTO> obtenerSeccionesEvento(Long idEvento) throws CompraBoletoException {
        try {
            List<SeccionDTO> secciones = seccionBO.consultarSeccionesPorEvento(idEvento);
            return secciones;
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar las secciones: " + ex.getMessage());
        }
    }

    /**
     * Obtiene los estados de los asientos (ocupados/disponibles) y los
     * convierte a AsientoEventoDTO.
     */
    @Override
    public List<AsientoEventoDTO> obtenerOcupacionEvento(Long idEvento) throws CompraBoletoException {
        try {
            List<AsientoEventoDTO> estados = asientoEventoBO.consultarEstadosPorEvento(idEvento);
            return estados;

        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar la ocupación del evento: " + ex.getMessage());
        }
    }

    /**
     * Obtiene el catálogo físico de asientos y lo convierte a AsientoDTO.
     */
    @Override
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

    @Override
    public List<EventoDTO> obtenerEventosCategoria(CategoriaDTO categoria) throws CompraBoletoException {
        try {
            return eventoBO.obtenerEventosPorCategoria(categoria);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar eventos por categoría: " + ex.getMessage());
        }
    }

    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException {
        try {
            return reservacionBO.agregarReservacion(reservacion);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al agregar la reservación: " + ex.getMessage());
        }
    }

    @Override
    public List<ReservacionDTO> consultarReservacionUsuario(Long idUsuario) throws CompraBoletoException {
        try {
            return reservacionBO.obtenerReservacionesUsuario(idUsuario);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al consultar las reservaciones: " + ex.getMessage());
        }
    }

    @Override
    public List<CategoriaDTO> consultarCategorias() throws CompraBoletoException {
        try {
            return categoriaBO.consultarCategorias();
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al consultar las reservaciones: " + ex.getMessage());
        }
    }
}
