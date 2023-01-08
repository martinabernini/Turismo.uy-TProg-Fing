package logica.entidades;

import logica.datatypes.DtCompraPaquete;

import java.time.LocalDate;
import java.util.Objects;

public class CompraPaquete {

    private String identificador;
    private LocalDate fechaCompra;
    private int cantidadTuristas;
    private int salidasDisponibles;
    private LocalDate validoHasta;
    private float costoTotal;
    private PaqueteActividades paquete;

    public CompraPaquete() {
    }

    // ------------------------------------------------------------------------
    // Getters y Setters
    // ------------------------------------------------------------------------


    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    public void setCantidadTuristas(int cantidadTuristas) {
        this.cantidadTuristas = cantidadTuristas;
    }

    public int getSalidasDisponibles() {
        return salidasDisponibles;
    }

    public void setSalidasDisponibles(int salidasDisponibles) {
        this.salidasDisponibles = salidasDisponibles;
    }

    public LocalDate getValidoHasta() {
        return validoHasta;
    }

    public void setValidoHasta(LocalDate validoHasta) {
        this.validoHasta = validoHasta;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public PaqueteActividades getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteActividades paquete) {
        this.paquete = paquete;
    }

    // ------------------------------------------------------------------------

    public void agregarSalida(SalidaTuristica salida) {
        this.salidasDisponibles--;
    }

    public void quitarSalida(SalidaTuristica salida) {
        this.salidasDisponibles++;
    }

    public void agregarTurista() {
        this.cantidadTuristas++;
    }

    public void quitarTurista() {
        this.cantidadTuristas--;
    }

    public float calcularCostoTotal() {
        return this.paquete.calularCosto() * this.cantidadTuristas;
    }


    // ------------------------------------------------------------------------

    public DtCompraPaquete newDataType() {

        DtCompraPaquete dtCompraPaquete = new DtCompraPaquete();

        dtCompraPaquete.setNombrePaquete(this.getIdentificador());
        dtCompraPaquete.setFechaCompra(this.getFechaCompra().toString());
        dtCompraPaquete.setPrecio(calcularCostoTotal());
        dtCompraPaquete.setValidoHasta(this.getValidoHasta().toString());
        dtCompraPaquete.setCantidadTuristas(this.getCantidadTuristas());
        dtCompraPaquete.setSalidasDisponibles(this.getSalidasDisponibles());

        return dtCompraPaquete;

    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidadTuristas, costoTotal, fechaCompra, identificador, paquete, salidasDisponibles,
                validoHasta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompraPaquete other = (CompraPaquete) obj;
        return cantidadTuristas == other.cantidadTuristas
                && Float.floatToIntBits(costoTotal) == Float.floatToIntBits(other.costoTotal)
                && Objects.equals(fechaCompra, other.fechaCompra) && identificador == other.identificador
                && Objects.equals(paquete, other.paquete) && salidasDisponibles == other.salidasDisponibles
                && Objects.equals(validoHasta, other.validoHasta);
    }

    @Override
    public String toString() {
        return "CompraPaquete [identificador=" + identificador + ", fechaCompra=" + fechaCompra + ", cantidadTuristas="
                + cantidadTuristas + ", salidasDisponibles=" + salidasDisponibles + ", validoHasta=" + validoHasta
                + ", costoTotal=" + costoTotal + ", paquete=" + paquete.getNombre() + ", costoPaquete=" + paquete.calularCosto() + "]";
    }


}
