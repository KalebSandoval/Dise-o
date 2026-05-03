/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import dtos.UsuarioITSONDTO;
import dtos.UsuarioInstitucionalDTO;

/**
 *
 * @author maria
 */
public class UsuarioInstitucionalAdapter {
    
    public static UsuarioITSONDTO dtoAInfraestructura(UsuarioInstitucionalDTO dto){
        if(dto == null){
            return null;
        }
        return new UsuarioITSONDTO(
                dto.getIdITSON(), 
                dto.getClaveITSON());
    }
    
    public static UsuarioInstitucionalDTO infraestructuraADTO(UsuarioITSONDTO infraestructura){
        if(infraestructura == null){
            return null;
        }
        return new UsuarioInstitucionalDTO(
                infraestructura.getIdentificadorITSON(), 
                infraestructura.getContraseniaITSON());
    }
}
