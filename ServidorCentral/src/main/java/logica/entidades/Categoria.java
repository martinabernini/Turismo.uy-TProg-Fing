package logica.entidades;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Categoria {
    private String nombre;

    private Map<String, ActividadTuristica> actividades = new HashMap<>();

    private Map<String, PaqueteActividades> paquetes = new HashMap<>();

    
    public Categoria() {
    }
    
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // ----------------------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------------------

    public Map<String, ActividadTuristica> getActividades() {
        return actividades;
    }

    public void setActividades(Map<String, ActividadTuristica> actividades) {
        this.actividades = actividades;
    }

    public Map<String, PaqueteActividades> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Map<String, PaqueteActividades> paquetes) {
        this.paquetes = paquetes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarActividad(ActividadTuristica actividad) {
        this.actividades.put(actividad.getNombre(), actividad);
    }
    
    public void agregarPaquete(PaqueteActividades paquete) {
        this.paquetes.put(paquete.getNombre(), paquete);
    }

     
	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + ", actividades=" + actividades + ", paquetes=" + paquetes + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(actividades, other.actividades) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(paquetes, other.paquetes);
	}

    
    
}
