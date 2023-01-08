package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.datatypes.DtProveedor;
import logica.datatypes.DtUsuario;

public class Proveedor extends Usuario {

	// Atributos
	private String descripcion;
	private String urlSitioWeb;
	private List<ActividadTuristica> actividadesTuristicas = new ArrayList<>();

	// Constructor

	public Proveedor() {
		super();
	}

	public Proveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento,
			String descripcion, String urlSitioWeb, String imagen, String password) {

		super(nickname, nombre, apellido, email, fechaNacimiento, imagen, password);

		this.descripcion = descripcion;
		this.urlSitioWeb = urlSitioWeb;

	}
	// ----------------------------------------------------------------
	// Getters y setters
	// ----------------------------------------------------------------

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlSitioWeb() {
		return urlSitioWeb;
	}

	public void setUrlSitioWeb(String urlSitioWeb) {
		this.urlSitioWeb = urlSitioWeb;
	}

	public List<ActividadTuristica> getActividadesTuristicas() {
		return actividadesTuristicas;
	}

	public void setActividadesTuristicas(List<ActividadTuristica> actividadesTuristicas) {
		this.actividadesTuristicas = actividadesTuristicas;
	}

	// ----------------------------------------------------------------

	public DtUsuario newDataType() {
		String[] actividades = null;
		if (this.actividadesTuristicas != null) {
			actividades = new String[this.actividadesTuristicas.size()];
			for (int i = 0; i < this.actividadesTuristicas.size(); i++) {
				actividades[i] = this.actividadesTuristicas.get(i).getNombre();
			}
		}

		DtProveedor dtProveedor = new DtProveedor();
		dtProveedor.setNickname(this.getNickname());
		dtProveedor.setNombre(this.getNombre());
		dtProveedor.setApellido(this.getApellido());
		dtProveedor.setEmail(this.getEmail());
		dtProveedor.setFechaNacimiento(this.getFechaNacimiento().toString());
		dtProveedor.setDescripcion(this.getDescripcion());
		dtProveedor.setUrlSitioWeb(this.getUrlSitioWeb());
		
		
		List<String> resActividades = new ArrayList<>();
        for(ActividadTuristica actividad : this.getActividadesTuristicas()){
        	resActividades.add(actividad.getNombre());
        }
        dtProveedor.setActividadesTuristicas(resActividades);

		
		
		
		dtProveedor.setImagen(this.getImagen());
		dtProveedor.setPassword(this.getPassword());
		
		List<String> resSeguidos = new ArrayList<>();
        for(String seguido : this.getSeguidos().keySet()){
        	resSeguidos.add(seguido);
        }
        dtProveedor.setSeguidos(resSeguidos);
		
        List<String> resSeguidores = new ArrayList<>();
        for(String seguido : this.getSeguidores().keySet()){
        	resSeguidores.add(seguido);
        }
        dtProveedor.setSeguidores(resSeguidores);
		
		return dtProveedor;
	}

	// ---------------------------------------------------------------------------------------------

	public void agregarActividadTuristica(ActividadTuristica nuevaActividad) {
		actividadesTuristicas.add(nuevaActividad);
	}

	@Override
	public String toString() {
		return "Proveedor [descripcion=" + descripcion + ", urlSitioWeb=" + urlSitioWeb + ", actividadesTuristicas="
				+ actividadesTuristicas + ", nickname=" + this.getNickname() + ", nombre=" + this.getNombre() + ", apellido=" + this.getApellido()
				+ ", email=" + this.getEmail() + ", fechaNacimiento=" + this.getFechaNacimiento() + ", imagen=" + this.getImagen() + ", password="
				+ this.getPassword() + "]";
	}

	// ---------------------------------------------------------------------------------------------



}
