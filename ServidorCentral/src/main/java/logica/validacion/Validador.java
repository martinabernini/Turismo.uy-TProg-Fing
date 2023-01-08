package logica.validacion;

import logica.datatypes.DtActividadTuristica;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtSalidaTuristica;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.entidades.InscripcionSalida;
import logica.entidades.Turista;
import logica.interfaces.IValidador;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import java.util.Objects;

public class Validador implements IValidador {

	@Override
	public boolean campoInvalidoAltaUsuario(DtUsuario usuario) {

		if (usuario == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(usuario.getNickname()) || nullEmptyOrBlank(usuario.getNombre())
				|| nullEmptyOrBlank(usuario.getPassword()) || nullEmptyOrBlank(usuario.getApellido())
				|| nullEmptyOrBlank(usuario.getEmail()) || usuario.getFechaNacimiento() == null) {
			return true;
		}

		// Chequeo que el nickname no contenga caracteres inválidos
		if (!isValidNickname(usuario.getNickname()) || !isEmail(usuario.getEmail())) {
			return true;
		}

		// Si es proveedor, chequeo que tenga descripción
		if (usuario instanceof DtProveedor) {
			return nullEmptyOrBlank(((DtProveedor) usuario).getDescripcion());
		}

		// Si es turista, chequeo que tenga fecha de nacimiento
		return nullEmptyOrBlank(((DtTurista) usuario).getNacionalidad());

	}

	@Override
	public boolean campoInvalidoModificarUsuario(DtUsuario usuario) {

		if (usuario == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(usuario.getNickname()) || nullEmptyOrBlank(usuario.getNombre())
				|| nullEmptyOrBlank(usuario.getApellido()) || nullEmptyOrBlank(usuario.getEmail())
				|| usuario.getFechaNacimiento() == null) {
			return true;
		}

		// Chequeo que el nickname no contenga caracteres inválidos
		if (!isValidNickname(usuario.getNickname()) || !isEmail(usuario.getEmail())) {
			return true;
		}

		// Si es proveedor, chequeo que tenga descripción
		if (usuario instanceof DtProveedor) {
			return nullEmptyOrBlank(((DtProveedor) usuario).getDescripcion());
		}

		// Si es turista, chequeo que tenga fecha de nacimiento
		return nullEmptyOrBlank(((DtTurista) usuario).getNacionalidad());
	}

	@Override
	public boolean campoInvalidoAltaActividad(DtActividadTuristica actividad) {

		if (actividad == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(actividad.getNombre()) || nullEmptyOrBlank(actividad.getDescripcion())
				|| nullEmptyOrBlank(actividad.getCiudad()) || actividad.getFechaAlta() == null
				|| nullEmptyOrBlank(actividad.getDepartamento()) || nullEmptyOrBlank(actividad.getProovedor())
				|| actividad.getCategorias() == null) {
			return true;
		}

		// Chequeo que el nombre no empiece o termine con espacios
		if (empiezaOTerminaConEspacio(actividad.getNombre())) {
			return true;
		}

		// Chequeo que el nombre no contenga caracteres inválidos
		if (!actividad.getNombre().matches(Constantes.getAlfanumericosRegex())) {
			return true;
		}

		// Relga de negocio
		return actividad.getDuracionHrs() < 0 || actividad.getCostoPorPersona() < 0
				|| actividad.getCategorias().size() == 0;
	}

	@Override
	public boolean campoInvalidoAltaSalida(DtSalidaTuristica salida) {

		if (salida == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(salida.getNombreActividad()) || nullEmptyOrBlank(salida.getNombreSalida())
				|| nullEmptyOrBlank(salida.getLugarSalida()) || salida.getFechaSalida() == null
				|| salida.getFechaAlta() == null) {
			return true;
		}

		// Chequeo que el nombre no empiece o termine con espacios
		if (empiezaOTerminaConEspacio(salida.getNombreSalida())
				|| empiezaOTerminaConEspacio(salida.getNombreActividad())) {
			return true;
		}

		// Chequeo que el nombre no contenga caracteres inválidos
		if (!salida.getNombreSalida().matches(Constantes.getAlfanumericosRegex())
				|| !salida.getNombreActividad().matches(Constantes.getAlfanumericosRegex())) {
			return true;
		}

		// Relga de negocio
		java.time.LocalDate fechaAlta = java.time.LocalDate.parse(salida.getFechaAlta());
		java.time.LocalDate fechaSalida = java.time.LocalDateTime.parse(salida.getFechaSalida()).toLocalDate();

		return salida.getCantidadMaximaTuristas() < Constantes.getMenorCantidadMaximaDeTuristasEnSalida()
				|| fechaSalida.isBefore(fechaAlta);

	}

