package webservices;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.datatypes.WrapperArrayList;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IPublicador;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorControladorDepartamento implements IPublicador {

	private Endpoint endpoint = null;

	private IControladorDepartamento controladorDepartamento = Fabrica.getInstance().getIControladorDepartamento();

	// Constructor
	public PublicadorControladorDepartamento() {
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar() {
		String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorDepartamento";
		System.out.println("Publicando servicio de ControladorDepartamento en " + url);
		endpoint = Endpoint.publish(url, this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public WrapperArrayList listarDepartamentos() throws NoHayEntidadesParaListarException {

		return WrapperArrayList.fromStringArrayList(controladorDepartamento.listarDepartamentos());
	}
	
	@WebMethod
	public void darDeAltaDepartamento(String nombre, String descripcion, String url)
			throws CampoInvalidoException, EntidadRepetidaException {

		controladorDepartamento.darDeAltaDepartamento(nombre, descripcion, url);
	}
	
	

}
