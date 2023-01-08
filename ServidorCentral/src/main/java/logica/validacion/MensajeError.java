package logica.validacion;

import logica.datatypes.DtActividadTuristica;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtSalidaTuristica;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;

public class MensajeError {

    public static String campoInvalidoAltaUsuario(DtUsuario usuario) {

        if (usuario == null) {
            return "El usuario no puede ser nulo";
        }

        String mensajeError = "Campo invalido ";

        if (nullOrEmpty(usuario.getNombre())) {
            mensajeError += "Nombre invalido. ";
        }
        if (nullOrEmpty(usuario.getNickname()) || !isWellFormedNickname(usuario.getNickname())) {
            mensajeError += "Nickname invalido. ";
        }
        if (nullOrEmpty(usuario.getApellido())) {
            mensajeError += "Apellido invalido. ";
        }
        if (nullOrEmpty(usuario.getEmail()) || !Validador.isEmailStaticMethod(usuario.getEmail())) {
            mensajeError += "Email invalido. ";
        }
        if (usuario.getFechaNacimiento() == null) {
            mensajeError += "Fecha de nacimiento invalida. ";
        }
        if (nullOrEmpty(usuario.getPassword())) {
            mensajeError += "Password invalido. ";
        }

        if (usuario instanceof DtProveedor) {

            if (nullOrEmpty(((DtProveedor) usuario).getDescripcion())) {
                mensajeError += "Descripcion invalida. ";
            }
            return mensajeError;
        }
        if (nullOrEmpty(((DtTurista) usuario).getNacionalidad())) {
            return mensajeError + "Nacionalidad invalida. ";
        }

        if (nullOrEmpty(((DtTurista) usuario).getPassword())) {
            return mensajeError + "Password invalida. ";
        }

        return mensajeError;

    }

    private static boolean isWellFormedNickname(String string) {
        // string can have only letters, numbers, and underscores
        return string.matches(Constantes.getNicknameRegex());
    }

    public static String campoInvalidoModificarUsuario(DtUsuario usuario) {
        // de momento es igual al alta, si quisieramos otro comportamiento se puede
        // cambiar
        return campoInvalidoAltaUsuario(usuario);
    }

    public static String campoInvalidoAltaActividad(DtActividadTuristica actividad) {

        if (actividad == null) {
            return "La actividad no puede ser nula";
        }

        String mensajeError = "Campo invalido ";

        if (nullOrEmpty(actividad.getNombre())) {
            mensajeError += "Nombre invalido. ";
        }
        if (nullOrEmpty(actividad.getDescripcion())) {
            mensajeError += "Descripcion invalida. ";
        }
        if (actividad.getDuracionHrs() < 0) {
            mensajeError += "Duracion en horas invilida. ";
        }
        if (actividad.getCostoPorPersona() < 0) {
            mensajeError += "Costo por persona invilido. ";
        }
        if (nullOrEmpty(actividad.getCiudad())) {
            mensajeError += "Ciudad invilida. ";
        }
        if (actividad.getFechaAlta() == null) {
            mensajeError += "Fecha de alta invilida. ";
        }
        if (nullOrEmpty(actividad.getDepartamento())) {
            mensajeError += "Departamento invilido. ";
        }
        if (nullOrEmpty(actividad.getProovedor())) {
            mensajeError += "Proovedor invilido. ";
        }

        if (actividad.getCategorias() == null || actividad.getCategorias().size() == 0) {
            mensajeError += "Debe seleccionar al menos una categoria. ";
        }

        return mensajeError;
    }

    public static String campoInvalidoAltaSalida(DtSalidaTuristica salida) {

        if (salida == null) {
            return "La salida no puede ser nula";
        }

        String mensajeError = "Campo invalido ";

        if (nullOrEmpty(salida.getNombreActividad())) {
            mensajeError += "Nombre actividad invalido. ";
        }
        if (nullOrEmpty(salida.getNombreSalida())) {
            mensajeError += "Nombre salida invalido. ";
        }
        if (salida.getFechaSalida() == null) {
            mensajeError += "Fecha salida invalida. ";
        }
        if (salida.getFechaAlta() == null) {
            mensajeError += "Fecha alta invalida. ";
        }
        if (nullOrEmpty(salida.getLugarSalida())) {
            mensajeError += "Lugar salida invalido. ";
        }
        if (salida.getCantidadMaximaTuristas() < Constantes.getMenorCantidadMaximaDeTuristasEnSalida()) {
            mensajeError += "Cantidad maxima de turistas invalida. ";
        }
        
        
        if (salida.getFechaSalida() != null && salida.getFechaAlta() != null && java.time.LocalDateTime.parse(salida.getFechaSalida()).toLocalDate().isBefore(java.time.LocalDate.parse(salida.getFechaAlta()))) {
            mensajeError += "La fecha de salida no puede ser anterior a la fecha de alta. ";
        }
        return mensajeError;
    }

    public static String campoInvalidoInscripcionASalida(DtInscripcionSalida inscripcionSalida) {

        if (inscripcionSalida == null) {
            return "La inscripcion no puede ser nula";
        }

        String mensajeError = "Campo invalido ";

        if (nullOrEmpty(inscripcionSalida.getNickname())) {
            mensajeError += "Nickname invalido. ";
        }
        if (nullOrEmpty(inscripcionSalida.getNombreSalidaTuristica())) {
            mensajeError += "Nombre salida turistica invalido. ";
        }
        if (inscripcionSalida.getCantidadTuristas() < Constantes.getMenorCantidadDeTuristasInscribiblesASalida()) {
            mensajeError += "Cantidad turistas invalido. ";
        }
        return mensajeError;
    }

    public static String campoInvalidoAltaPaquete(DtPaqueteActividades paquete) {
        // TODO: implementar
        return "Campo invalido";
    }

    public static String campoInvalidoAltaDepartamento(String nombre, String descripcion, String url) {

        String mensajeError = "Campo invalido ";

        if (nullOrEmpty(nombre) || nombre.isBlank() || !nombre.matches(Constantes.getAlfanumericosRegexSinGuiones())) {
            mensajeError += "Nombre invalido. ";
        }
        if (nullOrEmpty(descripcion) || descripcion.isBlank()) {
            mensajeError += "Descripcion invalida. ";
        }
        if (nullOrEmpty(url) || url.isBlank()) {
            mensajeError += "Url invalida. ";
        }
        return mensajeError;
    }

    // --------------------------------------------------------------------
    // --------------------------------------------------------------------

    private static Boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
