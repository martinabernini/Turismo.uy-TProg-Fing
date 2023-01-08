package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperArrayList {

  ArrayList<Object> lista;

  public WrapperArrayList() {
    this.lista = new ArrayList<>();
  }

  public WrapperArrayList(ArrayList<Object> lista) {
    this.lista = lista;
  }

  public ArrayList<Object> getLista() {
    return lista;
  }

  public void setLista(ArrayList<Object> lista) {
    this.lista = lista;
  }

  public static WrapperArrayList fromStringArrayList(ArrayList<String> strings){
    ArrayList<Object> listaString = new ArrayList<Object>();
    listaString.addAll(strings);

    return new WrapperArrayList(listaString);
  }

}
