/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inicioSistema;

import Controlador.coordinador.CoordinadorAplicacion;
import Controlador.interfaz.ICoordinadorAplicacion;

/**
 *
 * @author maria
 */
public class InicioPotroEventos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ICoordinadorAplicacion inicio = new CoordinadorAplicacion();
        inicio.mostrarInicio();
    }
    
}
