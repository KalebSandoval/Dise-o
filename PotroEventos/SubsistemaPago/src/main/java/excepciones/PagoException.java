package excepciones;

/**
 * Excepción personalizada utilizada por el subsistema de pagos.
 *
 * Se lanza cuando ocurre algún error durante el procesamiento de una
 * transacción, validación de datos o comunicación con la plataforma de pagos.
 *
 * Permite encapsular errores específicos del módulo de pagos para que otras
 * capas del sistema los manejen adecuadamente.
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class PagoException extends Exception {

    /**
     * Crea una nueva excepción con mensaje descriptivo.
     *
     * @param mensaje descripción del error ocurrido
     */
    public PagoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea una nueva excepción con mensaje y causa original.
     *
     * @param mensaje descripción del error ocurrido
     * @param causa excepción original que provocó el error
     */
    public PagoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
