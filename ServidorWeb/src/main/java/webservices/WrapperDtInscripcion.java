
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wrapperDtInscripcion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="wrapperDtInscripcion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="lista" type="{http://webservices/}dtInscripcionSalida" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wrapperDtInscripcion", propOrder = {
    "lista"
})
public class WrapperDtInscripcion {

    @XmlElement(nillable = true)
    protected List<DtInscripcionSalida> lista;

    /**
     * Gets the value of the lista property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the lista property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLista().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtInscripcionSalida }
     * 
     * 
     * @return
     *     The value of the lista property.
     */
    public List<DtInscripcionSalida> getLista() {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        return this.lista;
    }

}
