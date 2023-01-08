
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtInscripcionSalida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtInscripcionSalida">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreSalidaTuristica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantidadTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtInscripcionSalida", propOrder = {
    "nickname",
    "nombreSalidaTuristica",
    "cantidadTuristas",
    "fechaInscripcion",
    "costo"
})
public class DtInscripcionSalida {

    protected String nickname;
    protected String nombreSalidaTuristica;
    protected int cantidadTuristas;
    protected String fechaInscripcion;
    protected float costo;

    /**
     * Gets the value of the nickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the nombreSalidaTuristica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSalidaTuristica() {
        return nombreSalidaTuristica;
    }

    /**
     * Sets the value of the nombreSalidaTuristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSalidaTuristica(String value) {
        this.nombreSalidaTuristica = value;
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
     * Gets the value of the fechaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Sets the value of the fechaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcion(String value) {
        this.fechaInscripcion = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

}
