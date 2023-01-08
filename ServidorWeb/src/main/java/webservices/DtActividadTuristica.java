
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtActividadTuristica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtActividadTuristica">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="duracionHrs" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoPorPersona" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="proovedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="salidas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="paquetes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="estado" type="{http://webservices/}enumEstadoActividad" minOccurs="0"/>
 *         <element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantidadFavoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tieneSalidasVigentes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtActividadTuristica", propOrder = {
    "nombre",
    "descripcion",
    "duracionHrs",
    "costoPorPersona",
    "ciudad",
    "fechaAlta",
    "departamento",
    "proovedor",
    "salidas",
    "paquetes",
    "imagen",
    "categorias",
    "estado",
    "urlVideo",
    "cantidadFavoritos",
    "tieneSalidasVigentes"
})
public class DtActividadTuristica {

    protected String nombre;
    protected String descripcion;
    protected int duracionHrs;
    protected float costoPorPersona;
    protected String ciudad;
    protected String fechaAlta;
    protected String departamento;
    protected String proovedor;
    @XmlElement(nillable = true)
    protected List<String> salidas;
    @XmlElement(nillable = true)
    protected List<String> paquetes;
    protected String imagen;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    @XmlSchemaType(name = "string")
    protected EnumEstadoActividad estado;
    protected String urlVideo;
    protected int cantidadFavoritos;
    protected boolean tieneSalidasVigentes;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the duracionHrs property.
     * 
     */
    public int getDuracionHrs() {
        return duracionHrs;
    }

    /**
     * Sets the value of the duracionHrs property.
     * 
     */
    public void setDuracionHrs(int value) {
        this.duracionHrs = value;
    }

    /**
     * Gets the value of the costoPorPersona property.
     * 
     */
    public float getCostoPorPersona() {
        return costoPorPersona;
    }

    /**
     * Sets the value of the costoPorPersona property.
     * 
     */
    public void setCostoPorPersona(float value) {
        this.costoPorPersona = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the proovedor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProovedor() {
        return proovedor;
    }

    /**
     * Sets the value of the proovedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProovedor(String value) {
        this.proovedor = value;
    }

    /**
     * Gets the value of the salidas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the salidas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalidas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the salidas property.
     */
    public List<String> getSalidas() {
        if (salidas == null) {
            salidas = new ArrayList<>();
        }
        return this.salidas;
    }

    /**
     * Gets the value of the paquetes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the paquetes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquetes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the paquetes property.
     */
    public List<String> getPaquetes() {
        if (paquetes == null) {
            paquetes = new ArrayList<>();
        }
        return this.paquetes;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link EnumEstadoActividad }
     *     
     */
    public EnumEstadoActividad getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumEstadoActividad }
     *     
     */
    public void setEstado(EnumEstadoActividad value) {
        this.estado = value;
    }

    /**
     * Gets the value of the urlVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Sets the value of the urlVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

    /**
     * Gets the value of the cantidadFavoritos property.
     * 
     */
    public int getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    /**
     * Sets the value of the cantidadFavoritos property.
     * 
     */
    public void setCantidadFavoritos(int value) {
        this.cantidadFavoritos = value;
    }

    /**
     * Gets the value of the tieneSalidasVigentes property.
     * 
     */
    public boolean isTieneSalidasVigentes() {
        return tieneSalidasVigentes;
    }

    /**
     * Sets the value of the tieneSalidasVigentes property.
     * 
     */
    public void setTieneSalidasVigentes(boolean value) {
        this.tieneSalidasVigentes = value;
    }

}
