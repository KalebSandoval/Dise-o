package Entitys;

import Entitys.ENUMS.CategoriaEvento;
import Entitys.ENUMS.EstadoEvento;
import Entitys.ENUMS.TipoEventoP;
import java.time.LocalDateTime;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class Evento {

    private Long idEvento;
    private Categoria categoriaEvento;
    private String nombreEvento;
    private String informacionEvento;
    private LocalDateTime fechaHora;
    private Ubicacion ubicacion;
    private EstadoEvento estadoEvento;
    private String urlImagen;
    private boolean gratuito;
    private TipoEventoP tipoEvento;
    private Integer disponibilidad;

    public Evento() {
    }

    public Evento(Long idEvento, Categoria categoriaEvento, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, Ubicacion ubicacion, EstadoEvento estadoEvento, String urlImagen, boolean gratuito, TipoEventoP tipoEvento, Integer disponibilidad) {
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

    public Evento(Categoria categoriaEvento, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, Ubicacion ubicacion, EstadoEvento estadoEvento, String urlImagen, boolean gratuito, TipoEventoP tipoEvento, Integer disponibilidad) {
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

    public Categoria getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(Categoria categoriaEvento) {
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
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

    public TipoEventoP getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEventoP tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
