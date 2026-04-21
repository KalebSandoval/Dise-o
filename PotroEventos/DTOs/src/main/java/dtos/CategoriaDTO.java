/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import dtos.ENUMS.CategoriaEventoDTO;

/**
 *
 * @author aaron
 */
public class CategoriaDTO {

    private Long idCategoria;

    private String UrlImagen;

    private CategoriaEventoDTO nombreCategoria;

    public CategoriaDTO(Long idCategoria, String UrlImagen, CategoriaEventoDTO nombreCategoria) {
        this.idCategoria = idCategoria;
        this.UrlImagen = UrlImagen;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaDTO() {
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String UrlImagen) {
        this.UrlImagen = UrlImagen;
    }

    public CategoriaEventoDTO getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(CategoriaEventoDTO nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

}
