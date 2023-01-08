package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperDtInscripcion {
    ArrayList<DtInscripcionSalida> lista;

    public WrapperDtInscripcion() {
        this.lista = new ArrayList<>();
    }

    public WrapperDtInscripcion(ArrayList<DtInscripcionSalida> lista) {
        this.lista = lista;
    }

    public ArrayList<DtInscripcionSalida> getLista() {
        return lista;
    }

    public void setLista(ArrayList<DtInscripcionSalida> lista) {
        this.lista = lista;
    }

}
