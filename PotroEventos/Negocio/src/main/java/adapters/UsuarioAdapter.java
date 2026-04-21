/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import Entitys.Usuario;

/**
 *
 * @author Dayanara Peralta G
 */
public class UsuarioAdapter {
    public static UsuarioDTO entidadADTO(Usuario usuario){
        if(usuario == null){
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setidUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellidoPaterno(usuario.getApellidoPaterno());
        dto.setApellidoMaterno(usuario.getApellidoMaterno());
        dto.setCorreo(usuario.getCorreo());
        
        return dto;
    }
    
    public static Usuario dtoAEntidad(UsuarioDTO dto){
        if(dto == null){
            return null;
        }
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreo(dto.getCorreo());
        
        return usuario;
    }
}
