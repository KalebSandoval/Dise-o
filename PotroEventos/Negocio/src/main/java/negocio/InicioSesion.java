/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Dayanara Peralta G
 */
public class InicioSesion {
    public UsuarioDTO verificarUsuario(String correo, String contrasenia){
        if(correo.isEmpty() || contrasenia.isEmpty()){
            return null;
        }
        
    }
}
