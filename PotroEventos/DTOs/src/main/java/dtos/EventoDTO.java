package dtos;

import dtos.ENUMS.EstadoEventoDTO;
import dtos.ENUMS.TipoEventoN;
import java.time.LocalDateTime;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class EventoDTO {

    private Long idEvento;
    private CategoriaDTO categoriaEvento;
    private String nombreEvento;
    private String informacionEvento;
    private LocalDateTime fechaHora;
    private UbicacionDTO ubicacion;
    private EstadoEventoDTO estadoEvento;
    private String urlImagen;
    private boolean gratuito;
    private TipoEventoN tipoEvento;
    private Integer disponibilidad;

    public EventoDTO() {
    }

    public EventoDTO(Long idEvento, CategoriaDTO categoriaEvento, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, UbicacionDTO ubicacion, EstadoEventoDTO estadoEvento, String urlImagen, boolean gratuito, TipoEventoN tipoEvento, Integer disponibilidad) {
        this.idEvento = idEvento;
        this.categoriaEvento = categoriaEvento;
        this.nombreEvento = nombreEvento;
        this.informacionEvento = informacionEvento;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.estadoEvento = estadoEvento;
        this.urlImagen = urlImagen;
        this.gratuito = gratuito;
        this.tipoEvento = tipoEvento;
        this.disponibilidad = disponibilidad;
    }

    public EventoDTO(CategoriaDTO categoriaEvento, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, UbicacionDTO ubicacion, EstadoEventoDTO estadoEvento, String urlImagen, boolean gratuito, TipoEventoN tipoEvento, Integer disponibilidad) {
        this.categoriaEvento = categoriaEvento;
        this.nombreEvento = nombreEvento;
        this.informacionEvento = informacionEvento;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.estadoEvento = estadoEvento;
        this.urlImagen = urlImagen;
        this.gratuito = gratuito;
        this.tipoEvento = tipoEvento;
        this.disponibilidad = disponibilidad;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public CategoriaDTO getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(CategoriaDTO categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getInformacionEvento() {
        return informacionEvento;
    }

    public void setInformacionEvento(String informacionEvento) {
        this.informacionEvento = informacionEvento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoEventoDTO getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEventoDTO estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    public TipoEventoN getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEventoN tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
