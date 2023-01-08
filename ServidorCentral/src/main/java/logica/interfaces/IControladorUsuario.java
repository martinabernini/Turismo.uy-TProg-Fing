package logica.interfaces;

import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtUsuario;

public interface IControladorUsuario {
	
	void darDeAltaUsuario(DtUsuario nuevoUsuario) throws CampoInvalidoException, EntidadRepetidaException;

	ArrayList<String> listarUsuarios() throws NoHayEntidadesParaListarException;
	
	DtUsuario getUsuario(String nickname) throws EntidadNoExisteException, CampoInvalidoException;

	DtUsuario getUsuarioLogin(String nicknameOrEmail) throws EntidadNoExisteException, CampoInvalidoException;

	void modificarUsuario(DtUsuario usuarioModificado) throws CampoInvalidoException;
	
	void relacionSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException;
	
	void relacionDejarDeSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException;

	ArrayList<String> listarTuristas() throws NoHayEntidadesParaListarException;

	ArrayList<String> listarProveedores() throws NoHayEntidadesParaListarException;

	boolean esLoginValido(String nicknameOrEmail, String password);

	boolean estaEnUsoNick(String nickname);

	boolean estaEnUsoEmail(String email);

}
