package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.entidades.Proveedor;
import logica.entidades.Turista;
import logica.entidades.Usuario;
import logica.interfaces.IManejadorUsuario;

public class ManejadorUsuario implements IManejadorUsuario {

    private Map<String, Usuario> usuarios;

    private Map<String, String> emailNickname;

    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuarios = new HashMap<>();
        emailNickname = new HashMap<>();
    }

    public static ManejadorUsuario getInstance() {
        if (instancia == null) instancia = new ManejadorUsuario();

        return instancia;
    }

    // ----------------------------------------------

    @Override
    public void add(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
        emailNickname.put(usuario.getEmail(), usuario.getNickname());
    }

    @Override
    public void update(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
    }

    @Override
    public Usuario[] getAll() {
        if (usuarios.isEmpty()) {
            return null;
        }

        return usuarios.values().toArray(new Usuario[0]);
    }

    @Override
    public Usuario find(String nickname) {
        return usuarios.get(nickname);
    }

    @Override
    public Usuario findByEmail(String email) {
        String nickname = emailNickname.get(email);
        return usuarios.get(nickname);
    }


    @Override
    public boolean contains(String nickname) {
        return usuarios.containsKey(nickname);
    }

    @Override
    public Proveedor[] getAllProveedores() {

        List<Proveedor> listaProveedores = new ArrayList<>();

        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof Proveedor) {
                listaProveedores.add((Proveedor) usuario);
            }
        }

        if (listaProveedores.isEmpty()) {
            return null;
        }

        return listaProveedores.toArray(new Proveedor[0]);
    }

    @Override
    public Turista[] getAllTuristas() {

        List<Turista> listaTuristas = new ArrayList<>();

        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof Turista) {
                listaTuristas.add((Turista) usuario);
            }
        }

        if (listaTuristas.isEmpty()) {
            return null;
        }

        return listaTuristas.toArray(new Turista[0]);
    }

    @Override
    public boolean emailRegistrado(String email) {
        return emailNickname.containsKey(email);
    }

    @Override
    public void removeAll() {
        usuarios.clear();
        emailNickname.clear();
    }
}
