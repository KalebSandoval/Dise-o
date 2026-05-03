package control;

import adapters.UsuarioInstitucionalAdapter;
import dtos.*;
import objetosNegocio.*;
import excepciones.CompraBoletoException;
import excepciones.NegocioException;
import excepciones.PagoException;
import fachada.FachadaITSON;
import fachada.PagoFachada;
import interfaces.IAsientoBO;
import interfaces.IAsientoEventoBO;
import interfaces.ICategoriaBO;
import interfaces.IEventoBO;
import interfaces.IReservacionBO;
import interfaces.ISeccionBO;
import interfaces.IUsuarioBO;
import interfaz.IControlCompraBoleto;
import interfaz.IITSON;
import interfaz.IPago;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/**
 * Controlador de caso de uso para la compra de boletos. Orquesta la
 * comunicación con los BOs y transforma Entidades a DTOs.
 *
 * @author Kaleb
 */
public class ControlCompraBoleto implements IControlCompraBoleto {

    private final IEventoBO eventoBO;
    private final ISeccionBO seccionBO;
    private final IAsientoBO asientoBO;
    private final IAsientoEventoBO asientoEventoBO;
    private final IReservacionBO reservacionBO;
    private final ICategoriaBO categoriaBO;
    private final IUsuarioBO usuarioBO;
    private final IPago controlPago;
    private final IITSON controlItson;

    /**
     * Lista de asientos pendientes de pago
     */
    private List<AsientoEventoDTO> asientosPendientesCompra;

    /**
     * Total pendiente en centavos
     */
    private Long totalPendienteCompra;

    /**
     * Constructor que inicializa dependencias y estado interno.
     */
    public ControlCompraBoleto() {
        this.eventoBO = EventoBO.getInstance();
        this.seccionBO = SeccionBO.getInstance();
        this.asientoBO = AsientoBO.getInstance();
        this.asientoEventoBO = AsientoEventoBO.getInstance();
        this.reservacionBO = ReservacionBO.getInstance();
        this.categoriaBO = CategoriaBO.getInstance();
        this.usuarioBO = UsuarioBO.getInstance();
        this.controlPago = new PagoFachada();
        this.controlItson = new FachadaITSON();

        this.asientosPendientesCompra = new ArrayList<>();
        this.totalPendienteCompra = 0L;
    }

    /**
     * Obtiene la información de un evento por su ID.
     *
     * @param idEvento identificador del evento
     * @return evento encontrado
     * @throws CompraBoletoException si ocurre un error o no existe el evento
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
     * Obtiene las secciones de un evento.
     *
     * @param idEvento identificador del evento
     * @return lista de secciones
     * @throws CompraBoletoException si ocurre un error
     */
    @Override
    public List<SeccionDTO> obtenerSeccionesEvento(Long idEvento) throws CompraBoletoException {
        try {
            return seccionBO.consultarSeccionesPorEvento(idEvento);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar las secciones: " + ex.getMessage());
        }
    }

    /**
     * Obtiene la ocupación de los asientos de un evento.
     *
     * @param idEvento identificador del evento
     * @return lista de estados de asientos
     * @throws CompraBoletoException si ocurre un error
     */
    @Override
    public List<AsientoEventoDTO> obtenerOcupacionEvento(Long idEvento) throws CompraBoletoException {
        try {
            return asientoEventoBO.consultarEstadosPorEvento(idEvento);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al cargar la ocupación del evento: " + ex.getMessage());
        }
    }

    /**
     * Obtiene el catálogo de asientos físicos.
     *
     * @return lista de asientos
     * @throws CompraBoletoException si ocurre un error
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

    /**
     * Construye un mapa de ocupación agrupado por sección.
     *
     * @param idEvento identificador del evento
     * @return mapa de secciones con sus asientos
     * @throws CompraBoletoException si ocurre un error
     */
    @Override
    public Map<SeccionDTO, List<AsientoEventoDTO>> obtenerMapaOcupacion(Long idEvento) throws CompraBoletoException {
        try {
            List<SeccionDTO> secciones = seccionBO.consultarSeccionesPorEvento(idEvento);
            List<AsientoEventoDTO> ocupacion = asientoEventoBO.consultarEstadosPorEvento(idEvento);
            List<AsientoDTO> catalogo = this.obtenerCatalogoAsientos();

            Map<SeccionDTO, List<AsientoEventoDTO>> mapa = new HashMap<>();

            for (SeccionDTO seccion : secciones) {
                List<AsientoEventoDTO> asientosDeSeccion = ocupacion.stream()
                        .filter(ae -> catalogo.stream().anyMatch(asiento
                        -> asiento.getIdAsiento().equals(ae.getIdAsiento())
                        && asiento.getIdSeccion().equals(seccion.getIdSeccion())
                ))
                        .collect(Collectors.toList());

                mapa.put(seccion, asientosDeSeccion);
            }

            return mapa;

        } catch (Exception e) {
            throw new CompraBoletoException("Error al construir mapa de ocupación: " + e.getMessage());
        }
    }

