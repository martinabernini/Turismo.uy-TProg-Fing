package logica.interfaces;

import logica.datatypes.DtActividadTuristica;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.DtSalidaTuristica;
import logica.datatypes.DtUsuario;
import logica.entidades.Turista;

public interface IValidador {

    boolean campoInvalidoAltaUsuario(DtUsuario usuario);

    boolean campoInvalidoModificarUsuario(DtUsuario usuario);

    boolean campoInvalidoAltaActividad(DtActividadTuristica actividad);

    boolean campoInvalidoAltaSalida(DtSalidaTuristica salida);

    boolean campoInvalidoInscripcionASalida(DtInscripcionSalida inscripcion);

    boolean usuarioYaEstaRegistradoASalida(Turista turista, DtInscripcionSalida nuevaInscripcion);

    boolean campoInvalidoAltaPaquete(DtPaqueteActividades paquete);

    boolean campoInvalidoAltaDepartamento(String nombre, String descripcion, String url);

    boolean isEmail(String string);

    boolean isValidNickname(String string);

    boolean campoInvalidoComprarPaquete(String nombrePaquete, String nombreCliente);

    boolean campoInvalidoAltaCategoria(String nombre);

}
