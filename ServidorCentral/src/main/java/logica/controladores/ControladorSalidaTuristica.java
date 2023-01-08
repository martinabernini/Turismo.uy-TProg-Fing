package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.MaximoDeTuristasAlcanzadoException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtSalidaTuristica;
import logica.entidades.ActividadTuristica;
import logica.entidades.InscripcionSalida;
import logica.entidades.SalidaTuristica;
import logica.entidades.Turista;
import logica.entidades.Usuario;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IManejadorActividadTuristica;
import logica.interfaces.IManejadorInscripcionSalida;
import logica.interfaces.IManejadorSalidaTuristica;
import logica.interfaces.IManejadorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

public class ControladorSalidaTuristica implements IControladorSalidaTuristica {

    private final IManejadorSalidaTuristica manejadorSalidaTuristica;
    private final IManejadorUsuario manejadorUsuario;
    private final IManejadorInscripcionSalida manejadorInscripcionSalida;
    private final IManejadorActividadTuristica manejadorActividadTuristica;
    private final IValidador validador;

    public ControladorSalidaTuristica(IManejadorSalidaTuristica manejadorSalidaTuristica,
                                      IManejadorUsuario manejadorUsuario, IManejadorInscripcionSalida manejadorInscripcionSalida,
                                      IManejadorActividadTuristica manejadorActividadTuristica, IValidador validador) {
        this.manejadorSalidaTuristica = manejadorSalidaTuristica;
        this.manejadorUsuario = manejadorUsuario;
        this.manejadorInscripcionSalida = manejadorInscripcionSalida;
        this.manejadorActividadTuristica = manejadorActividadTuristica;
        this.validador = validador;
    }

    public void darDeAltaSalidaTuristica(DtSalidaTuristica nuevaSalida)
            throws CampoInvalidoException, EntidadRepetidaException {

        if (validador.campoInvalidoAltaSalida(nuevaSalida)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoAltaSalida(nuevaSalida));
        }

        if (manejadorSalidaTuristica.contains(nuevaSalida.getNombreSalida())) {
            throw new EntidadRepetidaException("Ya hay una salida registrada con ese nombre");
        }

        if (!manejadorActividadTuristica.contains(nuevaSalida.getNombreActividad())) {
            throw new CampoInvalidoException("No hay actividad con ese nombre");
        }

        ActividadTuristica actividad = manejadorActividadTuristica.find(nuevaSalida.getNombreActividad());

        // Salida con link a Actividad
//        SalidaTuristica salida = new SalidaTuristica(nuevaSalida.getNombreSalida(),
//                nuevaSalida.getCantidadMaximaTuristas(), LocalDate.parse(nuevaSalida.getFechaAlta()),LocalDateTime.parse(nuevaSalida.getFechaSalida()),
//                nuevaSalida.getLugarSalida(), actividad, nuevaSalida.getImagen());

        SalidaTuristica salida = new SalidaTuristica();
        salida.setNombre(nuevaSalida.getNombreSalida());
        salida.setCantidadMaximaTuristas(nuevaSalida.getCantidadMaximaTuristas());
        salida.setFechaAlta(LocalDate.parse(nuevaSalida.getFechaAlta()));
        salida.setFechaSalida(LocalDateTime.parse(nuevaSalida.getFechaSalida()));
        salida.setLugarSalida(nuevaSalida.getLugarSalida());
        salida.setActividad(actividad);
        if (nuevaSalida.getImagen() == null || nuevaSalida.getImagen().isEmpty()) {
            salida.setImagen("salida/_default.jpg");
        } else {
            salida.setImagen(nuevaSalida.getImagen());
        }


        // Link de Actividad a Salida
        actividad.agregarSalidaAsociada(salida);
        manejadorActividadTuristica.add(actividad);