	@Override
	public boolean campoInvalidoInscripcionASalida(DtInscripcionSalida inscripcion) {

		if (inscripcion == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(inscripcion.getNickname()) || nullEmptyOrBlank(inscripcion.getNombreSalidaTuristica())) {
			return true;
		}

		if (!isValidNickname(inscripcion.getNickname())) {
			return true;
		}

		// Chequeo que el nombre no empiece o termine con espacios
		if (empiezaOTerminaConEspacio(inscripcion.getNombreSalidaTuristica())
				|| empiezaOTerminaConEspacio(inscripcion.getNickname())) {
			return true;
		}

		// Chequeo que el nombre no contenga caracteres inválidos
		if (!inscripcion.getNombreSalidaTuristica().matches(Constantes.getAlfanumericosRegex())
				|| !isValidNickname(inscripcion.getNickname())) {
			return true;
		}

		// Relga de negocio
		return inscripcion.getCantidadTuristas() < Constantes.getMenorCantidadDeTuristasInscribiblesASalida();
	}

	/*
	 * Precondición: el turista no es null Precondición: la inscripción no es null
	 */
	@Override
	public boolean usuarioYaEstaRegistradoASalida(Turista turista, DtInscripcionSalida nuevaInscripcion) {

		// Chequeo que el turista no esté ya inscripto a la salida
		for (InscripcionSalida inscripcion : turista.getInscripciones().values()) {
			// Si el turista ya está inscripto a la salida, devuelvo true
			if (Objects.equals(inscripcion.getSalida().getNombre(), nuevaInscripcion.getNombreSalidaTuristica())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean campoInvalidoAltaPaquete(DtPaqueteActividades paquete) {

		if (paquete == null)
			return true;

		// Chequeo null o vacío
		if (nullEmptyOrBlank(paquete.getNombre()) || nullEmptyOrBlank(paquete.getDescripcion())
				|| nullEmptyOrBlank(paquete.getImagen()) || paquete.getFechaAlta() == null) {
			return true;
		}

		// Chequeo que el nombre no empiece o termine con espacios
		if (empiezaOTerminaConEspacio(paquete.getNombre())) {
			return true;
		}

		// Chequeo que el nombre no contenga caracteres inválidos
		if (!paquete.getNombre().matches(Constantes.getAlfanumericosRegex())) {
			return true;
		}

		// Relga de negocio
		// Descuento debe ser entre 0 y 100 (inclusive) porque es un porcentaje
		if (paquete.getDescuento() < 0 || paquete.getDescuento() > 100 || paquete.getValidezEnDias() < 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean campoInvalidoAltaDepartamento(String nombre, String descripcion, String url) {

		// Chequeo null o vacío
		if (nullEmptyOrBlank(nombre) || nullEmptyOrBlank(descripcion) || nullEmptyOrBlank(url)) {
			return true;
		}

		// Chequeo que el nombre no empiece o termine con espacios
		if (empiezaOTerminaConEspacio(nombre)) {
			return true;
		}

		return !nombre.matches(Constantes.getAlfanumericosRegexSinGuiones());
	}

	@Override
	public boolean isEmail(String string) {
		return isEmailStaticMethod(string);
	}

	@Override
	public boolean isValidNickname(String string) {
		return isValidNicknameStaticMethod(string);
	}

	public static boolean isEmailStaticMethod(String string) {
		if (string == null) {
			return false;
		}
		return string.matches(Constantes.getEmailRegex());
	}

	public static boolean isValidNicknameStaticMethod(String string) {
		if (string == null) {
			return false;
		}
		return string.matches(Constantes.getNicknameRegex());
	}

	@Override
	public boolean campoInvalidoComprarPaquete(String nombrePaquete, String nombreCliente) {

		if (nullEmptyOrBlank(nombrePaquete) || nullEmptyOrBlank(nombreCliente)) {
			return true;
		}

		return !isValidNickname(nombreCliente);
	}

	@Override
	public boolean campoInvalidoAltaCategoria(String nombre) {

		if (nullEmptyOrBlank(nombre)) {
			return true;
		}

		if (empiezaOTerminaConEspacio(nombre)) {
			return true;
		}

		return !nombre.matches(Constantes.getAlfanumericosRegexSinGuiones());
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------
	private static boolean empiezaOTerminaConEspacio(String string) {
		return string.startsWith(" ") || string.endsWith(" ");
	}

	private boolean nullEmptyOrBlank(String string) {
		return string == null || string.isEmpty() || string.isBlank();
	}

}
