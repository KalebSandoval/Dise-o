/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import dtos.UsuarioITSONDTO;
import dtos.UsuarioInstitucionalDTO;
import excepciones.ITSONException;

/**
 *
 * @author maria
 */
public interface IITSON {
    
    boolean validarUsuarioITSON(UsuarioITSONDTO usuario);
}
