/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import interfaces.ICategoriaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class CategoriaDAO implements ICategoriaDAO {
    
    private static List<Categoria> categorias = new ArrayList<>();
    private static CategoriaDAO instance;
    
    private CategoriaDAO(){}
    
    public static CategoriaDAO getInstance(){
        if(instance == null){
            instance = new CategoriaDAO();
        }
        datosPrueba();
        return instance;
    }

    @Override
    public List<Categoria> consultarCategorias() {
        return new ArrayList<>(categorias);
    }
    
    private static void datosPrueba(){
        categorias.add(new Categoria(1L, CategoriaEvento.FUTBOL, "/imgCate/futbol.png"));
        categorias.add(new Categoria(2L, CategoriaEvento.ARTE, "/imgCate/arte.jpg"));
        categorias.add(new Categoria(3L, CategoriaEvento.CULTURA, "/imgCate/cultura.jpg"));
        categorias.add(new Categoria(4L, CategoriaEvento.BASQUETBOL, "/imgCate/basquet.jpg"));
        categorias.add(new Categoria(5L, CategoriaEvento.NATACION, "/imgCate/natacion.jpg"));
        categorias.add(new Categoria(6L, CategoriaEvento.FUTBOLAMERICANO, "/imgCate/americano.jpg"));
    }
    
}
