package logica.interfaces;

import logica.entidades.Proveedor;
import logica.entidades.Turista;
import logica.entidades.Usuario;

public interface IManejadorUsuario {

	void add(Usuario usuario);

	void update(Usuario usuario);

	Usuario[] getAll();

	Proveedor[] getAllProveedores();

	Turista[] getAllTuristas();

	Usuario find(String nickname);

	Usuario findByEmail(String email);

	boolean contains(String nickname);

	boolean emailRegistrado(String email);

	void removeAll();

}
