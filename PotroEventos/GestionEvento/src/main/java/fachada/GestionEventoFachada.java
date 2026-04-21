/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import controladores.ControlGestionEvento;
import interfaces.IFachadaGestionEvento;
import java.util.List;

/**
 *
 * @author maria
 */
public class GestionEventoFachada implements IFachadaGestionEvento {
    
    private ControlGestionEvento control = ControlGestionEvento.getInstance();

    @Override
    public boolean agregarEvento(EventoDTO evento) {
        return control.agregarEvento(evento);
    }

    @Override
    public boolean cancelarEvento(Long idEvento) {
        return control.eliminarEvento(idEvento);
    }

    @Override
    public boolean actualizarEvento(EventoDTO evento) {
        return control.actualizarEvento(evento);
    }

    @Override
    public EventoDTO consultarEvento(Long idEvento) {
        return control.consultarEvento(idEvento);
    }

    @Override
    public List<EventoDTO> consultarEventos() {
        return control.consultarEventos();
    }

    @Override
    public List<EventoDTO> consultarEventosPorCategoria(Object categoria) {
        return control.consultarEventosPorCategoria(categoria);
    }
    
}
