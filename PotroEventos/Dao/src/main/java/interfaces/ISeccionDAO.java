/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entitys.Seccion;
import java.util.List;

/**
 *
 * @author Kaleb
 */
public interface ISeccionDAO {

    List<Seccion> buscarPorEvento(Long idEvento);
}
