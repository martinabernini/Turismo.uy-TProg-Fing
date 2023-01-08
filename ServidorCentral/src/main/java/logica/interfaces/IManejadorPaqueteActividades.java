package logica.interfaces;

import logica.entidades.PaqueteActividades;

public interface IManejadorPaqueteActividades {

	void add(PaqueteActividades paquete);

	PaqueteActividades[] getAll();

	PaqueteActividades find(String nombre);

	void update(PaqueteActividades paquete);

	Boolean contains(String nombre);

	void removeAll();

}
