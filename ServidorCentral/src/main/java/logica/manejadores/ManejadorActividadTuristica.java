package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;
import logica.entidades.ActividadTuristica;
import logica.interfaces.IManejadorActividadTuristica;
import logica.interfaces.IManejadorDepartamento;

public final class ManejadorActividadTuristica implements IManejadorActividadTuristica {

	private Map<String, ActividadTuristica> actividades;

	private static IManejadorDepartamento manejadorDepartamento;

	private static ManejadorActividadTuristica instancia = null;

	public static ManejadorActividadTuristica getInstance(IManejadorDepartamento manejadorDepartamento) {
		if (instancia == null)
			instancia = new ManejadorActividadTuristica();

		ManejadorActividadTuristica.manejadorDepartamento = manejadorDepartamento;

		return instancia;
	}

	private ManejadorActividadTuristica() {
		actividades = new HashMap<>();
	}

	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------

	@Override
	public void add(ActividadTuristica actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}

	@Override
	public void update(ActividadTuristica actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}

	@Override
	public ActividadTuristica[] getAll() {
		if (actividades.isEmpty())
			return null;
		return actividades.values().toArray(new ActividadTuristica[0]);
	}

	@Override
	public ActividadTuristica find(String nombre) {
		return actividades.get(nombre);
	}

	@Override
	public Boolean contains(String nombreActividad) {
		return actividades.containsKey(nombreActividad);
	}

	@Override
	public ActividadTuristica[] getAllActividadesEnEstadoAgregada() {
        if (actividades.isEmpty())
            return null;
        List<ActividadTuristica> actividadesAgregadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividades.values()) {
            if (actividad.esAgreagada())
                actividadesAgregadas.add(actividad);
        }

		if (actividadesAgregadas.isEmpty()){
			return null;
		}

        return actividadesAgregadas.toArray(new ActividadTuristica[0]);
    }
	
	public ActividadTuristica[] getAllActividadesEnEstadoConfirmada() {
        if (actividades.isEmpty())
            return null;
        List<ActividadTuristica> actividadesConfirmadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividades.values()) {
            if (actividad.esConfirmada())
            	actividadesConfirmadas.add(actividad);
        }

		if (actividadesConfirmadas.isEmpty()){
			return null;
		}

        return actividadesConfirmadas.toArray(new ActividadTuristica[0]);
    }
	
	@Override
	public ActividadTuristica[] getAllAsociadasADepartamento(String nombreDepto) {
		return manejadorDepartamento.getAllActividadesAsociadasADepartamento(nombreDepto);
	}

	@Override
	public ActividadTuristica[] getAllAsociadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete) {
		return manejadorDepartamento.getAllActividadesAsociadasADepartamentoNoEnPaquete(nombreDepto, nombrePaquete);
	}

	@Override
	public void removeAll() {
		actividades.clear();
	}

}
