/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import dtos.LoginDTO;
import dtos.UsuarioDTO;

/**
 *
 * @author aaron
 */
public interface IFachadaInicioSesion {
        //inicia sesion
    public UsuarioDTO iniciarSesion(LoginDTO login);
    
    public UsuarioDTO verificarUsuario(String correo, String contrasenia);
}
