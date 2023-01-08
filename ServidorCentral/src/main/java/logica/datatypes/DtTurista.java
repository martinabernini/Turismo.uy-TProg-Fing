package logica.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.entidades.CompraPaquete;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtTurista extends DtUsuario {

	private String nacionalidad;

	private WrapperArrayList nombreSalidasALasQueEstaInscripto;

	private WrapperMapCompraPaquete comprasPaquetes;
	
	private WrapperMapDtActividadTuristica actividadesFavoritas;
	
	private WrapperDtInscripcion DtInscripcionesASalidas;

	public DtTurista() {
		super();
	}

	// ----------------------------------------------------------------
	// Getters y Setters
	// ----------------------------------------------------------------

	public WrapperMapDtActividadTuristica getActividadesFavoritas() {
		return actividadesFavoritas;
	}

	public void setActividadesFavoritas(WrapperMapDtActividadTuristica actividadesFavoritas) {
		this.actividadesFavoritas = actividadesFavoritas;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	
	public WrapperArrayList getNombreSalidasALasQueEstaInscripto() {
		return this.nombreSalidasALasQueEstaInscripto;
	}

	public WrapperMapCompraPaquete getComprasPaquetes() {
		return comprasPaquetes;
	}

	public void setComprasPaquetes(WrapperMapCompraPaquete comprasPaquetes) {
		this.comprasPaquetes = comprasPaquetes;
	}

	public void setNombreSalidasALasQueEstaInscripto(WrapperArrayList nombreSalidasALasQueEstaInscripto) {
		this.nombreSalidasALasQueEstaInscripto = nombreSalidasALasQueEstaInscripto;
	}

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(actividadesFavoritas, comprasPaquetes, nombreSalidasALasQueEstaInscripto, nacionalidad);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtTurista other = (DtTurista) obj;
		return Objects.equals(actividadesFavoritas, other.actividadesFavoritas)
				&& Objects.equals(comprasPaquetes, other.comprasPaquetes)
				&& Objects.equals(nombreSalidasALasQueEstaInscripto, other.nombreSalidasALasQueEstaInscripto)
				&& Objects.equals(nacionalidad, other.nacionalidad);
	}

	@Override
	public String toString() {
		return "DtTurista" + super.toString() + "[nacionalidad=" + nacionalidad + ", inscripcionesASalidas=" + nombreSalidasALasQueEstaInscripto
				+ ", comprasPaquetes=" + comprasPaquetes + ", actividadesFavoritas=" + actividadesFavoritas + "]";
	}

	public WrapperDtInscripcion getDtInscripcionesASalidas() {
		return DtInscripcionesASalidas;
	}

	public void setDtInscripcionesASalidas(WrapperDtInscripcion dtInscripcionesASalidas) {
		DtInscripcionesASalidas = dtInscripcionesASalidas;
	}
}
