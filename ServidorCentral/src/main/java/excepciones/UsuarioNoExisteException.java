package excepciones;

/**
 * Excepción utilizada para indicar la inexistencia de un usuario en el sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException(String string) {
        super(string);
    }
}
