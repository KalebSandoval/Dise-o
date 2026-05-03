package Controlador.coordinador;

import Controlador.interfaz.ICoordinadorAplicacion;
import Pantallas.FrmInicioSesion;
import Pantallas.FrmPago;
import Pantallas.FrmPlantillaSistema;
import Pantallas.FrmRegistrarse;
import Pantallas.FrmDetallesCompra;
import Pantallas.FrmRegistroItson;
import Pantallas.vistas.PnlConsultar;
import Pantallas.vistas.PnlConsultarEvento;
import Pantallas.vistas.PnlEventos;
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
import dtos.UsuarioInstitucionalDTO;
import fachada.InicioSesionFachada;
import interfaces.IFachadaInicioSesion;
import excepciones.CompraBoletoException;
import excepciones.GestionEventoException;
import excepciones.GestionUsuarioException;
import excepciones.NegocioException;
import excepciones.PagoException;
import fachada.CompraBoletoFachada;
import fachada.GestionEventoFachada;
import fachada.GestionUsuarioFachada;
import fachada.PagoFachada;
import interfaces.IFachadaGestionEvento;
import interfaces.IGestionUsuariosFachada;
import interfaces.IReservacionBO;
import interfaz.ICompraBoleto;
import interfaz.IPago;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import objetosNegocio.AsientoBO;
import objetosNegocio.AsientoEventoBO;
import objetosNegocio.ReservacionBO;
import objetosNegocio.SeccionBO;

