package logica.interfaces;

import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.MaximoDeTuristasAlcanzadoException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtSalidaTuristica;

public interface IControladorSalidaTuristica {
	
	void darDeAltaSalidaTuristica(DtSalidaTuristica nuevaSalida) throws CampoInvalidoException, EntidadRepetidaException ;

	ArrayList<String> listarSalidasAsociadasAActividadTuristica(String nombreActividad) throws NoHayEntidadesParaListarException, CampoInvalidoException;
	
	DtSalidaTuristica getSalidaTuristica(String nombre) throws EntidadNoExisteException, CampoInvalidoException;

	ArrayList<String> listarSalidasVigentesAsociadasAActividadTuristica(String nombreActividad) throws NoHayEntidadesParaListarException, CampoInvalidoException ;
	
	void inscribirTuristaASalidaTuristica(DtInscripcionSalida nuevaInscripcion) throws EntidadRepetidaException, CampoInvalidoException, MaximoDeTuristasAlcanzadoException;

	boolean existeSalidaConNombre(String nombre);

}
