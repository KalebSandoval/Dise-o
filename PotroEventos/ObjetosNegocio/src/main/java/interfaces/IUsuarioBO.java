/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import excepciones.NegocioException;

/**
 *
 * @author aaron
 */
public interface IUsuarioBO {
    
    // --- Metodos para asociar un usuario a la sesion 
    
    public UsuarioDTO asociarUsuario(UsuarioDTO usuario) throws NegocioException;
    
    // --- Metodo para deslindar un usuario de una sesion ---
    
    public void desAsociarUsuario() throws NegocioException;
}
