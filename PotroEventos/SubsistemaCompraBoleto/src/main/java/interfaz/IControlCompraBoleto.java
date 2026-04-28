package interfaz;

import dtos.AsientoDTO;
import dtos.AsientoEventoDTO;
import dtos.CategoriaDTO;
import dtos.EventoDTO;
import dtos.ReservacionDTO;
import dtos.SeccionDTO;
import excepciones.CompraBoletoException;
import java.util.List;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public interface IControlCompraBoleto {

    /**
     * Obtiene la información detallada de un evento por su ID.
     */
    EventoDTO obtenerInformacionEvento(Long idEvento) throws CompraBoletoException;

    /**
     * Obtiene la lista de secciones asociadas a un evento.
     */
    List<SeccionDTO> obtenerSeccionesEvento(Long idEvento) throws CompraBoletoException;

    /**
     * Obtiene el estado de ocupación de los asientos para un evento específico.
     */
    List<AsientoEventoDTO> obtenerOcupacionEvento(Long idEvento) throws CompraBoletoException;

    /**
     * Obtiene el catálogo completo de asientos físicos registrados en el
     * sistema.
     */
    List<AsientoDTO> obtenerCatalogoAsientos() throws CompraBoletoException;

    /**
     * Filtra y obtiene eventos según una categoría específica.
     */
    List<EventoDTO> obtenerEventosCategoria(CategoriaDTO categoria) throws CompraBoletoException;

    /**
     * Registra una nueva reservación en el sistema.
     */
    boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException;

    /**
     * Consulta el historial de reservaciones de un usuario.
     */
    List<ReservacionDTO> consultarReservacionUsuario(Long idUsuario) throws CompraBoletoException;

    /**
     * Obtiene el catálogo de categorías de eventos disponibles.
     */
    List<CategoriaDTO> consultarCategorias() throws CompraBoletoException;
}
