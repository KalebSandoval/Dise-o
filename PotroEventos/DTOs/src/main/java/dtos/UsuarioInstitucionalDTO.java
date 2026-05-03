/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author maria
 */
public class UsuarioInstitucionalDTO {
    
    private String idITSON;
    private String claveITSON;

    public UsuarioInstitucionalDTO() {
    }

    public UsuarioInstitucionalDTO(String idITSON, String claveITSON) {
        this.idITSON = idITSON;
        this.claveITSON = claveITSON;
    }

    public String getIdITSON() {
        return idITSON;
    }

    public void setIdITSON(String idITSON) {
        this.idITSON = idITSON;
    }

    public String getClaveITSON() {
        return claveITSON;
    }

    public void setClaveITSON(String claveITSON) {
        this.claveITSON = claveITSON;
    }
    
}
