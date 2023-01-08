package webservices;

import java.util.ArrayList;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.WrapperArrayList;
import logica.interfaces.IControladorPaqueteActividades;
import logica.interfaces.IPublicador;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorControladorPaquete implements IPublicador {

	private Endpoint endpoint = null;

	private IControladorPaqueteActividades controladorPaquete = Fabrica.getInstance().getIControladorPaqueteActividades();

	// Constructor
	public PublicadorControladorPaquete() {
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar() {
		String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorPaquete";
		System.out.println("Publicando servicio de ControladorPaquete en: " + url);
		endpoint = Endpoint.publish(url, this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public void darDeAltaPaquete(DtPaqueteActividades nuevoPaquete)
            throws CampoInvalidoException, EntidadRepetidaException {
		controladorPaquete.darDeAltaPaquete(nuevoPaquete);
	}

	@WebMethod
	public WrapperArrayList listarPaquetes() throws NoHayEntidadesParaListarException {
		return WrapperArrayList.fromStringArrayList(controladorPaquete.listarPaquetes());
	}

	@WebMethod
	public WrapperArrayList listarPaquetesAsociadosAActividad(String nombreActividad) throws NoHayEntidadesParaListarException {
		return WrapperArrayList.fromStringArrayList(controladorPaquete.listarPaquetesAsociadosAActividad(nombreActividad));
	}

	@WebMethod
	public void ingresarActividadTuristicaAPaquete(String nombreActividad, String nombrePaquete)
            throws EntidadRepetidaException, CampoInvalidoException {

		controladorPaquete.ingresarActividadTuristicaAPaquete(nombreActividad, nombrePaquete);
	}

	@WebMethod
	public void ingresarCategoriaAPaquete(String categoria, String nombrePaquete)
            throws EntidadRepetidaException, CampoInvalidoException {

		controladorPaquete.ingresarCategoriaAPaquete(categoria, nombrePaquete);
	}

	@WebMethod
	public DtPaqueteActividades find(String nombre) throws EntidadNoExisteException, CampoInvalidoException {

		return controladorPaquete.find(nombre);
	}

	@WebMethod
	public void comprarPaquete(DtCompraPaquete compraPaquete) throws CampoInvalidoException, EntidadRepetidaException {

		controladorPaquete.comprarPaquete(compraPaquete);
	}


}
