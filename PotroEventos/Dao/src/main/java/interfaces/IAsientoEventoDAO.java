/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entitys.AsientoEvento;
import java.util.List;

/**
 *
 * @author Kaleb
 */
public interface IAsientoEventoDAO {

    List<AsientoEvento> buscarPorEvento(Long idEvento);
}
