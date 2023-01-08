
package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import logica.datatypes.DtActividadTuristica;
import logica.datatypes.EnumEstadoActividad;

public class ActividadTuristica {
    private String nombre;
    private String descripcion;
    private int duracionHrs;
    private float costoPorPersona;
    private String ciudad;
    private LocalDate fechaAlta;
    private Departamento departamento;
    private Map<String, PaqueteActividades> paquetesAsociados = new HashMap<>();
    private Map<String, SalidaTuristica> salidasAsociadas = new HashMap<>();
    private Proveedor proveedor;
    private EnumEstadoActividad estado;
    private Map<String, Categoria> categorias = new HashMap<>();

    private String imagen;

    private String urlVideo;
    private int cantidadFavoritos;


    public ActividadTuristica() {
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

    public int getDuracionHrs() {
        return duracionHrs;
    }

    public void setDuracionHrs(int duracionHrs) {
        this.duracionHrs = duracionHrs;
    }

    public float getCostoPorPersona() {
        return costoPorPersona;
    }

    public void setCostoPorPersona(float costoPorPersona) {
        this.costoPorPersona = costoPorPersona;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setCantidadFavoritos(int cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Map<String, PaqueteActividades> getPaquetesAsociados() {
        return paquetesAsociados;
    }

    public void setPaquetesAsociados(Map<String, PaqueteActividades> paquetesAsociados) {
        this.paquetesAsociados = paquetesAsociados;
    }

    public Map<String, SalidaTuristica> getSalidasAsociadas() {
        return salidasAsociadas;
    }

    public void setSalidasAsociadas(Map<String, SalidaTuristica> salidasAsociadas) {
        this.salidasAsociadas = salidasAsociadas;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public EnumEstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoActividad estado) {
        this.estado = estado;
    }


    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public int getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public Map<String, Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<String, Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<String> listarCategorias() {
        return this.categorias.keySet();
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void agregarPaquete(PaqueteActividades paquete) {
        this.paquetesAsociados.put(paquete.getNombre(), paquete);
    }

    public boolean tieneSalidasVigentes() {
        for (SalidaTuristica salida : this.salidasAsociadas.values()) {
            if (salida.esVigente()) {
                return true;
            }
        }
        return false;
    }

    // ----------------------------------------------------------------
    // Otros metodos
    // ----------------------------------------------------------------

    public void agregarPaqueteAsociado(PaqueteActividades paquete) {
        this.paquetesAsociados.put(paquete.getNombre(), paquete);
    }

    public void agregarSalidaAsociada(SalidaTuristica salida) {
        this.salidasAsociadas.put(salida.getNombre(), salida);
    }

    public boolean esAgreagada() {
        return this.estado == EnumEstadoActividad.AGREGADA;
    }

    public boolean esConfirmada() {
        return this.estado == EnumEstadoActividad.CONFIRMADA;
    }

    public void aceptar() {
        this.estado = EnumEstadoActividad.CONFIRMADA;
    }

    public void rechazar() {
        this.estado = EnumEstadoActividad.RECHAZADA;
    }

    public DtActividadTuristica newDataType() {
        DtActividadTuristica actividad = new DtActividadTuristica();
        actividad.setNombre(this.getNombre());
        actividad.setDescripcion(this.getDescripcion());
        actividad.setDuracionHrs(this.getDuracionHrs());
        actividad.setCostoPorPersona(this.getCostoPorPersona());
        actividad.setCiudad(this.getCiudad());
        actividad.setFechaAlta(this.getFechaAlta().toString());

        
        ArrayList<String> resSalidas = new ArrayList<>();
        for(String salida : salidasAsociadas.keySet()){
        	resSalidas.add(salida);
        }
        actividad.setSalidas(resSalidas);
        
        actividad.setDepartamento(this.getDepartamento().getNombre());
        actividad.setProovedor(this.getProveedor().getNickname());
               
        ArrayList<String> resPaquetes = new ArrayList<>();
        for(String paquete : paquetesAsociados.keySet()){
        	resPaquetes.add(paquete);
        }
        
        actividad.setPaquetes(resPaquetes);
        
        actividad.setImagen(this.imagen);
        actividad.setEstado(this.estado);
        
        ArrayList<String> resCategorias = new ArrayList<>();
        for(String categoria : this.getCategorias().keySet()){
        	resCategorias.add(categoria);
        }
        actividad.setCategorias(resCategorias);
        actividad.setUrlVideo(this.urlVideo);
        
        actividad.setTieneSalidasVigentes(this.tieneSalidasVigentes());
        return actividad;
    }

    public boolean tienePaqueteAsociado(String nombrePaquete) {
        return paquetesAsociados.containsKey(nombrePaquete);
    }

    public void finalizar() {
        this.estado = EnumEstadoActividad.FINALIZADA;
    }

    // ----------------------------------------------------------------
    // Metodos de sobrecarga
    // Hechos automaticamente con Eclipse en la pestania Source:
    // ----------------------------------------------------------------


    // Source > Generate toString()...
    @Override
    public String toString() {
//        return "hola";
        return "ActividadTuristica [nombre=" + nombre + ", descripcion=" + descripcion + ", duracionHrs=" + duracionHrs
                + ", costoPorPersona=" + costoPorPersona + ", ciudad=" + ciudad + ", fechaAlta=" + fechaAlta
                + ", departamento=" + departamento + ", paquetesAsociados=" + paquetesAsociados.keySet() + ", salidasAsociadas="
                + salidasAsociadas.keySet() + ", urlVideo=" + urlVideo + ", proveedor=" + proveedor.getNombre() + ", imagen=" + imagen + "Estado=" + estado + "Categorias=" + categorias.keySet() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActividadTuristica other = (ActividadTuristica) obj;
        return Objects.equals(ciudad, other.ciudad) && Objects.equals(costoPorPersona, other.costoPorPersona)
                && Objects.equals(departamento, other.departamento) && Objects.equals(descripcion, other.descripcion)
                && Objects.equals(duracionHrs, other.duracionHrs) && Objects.equals(fechaAlta, other.fechaAlta)
                && Objects.equals(nombre, other.nombre) && Objects.equals(proveedor.getNombre(), other.proveedor.getNombre()) && Objects.equals(imagen, other.imagen);
    }

}

