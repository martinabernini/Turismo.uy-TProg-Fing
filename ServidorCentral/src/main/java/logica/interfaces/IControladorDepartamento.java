package logica.interfaces;

import java.util.ArrayList;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;

public interface IControladorDepartamento {

	ArrayList<String> listarDepartamentos() throws NoHayEntidadesParaListarException;

	void darDeAltaDepartamento(String nombre, String descripcion, String url) throws CampoInvalidoException, EntidadRepetidaException;
	
}
