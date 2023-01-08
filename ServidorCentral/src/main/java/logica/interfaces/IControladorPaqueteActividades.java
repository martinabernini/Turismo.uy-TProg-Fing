package logica.interfaces;

import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtPaqueteActividades;

public interface IControladorPaqueteActividades {

	void darDeAltaPaquete(DtPaqueteActividades nuevoPaquete) throws CampoInvalidoException, EntidadRepetidaException;

	ArrayList<String> listarPaquetes() throws NoHayEntidadesParaListarException;

	ArrayList<String> listarActividadesAsociadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete)
			throws NoHayEntidadesParaListarException, CampoInvalidoException;

	void ingresarActividadTuristicaAPaquete(String nombreActividad, String nombrePaquete)
			throws EntidadRepetidaException, CampoInvalidoException;

	void ingresarCategoriaAPaquete(String categoria, String nombrePaquete)
			throws EntidadRepetidaException, CampoInvalidoException;

	ArrayList<String> listarPaquetesAsociadosAActividad(String nombreActividad) throws NoHayEntidadesParaListarException;


	DtPaqueteActividades find(String nombre) throws EntidadNoExisteException, CampoInvalidoException;

	void comprarPaquete(DtCompraPaquete compraPaquete) throws CampoInvalidoException , EntidadRepetidaException;


}
