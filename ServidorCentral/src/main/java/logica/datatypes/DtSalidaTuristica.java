package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtSalidaTuristica {

    private String nombreActividad;
    private String nombreSalida;
    private int cantidadMaximaTuristas;
    private String fechaAlta;
    private String fechaSalida;
    private String lugarSalida;
    private String imagen;

    public DtSalidaTuristica() {
    }

    // ----------------------------------------------------------------
    //Setters y getters
    // ----------------------------------------------------------------

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getNombreSalida() {
        return nombreSalida;
    }

    public void setNombreSalida(String nombreSalida) {
        this.nombreSalida = nombreSalida;
    }

    public int getCantidadMaximaTuristas() {
        return this.cantidadMaximaTuristas;
    }

    public void setCantidadMaximaTuristas(int cantidadMaximaTuristas) {
        this.cantidadMaximaTuristas = cantidadMaximaTuristas;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getLugarSalida() {
        return lugarSalida;
    }

    public void setLugarSalida(String lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // ----------------------------------------------------------------

    @Override
    public String toString() {
        return "DtSalidaTuristica [nombreActividad=" + nombreActividad + ", nombreSalida=" + nombreSalida
                + ", cantidadMaximaTuristas=" + cantidadMaximaTuristas + ", fechaAlta=" + fechaAlta + ", fechaSalida="
                + fechaSalida + ", lugarSalida=" + lugarSalida + "imagen=" + imagen + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(cantidadMaximaTuristas, fechaAlta, fechaSalida, lugarSalida, nombreActividad, nombreSalida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DtSalidaTuristica other = (DtSalidaTuristica) obj;
        return cantidadMaximaTuristas == other.cantidadMaximaTuristas && Objects.equals(fechaAlta, other.fechaAlta)
                && Objects.equals(fechaSalida, other.fechaSalida) && Objects.equals(lugarSalida, other.lugarSalida)
                && Objects.equals(nombreActividad, other.nombreActividad)
                && Objects.equals(nombreSalida, other.nombreSalida);
    }

}
