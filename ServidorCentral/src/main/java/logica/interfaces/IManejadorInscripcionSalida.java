package logica.interfaces;

import logica.entidades.InscripcionSalida;

public interface IManejadorInscripcionSalida {

	void add(InscripcionSalida nuevaInscripcion);
	
	int count();  // devuelve la cantidad de inscripciones 

	void removeAll();
}
