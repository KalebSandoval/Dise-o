package dtos;

import java.util.List;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class SeccionDTO {

    private Long idSeccion;
    private String nombre;
    private Integer capacidad;
    private Long precioBase;
    /*
    no lo quitaré porque no sé dónde se use ahorita mismo,
    pero estoy pensando que funciona mejor si cada seccion tiene su ubicación, y 
    cada asiento tiene su seccion, en vez de tener que la ubicación almacene una
    lista de secciones y la sección una lista de asientos.
     */
    private List<AsientoDTO> asientos;
    private UbicacionDTO ubicacion;

    public SeccionDTO() {
    }

    public SeccionDTO(Long idSeccion, String nombre, Integer capacidad, Long precioBase) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    public SeccionDTO(String nombre, Integer capacidad, Long precioBase) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Long getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Long precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public String toString() {
        return "SeccionDTO{" + "idSeccion=" + idSeccion + ", nombre=" + nombre + ", capacidad=" + capacidad + ", precioBase=" + precioBase + '}';
    }

}