/**
 * Clase que actúa como coordinador principal de la aplicación. Se encarga de
 * gestionar la navegación entre las diferentes pantallas y de comunicar la capa
 * de presentación (vistas) con la capa de negocio (fachadas y BOs).
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class CoordinadorAplicacion implements ICoordinadorAplicacion {

    private IFachadaInicioSesion logi = InicioSesionFachada.getInstance();
    private ICompraBoleto controlCompra = new CompraBoletoFachada();
    private IFachadaGestionEvento controlEvento = new GestionEventoFachada();
    private IGestionUsuariosFachada controlUsuarios = new GestionUsuarioFachada();
    private SeccionBO seccionBO = SeccionBO.getInstance();
    private AsientoEventoBO asientoEventoBO = AsientoEventoBO.getInstance();

    private UsuarioDTO usuario;
    private UsuarioInstitucionalDTO usuarioITSON;
    private FrmInicioSesion frmInicioSesion;
    private FrmRegistrarse frmRegistrarse;
    private FrmPago frmPago;
    private FrmPlantillaSistema frmPlantilla;
    private FrmDetallesCompra frmDetalles;
    private FrmRegistroItson frmRegistro;
    private ReservacionDTO reservacionActual;

    /**
     * Oculta todas las ventanas instanciadas actualmente en el sistema. Es
     * utilizado como un paso previo antes de mostrar una nueva pantalla para
     * asegurar que no queden ventanas superpuestas.
     */
    private void ocultarTodo() {
        if (frmInicioSesion != null) {
            frmInicioSesion.setVisible(false);
        }
        if (frmRegistrarse != null) {
            frmRegistrarse.setVisible(false);
        }
        if (frmPago != null) {
            frmPago.setVisible(false);
        }
        if (frmDetalles != null) {
            frmDetalles.setVisible(false);
        }
        if (frmRegistro != null) {
            frmRegistro.setVisible(false);
        }
        if(frmPlantilla != null){
            frmPlantilla.setVisible(false);
        }
    }

    public UsuarioDTO getUsuario() {
        if (usuario == null) {
            return null;
        }
        return usuario;
    }
    
    @Override
    public void setUsuarioITSON(UsuarioInstitucionalDTO usuario){
        this.usuarioITSON = usuario;
    }
    
    @Override
    public UsuarioInstitucionalDTO getUsuarioITSON(){
        if(usuarioITSON == null){
            return null;
        }
        return usuarioITSON;
    }

    @Override
    public void iniciaSistema() {
        if (frmInicioSesion == null) {
            frmInicioSesion = new FrmInicioSesion(this);
        }
        frmInicioSesion.setVisible(true);
        frmInicioSesion.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarInicioSesion() {
        ocultarTodo();
        if (frmPlantilla != null) {
            frmPlantilla.dispose();
            frmPlantilla = null;
        }
        if (frmInicioSesion == null) {
            frmInicioSesion = new FrmInicioSesion(this);
        }
        frmInicioSesion.limpiarCampos();
        frmInicioSesion.setVisible(true);
        frmInicioSesion.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarRegistro() {
        ocultarTodo();
        if (frmRegistrarse == null) {
            frmRegistrarse = new FrmRegistrarse(this);
        }
        frmRegistrarse.setVisible(true);
        frmRegistrarse.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarInicio() {
        ocultarTodo();
        if (frmPlantilla == null) {
            frmPlantilla = new FrmPlantillaSistema(this);
            frmPlantilla.setUsuario(usuario);
        }
        frmPlantilla.setCategorias();
        frmPlantilla.setVisible(true);
        if (frmInicioSesion != null) {
            frmInicioSesion.dispose();
        }
    }

    @Override
    public void mostrarConsultar() {
        ocultarTodo();
        if (frmPlantilla == null) {
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlConsultar(this));
        frmPlantilla.setVisible(true);
    }

    @Override
    public void mostrarInfoEvento(EventoDTO evento) {
        ocultarTodo();
        if (frmPlantilla == null) {
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlConsultarEvento(this, evento));
        frmPlantilla.setVisible(true);
    }

    @Override
    public void mostrarDetalles(ReservacionDTO reservacion) {
        ocultarTodo();
        if (frmDetalles == null) {
            frmDetalles = new FrmDetallesCompra(this, reservacion);
        } else {
            frmDetalles.setReservacion(reservacion);
        }
        frmDetalles.setVisible(true);
    }
    
    @Override
    public void mostarRegistroITSON(){
        ocultarTodo();
        if(frmRegistro == null){
            frmRegistro = new FrmRegistroItson(this);
        }
        frmRegistro.setVisible(true);
    }

    @Override
    public void finalizarCompra() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mostrarEventos(CategoriaDTO categoria) {
        ocultarTodo();
        if (frmPlantilla == null) {
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlEventos(this, categoria));
        frmPlantilla.setVisible(true);
    }

    @Override
    public void volverAEventos() {

    }

    @Override
    public void mostrarConsultarEvento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void volverAConsultar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EventoDTO> consultarEventos(CategoriaDTO categoria) {
        return controlEvento.consultarEventosPorCategoria(categoria);
    }

    @Override
    public List<CategoriaDTO> consultarCategorias() {
        try {
            return controlEvento.consultarCategorias();
        } catch (GestionEventoException ex) {
            System.out.println("Fallo al consultar categorías: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasenia) {
        UsuarioDTO usuarioDTO = logi.verificarUsuario(correo, contrasenia);
        setUsuarioSesion(usuarioDTO);
        return usuarioDTO;
    }

    @Override
    public void setUsuarioSesion(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public UsuarioDTO getUsuarioSesion() {
        return usuario;
    }

    @Override
    public void cerrarSesion() {
        logi.cerrarSesion();
        usuario = null;
        this.mostrarInicioSesion();
    }

    @Override
    public List<ReservacionDTO> consultarReservaciones(Long idUsuario) {
        try {
            return controlUsuarios.obtenerReservacionesUsuario(idUsuario);
        } catch (GestionUsuarioException ex) {
            System.out.println("Fallo al consultar reservaciones: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) {
        try {
            return controlCompra.agregarReservacion(reservacion);
        } catch (CompraBoletoException ex) {
            System.out.println("Falló al consultar reservaciones: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Construye un mapa estructurado que relaciona cada sección del evento con
     * la lista de sus asientos y su estado de ocupación respectivo. Utiliza el
     * catálogo de asientos para saber a qué sección pertenece cada asiento y
     * evitar duplicados en la interfaz gráfica.
     *
     * @param idEvento El identificador único del evento a consultar.
     * @return Un mapa que vincula objetos SeccionDTO con listas de
     * AsientoEventoDTO.
     */
    @Override
    public Map<SeccionDTO, List<AsientoEventoDTO>> obtenerMapaOcupacion(Long idEvento) {
        try {
            return controlCompra.obtenerMapaOcupacion(idEvento);
        } catch (CompraBoletoException e) {
            System.err.println(e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Obtiene el catálogo completo de asientos disponibles en el sistema. Sirve
     * como base técnica para conocer la estructura de filas y números
     * independientemente del evento.
     *
     * @return Una lista de objetos AsientoDTO. Retorna una lista vacía en caso
     * de error.
     */
    @Override
    public List<AsientoDTO> obtenerCatalogoAsientos() {
        try {
            return controlCompra.obtenerAsientosPorSeccion(null);
        } catch (CompraBoletoException ex) {
            System.err.println("Error al obtener catálogo de asientos: " + ex.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    @Override
    public boolean reservarAsiento(Long idAsientoEvento) {
        try {
            return controlCompra.reservarAsiento(idAsientoEvento);
        } catch (CompraBoletoException ex) {
            return false;
        }
    }

    @Override
    public boolean liberarAsiento(Long idAsientoEvento) {
        try {
            return controlCompra.liberarAsiento(idAsientoEvento);
        } catch (CompraBoletoException ex) {
            return false;
        }
    }

    @Override
    public boolean venderAsientos(List<AsientoEventoDTO> asientosSeleccionados, Long totalCompra, boolean gratuito, ReservacionDTO reservacion) {
        try {
            return controlCompra.venderAsientos(asientosSeleccionados, totalCompra, gratuito, reservacion);
        } catch (CompraBoletoException ex) {
            return false;
        }
    }

    @Override
    public boolean realizarCompra(TarjetaDTO tarjeta, CobroDTO cobro) {
        try {
            return controlCompra.realizarCompra(tarjeta, cobro);
        } catch (CompraBoletoException ex) {
            return false;
        }
    }

    @Override
    public Long getTotalPendiente() {
        return controlCompra.getTotalPendiente();
    }

    @Override
    public void volverAConsultarEvento() {
        if (frmPago != null) {
            frmPago.dispose();
        }
        frmRegistro.dispose();
        frmPlantilla.setVisible(true);
    }

    @Override
    public String generarQR(EventoDTO evento, AsientoEventoDTO asiento) {
        try {
            return controlCompra.generarCodigoQR(evento, asiento);
        } catch (CompraBoletoException ex) {
            return null;
        }
    }

    @Override
    public void mostrarPago(ReservacionDTO reservacion) {
        ocultarTodo();

        this.reservacionActual = reservacion;

        frmPago = new FrmPago(this, reservacionActual);
        frmPago.setVisible(true);
        frmPago.setLocationRelativeTo(null);
    }
    
    @Override
    public boolean validarCredenciales(UsuarioInstitucionalDTO credenciales){
        return controlCompra.validarCredencialesITSON(credenciales);
    }

}
