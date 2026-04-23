package Entitys;

import Entitys.ENUMS.EstadoBoleto;
import java.time.LocalDate;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class Boleto {

    private Long idBoleto;
    private String codigoQR;
    private Double precio;
    private EstadoBoleto estadoBoleto;
    private Evento evento;

    public Boleto() {
    }

    public Boleto(Long idBoleto, String codigoQR, Double precio, EstadoBoleto estadoBoleto, Evento evento) {
        this.idBoleto = idBoleto;
        this.codigoQR = codigoQR;
        this.precio = precio;
        this.estadoBoleto = estadoBoleto;
        this.evento = evento;
    }

    public Boleto(String codigoQR, Double precio, EstadoBoleto estadoBoleto, Evento evento) {
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

    public EstadoBoleto getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(EstadoBoleto estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

}
