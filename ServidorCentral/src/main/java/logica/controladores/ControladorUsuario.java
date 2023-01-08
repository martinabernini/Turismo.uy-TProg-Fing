package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtTurista;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtUsuario;
import logica.entidades.Proveedor;
import logica.entidades.Turista;
import logica.entidades.Usuario;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IManejadorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.Constantes;
import logica.validacion.MensajeError;

public class ControladorUsuario implements IControladorUsuario {

    private IManejadorUsuario manejadorUsuario;
    private final IValidador validador;

    public ControladorUsuario(IManejadorUsuario manejadorUsuario, IValidador validador) {
        this.manejadorUsuario = manejadorUsuario;
        this.validador = validador;
    }

    // ----------------------------------------------------------------
    // ----------------------------------------------------------------

    public void darDeAltaUsuario(DtUsuario nuevoUsuario) throws CampoInvalidoException, EntidadRepetidaException {

        if (validador.campoInvalidoAltaUsuario(nuevoUsuario)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoAltaUsuario(nuevoUsuario));
        }

        if (manejadorUsuario.contains(nuevoUsuario.getNickname())) {
            throw new EntidadRepetidaException("Ya hay un usuario registrado con ese nickname");
        }

        if (manejadorUsuario.emailRegistrado(nuevoUsuario.getEmail())) {
            throw new EntidadRepetidaException("Ya hay un usuario registrado con ese mail");
        }

        if (nuevoUsuario instanceof DtTurista) {
            agregarTurista((DtTurista) nuevoUsuario);
            return;
        }

