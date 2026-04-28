/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import dtos.UsuarioDTO;
import excepciones.GestionUsuarioException;
import excepciones.NegocioException;

import interfaces.IUsuarioBO;
import objetosNegocio.UsuarioBO;
/**
 * Controlador internod e la clase de gestion de usuarios. Dedicado a mantener activo y desvincular un usuario
 * de la sesión.
 * 
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class ControlGestionUsuarios {
    // guardar el usuario que inicia sesion;
    private UsuarioDTO usuarioActivo;
    
    // comunicacion con bo
    private IUsuarioBO usuarioBO;
    
    //singleton
    private static ControlGestionUsuarios instance;
            
    public ControlGestionUsuarios() {
        this.usuarioBO = UsuarioBO.getInstance();
    }
    
    public UsuarioDTO asociarUsuario(UsuarioDTO usuario){
        this.usuarioActivo = usuario;
        return this.usuarioActivo;
    }
    
    /**
     * Desvincula un usuario al cerrar sesión
     */
    public void limpiarSesion() throws GestionUsuarioException{
        try {
            usuarioBO.desAsociarUsuario();

            this.usuarioActivo = null;
        } catch (NegocioException ex) {
           throw new GestionUsuarioException("Error al obtener la información del usuario: " + ex.getMessage());
        }
    }
    
    /**
     * Para que otras partes del sistema sepan a quién está asociado
     * @return 
     */
    public UsuarioDTO obtenerUsuarioActivo(UsuarioDTO usuario)throws GestionUsuarioException{
        
        try {
            UsuarioDTO user = usuarioBO.asociarUsuario(usuario);

            return usuario;
        } catch (Exception ex) {
            throw new GestionUsuarioException("Error al obtener la información del usuario: " + ex.getMessage());
        }
    }

    /**
     * Auxiliar que regresa el usuario activo
     * @return el usuario actico
     */
    public UsuarioDTO getUsuarioActivo() {
        return usuarioActivo;
    }
    
}
