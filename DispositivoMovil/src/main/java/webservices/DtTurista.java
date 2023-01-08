
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtTurista complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtTurista">
 *   <complexContent>
 *     <extension base="{http://webservices/}dtUsuario">
 *       <sequence>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreSalidasALasQueEstaInscripto" type="{http://webservices/}wrapperArrayList" minOccurs="0"/>
 *         <element name="comprasPaquetes" type="{http://webservices/}wrapperMapCompraPaquete" minOccurs="0"/>
 *         <element name="actividadesFavoritas" type="{http://webservices/}wrapperMapDtActividadTuristica" minOccurs="0"/>
 *         <element name="DtInscripcionesASalidas" type="{http://webservices/}wrapperDtInscripcion" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTurista", propOrder = {
    "nacionalidad",
    "nombreSalidasALasQueEstaInscripto",
    "comprasPaquetes",
    "actividadesFavoritas",
    "dtInscripcionesASalidas"
})
public class DtTurista
    extends DtUsuario
{

    protected String nacionalidad;
    protected WrapperArrayList nombreSalidasALasQueEstaInscripto;
    protected WrapperMapCompraPaquete comprasPaquetes;
    protected WrapperMapDtActividadTuristica actividadesFavoritas;
    @XmlElement(name = "DtInscripcionesASalidas")
    protected WrapperDtInscripcion dtInscripcionesASalidas;

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nombreSalidasALasQueEstaInscripto property.
     * 
     * @return
     *     possible object is
     *     {@link WrapperArrayList }
     *     
     */
    public WrapperArrayList getNombreSalidasALasQueEstaInscripto() {
        return nombreSalidasALasQueEstaInscripto;
    }

    /**
     * Sets the value of the nombreSalidasALasQueEstaInscripto property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrapperArrayList }
     *     
     */
    public void setNombreSalidasALasQueEstaInscripto(WrapperArrayList value) {
        this.nombreSalidasALasQueEstaInscripto = value;
    }

    /**
     * Gets the value of the comprasPaquetes property.
     * 
     * @return
     *     possible object is
     *     {@link WrapperMapCompraPaquete }
     *     
     */
    public WrapperMapCompraPaquete getComprasPaquetes() {
        return comprasPaquetes;
    }

    /**
     * Sets the value of the comprasPaquetes property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrapperMapCompraPaquete }
     *     
     */
    public void setComprasPaquetes(WrapperMapCompraPaquete value) {
        this.comprasPaquetes = value;
    }

    /**
     * Gets the value of the actividadesFavoritas property.
     * 
     * @return
     *     possible object is
     *     {@link WrapperMapDtActividadTuristica }
     *     
     */
    public WrapperMapDtActividadTuristica getActividadesFavoritas() {
        return actividadesFavoritas;
    }

    /**
     * Sets the value of the actividadesFavoritas property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrapperMapDtActividadTuristica }
     *     
     */
    public void setActividadesFavoritas(WrapperMapDtActividadTuristica value) {
        this.actividadesFavoritas = value;
    }

    /**
     * Gets the value of the dtInscripcionesASalidas property.
     * 
     * @return
     *     possible object is
     *     {@link WrapperDtInscripcion }
     *     
     */
    public WrapperDtInscripcion getDtInscripcionesASalidas() {
        return dtInscripcionesASalidas;
    }

    /**
     * Sets the value of the dtInscripcionesASalidas property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrapperDtInscripcion }
     *     
     */
    public void setDtInscripcionesASalidas(WrapperDtInscripcion value) {
        this.dtInscripcionesASalidas = value;
    }

}
