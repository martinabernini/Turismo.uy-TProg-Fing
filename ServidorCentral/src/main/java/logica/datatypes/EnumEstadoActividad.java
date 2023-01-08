package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public enum EnumEstadoActividad {
    AGREGADA, CONFIRMADA, RECHAZADA, FINALIZADA
}
