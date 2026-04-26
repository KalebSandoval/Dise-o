/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import Entitys.ENUMS.TipoUbicacionP;

/**
 *
 * @author maria
 */
public class Ubicacion {
    
    private Long idUbicacion;
    private String nombre;
    private Integer capacidad;
    private TipoUbicacionP tipo;

    public Ubicacion() {
    }

    public Ubicacion(Long idUbicacion, String nombre, Integer capacidad, TipoUbicacionP tipo) {
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    public Ubicacion(String nombre, Integer capacidad, TipoUbicacionP tipo) {
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

    public TipoUbicacionP getTipo() {
        return tipo;
    }

    public void setTipo(TipoUbicacionP tipo) {
        this.tipo = tipo;
    }
    
}
