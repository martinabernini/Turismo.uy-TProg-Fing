package webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
import logica.datatypes.DtActividadTuristica;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IPublicador;
import logica.datatypes.*;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorControladorActividad implements IPublicador {

  private Endpoint endpoint = null;

  private IControladorActividadTuristica controladorActividadTuristica = Fabrica.getInstance().getIControladorActividadTuristica();

  // Constructor
  public PublicadorControladorActividad() {
  }

  // Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar() {
    String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorActividad";
    System.out.println("Publicando servicio de ControladorActividad en " + url);

    endpoint = Endpoint.publish(url, this);
  }


  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }

  @WebMethod
  public void darDeAltaActividadTuristica(DtActividadTuristica nuevaActividad) throws CampoInvalidoException, EntidadRepetidaException {
    controladorActividadTuristica.darDeAltaActividadTuristica(nuevaActividad);
  }

  @WebMethod
  public WrapperArrayList listarActividadesAsociadasADepartamento(String nombre) throws NoHayEntidadesParaListarException,
          CampoInvalidoException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesAsociadasADepartamento(nombre));
  }

  @WebMethod
  public WrapperArrayList listarActividadesAsociadasADepartamentoConfirmadas(String nombre) throws NoHayEntidadesParaListarException
          , CampoInvalidoException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesAsociadasADepartamentoConfirmadas(nombre));
  }

  @WebMethod
  public WrapperArrayList listarActividadesAsociadasACategoriaConfirmadas(String nombre) throws NoHayEntidadesParaListarException,
          CampoInvalidoException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesAsociadasACategoriaConfirmadas(nombre));
  }

  @WebMethod
  public DtActividadTuristica getActividadTuristica(String nombre) throws EntidadNoExisteException, CampoInvalidoException {
    return controladorActividadTuristica.getActividadTuristica(nombre);
  }

  @WebMethod
  public WrapperArrayList listarActividadesAsocadasADepartamentoNoEnPaquete(
          String nombreDepto,
          String nombrePaquete)
          throws NoHayEntidadesParaListarException, CampoInvalidoException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesAsocadasADepartamentoNoEnPaquete(nombreDepto, nombrePaquete));
  }

  @WebMethod
  public WrapperArrayList listarAllActividades() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarAllActividades());
  }

  @WebMethod
  public WrapperArrayList listarActividadesEnEstadoAgregada() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesEnEstadoAgregada());
  }

  @WebMethod
  public WrapperArrayList listarActividadesEnEstadoConfirmada() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesEnEstadoConfirmada());
  }

  @WebMethod
  public WrapperArrayList listarActividadesDeProveedorNoConfirmadas(String nombreProveedor) throws NoHayEntidadesParaListarException
          , CampoInvalidoException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarActividadesDeProveedorNoConfirmadas(nombreProveedor));
  }

  @WebMethod
  public void darDeAltaCategoria(String nombre) throws EntidadRepetidaException, CampoInvalidoException {
    controladorActividadTuristica.darDeAltaCategoria(nombre);
  }

  @WebMethod
  public void rechazarAceptarActividad(String nombre, boolean aceptada) throws EntidadNoExisteException, CampoInvalidoException {
    controladorActividadTuristica.rechazarAceptarActividad(nombre, aceptada);
  }

  @WebMethod
  public WrapperArrayList listarTodasLasCategorias() throws NoHayEntidadesParaListarException {
    return WrapperArrayList.fromStringArrayList(controladorActividadTuristica.listarTodasLasCategorias());
  }

  @WebMethod
  public void finalizarActividad(String nombre) throws EntidadNoExisteException, CampoInvalidoException {
    controladorActividadTuristica.finalizarActividad(nombre);
  }


  @WebMethod
  public boolean existeActividadConNombre(String nombre) {
    return controladorActividadTuristica.existeActividadConNombre(nombre);
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
}