        // Link de manejador a Salida
        manejadorSalidaTuristica.add(salida);
    }

    public ArrayList<String> listarSalidasAsociadasAActividadTuristica(String nombreActividad)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombreActividad)) {
            throw new CampoInvalidoException("Nombre de actividad invalido");
        }

        SalidaTuristica[] salidas = manejadorSalidaTuristica.getAllAsociadasAActividadTuristica(nombreActividad);

        if (salidas == null) {
            throw new NoHayEntidadesParaListarException("No hay salidas para listar");
        }


        ArrayList<String> nombreSalidas = new ArrayList<>();

        for (int i = 0; i < salidas.length; i++) {
            nombreSalidas.add(salidas[i].getNombre());
        }

        return nombreSalidas;

    }

    public DtSalidaTuristica getSalidaTuristica(String nombre) throws EntidadNoExisteException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre salida invalido");
        }

        if (!manejadorSalidaTuristica.contains(nombre)) {
            throw new EntidadNoExisteException("No existe salida con ese nombre");
        }

        return manejadorSalidaTuristica.find(nombre).newDataType();
    }

    public ArrayList<String> listarSalidasVigentesAsociadasAActividadTuristica(String nombreActividad)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombreActividad)) {
            throw new CampoInvalidoException("Nombre de actividad invalido");
        }

        SalidaTuristica[] salidas = manejadorSalidaTuristica.getAllVigentesAsociadasAActividad(nombreActividad);

        if (salidas == null) {
            throw new NoHayEntidadesParaListarException("No hay salidas para listar");
        }

        ArrayList<String> nombreSalidas = new ArrayList<>();

        for (int i = 0; i < salidas.length; i++) {
            nombreSalidas.add(salidas[i].getNombre());
        }

        return nombreSalidas;

    }

    public void inscribirTuristaASalidaTuristica(DtInscripcionSalida nuevaInscripcion)
            throws EntidadRepetidaException, CampoInvalidoException, MaximoDeTuristasAlcanzadoException {

        if (validador.campoInvalidoInscripcionASalida(nuevaInscripcion)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoInscripcionASalida(nuevaInscripcion));
        }

        if (!manejadorSalidaTuristica.contains(nuevaInscripcion.getNombreSalidaTuristica())) {
            throw new CampoInvalidoException(
                    "No hay salida turistica con ese nombre: " + nuevaInscripcion.getNombreSalidaTuristica());
        }

        if (!manejadorUsuario.contains(nuevaInscripcion.getNickname())) {
            throw new CampoInvalidoException("No hay usuario con ese nickname");
        }

        Usuario usuario = manejadorUsuario.find(nuevaInscripcion.getNickname());

        if (!(usuario instanceof Turista)) {
            throw new CampoInvalidoException("No hay turista con ese nickname");
        }

        if (validador.usuarioYaEstaRegistradoASalida((Turista) usuario, nuevaInscripcion)) {
            throw new EntidadRepetidaException("Este usuario ya tiene una inscripcion a la salida seleccionada");
        }

        Turista turista = (Turista) usuario;

        SalidaTuristica salida = manejadorSalidaTuristica.find(nuevaInscripcion.getNombreSalidaTuristica());

        if (salida.cuposDisponibles() <= 0) {
            throw new MaximoDeTuristasAlcanzadoException("No hay cupos disponibles para esa salida");
        }

        System.out.println("MARTINA, TE COMENTO QUE LOS CUPOS SON: " + salida.cuposDisponibles() + " Y LOS QUE QUIERO INSCRIBIR SON: " + nuevaInscripcion.getCantidadTuristas());

        if (salida.cuposDisponibles() < nuevaInscripcion.getCantidadTuristas()) {
            throw new MaximoDeTuristasAlcanzadoException("Solo quedan " + Integer.toString(salida.cuposDisponibles())
                    + " cupos disponibles para esa salida");
        }

        salida.incrementarCantidadInscriptos(nuevaInscripcion.getCantidadTuristas());
        manejadorSalidaTuristica.update(salida);

        // Se crea InscripcionSalida con link a Salida
        InscripcionSalida inscripcion = new InscripcionSalida();
        inscripcion.setIdentificador(manejadorInscripcionSalida.count());
        inscripcion.setFechaInscripcion(LocalDate.parse(nuevaInscripcion.getFechaInscripcion()));
        inscripcion.setCantidadTuristas(nuevaInscripcion.getCantidadTuristas());
        inscripcion.setSalida(salida);
        inscripcion.setCostoTotal(calcularCosto(salida, nuevaInscripcion.getCantidadTuristas()));

        // Link turista a InscripcionSalida
        turista.agregarInscripcionASalida(inscripcion);
        manejadorUsuario.add(turista);

        // Link manejador a InscripcionSalida
        manejadorInscripcionSalida.add(inscripcion);

    }

    @Override
    public boolean existeSalidaConNombre(String nombre) {
        if (nullEmptyOrBlank(nombre)) {
            return false;
        }

        return manejadorSalidaTuristica.contains(nombre);
    }

    // ---------------------------------------------------------------------------------------------

    private Float calcularCosto(SalidaTuristica salida, Integer cantidadTuristas) {
        Float costoPorPersona = salida.getCostoPorPersona();
        return costoPorPersona * cantidadTuristas;
    }

    private Boolean nullEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

}
