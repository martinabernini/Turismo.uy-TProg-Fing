package webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.MaximoDeTuristasAlcanzadoException;
import excepciones.NoHayEntidadesParaListarException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.datatypes.*;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IPublicador;
import logica.interfaces.IControladorSalidaTuristica;
import utils.ConfigHelper;
import utils.Fabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorControladorSalida implements IPublicador {

	private Endpoint endpoint = null;

	private IControladorSalidaTuristica controladorSalida = Fabrica.getInstance().getIControladorSalidaTuristica();

	// Constructor
	public PublicadorControladorSalida() {
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar() {
		String url = ConfigHelper.getWebServiceBaseURL() + "/ControladorSalida";
		System.out.println("Publicando servicio de ControladorSalida en: " + url);

		endpoint = Endpoint.publish(url, this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public void darDeAltaSalidaTuristica(DtSalidaTuristica nuevaSalida)
            throws CampoInvalidoException, EntidadRepetidaException {
		controladorSalida.darDeAltaSalidaTuristica(nuevaSalida);
	}
	
	@WebMethod
	public WrapperArrayList listarSalidasAsociadasAActividadTuristica(String nombreActividad)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {
		return WrapperArrayList.fromStringArrayList(controladorSalida.listarSalidasAsociadasAActividadTuristica(nombreActividad));
	}
	
	@WebMethod
	public DtSalidaTuristica getSalidaTuristica(String nombre) throws EntidadNoExisteException, CampoInvalidoException {
		return controladorSalida.getSalidaTuristica(nombre);
	}
	
	@WebMethod
	public WrapperArrayList listarSalidasVigentesAsociadasAActividadTuristica(String nombreActividad)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {
		return WrapperArrayList.fromStringArrayList(controladorSalida.listarSalidasVigentesAsociadasAActividadTuristica(nombreActividad));
	}
	
	@WebMethod
	public void inscribirTuristaASalidaTuristica(DtInscripcionSalida nuevaInscripcion)
            throws EntidadRepetidaException, CampoInvalidoException, MaximoDeTuristasAlcanzadoException {
		controladorSalida.inscribirTuristaASalidaTuristica(nuevaInscripcion);
	}
	
	@WebMethod
	public boolean existeSalidaConNombre(String nombre) {
		return controladorSalida.existeSalidaConNombre(nombre);
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
