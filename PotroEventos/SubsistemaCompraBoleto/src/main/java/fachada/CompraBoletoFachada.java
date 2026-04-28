package fachada;

import control.ControlCompraBoleto;
import dtos.AsientoDTO;
import dtos.AsientoEventoDTO;
import dtos.CategoriaDTO;
import dtos.EventoDTO;
import dtos.ReservacionDTO;
import dtos.SeccionDTO;
import excepciones.CompraBoletoException;
import interfaz.ICompraBoleto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kaleb
 */
public class CompraBoletoFachada implements ICompraBoleto {

    private ControlCompraBoleto controlCompra = new ControlCompraBoleto();
    
    @Override
    public EventoDTO obtenerEvento(Long idEvento) throws CompraBoletoException {
        return controlCompra.obtenerInformacionEvento(idEvento);
    }

    @Override
    public List<SeccionDTO> obtenerSeccionesPorEvento(Long idEvento) throws CompraBoletoException {
        return controlCompra.obtenerSeccionesEvento(idEvento);
    }

    @Override
    public List<AsientoDTO> obtenerAsientosPorSeccion(Long idSeccion) throws CompraBoletoException {
        return controlCompra.obtenerCatalogoAsientos(); // ojo xq donde me meto el id? ahorita checas majo
    }

    @Override
    public List<AsientoEventoDTO> obtenerEstadoAsientosPorEvento(Long idEvento) throws CompraBoletoException {
        return controlCompra.obtenerOcupacionEvento(idEvento);
    }

    @Override
    public List<EventoDTO> obtenerEventosCategoria(CategoriaDTO categoria) throws CompraBoletoException {
        return controlCompra.obtenerEventosCategoria(categoria);
    }

    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException {
        return controlCompra.agregarReservacion(reservacion);
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesUsuario(Long idUsuario) throws CompraBoletoException {
        return controlCompra.consultarReservacionUsuario(idUsuario);
    }

    @Override
    public List<CategoriaDTO> obtenerCategorias() throws CompraBoletoException {
        return controlCompra.consultarCategorias();
    }

    @Override
    public List<AsientoDTO> obtenerCatalogoAsientos() throws CompraBoletoException {
        return controlCompra.obtenerCatalogoAsientos();
    }

}
