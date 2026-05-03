/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import dominio.UsuarioITSON;
import dtos.UsuarioITSONDTO;

/**
 *
 * @author maria
 */
public class UsuarioITSONAdapter {
    
    public static UsuarioITSON dtoAEntidad(UsuarioITSONDTO dto){
        if(dto == null){
            return null;
        }
        return new UsuarioITSON(
                dto.getIdentificadorITSON(), 
                dto.getContraseniaITSON());
    }
    
    public static UsuarioITSONDTO entidadADTO(UsuarioITSON entidad){
        if(entidad == null){
            return null;
        }
        return new UsuarioITSONDTO(
                entidad.getIdentificador(), 
                entidad.getContrasenia());
    }
}
