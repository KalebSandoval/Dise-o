/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adapters.ReservacionAdapter;
import daos.ReservacionDAO;
import dtos.ReservacionDTO;
import excepciones.NegocioException;
import interfaces.IReservacionBO;
import interfaces.IReservacionDAO;
import java.util.List;

/**
 *
 * @author maria
 */
public class ReservacionBO implements IReservacionBO {

    private static ReservacionBO instance;
    private final IReservacionDAO reservacionDAO = ReservacionDAO.getInstance();
    
    private ReservacionBO(){}
    
    public static ReservacionBO getInstance(){
        if(instance == null){
            instance = new ReservacionBO();
        }
        return instance;
    }
    
    @Override
    public boolean agregarReservacion(ReservacionDTO reservacion) throws NegocioException {
        if(!validarDatos(reservacion)){
            throw new NegocioException("Reservación inválida.");
        }
        return reservacionDAO.guardarReservacion(ReservacionAdapter.dtoAEntidad(reservacion));
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesUsuario(Long idUsuario) throws NegocioException {
        if(idUsuario == null){
            throw new NegocioException("ID usuario inválido.");
        }
        return ReservacionAdapter.listaDTOs(reservacionDAO.obtenerReservacionesUsuario(idUsuario));
    }
    
    private boolean validarDatos(ReservacionDTO r){
        if(r == null){
            return false;
        }
        
        if(r.getBoleto() == null){
            return false;
        }
        
        if(r.getEstado() == null){
            return false;
        }
        
        if(r.getFechaHora() == null){
            return false;
        }
        
        if(r.getTotal() == null){
            return false;
        }
        
        if(r.getUsuario() == null){
            return false;
        }
        
        return true;
    }
    
}
