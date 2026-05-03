/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adapter.UsuarioITSONAdapter;
import dao.UsuarioITSONDAO;
import dominio.UsuarioITSON;
import dtos.UsuarioITSONDTO;
import excepciones.ITSONException;

/**
 *
 * @author maria
 */
public class ControlITSON {
    
    private UsuarioITSONDAO usuarioItsonDAO = UsuarioITSONDAO.getInstance();
    
    protected UsuarioITSONDTO buscarUsuario(UsuarioITSONDTO usuario){
        try{
            return UsuarioITSONAdapter.entidadADTO(usuarioItsonDAO.consultarUsuario(UsuarioITSONAdapter.dtoAEntidad(usuario)));
        } catch(ITSONException e){
            return null;
        }
    }
    
}
