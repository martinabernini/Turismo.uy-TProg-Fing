package logica.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import logica.datatypes.DtSalidaTuristica;

public class SalidaTuristica {

	private String nombre;
	private ActividadTuristica actividad;
	private int cantidadMaximaTuristas;
	private LocalDate fechaAlta;
	private LocalDateTime fechaSalida;
	private String lugarSalida;
	private int cantidadInscriptos;
	private String imagen;

	// Constructores

	public SalidaTuristica() {
		
	}
	
	
	public SalidaTuristica(String nombre, int cantidadMaximaTuristas, LocalDate fechaAlta, LocalDateTime fechaSalida, String lugarSalida,
			ActividadTuristica actividadTuristica, String imagen) {
		
		this.nombre = nombre;
		this.cantidadMaximaTuristas = cantidadMaximaTuristas;
		this.fechaAlta = fechaAlta;
		this.fechaSalida = fechaSalida;
		this.lugarSalida = lugarSalida;
		this.cantidadInscriptos = 0;
		this.actividad = actividadTuristica;
		this.imagen= imagen;
	}

	public SalidaTuristica(String nombre, int cantidadMaximaTuristas, LocalDate fechaAlta, LocalDateTime fechaSalida, String lugarSalida, int cantidadInscriptos,
			ActividadTuristica actividadTuristica, String imagen) {
		this.nombre = nombre;
		this.cantidadMaximaTuristas = cantidadMaximaTuristas;
		this.fechaAlta = fechaAlta;
		this.fechaSalida = fechaSalida;
		this.lugarSalida = lugarSalida;
		this.cantidadInscriptos = cantidadInscriptos;
		this.actividad = actividadTuristica;
		this.imagen= imagen;
	}

	// Getters y setters

	public ActividadTuristica getActividad() {
		return actividad;
	}

	public void setActividad(ActividadTuristica actividad) {
		this.actividad = actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadMaximaTuristas() {
		return cantidadMaximaTuristas;
	}

	public void setCantidadMaximaTuristas(int cantidadMaximaTuristas) {
		this.cantidadMaximaTuristas = cantidadMaximaTuristas;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public int getCantidadInscriptos() {
		return cantidadInscriptos;
	}

	public void setCantidadInscriptos(int cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	

	// ------------------------------------------------------------------

	public Boolean hayCuposDisponibles() {
		return this.cantidadMaximaTuristas < this.cantidadInscriptos;
	}

	public int cuposDisponibles() {
		return this.cantidadMaximaTuristas - this.cantidadInscriptos;
	}

	public void incrementarCantidadInscriptos(int cantidad) {
		this.cantidadInscriptos = this.cantidadInscriptos + cantidad;
	}

	public DtSalidaTuristica newDataType() {

		DtSalidaTuristica salidaTuristica = new DtSalidaTuristica();

		salidaTuristica.setNombreActividad(actividad.getNombre());
		salidaTuristica.setNombreSalida(nombre);
		salidaTuristica.setCantidadMaximaTuristas(cantidadMaximaTuristas);
		salidaTuristica.setFechaAlta(fechaAlta.toString());
		salidaTuristica.setFechaSalida(fechaSalida.toString());
		salidaTuristica.setLugarSalida(lugarSalida);
		salidaTuristica.setImagen(imagen);

		return salidaTuristica;
	}

	public boolean esVigente() {
		return this.fechaSalida.isAfter(LocalDateTime.now());
	}

	public float getCostoPorPersona() {
		return actividad.getCostoPorPersona();
	}

	// ------------------------------------------------------------------

	@Override
	public String toString() {
		return "SalidaTuristica [nombre=" + nombre + ", actividad=" + actividad.getNombre() + ", cantidadMaximaTuristas=" + cantidadMaximaTuristas
				+ ", fechaAlta=" + fechaAlta + ", fechaSalida=" + fechaSalida + ", lugarSalida=" + lugarSalida + ", cantidadInscriptos="
				+ cantidadInscriptos + "imagen=" + imagen + "]"  ;
	}

}
