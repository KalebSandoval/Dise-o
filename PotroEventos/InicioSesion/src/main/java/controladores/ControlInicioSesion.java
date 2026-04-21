/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import 
/**
 *
 * @author aaron
 */
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
    
    //inicio sesion
    public UsuarioDTO iniciarSesion(UsuarioDTO contrasenia){
        for(UsuarioDTO u : listaUsuarios){
            if(u.getCorreo().equals(u.getCorreo())){
                if(u.getContrasenia().equals(contrasenia.getContrasenia())){
                    UsuarioDTO dto = UsuarioAdapter.entidadADTO(u);
                    return dto;
                }
            }
        }
        return null;
    }
    
    public UsuarioDTO verificarUsuario(String correo, String contrasenia){
        if(correo.isEmpty() || contrasenia.isEmpty()){
            return null;
        }
    }
}
