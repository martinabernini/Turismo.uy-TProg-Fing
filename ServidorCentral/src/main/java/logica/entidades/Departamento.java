package logica.entidades;

import java.util.HashMap;
import java.util.Map;


public class Departamento {

	private String nombre;
	private String descripcion;
	private String url;
	private Map<String, ActividadTuristica> actividadesTuristicas =  new HashMap<>();
	
	public Departamento() {
		
	}

	public Departamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}

	// ----------------------------------------------------------------
	// Getters y Setters
	// ----------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl() {
		return url;
	}

	public Map<String, ActividadTuristica> getActividadesTuristicas() {
		return actividadesTuristicas;
	}

	// ----------------------------------------------------------------
	// Otros metodos
	// ----------------------------------------------------------------

	public void agregarActividadTuristica(ActividadTuristica actividad) {
		actividadesTuristicas.put(actividad.getNombre(), actividad);
	}
	
	// ----------------------------------------------------------------
	// Metodos de sobrecarga
	// ----------------------------------------------------------------

	@Override
	public boolean equals(Object object) {
		Departamento depto = (Departamento) object;
		return nombre == depto.getNombre() && this.getDescripcion() == depto.getDescripcion() && this.getUrl() == depto.getUrl();
	}

	@Override
	public String toString() {
		return "Departamento [nombre=" + nombre + ", descripcion=" + descripcion + ", url=" + url + "]";
	}

}
