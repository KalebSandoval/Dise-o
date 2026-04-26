/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import dtos.ENUMS.TipoUbicacionN;

/**
 *
 * @author maria
 */
public class UbicacionDTO {
    
    private Long idUbicacion;
    private String nombre;
    private Integer capacidad;
    private TipoUbicacionN tipo;

    public UbicacionDTO() {
    }

    public UbicacionDTO(Long idUbicacion, String nombre, Integer capacidad, TipoUbicacionN tipo) {
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    public UbicacionDTO(String nombre, Integer capacidad, TipoUbicacionN tipo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    public Long getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Long idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public TipoUbicacionN getTipo() {
        return tipo;
    }

    public void setTipo(TipoUbicacionN tipo) {
        this.tipo = tipo;
    }
    
}
