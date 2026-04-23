package dtos;

import dtos.ENUMS.EstadoBoletoDTO;
import java.time.LocalDate;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class BoletoDTO {

    private Long idBoleto;
    private String codigoQR;
    private Double precio;
    private EstadoBoletoDTO estadoBoleto;
    private EventoDTO evento;

    public BoletoDTO() {
    }

    public BoletoDTO(Long idBoleto, String codigoQR, Double precio, EstadoBoletoDTO estadoBoleto, EventoDTO evento) {
        this.idBoleto = idBoleto;
        this.codigoQR = codigoQR;
        this.precio = precio;
        this.estadoBoleto = estadoBoleto;
        this.evento = evento;
    }

    public Long getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(Long idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public EstadoBoletoDTO getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(EstadoBoletoDTO estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }

}
