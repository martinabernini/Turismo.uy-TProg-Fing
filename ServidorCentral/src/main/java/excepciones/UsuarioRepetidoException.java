package excepciones;

/**
 * Excepción utilizada para indicar la existencia de un usuario repetido en el sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class UsuarioRepetidoException extends Exception {

    public UsuarioRepetidoException(String string) {
        super(string);
    }

}
