package logica.interfaces;

import logica.entidades.SalidaTuristica;

public interface IManejadorSalidaTuristica {

	void add(SalidaTuristica salida);

	void update(SalidaTuristica salida);

	SalidaTuristica[] getAllAsociadasAActividadTuristica(String nombreActividad);

	SalidaTuristica find(String nombre);

	SalidaTuristica[] getAllVigentesAsociadasAActividad(String nombreActividad);

	Boolean contains(String nombreSalida);

	void removeAll();

}
