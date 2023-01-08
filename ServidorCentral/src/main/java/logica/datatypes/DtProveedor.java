package logica.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProveedor extends DtUsuario {
	private String descripcion;
	private String urlSitioWeb;
	private List<String> actividadesTuristicas = new ArrayList<>() ;

	public DtProveedor() {
		super();
	}

	// ----------------------------------------------------------------
	// Getters y Setters
	// ----------------------------------------------------------------

	public String getDescripcion() {
		if (descripcion == null) {
			return "NO HAY TEXTO";
		} else {
			return descripcion;
		}
		
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlSitioWeb() {
		return urlSitioWeb;
	}

	public void setUrlSitioWeb(String urlSitioWeb) {
		this.urlSitioWeb = urlSitioWeb;
	}	
	public List<String> getActividadesTuristicas() {
		return actividadesTuristicas;
	}

	public void setActividadesTuristicas(List<String> actividadesTuristicas) {
		this.actividadesTuristicas = actividadesTuristicas;
	}

	// -------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(actividadesTuristicas, descripcion, urlSitioWeb);
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
		DtProveedor other = (DtProveedor) obj;
		return Objects.equals(actividadesTuristicas, other.actividadesTuristicas)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(urlSitioWeb, other.urlSitioWeb);
	}


	@Override
	public String toString() {
		return "DtProveedor" + super.toString() + "[descripcion=" + descripcion + ", urlSitioWeb=" + urlSitioWeb + ", actividadesTuristicas="
				+ actividadesTuristicas + "]";
	}
	
	// -------------------------------------------------------------------------
	
}
