package interfaz;

import Entitys.AsientoEvento;
import Entitys.Evento;
import Entitys.Usuario;
import java.util.List;

/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public interface IControlOperaciones {

    // Eventos
    List<Evento> obtenerEventosDisponibles();

    Evento obtenerEvento(Long idEvento);

    // Usuarios
    List<Usuario> obtenerUsuarios();

    Usuario obtenerUsuario(Long idUsuario);

    // Asientos por evento
    List<AsientoEvento> obtenerAsientosEvento(Long idEvento);

    boolean validarDisponibilidad(Long idEvento, Long idAsiento);

    void agregarEvento(Evento evento);

    void agregarUsuario(Usuario usuario);

    void agregarAsientoEvento(AsientoEvento asientoEvento);
}
