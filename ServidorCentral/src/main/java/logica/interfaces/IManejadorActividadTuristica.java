package logica.interfaces;

import logica.entidades.ActividadTuristica;

public interface IManejadorActividadTuristica {

	void add(ActividadTuristica actividad);
	
	void update(ActividadTuristica actividad);
	
	ActividadTuristica[] getAll();
	
	ActividadTuristica find(String nombre);
	
	ActividadTuristica[] getAllAsociadasADepartamento(String nombreDepto);
	
	ActividadTuristica[] getAllAsociadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete);
	
	Boolean contains(String nombreActividad);
	
	ActividadTuristica[] getAllActividadesEnEstadoAgregada();

	void removeAll();
	
	ActividadTuristica[] getAllActividadesEnEstadoConfirmada();
	
}
