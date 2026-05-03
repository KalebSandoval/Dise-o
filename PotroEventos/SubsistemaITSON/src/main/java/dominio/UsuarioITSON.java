/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author maria
 */
@Entity
@Table(name="usuarios")
public class UsuarioITSON implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    
    @Column(name="identificador_itson")
    private String identificador;
    
    @Column(name="contrasenia_itson")
    private String contrasenia;

    public UsuarioITSON() {
    }

    public UsuarioITSON(Long idUsuario, String identificador, String contrasenia) {
        this.idUsuario = idUsuario;
        this.identificador = identificador;
        this.contrasenia = contrasenia;
    }

    public UsuarioITSON(String identificador, String contrasenia) {
        this.identificador = identificador;
        this.contrasenia = contrasenia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
