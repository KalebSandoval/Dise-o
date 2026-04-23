/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entitys.Reservacion;
import java.util.List;

/**
 *
 * @author maria
 */
public interface IReservacionDAO {
    
    boolean guardarReservacion(Reservacion reservacion);
    
    List<Reservacion> obtenerReservacionesUsuario(Long idUsuario);
}
