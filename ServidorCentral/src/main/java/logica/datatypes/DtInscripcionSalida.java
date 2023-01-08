package logica.datatypes;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtInscripcionSalida {

	private String nickname;
	private String nombreSalidaTuristica;
	private int cantidadTuristas;
	private String fechaInscripcion;
	private float costo;

	public DtInscripcionSalida() {
	}

	// ----------------------------------------------------------------
	// Getters y Setters



	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombreSalidaTuristica() {
		return nombreSalidaTuristica;
	}

	public void setNombreSalidaTuristica(String nombreSalidaTuristica) {
		this.nombreSalidaTuristica = nombreSalidaTuristica;
	}

	public int getCantidadTuristas() {
		return cantidadTuristas;
	}

	public void setCantidadTuristas(int cantidadTuristas) {
		this.cantidadTuristas = cantidadTuristas;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo=costo;
	}

	// ----------------------------------------------------------------

	@Override
	public String toString() {
		return "DtInscripcionSalida [nickname=" + nickname + ", nombreSalidaTuristica=" + nombreSalidaTuristica
				+ ", cantidadTuristas=" + cantidadTuristas + ", fechaInscripcion=" + fechaInscripcion + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtInscripcionSalida other = (DtInscripcionSalida) obj;
		return cantidadTuristas == other.cantidadTuristas && Objects.equals(fechaInscripcion, other.fechaInscripcion)
				&& Objects.equals(nickname, other.nickname)
				&& Objects.equals(nombreSalidaTuristica, other.nombreSalidaTuristica);
	}

}
