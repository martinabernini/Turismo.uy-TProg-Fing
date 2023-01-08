package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import logica.datatypes.DtPaqueteActividades;

public class PaqueteActividades {

    private String nombre;
    private String descripcion;
    private int validezEnDias;
    // entre 0 y 1
    private float descuento;
    private LocalDate fechaAlta;
    private Map<String, ActividadTuristica> actividadesIncluidas = new HashMap<String, ActividadTuristica>();
    private String imagen;

    private Map<String, Categoria> categorias = new HashMap<>();
    public PaqueteActividades() {
        this.actividadesIncluidas = new HashMap<String, ActividadTuristica>();
    }


    // ----------------------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValidezEnDias() {
        return validezEnDias;
    }

    public void setValidezEnDias(int validezEnDias) {
        this.validezEnDias = validezEnDias;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Map<String, ActividadTuristica> getActividadesIncluidas() {
        return actividadesIncluidas;
    }

    public void setActividadesIncluidas(Map<String, ActividadTuristica> actividadesIncluidas) {
        this.actividadesIncluidas = actividadesIncluidas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Map<String, Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<String, Categoria> categorias) {
        this.categorias = categorias;
    }


    // ----------------------------------------------------------------
    // Otros metodos
    // ----------------------------------------------------------------

    public float calularCosto() {
        // el costo es la suma de los costos de las actividades incluidas
        // menos el descuento del paquete
        float costo = 0;
        for (ActividadTuristica actividadTuristica : actividadesIncluidas.values()) {
            costo += actividadTuristica.getCostoPorPersona();
        }
        costo -= costo * descuento;
        return costo;
    }

    public boolean contieneActividad(String nombreActividad) {
        return actividadesIncluidas.containsKey(nombreActividad);
    }
    
    public boolean contieneCategoria(String categoria) {
        return categorias.containsKey(categoria);
    }

    public void agregarActividad(ActividadTuristica actividad) {
        this.actividadesIncluidas.put(actividad.getNombre(), actividad);
    }
    
    public void agregarCategoria(Categoria categoria) {
        this.categorias.put(categoria.getNombre(), categoria);
    }

    public DtPaqueteActividades newDataType() {

        DtPaqueteActividades dtPaquete = new DtPaqueteActividades();
        dtPaquete.setNombre(this.nombre);
        dtPaquete.setDescripcion(this.descripcion);
        dtPaquete.setValidezEnDias(this.validezEnDias);
        dtPaquete.setFechaAlta(this.fechaAlta.toString());
        dtPaquete.setDescuento(this.descuento);
        dtPaquete.setImagen(this.imagen);

        if (actividadesIncluidas == null || actividadesIncluidas.isEmpty()) {
            dtPaquete.setNombreActividades(null);
        } else {
        	List<String> resPaquete = new ArrayList<>();
        	for(String actividad : actividadesIncluidas.keySet()){
        		resPaquete.add(actividad);
        	}
            dtPaquete.setNombreActividades(resPaquete);
        }

        if (categorias == null || categorias.isEmpty()) {
            dtPaquete.setCategorias(null);
        } else {
        	
        	List<String> resCategoriasActs = new ArrayList<>();
        	for(String categoria : this.getCategorias().keySet()){
        		resCategoriasActs.add(categoria);
        	}
        	dtPaquete.setCategorias(resCategoriasActs);
        	
        }

        return dtPaquete;
    }

    // ----------------------------------------------------------------
    // Metodos de sobrecarga
    // Hechos automaticamente con Eclipse en la pestania Source:
    // Source > Generate toString()...
    // Source > Generate hashCode() and equals()...

    @Override
    public int hashCode() {
        return Objects.hash(actividadesIncluidas, descripcion, descuento, fechaAlta, nombre, validezEnDias);
    }

    @Override
    public String toString() {
        return "PaqueteActividades [nombre=" + nombre + ", descripcion=" + descripcion + ", validezEnDias="
                + validezEnDias + ", descuento=" + descuento + ", fechaAlta=" + fechaAlta + ", actividadesIncluidas="
                + actividadesIncluidas.keySet() + "Imagen=" + imagen + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaqueteActividades other = (PaqueteActividades) obj;
        return Objects.equals(actividadesIncluidas, other.actividadesIncluidas)
                && Objects.equals(descripcion, other.descripcion)
                && Float.floatToIntBits(descuento) == Float.floatToIntBits(other.descuento)
                && Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(nombre, other.nombre)
                && validezEnDias == other.validezEnDias;
    }
}
