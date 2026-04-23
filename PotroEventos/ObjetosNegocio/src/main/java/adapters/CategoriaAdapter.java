/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import dtos.CategoriaDTO;
import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import dtos.ENUMS.CategoriaEventoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class CategoriaAdapter {
    
    public static CategoriaDTO entidadADTO(Categoria categoria){
        if(categoria == null){
            return null;
        }
        
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getUrlImagen(),
                CategoriaEventoDTO.valueOf(categoria.getNombre().name())
        );
    }
    
    public static Categoria dtoAEntidad(CategoriaDTO dto){
        if(dto == null){
            return null;
        }
        
        return new Categoria(
                dto.getIdCategoria(),
                CategoriaEvento.valueOf(dto.getNombreCategoria().name()),
                dto.getUrlImagen()
        );
    }
    
    public static List<CategoriaDTO> listaDTOs(List<Categoria> lista){
        List<CategoriaDTO> dtos = new ArrayList<>();
        
        for(Categoria c : lista){
            dtos.add(entidadADTO(c));
        }
        
        return dtos;
    }
    
}
