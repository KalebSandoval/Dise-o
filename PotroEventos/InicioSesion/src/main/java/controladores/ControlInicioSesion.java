/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import adapters.LoginAdapter;
import dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aaron
 */
import dtos.LoginDTO;
public class ControlInicioSesion {
        
    private List<UsuarioDTO> listaUsuarios = new ArrayList<>();
    
    private static ControlInicioSesion instancia;
    
    private ControlInicioSesion(){}
    
    public static ControlInicioSesion getIntance(){
        if(instancia == null){
            instancia = new ControlInicioSesion();
        }
        return instancia;
    }
    
    /**
     * 
     * @param login
     * @return 
     */
    public UsuarioDTO iniciarSesion(LoginDTO login){
        
        for(UsuarioDTO usuario : listaUsuarios){
            if(usuario.getCorreo().equals(login.getCorreo())){
                if(usuario.getContrasenia().equals(login.getContrasenia())){
                    return usuario;
                }
            }
        }
        return null;
    }
    
    public UsuarioDTO verificarUsuario(String correo, String contrasenia){
        if(correo.isEmpty() || contrasenia.isEmpty()){
            return null;
        }
        return null;
    }
}
