package excepciones;

/**
 *
 * @author Kaleb
 */
public class CompraBoletoException extends Exception {

    public CompraBoletoException() {
        super();
    }

    public CompraBoletoException(String message) {
        super(message);
    }

    public CompraBoletoException(String message, Throwable cause) {
        super(message, cause);
    }

}
