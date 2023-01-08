package logica.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaqueteActividades {

	private String nombre;
	private String descripcion;
	private int validezEnDias;
	private String fechaAlta;
	private List<String> nombreActividades = new ArrayList<>();
	private float descuento;
	private String imagen;

	private List<String> categorias;

	public DtPaqueteActividades() {
	}

	// ----------------------------------------------------------------
	// Getters y Setters
	// ----------------------------------------------------------------

	public List<String> getNombreActividades() {
		return nombreActividades;
	}

	public void setNombreActividades(List<String> nombreActividades) {
		this.nombreActividades = nombreActividades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValidezEnDias() {
		return validezEnDias;
	}

	public void setValidezEnDias(int validezEnDias) {
		this.validezEnDias = validezEnDias;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<String> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	
	// --------------------------------------------------------------------

	@Override
	public String toString() {
		return "DtPaqueteActividades [nombre=" + nombre + ", descripcion=" + descripcion + ", validezEnDias="
				+ validezEnDias + ", fechaAlta=" + fechaAlta + ", nombreActividades=" + nombreActividades
				+ ", descuento=" + descuento + ", imagen=" + imagen + ", categorias=" + categorias + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categorias, descripcion, descuento, fechaAlta, imagen, nombre, nombreActividades,
				validezEnDias);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtPaqueteActividades other = (DtPaqueteActividades) obj;
		return Objects.equals(categorias, other.categorias) && Objects.equals(descripcion, other.descripcion)
				&& Float.floatToIntBits(descuento) == Float.floatToIntBits(other.descuento)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreActividades, other.nombreActividades)
				&& validezEnDias == other.validezEnDias;
	}

}
