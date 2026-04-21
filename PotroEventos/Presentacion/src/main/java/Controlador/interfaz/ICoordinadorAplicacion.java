package Controlador.interfaz;

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

    public void mostrarInfoEvento();
    
    public void mostrarDetalles(EventoDTO evento);

    public void finalizarCompra();

    public void mostrarEventos(CategoriaDTO categoria);

    public void volverAEventos();

    public void mostrarConsultarEvento();

    public void volverAConsultar();
    
    
}