    /**
     * Agrega una reservación al sistema.
     *
     * @param reservacion datos de la reservación
     * @return true si se agregó correctamente
     * @throws CompraBoletoException si ocurre un error
     */
    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) throws CompraBoletoException {
        try {
            return reservacionBO.agregarReservacion(reservacion);
        } catch (Exception ex) {
            throw new CompraBoletoException("Error al agregar la reservación: " + ex.getMessage());
        }
    }

    /**
     * Genera un código QR para un boleto.
     *
     * @param evento evento asociado
     * @param asiento asiento del boleto
     * @return ruta del archivo QR generado
     * @throws CompraBoletoException si ocurre un error
     */
    public String generarCodigoQR(EventoDTO evento, AsientoEventoDTO asiento) throws CompraBoletoException {
        try {
            int asientoID = 0;
            int identificador = 0;
            if(asiento == null){
                asientoID = 0;
                identificador = LocalDateTime.now().getNano();
            } else {
                asientoID = asiento.getIdAsiento().intValue();
                identificador = asiento.getIdAsiento().intValue();
            }

            String datos = "Evento: " + evento.getNombreEvento()
                    + ", Fecha: " + evento.getFechaHora()
                    + ", Ubicación: " + evento.getUbicacion().getNombre()
                    + ", Asiento: " + asientoID;
            
            String ruta = "/src/main/resources/qrs-boletos";
            Path directorioDestino = Paths.get("src", "main", "resources", "qrs-boletos");
            if (!Files.exists(directorioDestino)) {
                Files.createDirectories(directorioDestino);
            }
            
            File temp = QRCode.from(datos).to(ImageType.PNG).withSize(300, 300).file();
            String nombreArchivo = "Boleto_" + identificador + "_" + evento.getIdEvento() + ".png";
            Path rutaDestino = directorioDestino.resolve(nombreArchivo);
            Files.copy(temp.toPath(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            return rutaDestino.toAbsolutePath().toString();
        } catch (Exception e) {
            throw new CompraBoletoException("Error al generar QR: " + e.getMessage());
        }
    }

    /**
     * Reserva un asiento.
     *
     * @param idAsientoEvento identificador del asiento
     * @return true si se reservó
     * @throws CompraBoletoException si ocurre un error
     */
    public boolean reservarAsiento(Long idAsientoEvento) throws CompraBoletoException {
        try {
            return asientoEventoBO.reservarAsiento(idAsientoEvento);
        } catch (NegocioException e) {
            throw new CompraBoletoException(e.getMessage());
        }
    }

    /**
     * Libera un asiento reservado.
     *
     * @param idAsientoEvento identificador del asiento
     * @return true si se liberó
     * @throws CompraBoletoException si ocurre un error
     */
    public boolean liberarAsiento(Long idAsientoEvento) throws CompraBoletoException {
        try {
            return asientoEventoBO.liberarAsiento(idAsientoEvento);
        } catch (NegocioException e) {
            throw new CompraBoletoException(e.getMessage());
        }
    }

    /**
     * Maneja la lógica de venta de asientos.
     */
    public boolean venderAsientos(List<AsientoEventoDTO> asientosSeleccionados, Long totalCompra, boolean gratuito, ReservacionDTO reservacion) throws CompraBoletoException {
        try {
            if (reservacion == null) {
                return false;
            }

            if (gratuito) {
                reservacionBO.agregarReservacion(reservacion);
                return true;
            }

            if (reservacion.getCobro() != null) {
                System.out.println((int)(totalCompra/100.0 * 2));
                if (usuarioBO.restarCreditos((int)(totalCompra/100.0 * 2), reservacion.getUsuario().getIdUsuario())) {
                    asientoEventoBO.venderAsiento(reservacion.getBoleto().getAsiento().getIdAsiento());
                    reservacionBO.agregarReservacion(reservacion);
                    return true;
                }
                return false;
            }

            this.asientosPendientesCompra = new ArrayList<>(asientosSeleccionados);
            this.totalPendienteCompra = totalCompra;

            return true;

        } catch (NegocioException e) {
            throw new CompraBoletoException(e.getMessage());
        }
    }

    /**
     * Realiza el pago de la compra pendiente.
     */
    public boolean realizarCompra(TarjetaDTO tarjeta, CobroDTO cobro) throws CompraBoletoException {
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
            throw new CompraBoletoException(ex.getMessage());
        }

        return false;
    }

    /**
     * Obtiene el total pendiente de pago.
     *
     * @return total en centavos
     */
    public Long getTotalPendiente() {
        return totalPendienteCompra;
    }
    
    public boolean validarCredencialesITSON(UsuarioInstitucionalDTO usuario){
        return controlItson.validarUsuarioITSON(UsuarioInstitucionalAdapter.dtoAInfraestructura(usuario));
    }
}
