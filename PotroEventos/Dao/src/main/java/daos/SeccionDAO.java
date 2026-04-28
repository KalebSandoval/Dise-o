/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Entitys.Seccion;
import interfaces.ISeccionDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaleb
 */
public class SeccionDAO implements ISeccionDAO {

    @Override
    public List<Seccion> buscarPorEvento(Long idEvento) {
        // Creamos una lista manual para que el sistema tenga qué mostrar
        List<Seccion> secciones = new ArrayList<>();

        // Sección 1
        Seccion vip = new Seccion();
        vip.setIdSeccion(1L);
        vip.setNombre("VIP");
        vip.setPrecioBase(1500000L);
        secciones.add(vip);

        // Sección 2
        Seccion platea = new Seccion();
        platea.setIdSeccion(2L);
        platea.setNombre("PLATEA");
        platea.setPrecioBase(80000L);
        secciones.add(platea);

        // Sección 3
        Seccion general = new Seccion();
        general.setIdSeccion(3L);
        general.setNombre("GENERAL");
        general.setPrecioBase(400000L);
        secciones.add(general);

        return secciones;
    }

}
