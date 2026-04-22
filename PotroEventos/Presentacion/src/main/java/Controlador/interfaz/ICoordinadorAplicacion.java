package Controlador.interfaz;

import dtos.CategoriaDTO;
import dtos.EventoDTO;
import dtos.LoginDTO;
import dtos.UsuarioDTO;
import java.util.List;


/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public interface ICoordinadorAplicacion {

    public void iniciaSistema();

    public void mostrarInicioSesion();

    public void mostrarRegistro();

    public void mostrarInicio();

    public void mostrarConsultar();

    public void mostrarInfoEvento(EventoDTO evento);
    
    public void mostrarDetalles(EventoDTO evento);

    public void finalizarCompra();

    public void mostrarEventos(CategoriaDTO categoria);

    public void volverAEventos();

    public void mostrarConsultarEvento();

    public void volverAConsultar();
    
    public List<EventoDTO> consultarEventos();
    
    public List<EventoDTO> consultarEventos(CategoriaDTO categoria);
    
    public List<EventoDTO> consultarEventosProximos(Long idUsuario);
    
    public List<EventoDTO> consultarEventosPasados(Long idUsuario);
    
    public List<EventoDTO> consultarEventosCancelados(Long idUsuario);
    
    public List<CategoriaDTO> consultarCategorias();
    
    public UsuarioDTO iniciarSesion(LoginDTO login);
    
    public UsuarioDTO verificarUsuario(String correo, String contrasenia);
    
    public void setUsuarioSesion(UsuarioDTO usuario);
}
