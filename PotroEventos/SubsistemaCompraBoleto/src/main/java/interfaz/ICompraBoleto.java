package interfaz;

import dtos.EventoDTO;
import dtos.SeccionDTO;
import dtos.AsientoEventoDTO;
import dtos.AsientoDTO;
import dtos.CategoriaDTO;
import dtos.ReservacionDTO;
import excepciones.CompraBoletoException;
import java.util.List;

/**
 * Interfaz del subsistema (Fachada) para la Compra de Boletos. Permite a
 * cualquier módulo o interfaz gráfica obtener la información necesaria.
 *
 * * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public interface ICompraBoleto {

    /**
     * 1. Carga la información general del evento (Título, fecha, imagen,
     * ubicación).
     *
     * @param idEvento
     * @return
     * @throws excepciones.CompraBoletoException
     */
    EventoDTO obtenerEvento(Long idEvento) throws CompraBoletoException;

    /**
     * 2. Obtiene las secciones de un evento específico (Para saber los precios:
     * ej. $1800).
     *
     * @param idEvento
     * @return
     * @throws excepciones.CompraBoletoException
     */
    List<SeccionDTO> obtenerSeccionesPorEvento(Long idEvento) throws CompraBoletoException;

    /**
     * 3. Obtiene el catálogo físico de los asientos de una sección (Para saber
     * su Fila y Número).
     *
     * @param idSeccion
     * @return
     * @throws excepciones.CompraBoletoException
     */
    List<AsientoDTO> obtenerAsientosPorSeccion(Long idSeccion) throws CompraBoletoException;

    /**
     * 4. Obtiene el estado dinámico de los asientos (Para saber si el asiento
     * está OCUPADO o DISPONIBLE).
     *
     * @param idEvento
     * @return
     * @throws excepciones.CompraBoletoException
     */
    List<AsientoEventoDTO> obtenerEstadoAsientosPorEvento(Long idEvento) throws CompraBoletoException;

    /*
    esto se va al subsistema d gestion eventos
    */
    List<EventoDTO> obtenerEventosCategoria(CategoriaDTO categoria) throws CompraBoletoException;

    boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException;

    /*
    esto debería estar en el subsistema de usuarios (porque de reservas no hay)
    */
    List<ReservacionDTO> obtenerReservacionesUsuario(Long idUsuario) throws CompraBoletoException;

    /*
    debería irse a eventos?
    */
    List<CategoriaDTO> obtenerCategorias() throws CompraBoletoException;

    List<AsientoDTO> obtenerCatalogoAsientos() throws CompraBoletoException;

}
