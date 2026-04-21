/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import dtos.EventoDTO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author maria
 */

public class ControlGestionEvento {
    
    private List<EventoDTO> listaEventos = new ArrayList<>();
    
    private static ControlGestionEvento instance;
    
    private ControlGestionEvento(){}
    
    public static ControlGestionEvento getInstance(){
        if(instance == null){
            instance = new ControlGestionEvento();
        }
        return instance;
    }
    
    public boolean agregarEvento(EventoDTO evento){
        int contador = listaEventos.size();
        evento.setIdEvento(Long.parseLong(contador+1));
        listaEventos.add(evento);
        if(contador != listaEventos.size()){
            return true;
        }
        return false;
    }
    
    public boolean eliminarEvento(Long idEvento){
        for(EventoDTO evento : listaEventos){
            if(evento.getId() == idEvento){
                evento.setEstado(EstadoEventoDTO.CANCELADO);
                return true;
            }
        }
        return false;
    }
    
    public boolean actualizarEvento(EventoDTO evento){
        int posicion = 0;
        for(EventoDTO e : listaEventos){
            if(evento.getId() == e.getId()){
                listaEventos.add(posicion, evento);
                return true;
            }
            posicion++;
        }
        return false;
    }
    
    public EventoDTO consultarEvento(Long idEvento){
        for(EventoDTO e : listaEventos){
            if(e.getId() == idEvento){
                return e;
            }
        }
        return null;
    }
    
    public List<EventoDTO> consultarEventos(){
        return listaEventos;
    }
    
    public List<EventoDTO> consultarEventosPorCategoria(CategoriaDTO categoria){
        List<EventoDTO> eventosCategoria = new ArrayList<>();
        for(EventoDTO e : listaEventos){
            if(e.getCategoria() == categoria){
                eventosCategoria.add(e);
            }
        }
        return eventosCategoria;
    }
}
