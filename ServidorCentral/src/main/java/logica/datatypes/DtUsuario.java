package logica.datatypes;

import jakarta.xml.bind.annotation.XmlType;
import org.joda.time.*;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String fechaNacimiento;
	private String imagen;
	private String password;
	private List<String> seguidos = new ArrayList<>();
	private List<String> seguidores = new ArrayList<>();

	public DtUsuario() {
	}
	
	public DtUsuario(String nickname, String nombre, String apellido, String email, String fechaNacimiento, String imagen, String password) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen= imagen;
		this.password= password;
	}

	// ----------------------------------------------------------------
	// Getters y Setters
	// ----------------------------------------------------------------


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(List<String> seguidos) {
		this.seguidos = seguidos;
	}

	public List<String> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<String> seguidores) {
		this.seguidores = seguidores;
	}
	
	
	// ---------------------------------------------------------------

	public Boolean loSigue(String nickname){
		return this.getSeguidos().contains(nickname);
	}
		
	
	@Override
	public int hashCode() {
		return Objects.hash(apellido, email, fechaNacimiento, imagen, nickname, nombre, password, seguidores, seguidos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtUsuario other = (DtUsuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(email, other.email)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nickname, other.nickname) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(seguidores, other.seguidores)
				&& Objects.equals(seguidos, other.seguidos);
	}

	@Override
	public String toString() {
		return "DtUsuario [nickname=" + nickname + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", imagen=" + imagen + ", password=" + password
				+ ", seguidos=" + seguidos + ", seguidores=" + seguidores + "]";
	}
	
	
	
	
	
	
}
