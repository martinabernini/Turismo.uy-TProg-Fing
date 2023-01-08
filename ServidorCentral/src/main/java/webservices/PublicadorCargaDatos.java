package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.interfaces.IPublicador;
import utils.ConfigHelper;
import utils.Fabrica;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorCargaDatos implements IPublicador{

	private Endpoint endpoint = null;


	// Constructor
	public PublicadorCargaDatos() {
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar() {
		String url = ConfigHelper.getWebServiceBaseURL() + "/CargaDatos";
		System.out.println("Publicando servicio de Carga de Datos en " + url);
		endpoint = Endpoint.publish(url, this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public void cargarDatos() {
		Fabrica.getInstance().getICargaDatos().cargar();
	}

	
}
