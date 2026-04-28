package Entitys;

import java.util.Objects;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class Seccion {

    private Long idSeccion;
    private String nombre;
    private Integer capacidad;
    private Long precioBase;

    public Seccion() {
    }

    public Seccion(String nombre, Integer capacidad, Long precioBase) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    public Seccion(Long idSeccion, String nombre, Integer capacidad, Long precioBase) {
        this.idSeccion = idSeccion;
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
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idSeccion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seccion other = (Seccion) obj;
        return Objects.equals(this.idSeccion, other.idSeccion);
    }

    @Override
    public String toString() {
        return "Seccion{" + "idSeccion=" + idSeccion + ", nombre=" + nombre + ", capacidad=" + capacidad + ", precioBase=" + precioBase + '}';
    }

}
