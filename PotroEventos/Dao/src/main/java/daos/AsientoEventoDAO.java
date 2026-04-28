package daos;

import Entitys.Asiento;
import Entitys.AsientoEvento;
import Entitys.ENUMS.EstadoAsiento;
import Entitys.Seccion;
import excepciones.PersistenciaException;
import interfaces.IAsientoEventoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO mock para la gestión de asientos por evento.
 *
 * Esta implementación utiliza una lista en memoria a nivel de clase para
 * facilitar pruebas unitarias y simulaciones sin necesidad de base de datos
 * real.
 *
 * Permite consultar los asientos de un evento y reservar un asiento únicamente
 * si aún se encuentra disponible.
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class AsientoEventoDAO implements IAsientoEventoDAO {

    private static AsientoEventoDAO instancia;

    /**
     * Lista mock a nivel de clase para persistencia en memoria. Esto permite
     * que las pruebas mantengan estado entre llamadas.
     */
    private final List<AsientoEvento> asientosEvento;

    private AsientoEventoDAO() {
        this.asientosEvento = new ArrayList<>();
        cargarDatosMock();
    }

    public static AsientoEventoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AsientoEventoDAO();
        }
        return instancia;
    }

    /**
     * Carga los datos iniciales mock.
     */
    private void cargarDatosMock() {
        if (!asientosEvento.isEmpty()) {
            return;
        }

        // SECCIONES PREDEFINIDAS
        Seccion sVip = new Seccion(1L, "VIP", 50, 1500000L);
        Seccion sPlatea = new Seccion(2L, "PLATEA", 50, 800000L);
        Seccion sGral = new Seccion(3L, "GENERAL", 50, 40000L);

        // --- ASIENTOS VIP ---
        asientosEvento.add(crearAE(101L, "A", 1, sVip, EstadoAsiento.DISPONIBLE));
        asientosEvento.add(crearAE(102L, "A", 2, sVip, EstadoAsiento.VENDIDO));
        asientosEvento.add(crearAE(103L, "B", 1, sVip, EstadoAsiento.DISPONIBLE));

        // --- ASIENTOS PLATEA ---
        asientosEvento.add(crearAE(201L, "A", 1, sPlatea, EstadoAsiento.DISPONIBLE));
        asientosEvento.add(crearAE(202L, "A", 2, sPlatea, EstadoAsiento.DISPONIBLE));

        // --- ASIENTOS GENERAL ---
        asientosEvento.add(crearAE(301L, "A", 1, sGral, EstadoAsiento.VENDIDO));
    }

    /**
     * Consulta todos los asientos asociados a un evento.
     *
     * En esta versión mock se retorna la lista completa.
     *
     * @param idEvento Identificador del evento.
     * @return Lista de asientos del evento.
     */
    @Override
    public List<AsientoEvento> buscarPorEvento(Long idEvento) {
        return asientosEvento;
    }

    /**
     * Intenta reservar un asiento únicamente si aún se encuentra disponible.
     *
     * Si el asiento está en estado DISPONIBLE, cambia su estado a RESERVADO y
     * retorna true.
     *
     * Si ya está VENDIDO o RESERVADO, retorna false.
     *
     * @param idAsiento Identificador del asiento.
     * @return true si se reservó correctamente, false en caso contrario.
     */
    @Override
    public boolean reservarAsiento(Long idAsiento) {
        for (AsientoEvento ae : asientosEvento) {
            if (ae.getAsiento().getIdAsiento().equals(idAsiento)) {

                if (ae.getEstadoAsiento() == EstadoAsiento.DISPONIBLE) {
                    ae.setEstadoAsiento(EstadoAsiento.RESERVADO);
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    @Override
    public boolean liberarAsiento(Long idAsiento) {
        for (AsientoEvento ae : asientosEvento) {
            if (ae.getAsiento().getIdAsiento().equals(idAsiento)) {

                if (ae.getEstadoAsiento() == EstadoAsiento.RESERVADO) {
                    ae.setEstadoAsiento(EstadoAsiento.DISPONIBLE);
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    @Override
    public boolean venderAsiento(Long idAsiento) throws PersistenciaException {
        for (AsientoEvento ae : asientosEvento) {
            if (ae.getAsiento().getIdAsiento().equals(idAsiento)) {

                if (ae.getEstadoAsiento() == EstadoAsiento.RESERVADO) {
                    ae.setEstadoAsiento(EstadoAsiento.VENDIDO);
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * Método auxiliar para evitar repetición de código en la creación de
     * asientos mock.
     *
     * @param id Id del asiento.
     * @param fila Fila del asiento.
     * @param num Número del asiento.
     * @param s Sección a la que pertenece.
     * @param estado Estado inicial del asiento.
     * @return Instancia de AsientoEvento.
     */
    private AsientoEvento crearAE(Long id, String fila, int num, Seccion s, EstadoAsiento estado) {
        Asiento a = new Asiento(id, fila, num, s);

        AsientoEvento ae = new AsientoEvento();
        ae.setAsiento(a);
        ae.setEstadoAsiento(estado);
        ae.setIdReservacion(id + 10000);

        return ae;
    }

}
