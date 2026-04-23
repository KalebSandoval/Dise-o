/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dtos.UsuarioDTO;
import interfaces.IUsuarioBO;

/**
 *
 * @author aaron
 */
public class UsuarioBO implements IUsuarioBO{

    private static UsuarioBO instance;
    
    private UsuarioBO(){
        
    }
    
    public static UsuarioBO getInstance(){
        if(instance == null){
            instance = new UsuarioBO();
        }
        return instance;
    }
    
    @Override
    public UsuarioDTO asociarUsuario(UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void desAsociarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
