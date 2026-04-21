/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import dtos.UsuarioDTO;
import dtos.LoginDTO;
/**
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public  class LoginAdapter {
    
    public static LoginDTO convertirDTO(UsuarioDTO usuario){
        
        if(usuario == null) return null;
        // crear objeto
        LoginDTO login = new LoginDTO();
        
        // extraer elementos
        login.setContrasenia(usuario.getContrasenia());
        login.setCorreo(usuario.getCorreo());
        
        return login;
    }
}
