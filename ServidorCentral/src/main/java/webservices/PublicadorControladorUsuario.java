package webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.datatypes.WrapperArrayList;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IPublicador;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorControladorUsuario implements IPublicador {

  private Endpoint endpoint = null;

  private IControladorUsuario controladorUsuario = Fabrica.getInstance().getIControladorUsuario();

  // Constructor
  public PublicadorControladorUsuario() {
  }

  // Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar() {
    String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorUsuario";
    System.out.println("Publicando servicio de ControladorUsuario en: " + url);

    endpoint = Endpoint.publish(url, this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }

  @WebMethod
  public void darDeAltaUsuario(DtUsuario nuevoUsuario) throws CampoInvalidoException, EntidadRepetidaException {
    controladorUsuario.darDeAltaUsuario(nuevoUsuario);
  }

  @WebMethod
  public void relacionSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException {
    controladorUsuario.relacionSeguirUsuario(nickUsuarioSeguido, nickUsuarioSeguidor);
  }

  @WebMethod
  public void relacionDejarDeSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException {
    controladorUsuario.relacionDejarDeSeguirUsuario(nickUsuarioSeguido, nickUsuarioSeguidor);
  }

  @WebMethod
  public WrapperArrayList listarUsuarios() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorUsuario.listarUsuarios());
  }

  @WebMethod
  public DtUsuario getUsuario(String nickname) throws EntidadNoExisteException, CampoInvalidoException {
    return controladorUsuario.getUsuario(nickname);
  }

  @WebMethod
  public DtUsuario getUsuarioLogin(String nicknameOrEmail) throws EntidadNoExisteException, CampoInvalidoException {
    return controladorUsuario.getUsuarioLogin(nicknameOrEmail);
  }

  @WebMethod
  public void modificarUsuario(DtUsuario usuarioModificado) throws CampoInvalidoException {
    controladorUsuario.modificarUsuario(usuarioModificado);
  }

  @WebMethod
  public WrapperArrayList listarTuristas() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorUsuario.listarTuristas());
  }

  @WebMethod
  public WrapperArrayList listarProveedores() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorUsuario.listarProveedores());
  }

  @WebMethod
  public boolean esLoginValido(String nicknameOrEmail, String password) {
    return controladorUsuario.esLoginValido(nicknameOrEmail, password);
  }

  @WebMethod
  public byte[] getFile(@WebParam(name = "fileName") String name) throws IOException {
    byte[] byteArray = null;
    try {
      File f = new File("files/" + name);
      FileInputStream streamer = new FileInputStream(f);
      byteArray = new byte[streamer.available()];
      streamer.read(byteArray);
    } catch (IOException e) {
      throw e;
    }
    return byteArray;
  }

  @WebMethod
  public boolean estaEnUsoNick(String nickname) {
    return controladorUsuario.estaEnUsoNick(nickname);
  }

  @WebMethod
  public boolean estaEnUsoEmail(String email) {
    return controladorUsuario.estaEnUsoEmail(email);
  }


  // -------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------

  @WebMethod
  public DtProveedor dummyProveedor() {
    DtProveedor juanCarlos = new DtProveedor();
    return juanCarlos;
  }

  @WebMethod
  public DtTurista dummyTurista() {
    return new DtTurista();
  }


}
