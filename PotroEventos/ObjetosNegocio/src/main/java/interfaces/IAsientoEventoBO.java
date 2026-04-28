/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AsientoEventoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author maria
 */
public interface IAsientoEventoBO {

    List<AsientoEventoDTO> consultarEstadosPorEvento(Long idEvento) throws NegocioException;

    boolean reservarAsiento(Long idAsiento) throws NegocioException;

    boolean liberarAsiento(Long idAsiento) throws NegocioException;

    boolean venderAsiento(Long idAsiento) throws NegocioException;
}
