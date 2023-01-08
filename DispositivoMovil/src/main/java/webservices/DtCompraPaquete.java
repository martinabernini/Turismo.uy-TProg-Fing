
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCompraPaquete complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtCompraPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombrePaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreTurista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="precio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="validoHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantidadTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="salidasDisponibles" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompraPaquete", propOrder = {
    "nombrePaquete",
    "nombreTurista",
    "fechaCompra",
    "precio",
    "validoHasta",
    "cantidadTuristas",
    "salidasDisponibles"
})
public class DtCompraPaquete {

    protected String nombrePaquete;
    protected String nombreTurista;
    protected String fechaCompra;
    protected float precio;
    protected String validoHasta;
    protected int cantidadTuristas;
    protected int salidasDisponibles;

    /**
     * Gets the value of the nombrePaquete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePaquete() {
        return nombrePaquete;
    }

    /**
     * Sets the value of the nombrePaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePaquete(String value) {
        this.nombrePaquete = value;
    }

    /**
     * Gets the value of the nombreTurista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTurista() {
        return nombreTurista;
    }

    /**
     * Sets the value of the nombreTurista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTurista(String value) {
        this.nombreTurista = value;
    }

    /**
     * Gets the value of the fechaCompra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Sets the value of the fechaCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCompra(String value) {
        this.fechaCompra = value;
    }

    /**
     * Gets the value of the precio property.
     * 
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * 
     */
    public void setPrecio(float value) {
        this.precio = value;
    }

    /**
     * Gets the value of the validoHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidoHasta() {
        return validoHasta;
    }

    /**
     * Sets the value of the validoHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidoHasta(String value) {
        this.validoHasta = value;
    }

    /**
     * Gets the value of the cantidadTuristas property.
     * 
     */
    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    /**
     * Sets the value of the cantidadTuristas property.
     * 
     */
    public void setCantidadTuristas(int value) {
        this.cantidadTuristas = value;
    }

    /**
     * Gets the value of the salidasDisponibles property.
     * 
     */
    public int getSalidasDisponibles() {
        return salidasDisponibles;
    }

    /**
     * Sets the value of the salidasDisponibles property.
     * 
     */
    public void setSalidasDisponibles(int value) {
        this.salidasDisponibles = value;
    }

}
