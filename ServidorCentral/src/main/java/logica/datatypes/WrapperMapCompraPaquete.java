package logica.datatypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.entidades.CompraPaquete;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperMapCompraPaquete {

	private Map<String, DtCompraPaquete> MapCompraPaquete = new HashMap<>();
	
	public WrapperMapCompraPaquete() {
	}
	
	public WrapperMapCompraPaquete(Map<String, DtCompraPaquete> mapCompraPaquete) {
		super();
		MapCompraPaquete = mapCompraPaquete;
	}

	public Map<String, DtCompraPaquete> getMapCompraPaquete() {
		return MapCompraPaquete;
	}

	public void setMapCompraPaquete(Map<String, DtCompraPaquete> mapCompraPaquete) {
		MapCompraPaquete = mapCompraPaquete;
	}

	
	
	
	// ----------------------------------------------------
	
	@Override
	public int hashCode() {
		return Objects.hash(MapCompraPaquete);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrapperMapCompraPaquete other = (WrapperMapCompraPaquete) obj;
		return Objects.equals(MapCompraPaquete, other.MapCompraPaquete);
	}

	@Override
	public String toString() {
		return "WrapperMapCompraPaquete [MapCompraPaquete=" + MapCompraPaquete + "]";
	}
	
	
	
}
