
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PublicadorControladorUsuario", targetNamespace = "http://webservices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorControladorUsuario {


    /**
     * 
     * @param fileName
     * @return
     *     returns byte[]
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/getFileRequest", output = "http://webservices/PublicadorControladorUsuario/getFileResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/getFile/Fault/IOException")
    })
    public byte[] getFile(
        @WebParam(name = "fileName", partName = "fileName")
        String fileName)
        throws IOException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws CampoInvalidoException_Exception
     * @throws EntidadRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://webservices/PublicadorControladorUsuario/darDeAltaUsuarioRequest", output = "http://webservices/PublicadorControladorUsuario/darDeAltaUsuarioResponse", fault = {
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/darDeAltaUsuario/Fault/CampoInvalidoException"),
        @FaultAction(className = EntidadRepetidaException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/darDeAltaUsuario/Fault/EntidadRepetidaException")
    })
    public void darDeAltaUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        DtUsuario arg0)
        throws CampoInvalidoException_Exception, EntidadRepetidaException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @throws CampoInvalidoException_Exception
     * @throws EntidadNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://webservices/PublicadorControladorUsuario/relacionSeguirUsuarioRequest", output = "http://webservices/PublicadorControladorUsuario/relacionSeguirUsuarioResponse", fault = {
        @FaultAction(className = EntidadNoExisteException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/relacionSeguirUsuario/Fault/EntidadNoExisteException"),
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/relacionSeguirUsuario/Fault/CampoInvalidoException")
    })
    public void relacionSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws CampoInvalidoException_Exception, EntidadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @throws CampoInvalidoException_Exception
     * @throws EntidadNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://webservices/PublicadorControladorUsuario/relacionDejarDeSeguirUsuarioRequest", output = "http://webservices/PublicadorControladorUsuario/relacionDejarDeSeguirUsuarioResponse", fault = {
        @FaultAction(className = EntidadNoExisteException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/relacionDejarDeSeguirUsuario/Fault/EntidadNoExisteException"),
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/relacionDejarDeSeguirUsuario/Fault/CampoInvalidoException")
    })
    public void relacionDejarDeSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws CampoInvalidoException_Exception, EntidadNoExisteException_Exception
    ;

    /**
     * 
     * @return
     *     returns webservices.WrapperArrayList
     * @throws NoHayEntidadesParaListarException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/listarProveedoresRequest", output = "http://webservices/PublicadorControladorUsuario/listarProveedoresResponse", fault = {
        @FaultAction(className = NoHayEntidadesParaListarException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/listarProveedores/Fault/NoHayEntidadesParaListarException")
    })
    public WrapperArrayList listarProveedores()
        throws NoHayEntidadesParaListarException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtUsuario
     * @throws CampoInvalidoException_Exception
     * @throws EntidadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/getUsuarioLoginRequest", output = "http://webservices/PublicadorControladorUsuario/getUsuarioLoginResponse", fault = {
        @FaultAction(className = EntidadNoExisteException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/getUsuarioLogin/Fault/EntidadNoExisteException"),
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/getUsuarioLogin/Fault/CampoInvalidoException")
    })
    public DtUsuario getUsuarioLogin(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws CampoInvalidoException_Exception, EntidadNoExisteException_Exception
    ;

    /**
     * 
     * @return
     *     returns webservices.WrapperArrayList
     * @throws NoHayEntidadesParaListarException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/listarUsuariosRequest", output = "http://webservices/PublicadorControladorUsuario/listarUsuariosResponse", fault = {
        @FaultAction(className = NoHayEntidadesParaListarException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/listarUsuarios/Fault/NoHayEntidadesParaListarException")
    })
    public WrapperArrayList listarUsuarios()
        throws NoHayEntidadesParaListarException_Exception
    ;

    /**
     * 
     * @return
     *     returns webservices.WrapperArrayList
     * @throws NoHayEntidadesParaListarException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/listarTuristasRequest", output = "http://webservices/PublicadorControladorUsuario/listarTuristasResponse", fault = {
        @FaultAction(className = NoHayEntidadesParaListarException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/listarTuristas/Fault/NoHayEntidadesParaListarException")
    })
    public WrapperArrayList listarTuristas()
        throws NoHayEntidadesParaListarException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/estaEnUsoEmailRequest", output = "http://webservices/PublicadorControladorUsuario/estaEnUsoEmailResponse")
    public boolean estaEnUsoEmail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/estaEnUsoNickRequest", output = "http://webservices/PublicadorControladorUsuario/estaEnUsoNickResponse")
    public boolean estaEnUsoNick(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtUsuario
     * @throws CampoInvalidoException_Exception
     * @throws EntidadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/getUsuarioRequest", output = "http://webservices/PublicadorControladorUsuario/getUsuarioResponse", fault = {
        @FaultAction(className = EntidadNoExisteException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/getUsuario/Fault/EntidadNoExisteException"),
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/getUsuario/Fault/CampoInvalidoException")
    })
    public DtUsuario getUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws CampoInvalidoException_Exception, EntidadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/esLoginValidoRequest", output = "http://webservices/PublicadorControladorUsuario/esLoginValidoResponse")
    public boolean esLoginValido(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @throws CampoInvalidoException_Exception
     */
    @WebMethod
    @Action(input = "http://webservices/PublicadorControladorUsuario/modificarUsuarioRequest", output = "http://webservices/PublicadorControladorUsuario/modificarUsuarioResponse", fault = {
        @FaultAction(className = CampoInvalidoException_Exception.class, value = "http://webservices/PublicadorControladorUsuario/modificarUsuario/Fault/CampoInvalidoException")
    })
    public void modificarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        DtUsuario arg0)
        throws CampoInvalidoException_Exception
    ;

    /**
     * 
     * @return
     *     returns webservices.DtTurista
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/dummyTuristaRequest", output = "http://webservices/PublicadorControladorUsuario/dummyTuristaResponse")
    public DtTurista dummyTurista();

    /**
     * 
     * @return
     *     returns webservices.DtProveedor
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/PublicadorControladorUsuario/dummyProveedorRequest", output = "http://webservices/PublicadorControladorUsuario/dummyProveedorResponse")
    public DtProveedor dummyProveedor();

}