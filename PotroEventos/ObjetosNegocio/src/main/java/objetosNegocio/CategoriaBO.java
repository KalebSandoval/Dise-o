/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adapters.CategoriaAdapter;
import daos.CategoriaDAO;
import dtos.CategoriaDTO;
import excepciones.NegocioException;
import interfaces.ICategoriaBO;
import interfaces.ICategoriaDAO;
import java.util.List;

/**
 *
 * @author maria
 */
public class CategoriaBO implements ICategoriaBO {

    private static CategoriaBO instance;
    private ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
    
    private CategoriaBO(){}
    
    public static CategoriaBO getInstance(){
        if(instance == null){
            instance = new CategoriaBO();
        }
        return instance;
    }
    
    @Override
    public List<CategoriaDTO> consultarCategorias() throws NegocioException {
        return CategoriaAdapter.listaDTOs(categoriaDAO.consultarCategorias());
    }
    
}
