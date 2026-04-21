package dtos;

import dtos.ENUMS.CategoriaEventoDTO;
import dtos.ENUMS.EstadoEventoDTO;
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
    private CategoriaEventoDTO categoriaEvento;
    private String nombreEvento;
    private String informacionEvento;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private CategoriaDTO categoriaDTO;
    private String urlImagen;

    public EventoDTO() {
    }

    public EventoDTO(Long idEvento, CategoriaDTO categoriaDTO, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, String ubicacion, EstadoEventoDTO estadoEvento, String urlImagen) {
        this.idEvento = idEvento;
        this.categoriaEvento = categoriaEvento;
        this.nombreEvento = nombreEvento;
        this.informacionEvento = informacionEvento;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.categoriaDTO = categoriaDTO;
        this.urlImagen = urlImagen;
    }

    public EventoDTO(CategoriaDTO categoriaDTO, String nombreEvento, String informacionEvento, LocalDateTime fechaHora, String ubicacion, EstadoEventoDTO estadoEvento, String urlImagen) {
        this.categoriaEvento = categoriaEvento;
        this.nombreEvento = nombreEvento;
        this.informacionEvento = informacionEvento;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.categoriaDTO = categoriaDTO;
        this.urlImagen = urlImagen;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public CategoriaEventoDTO getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(CategoriaEventoDTO categoriaEvento) {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public CategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }

    public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
        this.categoriaDTO = categoriaDTO;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "EventoDTO{" + "idEvento=" + idEvento + ", categoriaEvento=" + categoriaEvento + ", nombreEvento=" + nombreEvento + ", informacionEvento=" + informacionEvento + ", fechaHora=" + fechaHora + ", ubicacion=" + ubicacion + ", categoriaDTO=" + categoriaDTO + ", urlImagen=" + urlImagen + '}';
    }

}
