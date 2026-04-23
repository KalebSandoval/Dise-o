/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entitys.Categoria;
import java.util.List;

/**
 *
 * @author maria
 */
public interface ICategoriaDAO {
    
    List<Categoria> consultarCategorias();
    
}
