package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtActividadTuristica {

    private String nombre;
    private String descripcion;
    private int duracionHrs;
    private float costoPorPersona;
    private String ciudad;
    private String fechaAlta;
    private String departamento;
    private String proovedor;
    private ArrayList<String> salidas = new ArrayList<>();
    private ArrayList<String> paquetes = new ArrayList<>();
    private String imagen;
    private ArrayList<String> categorias = new ArrayList<>();
    private EnumEstadoActividad estado = EnumEstadoActividad.AGREGADA;
    
    private String urlVideo;
    private int cantidadFavoritos;
    private boolean tieneSalidasVigentes;

	public DtActividadTuristica() {
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

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public ArrayList<String> getSalidas() {
        return salidas;
    }

    public void setSalidas(ArrayList<String> salidas) {
        this.salidas = salidas;
    }

    public ArrayList<String> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(ArrayList<String> paquetes) {
        this.paquetes = paquetes;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProovedor() {
        return proovedor;
    }

    public void setProovedor(String proovedor) {
        this.proovedor = proovedor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<String> categorias) {
        this.categorias = categorias;
    }

    public EnumEstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoActividad estado) {
        this.estado = estado;
    }
    
    public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public int getCantidadFavoritos() {
		return cantidadFavoritos;
	}

	public void setCantidadFavoritos(int cantidadFavoritos) {
		this.cantidadFavoritos = cantidadFavoritos;
	}

    public boolean getTieneSalidasVigentes() {
        return tieneSalidasVigentes;
    }

    public void setTieneSalidasVigentes(boolean tieneSalidasVigentes) {
        this.tieneSalidasVigentes = tieneSalidasVigentes;
    }

    // ----------------------------------------------------------------

    @Override
	public String toString() {
		return "DtActividadTuristica [nombre=" + nombre + ", descripcion=" + descripcion + ", duracionHrs="
				+ duracionHrs + ", costoPorPersona=" + costoPorPersona + ", ciudad=" + ciudad + ", fechaAlta="
				+ fechaAlta + ", departamento=" + departamento + ", proovedor=" + proovedor + ", salidas=" + salidas
				+ ", paquetes=" + paquetes + ", imagen=" + imagen + ", categorias=" + categorias + ", estado=" + estado
				+ ", urlVideo=" + urlVideo + ", cantidadFavoritos=" + cantidadFavoritos + ", tieneSalidasVigentes="
				+ tieneSalidasVigentes + "]";
	}

    @Override
	public int hashCode() {
		return Objects.hash(cantidadFavoritos, categorias, ciudad, costoPorPersona, departamento, descripcion,
				duracionHrs, estado, fechaAlta, imagen, nombre, paquetes, proovedor, salidas, tieneSalidasVigentes,
				urlVideo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtActividadTuristica other = (DtActividadTuristica) obj;
		return cantidadFavoritos == other.cantidadFavoritos && Objects.equals(categorias, other.categorias)
				&& Objects.equals(ciudad, other.ciudad)
				&& Float.floatToIntBits(costoPorPersona) == Float.floatToIntBits(other.costoPorPersona)
				&& Objects.equals(departamento, other.departamento) && Objects.equals(descripcion, other.descripcion)
				&& duracionHrs == other.duracionHrs && estado == other.estado
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(paquetes, other.paquetes)
				&& Objects.equals(proovedor, other.proovedor) && Objects.equals(salidas, other.salidas)
				&& tieneSalidasVigentes == other.tieneSalidasVigentes && Objects.equals(urlVideo, other.urlVideo);
	}
}
