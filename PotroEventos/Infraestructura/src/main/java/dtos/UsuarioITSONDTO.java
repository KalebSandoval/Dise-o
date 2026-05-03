/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author maria
 */
public class UsuarioITSONDTO {
    
    private String identificadorITSON;
    private String contraseniaITSON;

    public UsuarioITSONDTO() {
    }

    public UsuarioITSONDTO(String identificadorITSON, String contraseniaITSON) {
        this.identificadorITSON = identificadorITSON;
        this.contraseniaITSON = contraseniaITSON;
    }

    public String getIdentificadorITSON() {
        return identificadorITSON;
    }

    public void setIdentificadorITSON(String identificadorITSON) {
        this.identificadorITSON = identificadorITSON;
    }

    public String getContraseniaITSON() {
        return contraseniaITSON;
    }

    public void setContraseniaITSON(String contraseniaITSON) {
        this.contraseniaITSON = contraseniaITSON;
    }
    
}
