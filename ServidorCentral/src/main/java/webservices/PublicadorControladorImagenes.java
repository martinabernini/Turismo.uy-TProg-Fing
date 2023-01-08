package webservices;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.interfaces.IControladorImagen;
import logica.interfaces.IPublicador;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorControladorImagenes implements IPublicador, IControladorImagen {

    private Endpoint endpoint = null;

    // Constructor
    public PublicadorControladorImagenes() {
    }

    // Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar() {
        String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorImagenes";
        System.out.println("Publicando servicio de Controlador Imagenes " + url);
        endpoint = Endpoint.publish(url, this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }


    @Override
    @WebMethod
    public String almacenarImagenUsuario(byte[] imagen, String nick, String extension) {
        return Fabrica.getInstance().getIControladorImagen().almacenarImagenUsuario(imagen, nick, extension);
    }

    @Override
    @WebMethod
    public String almacenarImagenActividad(byte[] imagen, String nombre, String extension) {
        return Fabrica.getInstance().getIControladorImagen().almacenarImagenActividad(imagen, nombre, extension);
    }

    @Override
    @WebMethod
    public String almacenarImagenSalida(byte[] imagen, String nombre, String extension) {
        return Fabrica.getInstance().getIControladorImagen().almacenarImagenSalida(imagen, nombre, extension);
    }

    @Override
    @WebMethod
    public String almacenarImagenPaquete(byte[] imagen, String nombre, String extension) {
        return Fabrica.getInstance().getIControladorImagen().almacenarImagenPaquete(imagen, nombre, extension);
    }

    @Override
    @WebMethod
    public byte[] obtenerImagen(String identificador) {
        return Fabrica.getInstance().getIControladorImagen().obtenerImagen(identificador);
    }
}
