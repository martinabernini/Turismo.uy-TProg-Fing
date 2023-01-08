package logica.entidades;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.DtUsuario;

public abstract class Usuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private LocalDate fechaNacimiento;
	private String imagen;
	private String password;
	private Map<String, Usuario> seguidos = new HashMap<>();
	private Map<String, Usuario> seguidores = new HashMap<>();

	

	public Usuario() {
	}
	
	public Usuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, String imagen, String password) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen=imagen;
		this.password=password;
	}

	// -----------------------------------------------------------------
	// Getters y Setters
	// -----------------------------------------------------------------

	public String getNickname() {
		return nickname;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getImagen() {
		return imagen;
	}

	public String getPassword() {
		return password;
	}
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setFechaNacimiento(LocalDate fecha) {
		this.fechaNacimiento = fecha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Map<String, Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(Map<String, Usuario> seguidos) {
		this.seguidos = seguidos;
	}

	public Map<String, Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Map<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	//----------- funciones varias ------------------
	
	public void agregarSeguidor(Usuario seguidor) {
        this.seguidores.put(seguidor.getNickname(), seguidor);
    }
	
	public void seguirUsuario(Usuario seguido) {
        this.seguidos.put(seguido.getNickname(), seguido);
    }
	
	public void quitarSeguidor(Usuario seguidor) {
        this.seguidores.remove(seguidor.getNickname());
    }
	
	public void dejarDeSeguirUsuario(Usuario seguido) {
        this.seguidos.remove(seguido.getNickname());
    }
	
	public Boolean loSigo(Usuario usuario){
		return this.seguidos.containsKey(usuario.getNickname());
	}

	// -----------------------------------------------------------------
	public abstract DtUsuario newDataType();

	@Override
	public String toString() {
		return "Usuario [nickname=" + nickname + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", imagen=" + imagen  + "]";
	}
	
}