        agregarProveedor((DtProveedor) nuevoUsuario);

    }
    
    public void relacionSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException{
    	
    	if (nullEmptyOrBlank(nickUsuarioSeguido) || nullEmptyOrBlank(nickUsuarioSeguidor)) {
            throw new CampoInvalidoException("Nickname invalido");
        }

        if (!manejadorUsuario.contains(nickUsuarioSeguido) || !manejadorUsuario.contains(nickUsuarioSeguidor)) {
            throw new EntidadNoExisteException("No existe usuario con ese nombre");
        }
    	
    	Usuario seguido = manejadorUsuario.find(nickUsuarioSeguido); //al que lo siguen
    	Usuario seguidor = manejadorUsuario.find(nickUsuarioSeguidor); //el que sigue a alguien
    	
    	if (!seguidor.loSigo(seguido)) {
    		seguido.agregarSeguidor(seguidor);
        	seguidor.seguirUsuario(seguido); 
        } 	
    }
    
    
    
    	public void relacionDejarDeSeguirUsuario(String nickUsuarioSeguido, String nickUsuarioSeguidor) throws EntidadNoExisteException, CampoInvalidoException{
    	
    	if (nullEmptyOrBlank(nickUsuarioSeguido) || nullEmptyOrBlank(nickUsuarioSeguidor)) {
            throw new CampoInvalidoException("Nickname invalido");
        }

        if (!manejadorUsuario.contains(nickUsuarioSeguido) || !manejadorUsuario.contains(nickUsuarioSeguidor)) {
            throw new EntidadNoExisteException("No existe usuario con ese nombre");
        }
    	
    	Usuario seguido = manejadorUsuario.find(nickUsuarioSeguido); //al que lo dejan de seguir
    	Usuario seguidor = manejadorUsuario.find(nickUsuarioSeguidor); //el que deja de seguir a alguien
    	
    	if (seguidor.loSigo(seguido)) {
    		seguido.quitarSeguidor(seguidor);
        	seguidor.dejarDeSeguirUsuario(seguido);
    	}
    	
    	
    }

    public ArrayList<String> listarUsuarios() throws NoHayEntidadesParaListarException {

        Usuario[] listaUsuarios = manejadorUsuario.getAll();

        if (listaUsuarios == null || listaUsuarios.length == 0)
            throw new NoHayEntidadesParaListarException("No hay usuarios para listar");

        ArrayList<String> listaNicknames = new ArrayList<>();

        for (int i = 0; i < listaUsuarios.length; i++) {
            listaNicknames.add(listaUsuarios[i].getNickname());
        }

        return listaNicknames;
    }

    public DtUsuario getUsuario(String nickname) throws EntidadNoExisteException, CampoInvalidoException {

        if (nullEmptyOrBlank(nickname)) {
            throw new CampoInvalidoException("Nickname invalido");
        }

        if (!manejadorUsuario.contains(nickname)) {
            throw new EntidadNoExisteException("No existe usuario con ese nombre");
        }

        return manejadorUsuario.find(nickname).newDataType();

    }

    public DtUsuario getUsuarioLogin(String nicknameOrEmail) throws EntidadNoExisteException, CampoInvalidoException {

        if (nullEmptyOrBlank(nicknameOrEmail)) {
            throw new CampoInvalidoException("Nickname o email invalido 1");
        }

        if (validador.isEmail(nicknameOrEmail)) {
            Usuario usuario = manejadorUsuario.findByEmail(nicknameOrEmail);
            if (usuario == null) {
                throw new EntidadNoExisteException("No existe usuario con ese email");
            }
            return usuario.newDataType();
        }

        if (validador.isValidNickname(nicknameOrEmail)) {
            Usuario usuario = manejadorUsuario.find(nicknameOrEmail);
            if (usuario == null) {
                throw new EntidadNoExisteException("No existe usuario con ese nickname");
            }
            return usuario.newDataType();
        }

        throw new CampoInvalidoException("Nickname o email invalido");
    }

    public void modificarUsuario(DtUsuario usuarioModificado) throws CampoInvalidoException {

        if (validador.campoInvalidoModificarUsuario(usuarioModificado)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoModificarUsuario(usuarioModificado));
        }

        if (!manejadorUsuario.contains(usuarioModificado.getNickname())) {
            throw new CampoInvalidoException("No hay usuario con ese nickname en el sistema");
        }

        Usuario viejoUsuario = manejadorUsuario.find(usuarioModificado.getNickname());

        if (usuarioModificado instanceof DtTurista) {

            DtTurista dtTuristaModificado = (DtTurista) usuarioModificado;

            if (!(viejoUsuario instanceof Turista)) {
                throw new CampoInvalidoException("No hay turista con ese nickname en el sistema");
            }

            Turista viejoTurista = (Turista) viejoUsuario;

            viejoTurista.setNombre(dtTuristaModificado.getNombre());
            viejoTurista.setApellido(dtTuristaModificado.getApellido());
            viejoTurista.setFechaNacimiento(LocalDate.parse(dtTuristaModificado.getFechaNacimiento()));
            viejoTurista.setImagen(dtTuristaModificado.getImagen());
            if (dtTuristaModificado.getPassword() != null && !dtTuristaModificado.getPassword().isEmpty()) {
                viejoTurista.setPassword(dtTuristaModificado.getPassword());
            }
            viejoTurista.setNacionalidad(dtTuristaModificado.getNacionalidad());
            manejadorUsuario.update(viejoTurista);
            return;
        }

        DtProveedor dtProveedorModificado = (DtProveedor) usuarioModificado;

        if (!(viejoUsuario instanceof Proveedor)) {
            throw new CampoInvalidoException("No hay proveedor con ese nickname en el sistema");
        }

        Proveedor viejoProveedor = (Proveedor) viejoUsuario;

        viejoProveedor.setNombre(dtProveedorModificado.getNombre());
        viejoProveedor.setApellido(dtProveedorModificado.getApellido());
        viejoProveedor.setFechaNacimiento(LocalDate.parse(dtProveedorModificado.getFechaNacimiento()));
        viejoProveedor.setImagen(dtProveedorModificado.getImagen());
        if (dtProveedorModificado.getPassword() != null && !dtProveedorModificado.getPassword().isEmpty()) {
            viejoProveedor.setPassword(dtProveedorModificado.getPassword());
        }
        viejoProveedor.setDescripcion(dtProveedorModificado.getDescripcion());
        viejoProveedor.setUrlSitioWeb(dtProveedorModificado.getUrlSitioWeb());

        manejadorUsuario.update(viejoProveedor);
    }

    public ArrayList<String> listarTuristas() throws NoHayEntidadesParaListarException {

        Turista[] listaUsuarios = manejadorUsuario.getAllTuristas();

        if (listaUsuarios == null || listaUsuarios.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay turistas para listar");
        }

        ArrayList<String> listaNicknames = new ArrayList<>();

        for (int i = 0; i < listaUsuarios.length; i++) {
            listaNicknames.add(listaUsuarios[i].getNickname());
        }

        return listaNicknames;
    }

    public ArrayList<String> listarProveedores() throws NoHayEntidadesParaListarException {

        Proveedor[] listaUsuarios = manejadorUsuario.getAllProveedores();

        if (listaUsuarios == null || listaUsuarios.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay proveedores para listar");
        }

        ArrayList<String> listaNicknames = new ArrayList<>();

        for (int i = 0; i < listaUsuarios.length; i++) {
            listaNicknames.add(listaUsuarios[i].getNickname());
        }

        return listaNicknames;
    }

    public boolean esLoginValido(String nicknameOrEmail, String password) {
        if (nullEmptyOrBlank(nicknameOrEmail) || nullEmptyOrBlank(password)) {
            return false;
        }

        // Si no es un email ni un nickname, no es valido
        if (!nicknameOrEmail.matches(Constantes.getNicknameRegex()) && !nicknameOrEmail.matches(Constantes.getEmailRegex())) {
            return false;
        }

        if (validador.isEmail(nicknameOrEmail)) {
            if (!manejadorUsuario.emailRegistrado(nicknameOrEmail)) {
                return false;
            }
            Usuario usuario = manejadorUsuario.findByEmail(nicknameOrEmail);
            String realPassword = usuario.getPassword();
            return password.equals(realPassword);
        }

        if (!manejadorUsuario.contains(nicknameOrEmail)) {
            return false;
        }

        Usuario usuario = manejadorUsuario.find(nicknameOrEmail);
        String realPassword = usuario.getPassword();
        return password.equals(realPassword);

    }

    // ----------------------------------------------------------------
    // Metodos auxiliares
    // ----------------------------------------------------------------

    private void agregarProveedor(DtProveedor nuevoProveedor) {

        Proveedor proveedor = new Proveedor();

        proveedor.setNickname(nuevoProveedor.getNickname());
        proveedor.setNombre(nuevoProveedor.getNombre());
        proveedor.setApellido(nuevoProveedor.getApellido());
        proveedor.setEmail(nuevoProveedor.getEmail());
        proveedor.setFechaNacimiento(LocalDate.parse(nuevoProveedor.getFechaNacimiento()));
        proveedor.setDescripcion(nuevoProveedor.getDescripcion());
        proveedor.setUrlSitioWeb(nuevoProveedor.getUrlSitioWeb());
        proveedor.setImagen(nuevoProveedor.getImagen());
        proveedor.setPassword(nuevoProveedor.getPassword());
        

        manejadorUsuario.add(proveedor);
    }

    private void agregarTurista(DtTurista nuevoTurista) {

        Turista turista = new Turista();

        turista.setNickname(nuevoTurista.getNickname());
        turista.setNombre(nuevoTurista.getNombre());
        turista.setApellido(nuevoTurista.getApellido());
        turista.setEmail(nuevoTurista.getEmail());
        turista.setFechaNacimiento(LocalDate.parse(nuevoTurista.getFechaNacimiento()));
        turista.setNacionalidad(nuevoTurista.getNacionalidad());
        turista.setPassword(nuevoTurista.getPassword());
        if (nuevoTurista.getImagen() == null || nuevoTurista.getImagen().isEmpty()) {
            turista.setImagen("usuario/_default.jpg");
        } else {
            turista.setImagen(nuevoTurista.getImagen());
        }

        manejadorUsuario.add(turista);
    }

    @Override
    public boolean estaEnUsoNick(String nickname) {
        return manejadorUsuario.contains(nickname);
    }

    @Override
    public boolean estaEnUsoEmail(String email) {
        return manejadorUsuario.emailRegistrado(email);
    }

    private Boolean nullEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

}
