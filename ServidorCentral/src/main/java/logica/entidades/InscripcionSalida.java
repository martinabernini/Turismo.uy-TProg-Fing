package logica.entidades;

import java.time.LocalDate;
import java.util.Objects;

import logica.datatypes.DtInscripcionSalida;

public class InscripcionSalida {

    private int identificador;
    private LocalDate fechaInscripcion;
    private int cantidadTuristas;
    private SalidaTuristica salida;
    private float costoTotal = 0;

    public InscripcionSalida() {
    }

    // ----------------------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------------------

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcin) {
        this.fechaInscripcion = fechaInscripcin;
    }

    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    public void setCantidadTuristas(int cantidadTuristas) {
        this.cantidadTuristas = cantidadTuristas;
    }

    public SalidaTuristica getSalida() {
        return salida;
    }

    public void setSalida(SalidaTuristica salida) {
        this.salida = salida;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    // ----------------------------------------------------------------
    // Otros metodos
    // ----------------------------------------------------------------

    public DtInscripcionSalida newDataType() {
    	DtInscripcionSalida dtInscripcionSalida = new DtInscripcionSalida();
    	
    	dtInscripcionSalida.setNombreSalidaTuristica(salida.getNombre());
    	dtInscripcionSalida.setCantidadTuristas(cantidadTuristas);
    	dtInscripcionSalida.setFechaInscripcion(fechaInscripcion.toString());
    	dtInscripcionSalida.setCosto(costoTotal);
    	
		/*
		 * private String nickname; 
		 * private String nombreSalidaTuristica; 
		 * private int cantidadTuristas; 
		 * private Date fechaInscripcion; 
		 * private float costo;
		 */
    	
    	
    	return dtInscripcionSalida;
    }
    
    
    
    
    // ----------------------------------------------------------------
    // Metodos auxliares
    // ----------------------------------------------------------------

    private float calcularCostoTotal(int cantidadTuristas, float costoSalida) {
        return cantidadTuristas * costoSalida;
    }

    // ----------------------------------------------------------------
    // Metodos de sobrecarga
    // ----------------------------------------------------------------

    @Override
    public String toString() {
        return "InscripcionSalida [id=" + identificador + ", fechaInscripcin=" + fechaInscripcion + ", cantidadTuristas="
                + cantidadTuristas + ", salida=" + salida + ", costoTotal=" + costoTotal + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidadTuristas, costoTotal, fechaInscripcion, identificador, salida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InscripcionSalida other = (InscripcionSalida) obj;
        return cantidadTuristas == other.cantidadTuristas
                && Float.floatToIntBits(costoTotal) == Float.floatToIntBits(other.costoTotal)
                && Objects.equals(fechaInscripcion, other.fechaInscripcion) && identificador == other.identificador
                && Objects.equals(salida, other.salida);
    }

    ;

}
