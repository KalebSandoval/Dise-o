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
import dtos.CategoriaDTO;
import dtos.CobroDTO;
import dtos.EventoDTO;
import dtos.ReservacionDTO;
import dtos.SeccionDTO;
import dtos.TarjetaDTO;
import dtos.UsuarioDTO;
import fachada.InicioSesionFachada;
import interfaces.IFachadaInicioSesion;
import excepciones.CompraBoletoException;
import excepciones.NegocioException;
import excepciones.PagoException;
import fachada.CompraBoletoFachada;
import fachada.PagoFachada;
import interfaz.ICompraBoleto;
import interfaz.IPago;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import objetosNegocio.AsientoBO;
import objetosNegocio.AsientoEventoBO;
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
    private IPago controlPago = new PagoFachada();
    private SeccionBO seccionBO = SeccionBO.getInstance();
    private AsientoBO asientoBO = AsientoBO.getInstance();
    private AsientoEventoBO asientoEventoBO = AsientoEventoBO.getInstance();

    private FrmInicioSesion frmInicioSesion;
    private FrmRegistrarse frmRegistrarse;
    private FrmPago frmPago;
    private FrmPlantillaSistema frmPlantilla;
    private FrmDetallesCompra frmDetalles;
    private FrmRegistroItson frmRegistro;

    private List<AsientoEventoDTO> asientosPendientesCompra;
    private Long totalPendienteCompra;

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
        }
        frmDetalles.setVisible(true);
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
        try {
            return controlCompra.obtenerEventosCategoria(categoria);
        } catch (CompraBoletoException ex) {
            System.out.println("Fallo al consultar eventos: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<CategoriaDTO> consultarCategorias() {
        try {
            return controlCompra.obtenerCategorias();
        } catch (CompraBoletoException ex) {
            System.out.println("Fallo al consultar categorías: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasenia) {
        return logi.verificarUsuario(correo, contrasenia);
    }

    @Override
    public void setUsuarioSesion(UsuarioDTO usuario) {
    }

    @Override
    public UsuarioDTO getUsuarioSesion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cerrarSesion() {
        logi.cerrarSesion();
        this.mostrarInicioSesion();
    }

    public List<ReservacionDTO> consultarReservaciones(Long idUsuario) {
        try {
            return controlCompra.obtenerReservacionesUsuario(idUsuario);
        } catch (CompraBoletoException ex) {
            System.out.println("Fallo al consultar reservaciones: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) {
        try {
            return controlCompra.agregarReservacion(reservacion);
        } catch (CompraBoletoException ex) {
            System.out.println("Fallo al consultar reservaciones: " + ex.getMessage());
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
            List<SeccionDTO> secciones = seccionBO.consultarSeccionesPorEvento(idEvento);
            List<AsientoEventoDTO> ocupacion = asientoEventoBO.consultarEstadosPorEvento(idEvento);

            // Obtenemos el catálogo general para verificar la pertenencia de los asientos
            List<AsientoDTO> catalogo = obtenerCatalogoAsientos();

            Map<SeccionDTO, List<AsientoEventoDTO>> mapa = new HashMap<>();

            if (secciones != null && ocupacion != null) {
                for (SeccionDTO seccion : secciones) {

                    // Filtramos los asientos del evento asegurándonos de que solo pasen
                    // los que realmente pertenecen a la sección iterada actualmente.
                    List<AsientoEventoDTO> asientosDeEstaSeccion = ocupacion.stream()
                            .filter(ae -> {
                                for (AsientoDTO asientoInfo : catalogo) {
                                    // Verificamos si el asiento del evento coincide con el del catálogo
                                    if (asientoInfo.getIdAsiento().equals(ae.getIdAsiento())) {
                                        // Comparamos el ID de la sección del asiento contra el de la sección actual
                                        return asientoInfo.getIdSeccion().equals(seccion.getIdSeccion());
                                    }
                                }
                                return false;
                            })
                            .collect(Collectors.toList());

                    mapa.put(seccion, asientosDeEstaSeccion);
                }
            }
            return mapa;

        } catch (NegocioException e) {
            System.err.println("Error en el coordinador: " + e.getMessage());
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
            return asientoEventoBO.reservarAsiento(idAsientoEvento);
        } catch (NegocioException e) {
            return false;
        }
    }

    @Override
    public boolean liberarAsiento(Long idAsientoEvento) {
        try {
            return asientoEventoBO.liberarAsiento(idAsientoEvento);
        } catch (NegocioException e) {
            return false;
        }
    }

    @Override
    public boolean venderAsientos(List<AsientoEventoDTO> asientosSeleccionados, Long totalCompra, boolean gratuito) {

        if (gratuito) {
            for (AsientoEventoDTO asiento : asientosSeleccionados) {
                try {
                    asientoEventoBO.venderAsiento(asiento.getIdAsiento());
                } catch (NegocioException ex) {
                    System.getLogger(CoordinadorAplicacion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }

            return true;

        } else {
            this.asientosPendientesCompra = new ArrayList<>(asientosSeleccionados);
            this.totalPendienteCompra = totalCompra;

            if (frmPago == null) {
                frmPago = new FrmPago(this);
            }

            frmPago.setVisible(true);
            frmPlantilla.setVisible(false);

            return true;
        }
    }

    @Override
    public boolean realizarCompra(TarjetaDTO tarjeta, CobroDTO cobro) {
        try {

            boolean pagado = controlPago.procesarPago(tarjeta, cobro);

            if (pagado) {

                for (AsientoEventoDTO asiento : asientosPendientesCompra) {
                    asientoEventoBO.venderAsiento(asiento.getIdAsiento());
                }

                asientosPendientesCompra.clear();
                totalPendienteCompra = 0L;

                return true;
            }

        } catch (NegocioException | PagoException ex) {
            System.getLogger(CoordinadorAplicacion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return false;
    }

    @Override
    public Long getTotalPendiente() {
        return totalPendienteCompra;
    }

    @Override
    public void volverAConsultarEvento() {
        frmPago.dispose();
        frmPlantilla.setVisible(true);
    }

}
