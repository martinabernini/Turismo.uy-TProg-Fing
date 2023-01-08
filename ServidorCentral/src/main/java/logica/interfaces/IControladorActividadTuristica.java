package logica.interfaces;

import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtActividadTuristica;
//import logica.datatypes.DtInscripcionSalida;

public interface IControladorActividadTuristica {

    void darDeAltaActividadTuristica(DtActividadTuristica nuevaActividad)
            throws CampoInvalidoException, EntidadRepetidaException;

    ArrayList<String> listarActividadesAsociadasADepartamento(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException;

    ArrayList<String> listarActividadesAsociadasADepartamentoConfirmadas(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException;

    ArrayList<String> listarActividadesAsociadasACategoriaConfirmadas(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException;

    DtActividadTuristica getActividadTuristica(String nombre)
            throws EntidadNoExisteException, CampoInvalidoException;

    ArrayList<String> listarActividadesAsocadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete)
            throws NoHayEntidadesParaListarException, CampoInvalidoException;

    ArrayList<String> listarAllActividades()
            throws NoHayEntidadesParaListarException;

    ArrayList<String> listarActividadesEnEstadoAgregada()
            throws NoHayEntidadesParaListarException;

    ArrayList<String> listarActividadesEnEstadoConfirmada()
            throws NoHayEntidadesParaListarException;


    ArrayList<String> listarActividadesDeProveedorNoConfirmadas(String nombreProveedor)
            throws NoHayEntidadesParaListarException, CampoInvalidoException;


    void darDeAltaCategoria(String nombre) throws EntidadRepetidaException, CampoInvalidoException;

    ArrayList<String> listarTodasLasCategorias() throws NoHayEntidadesParaListarException;

    void rechazarAceptarActividad(String nombre, boolean aceptada) throws EntidadNoExisteException, CampoInvalidoException;

    void finalizarActividad(String nombre) throws EntidadNoExisteException, CampoInvalidoException;

    boolean existeActividadConNombre(String nombre);
}
