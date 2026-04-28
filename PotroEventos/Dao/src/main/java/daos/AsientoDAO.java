package daos;

import Entitys.Asiento;
import Entitys.Seccion;
import interfaces.IAsientoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaleb
 */
public class AsientoDAO implements IAsientoDAO {

    @Override
    public List<Asiento> consultarTodos() {
        List<Asiento> todos = new ArrayList<>();

        // --- SECCIÓN VIP (ID 1) ---
        Seccion vip = new Seccion(1L, "VIP", 50, 1500000L);
        todos.add(new Asiento(101L, "A", 1, vip));
        todos.add(new Asiento(102L, "A", 2, vip));
        todos.add(new Asiento(103L, "B", 1, vip));

        // --- SECCIÓN PLATEA (ID 2) ---
        Seccion platea = new Seccion(2L, "PLATEA", 50, 800000L);
        todos.add(new Asiento(201L, "A", 1, platea));
        todos.add(new Asiento(202L, "A", 2, platea));

        // --- SECCIÓN GENERAL (ID 3) ---
        Seccion general = new Seccion(3L, "GENERAL", 50, 40000L);
        todos.add(new Asiento(301L, "A", 1, general));

        return todos;
    }

    @Override
    public List<Asiento> consultarPorSeccion(Long idSeccion) {
        List<Asiento> todosLosAsientos = consultarTodos();
        List<Asiento> asientosFiltrados = new ArrayList<>();

        // Recorremos todos los asientos y solo guardamos los que coincidan con el ID de la sección
        for (Asiento asiento : todosLosAsientos) {
            if (asiento.getSeccion().getIdSeccion().equals(idSeccion)) {
                asientosFiltrados.add(asiento);
            }
        }

        return asientosFiltrados;
    }
}
