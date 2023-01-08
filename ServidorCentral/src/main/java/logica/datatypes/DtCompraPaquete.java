package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtCompraPaquete {

    private String nombrePaquete;
    private String nombreTurista;

    private String fechaCompra;

    private float precio;

    private String validoHasta;

    private int cantidadTuristas;

    private int salidasDisponibles;

    public DtCompraPaquete() {
    }

    // ----------------------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------------------

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getNombreTurista() {
        return nombreTurista;
    }

    public void setNombreTurista(String nombreTurista) {
        this.nombreTurista = nombreTurista;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getValidoHasta() {
        return validoHasta;
    }

    public void setValidoHasta(String validoHasta) {
        this.validoHasta = validoHasta;
    }

    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    /*
     * Se usa para setear la cantidad de turistas que compran el paquete
     */
    public void setCantidadTuristas(int cantidadTuristas) {
        this.cantidadTuristas = cantidadTuristas;
    }

    public int getSalidasDisponibles() {
        return salidasDisponibles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DtCompraPaquete other = (DtCompraPaquete) obj;
        return cantidadTuristas == other.cantidadTuristas && Objects.equals(fechaCompra, other.fechaCompra)
                && Objects.equals(nombrePaquete, other.nombrePaquete)
                && Objects.equals(nombreTurista, other.nombreTurista)
                && Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
                && salidasDisponibles == other.salidasDisponibles && Objects.equals(validoHasta, other.validoHasta);
    }

    /*
     * Se usa solo para pasar de entidad a datatype
     */
    public void setSalidasDisponibles(int salidasDisponibles) {
        this.salidasDisponibles = salidasDisponibles;
    }

    // ---------------------------------------------------------------------------

    @Override
    public String toString() {
        return "DtCompraPaquete [nombrePaquete=" + this.getNombrePaquete() + ", nombreTurista=" + this.getNombreTurista() + ", fechaCompra="
                + this.getFechaCompra() + ", precio=" + this.getPrecio() + ", validoHasta=" + this.getValidoHasta() + ", cantidadTuristas="
                + this.getCantidadTuristas() + ", salidasDisponibles=" + this.getSalidasDisponibles() + "]";
    }

}
