package Controlador.interfaz;

import dtos.AsientoDTO;
import dtos.AsientoEventoDTO;
import dtos.BoletoDTO;
import dtos.CategoriaDTO;
import dtos.CobroDTO;
import dtos.EventoDTO;
import dtos.ReservacionDTO;
import dtos.SeccionDTO;
import dtos.TarjetaDTO;
import dtos.UsuarioDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public interface ICoordinadorAplicacion {

    public void iniciaSistema();

    public void mostrarInicioSesion();

    public void mostrarRegistro();

    public void mostrarInicio();

    public void mostrarConsultar();

    public void mostrarInfoEvento(EventoDTO evento);

    public void mostrarDetalles(ReservacionDTO reservacion);

    public void finalizarCompra();

    public void mostrarEventos(CategoriaDTO categoria);
    
    public void mostrarPago(ReservacionDTO reservacion);
    
    public void volverAEventos();

    public void mostrarConsultarEvento();

    public void volverAConsultar();
    
    public void volverAConsultarEvento();

    public List<EventoDTO> consultarEventos(CategoriaDTO categoria);

    public List<CategoriaDTO> consultarCategorias();

    public List<ReservacionDTO> consultarReservaciones(Long idUsuario);

    public Map<SeccionDTO, List<AsientoEventoDTO>> obtenerMapaOcupacion(Long idEvento);

    public List<AsientoDTO> obtenerCatalogoAsientos();

    public boolean agregarReservacion(ReservacionDTO reservacion);

    public UsuarioDTO iniciarSesion(String correo, String contrasenia);

    public void setUsuarioSesion(UsuarioDTO usuario);

    public UsuarioDTO getUsuarioSesion();

    public void cerrarSesion();

    boolean reservarAsiento(Long idAsientoEvento);

    boolean liberarAsiento(Long idAsientoEvento);

    public boolean venderAsientos(List<AsientoEventoDTO> asientosSeleccionados, Long totalCompra, boolean gratuito, ReservacionDTO reservacion);

    boolean realizarCompra(TarjetaDTO noTarjeta, CobroDTO cobro);
    
    Long getTotalPendiente();
    
    String generarQR(EventoDTO evento, AsientoEventoDTO asiento);
}
