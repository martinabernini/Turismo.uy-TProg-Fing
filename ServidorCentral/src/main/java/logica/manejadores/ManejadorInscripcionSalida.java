package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.entidades.InscripcionSalida;
import logica.interfaces.IManejadorInscripcionSalida;

public class ManejadorInscripcionSalida implements IManejadorInscripcionSalida {

	private Map<Integer, InscripcionSalida> inscripcionesSalidas;

	private static ManejadorInscripcionSalida instancia = null;

	private ManejadorInscripcionSalida() {
		inscripcionesSalidas = new HashMap<>();
	}

	public static ManejadorInscripcionSalida getInstance() {
		if (instancia == null)
			instancia = new ManejadorInscripcionSalida();

		return instancia;
	}

	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------

	@Override
	public void add(InscripcionSalida nuevaInscripcion) {
		inscripcionesSalidas.put(nuevaInscripcion.getIdentificador(), nuevaInscripcion);
	}

	@Override
	public int count() {
		return inscripcionesSalidas.size();
	}

	@Override
	public void removeAll() {
		inscripcionesSalidas.clear();
	}
}
