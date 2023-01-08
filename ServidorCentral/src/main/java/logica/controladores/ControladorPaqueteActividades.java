package logica.controladores;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtPaqueteActividades;
import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.CompraPaquete;
import logica.entidades.PaqueteActividades;
import logica.entidades.Turista;
import logica.interfaces.IControladorPaqueteActividades;
import logica.interfaces.IManejadorActividadTuristica;
import logica.interfaces.IManejadorCategoria;
import logica.interfaces.IManejadorPaqueteActividades;
import logica.interfaces.IManejadorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorPaqueteActividades implements IControladorPaqueteActividades {

    private final IManejadorPaqueteActividades manejadorPaqueteActividades;
    private final IManejadorActividadTuristica manejadorActividadTuristica;
    private final IManejadorUsuario manejadorUsuario;

    private final IManejadorCategoria manejadorCategoria;
    private final IValidador validador;

    public ControladorPaqueteActividades(IManejadorPaqueteActividades manejadorPaqueteActividades,
                                         IManejadorActividadTuristica manejadorActividadTuristica,
                                         IManejadorUsuario manejadorUsuario,
                                         IManejadorCategoria manejadorCategoria,
                                         IValidador validador

    ) {
        this.manejadorPaqueteActividades = manejadorPaqueteActividades;
        this.manejadorActividadTuristica = manejadorActividadTuristica;
        this.validador = validador;
        this.manejadorUsuario = manejadorUsuario;
        this.manejadorCategoria = manejadorCategoria;
    }

    // ---------------------------------------------------------------------------------------------------------
    // NO ESTAN IMPLEMENTADO LOS CHEQUEOS, ES SOLO PARA QUE FUNCIONE CON LOS DATOS
    // DE CARGA, LO QUE ESTA ACA ES TEMPORAL
    // ---------------------------------------------------------------------------------------------------------

    public void darDeAltaPaquete(DtPaqueteActividades nuevoPaquete)
            throws CampoInvalidoException, EntidadRepetidaException {

        if (validador.campoInvalidoAltaPaquete(nuevoPaquete)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoAltaPaquete(nuevoPaquete));
        }

        if (manejadorPaqueteActividades.contains(nuevoPaquete.getNombre())) {
            throw new EntidadRepetidaException("Ya existe un paquete con el nombre " + nuevoPaquete.getNombre());
        }

        PaqueteActividades paquete = new PaqueteActividades();
        paquete.setNombre(nuevoPaquete.getNombre());
        paquete.setDescripcion(nuevoPaquete.getDescripcion());
        paquete.setDescuento(nuevoPaquete.getDescuento());
        paquete.setNombre(nuevoPaquete.getNombre());
        paquete.setFechaAlta(LocalDate.parse(nuevoPaquete.getFechaAlta()));
        paquete.setValidezEnDias(nuevoPaquete.getValidezEnDias());
        paquete.setImagen(nuevoPaquete.getImagen());
        if (nuevoPaquete.getImagen() == null || nuevoPaquete.getImagen().isEmpty()) {
            paquete.setImagen("paquete/_default.jpg");
        } else {
            paquete.setImagen(nuevoPaquete.getImagen());
        }

        manejadorPaqueteActividades.add(paquete);
    }

    public ArrayList<String> listarPaquetes() throws NoHayEntidadesParaListarException {

        PaqueteActividades[] paquetes = manejadorPaqueteActividades.getAll();
        if (paquetes == null || paquetes.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay paquetes de actividades para listar");
        }

        ArrayList<String> listaNombres = new ArrayList<>();

        for (int i = 0; i < paquetes.length; i++) {
        	listaNombres.add(paquetes[i].getNombre());
        }

        return listaNombres;
    }

    public ArrayList<String> listarActividadesAsociadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {
        // TODO IMPLEMENTAR
        ArrayList<String> res = new ArrayList<>();
        return res;
    }

    public ArrayList<String> listarPaquetesAsociadosAActividad(String nombreActividad) throws NoHayEntidadesParaListarException {
        ActividadTuristica actividad = manejadorActividadTuristica.find(nombreActividad);
        Map<String, PaqueteActividades> paquetes = actividad.getPaquetesAsociados();

        ArrayList<String> listaNombres = new ArrayList<>();

        for (PaqueteActividades paquete : paquetes.values()) {
        	listaNombres.add(paquete.getNombre());
        }

        return listaNombres;
    }


    public void ingresarActividadTuristicaAPaquete(String nombreActividad, String nombrePaquete)
            throws EntidadRepetidaException, CampoInvalidoException {

        if (isNullEmptyOrBlank(nombreActividad) || isNullEmptyOrBlank(nombrePaquete)) {
            throw new CampoInvalidoException("El nombre de la actividad o el nombre del paquete no pueden ser nulos o vacios");
        }

        if (!manejadorActividadTuristica.contains(nombreActividad)) {
            throw new CampoInvalidoException("No existe una actividad con el nombre " + nombreActividad);
        }

        if (!manejadorPaqueteActividades.contains(nombrePaquete)) {
            throw new CampoInvalidoException("No existe un paquete con el nombre " + nombrePaquete);
        }

        PaqueteActividades paquete = manejadorPaqueteActividades.find(nombrePaquete);
        ActividadTuristica actividad = manejadorActividadTuristica.find(nombreActividad);

        if (paquete.contieneActividad(actividad.getNombre())) {
            throw new EntidadRepetidaException("La actividad " + actividad.getNombre() + " ya esta incluida en el paquete " + paquete.getNombre());
        }

        paquete.agregarActividad(actividad);
        manejadorPaqueteActividades.update(paquete);

        actividad.agregarPaqueteAsociado(paquete);
        manejadorActividadTuristica.update(actividad);
    }

    public void ingresarCategoriaAPaquete(String categoria, String nombrePaquete)
            throws EntidadRepetidaException, CampoInvalidoException{
        if (isNullEmptyOrBlank(categoria) || isNullEmptyOrBlank(nombrePaquete)){
            throw new CampoInvalidoException("El nombre de la categoria o el nombre del paquete no pueden ser nulos o vacios");
        }

        if (!manejadorCategoria.contains(categoria)){
            throw new CampoInvalidoException("No existe una categoria con el nombre " + categoria);
        }

        if (!manejadorPaqueteActividades.contains(nombrePaquete)) {
            throw new CampoInvalidoException("No existe un paquete con el nombre " + nombrePaquete);
        }

        PaqueteActividades paquete = manejadorPaqueteActividades.find(nombrePaquete);
        Categoria cat = manejadorCategoria.find(categoria);

        if (paquete.contieneCategoria(categoria)) {
            throw new EntidadRepetidaException("La categoria " + categoria + " ya esta incluida en el paquete " + paquete.getNombre());
        }

        paquete.agregarCategoria(cat);
        manejadorPaqueteActividades.update(paquete);

        cat.agregarPaquete(paquete);
        manejadorCategoria.update(cat);
    }


    public DtPaqueteActividades find(String nombre) throws EntidadNoExisteException, CampoInvalidoException {

        if (isNullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("El nombre del paquete no puede ser nulo, vacio o blanco");
        }

        if (manejadorPaqueteActividades.contains(nombre)) {
            return manejadorPaqueteActividades.find(nombre).newDataType();
        }

        throw new EntidadNoExisteException("No existe un paquete con el nombre " + nombre);

    }

    private boolean isNullEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    @Override
    public void comprarPaquete(DtCompraPaquete compraPaquete) throws CampoInvalidoException, EntidadRepetidaException {

        String nombrePaquete = compraPaquete.getNombrePaquete();
        String nombreTurista = compraPaquete.getNombreTurista();

        if (validador.campoInvalidoComprarPaquete(nombrePaquete, nombreTurista)) {
            throw new CampoInvalidoException("Campo invalido");
        }

        if (!manejadorPaqueteActividades.contains(nombrePaquete)) {
            throw new CampoInvalidoException("Campo invalido no se encontró el paquete");
        }

        if (!manejadorUsuario.contains(nombreTurista)) {
            throw new CampoInvalidoException("Campo invalido no se encontró el usuario");
        }

        Turista turista = (Turista) manejadorUsuario.find(nombreTurista);
        if (turista == null) {
            throw new CampoInvalidoException("Campo invalido no se encontró el turista");
        }

        if (turista.yaComproPaquete(nombrePaquete)) {
            throw new EntidadRepetidaException("El turista ya compro el paquete");
        }

        CompraPaquete compra = new CompraPaquete();

        PaqueteActividades paquete = manejadorPaqueteActividades.find(nombrePaquete);
        compra.setPaquete(paquete);
        compra.setFechaCompra(LocalDate.parse(compraPaquete.getFechaCompra()));
        compra.setCantidadTuristas(compraPaquete.getCantidadTuristas());
        compra.setSalidasDisponibles(compraPaquete.getCantidadTuristas());
        compra.setIdentificador(nombrePaquete);

        LocalDate validez = LocalDate.parse(compraPaquete.getFechaCompra()).plusDays(paquete.getValidezEnDias());
        compra.setValidoHasta(validez);
        // TODO: chequear que el paquete no este vencido y todo tema de fechas
        // TODO chequear si esta bien esto
        // Chequeo que la fecha de compra no sea posterior a la fecha de vencimiento del paquete
        if (compra.getFechaCompra().isAfter(compra.getValidoHasta())) {
            throw new CampoInvalidoException("La fecha de compra no puede ser posterior a la fecha de vencimiento del paquete");
        }

        turista.agregarCompraPaquete(compra);
        manejadorUsuario.update(turista);
    }
    


    // ---------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------

}
