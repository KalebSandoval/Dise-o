package fachada;

import control.ControlCompraBoleto;
import dtos.AsientoDTO;
import dtos.AsientoEventoDTO;
import dtos.BoletoDTO;
import dtos.CategoriaDTO;
import dtos.CobroDTO;
import dtos.EventoDTO;
import dtos.ReservacionDTO;
import dtos.SeccionDTO;
import dtos.TarjetaDTO;
import dtos.UsuarioInstitucionalDTO;
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
    public boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException {
        return controlCompra.agregarReservacion(reservacion);
    }

    @Override
    public List<AsientoDTO> obtenerCatalogoAsientos() throws CompraBoletoException {
        return controlCompra.obtenerCatalogoAsientos();
    }

    @Override
    public String generarCodigoQR(EventoDTO evento, AsientoEventoDTO asiento) throws CompraBoletoException {
        return controlCompra.generarCodigoQR(evento, asiento);
    }

    @Override
    public boolean reservarAsiento(Long idAsientoEvento) throws CompraBoletoException {
        return controlCompra.reservarAsiento(idAsientoEvento);
    }

    @Override
    public boolean liberarAsiento(Long idAsientoEvento) throws CompraBoletoException {
        return controlCompra.liberarAsiento(idAsientoEvento);
    }

    @Override
    public boolean venderAsientos(List<AsientoEventoDTO> asientosSeleccionados, Long totalCompra, boolean gratuito, ReservacionDTO reservacion) throws CompraBoletoException {
        return controlCompra.venderAsientos(asientosSeleccionados, totalCompra, gratuito, reservacion);
    }

    @Override
    public boolean realizarCompra(TarjetaDTO noTarjeta, CobroDTO cobro) throws CompraBoletoException {
        return controlCompra.realizarCompra(noTarjeta, cobro);
    }

    @Override
    public Long getTotalPendiente() {
        return controlCompra.getTotalPendiente();
    }

    @Override
    public Map<SeccionDTO, List<AsientoEventoDTO>> obtenerMapaOcupacion(Long idEvento) throws CompraBoletoException {
        return controlCompra.obtenerMapaOcupacion(idEvento);
    }
    
    @Override
    public boolean validarCredencialesITSON(UsuarioInstitucionalDTO usuario){
        return controlCompra.validarCredencialesITSON(usuario);
    }
}
