package logica.datatypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperMapDtActividadTuristica {

	private Map<String, DtActividadTuristica> MapDtActividadTuristica = new HashMap<>();


	public WrapperMapDtActividadTuristica() {
	}

	public WrapperMapDtActividadTuristica(Map<String, DtActividadTuristica> mapDtActividadTuristica) {
		super();
		MapDtActividadTuristica = mapDtActividadTuristica;
	}
	
	public Map<String, DtActividadTuristica> getMapDtActividadTuristica() {
		return MapDtActividadTuristica;
	}

	public void setMapDtActividadTuristica(Map<String, DtActividadTuristica> mapDtActividadTuristica) {
		MapDtActividadTuristica = mapDtActividadTuristica;
	}
	
	// -----------------------------------
	
	@Override
	public String toString() {
		return "WrapperMapDtActividadTuristica [MapDtActividadTuristica=" + MapDtActividadTuristica + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(MapDtActividadTuristica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrapperMapDtActividadTuristica other = (WrapperMapDtActividadTuristica) obj;
		return Objects.equals(MapDtActividadTuristica, other.MapDtActividadTuristica);
	}
	
}
