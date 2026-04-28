/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import dtos.UsuarioDTO;
import interfaces.IGestionUsuariosFachada;
import controles.ControlGestionUsuarios;
import excepciones.GestionUsuarioException;
/**
 * Fachada del subsistema, se encarga de comunicarse con el controlador.
 * 
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class GestionUsuarioFachada implements IGestionUsuariosFachada{

    private final  ControlGestionUsuarios control;

    public GestionUsuarioFachada() {
        this.control = new ControlGestionUsuarios();
    }
    
    // --- Metodos para asociar un usuario a la sesion 
    @Override
    public UsuarioDTO vincularUsuario(UsuarioDTO usuario) throws GestionUsuarioException{
        try{
            // recibe el usuario
            UsuarioDTO user = control.obtenerUsuarioActivo(usuario);
            
            if(user == null){
                throw new GestionUsuarioException("El usuario no se ha podido vincular.");
            }
            user = control.asociarUsuario(usuario);
            return user;
        }catch(GestionUsuarioException ex){
            throw new GestionUsuarioException(ex.getMessage());
        }
        
    }
    
    // --- Metodo para deslindar un usuario de una sesion ---
    @Override
    public void desvincularUsuario()throws GestionUsuarioException{
        control.limpiarSesion();
    }
    
    // --- Método que regresa el usuario con la sesion activa --- 
    @Override
    public UsuarioDTO obtenerUsuarioActivo(){
        return control.getUsuarioActivo();
    }
}
