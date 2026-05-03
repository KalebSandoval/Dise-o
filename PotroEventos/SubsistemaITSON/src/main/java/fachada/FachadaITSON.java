/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import control.ControlITSON;
import dtos.UsuarioITSONDTO;
import excepciones.ITSONException;
import interfaz.IITSON;

/**
 *
 * @author maria
 */
public class FachadaITSON extends ControlITSON implements IITSON {
    
    @Override
    public boolean validarUsuarioITSON(UsuarioITSONDTO usuario){
        UsuarioITSONDTO resultado = super.buscarUsuario(usuario);
        if(resultado != null){
            return true;
        }
        return false;
    }
    
}
