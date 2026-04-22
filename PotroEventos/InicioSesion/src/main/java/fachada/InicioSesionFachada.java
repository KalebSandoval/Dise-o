/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import controladores.ControlInicioSesion;
import dtos.LoginDTO;
import dtos.UsuarioDTO;
import interfaces.IFachadaInicioSesion;

/**
 *
 * @author aaron
 */
public class InicioSesionFachada implements IFachadaInicioSesion{
    
    private ControlInicioSesion control = ControlInicioSesion.getIntance();
    
    @Override
    public UsuarioDTO iniciarSesion(LoginDTO login){
        return control.iniciarSesion(login);
    }
    @Override
    public UsuarioDTO verificarUsuario(String correo, String contrasenia){
        return control.verificarUsuario(correo, contrasenia);
    }

}
