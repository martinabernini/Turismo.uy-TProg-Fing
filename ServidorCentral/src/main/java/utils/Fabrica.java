package utils;

import cargadatos.CargaDatos;
import logica.controladores.*;
import logica.interfaces.*;
import logica.manejadores.*;
import logica.validacion.Validador;

public class Fabrica implements IFabrica{

    private Boolean logManejadores = true;
    private Boolean logControladores = true;
    private Boolean logValidacion = true;
    private IValidador validador = null;

    // ------------------------------------------------------------------------------

    private static Fabrica instancia;

    private Fabrica() {
    }

    public static Fabrica getInstance() {

        if (instancia == null) {
            instancia = new Fabrica();
        }

        return instancia;
    }

    // ------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------

    public IControladorActividadTuristica getIControladorActividadTuristica() {

        IControladorActividadTuristica controladorActividades = new ControladorActividadTuristica(getIManejadorActividadTuristica(), getIManejadorDepartamento(), getIManejadorUsuario(), getIManejadorCategoria(), getIValidador());

        if (logControladores) {
            return DynamicProxy.withLogging(controladorActividades, IControladorActividadTuristica.class);
        }

        return controladorActividades;
    }

    public IControladorDepartamento getIControladorDepartamento() {

        IControladorDepartamento controladorDepartamento = new ControladorDepartamento(getIManejadorDepartamento(), getIValidador());

        if (logControladores) {
            return DynamicProxy.withLogging(controladorDepartamento, IControladorDepartamento.class);
        }

        return controladorDepartamento;
    }

    public IControladorPaqueteActividades getIControladorPaqueteActividades() {

        IControladorPaqueteActividades controladorPaquetes = new ControladorPaqueteActividades(getIManejadorPaqueteActividades(), getIManejadorActividadTuristica(), getIManejadorUsuario(), getIManejadorCategoria(), getIValidador());

        if (logControladores) {
            return DynamicProxy.withLogging(controladorPaquetes, IControladorPaqueteActividades.class);
        }

        return controladorPaquetes;
    }

    public IControladorSalidaTuristica getIControladorSalidaTuristica() {

        IControladorSalidaTuristica controladorSalida = new ControladorSalidaTuristica(getIManejadorSalidaTuristica(), getIManejadorUsuario(), getIManejadorInscripcionSalida(), getIManejadorActividadTuristica(), getIValidador());

        if (logControladores) {
            return DynamicProxy.withLogging(controladorSalida, IControladorSalidaTuristica.class);
        }

        return controladorSalida;

    }

    public IControladorUsuario getIControladorUsuario() {

        IControladorUsuario controladorUsuario = new ControladorUsuario(getIManejadorUsuario(), getIValidador());

        if (logControladores) {
            return DynamicProxy.withLogging(controladorUsuario, IControladorUsuario.class);
        }

        return controladorUsuario;
    }

    public IControladorImagen getIControladorImagen() {
        IControladorImagen controladorImagen = new ControladorImagen();
        return controladorImagen;
    }

    // ------------------------------------------------------------------------------

    public IManejadorActividadTuristica getIManejadorActividadTuristica() {

        IManejadorActividadTuristica manejadorActividad = ManejadorActividadTuristica.getInstance(getIManejadorDepartamento());

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorActividad, IManejadorActividadTuristica.class);
        }

        return manejadorActividad;
    }

    public IManejadorDepartamento getIManejadorDepartamento() {

        IManejadorDepartamento manejadorDepartamento = ManejadorDepartamento.getInstance();

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorDepartamento, IManejadorDepartamento.class);
        }

        return manejadorDepartamento;
    }

    public IManejadorPaqueteActividades getIManejadorPaqueteActividades() {

        IManejadorPaqueteActividades manejadorPaqueteActividades = ManejadorPaqueteActividades.getInstance();

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorPaqueteActividades, IManejadorPaqueteActividades.class);
        }

        return manejadorPaqueteActividades;
    }

    public IManejadorSalidaTuristica getIManejadorSalidaTuristica() {

        IManejadorSalidaTuristica manejadorSalidaTuristica = ManejadorSalidaTuristica.getInstance(getIManejadorActividadTuristica());

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorSalidaTuristica, IManejadorSalidaTuristica.class);
        }

        return manejadorSalidaTuristica;
    }

    public IManejadorUsuario getIManejadorUsuario() {

        IManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorUsuario, IManejadorUsuario.class);
        }

        return manejadorUsuario;
    }

    public IManejadorInscripcionSalida getIManejadorInscripcionSalida() {

        IManejadorInscripcionSalida manejadorInscripcionSalida = ManejadorInscripcionSalida.getInstance();

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorInscripcionSalida, IManejadorInscripcionSalida.class);
        }

        return manejadorInscripcionSalida;
    }

    public IManejadorCategoria getIManejadorCategoria() {

        IManejadorCategoria manejadorCategoria = ManejadorCategoria.getInstance();

        if (logManejadores) {
            return DynamicProxy.withLogging(manejadorCategoria, IManejadorCategoria.class);
        }

        return manejadorCategoria;
    }

    // ------------------------------------------------------------------------------

    public IValidador getIValidador() {

        validador = new Validador();

        if (logValidacion) {
            return DynamicProxy.withLogging(validador, IValidador.class);
        }

        return validador;
    }

    public ICargaDatos getICargaDatos() {

        return new CargaDatos(
                getIControladorActividadTuristica(),
                getIControladorDepartamento(),
                getIControladorPaqueteActividades(),
                getIControladorSalidaTuristica(),
                getIControladorUsuario()
        );
    }


    // ------------------------------------------------------------------------------

    public void setLogManejadores(Boolean log) {
        logManejadores = log;
    }

    public void setLogControladores(Boolean log) {
        logControladores = log;
    }

    public void setLogValidacion(Boolean log) {
        logControladores = log;
    }

}
