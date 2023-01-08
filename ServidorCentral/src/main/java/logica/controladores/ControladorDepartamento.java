package logica.controladores;

import java.util.ArrayList;
import java.util.List;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.entidades.Departamento;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IManejadorDepartamento;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

public class ControladorDepartamento implements IControladorDepartamento {

    private final IManejadorDepartamento manejadorDepartamento;
    private final IValidador validador;


    public ControladorDepartamento(IManejadorDepartamento manejadorDepartamento, IValidador validador) {
        this.manejadorDepartamento = manejadorDepartamento;
        this.validador = validador;
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Override
    public ArrayList<String> listarDepartamentos() throws NoHayEntidadesParaListarException {

        Departamento[] listaDepartamentos = manejadorDepartamento.getAll();

        if (listaDepartamentos == null) {
            throw new NoHayEntidadesParaListarException("No hay departamentos para listar");
        }

        ArrayList<String> listaNombres = new ArrayList<>();

        for (int i = 0; i < listaDepartamentos.length; i++) {
            listaNombres.add(listaDepartamentos[i].getNombre());
        }

        return listaNombres;    
        }

    @Override
    public void darDeAltaDepartamento(String nombre, String descripcion, String url)
            throws CampoInvalidoException, EntidadRepetidaException {

        if (validador.campoInvalidoAltaDepartamento(nombre, descripcion, url)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoAltaDepartamento(nombre, descripcion, url));
        }

        if (manejadorDepartamento.contains(nombre)) {
            throw new EntidadRepetidaException("Ya hay un departamento registrado con ese nombre");
        }

        manejadorDepartamento.add(new Departamento(nombre, descripcion, url));

    }

}
