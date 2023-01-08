package logica.controladores;

import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtActividadTuristica;
import logica.entidades.*;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IManejadorActividadTuristica;
import logica.interfaces.IManejadorCategoria;
import logica.interfaces.IManejadorDepartamento;
import logica.interfaces.IManejadorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorActividadTuristica implements IControladorActividadTuristica {

    private final IManejadorActividadTuristica manejadorActividadTuristica;
    private final IManejadorDepartamento manejadorDepartamento;
    private final IManejadorUsuario manejadorUsuario;
    private final IValidador validador;
    private final IManejadorCategoria manejadorCategoria;

    public ControladorActividadTuristica(IManejadorActividadTuristica manejadorActividadTuristica,
                                         IManejadorDepartamento manejadorDepartamento, IManejadorUsuario manejadorUsuario,
                                         IManejadorCategoria manejadorCategoria, IValidador validador) {
        this.manejadorActividadTuristica = manejadorActividadTuristica;
        this.manejadorDepartamento = manejadorDepartamento;
        this.manejadorUsuario = manejadorUsuario;
        this.manejadorCategoria = manejadorCategoria;
        this.validador = validador;
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Override
    public void darDeAltaActividadTuristica(DtActividadTuristica nuevaActividad)
            throws CampoInvalidoException, EntidadRepetidaException {

        if (validador.campoInvalidoAltaActividad(nuevaActividad)) {
            throw new CampoInvalidoException(MensajeError.campoInvalidoAltaActividad(nuevaActividad));
        }

        if (manejadorActividadTuristica.contains(nuevaActividad.getNombre()))
            throw new EntidadRepetidaException("Ya hay una actividad turistica con ese nombre");

        if (!manejadorDepartamento.contains(nuevaActividad.getDepartamento())) {
            throw new CampoInvalidoException(
                    "No existe ese departamento en el sistema: " + nuevaActividad.getDepartamento());
        }

        Proveedor proveedor = (Proveedor) manejadorUsuario.find(nuevaActividad.getProovedor());

        if (proveedor == null) {
            throw new CampoInvalidoException("Nombre de proveedor invalido: " + nuevaActividad.getProovedor());
        }

        Departamento departamento = manejadorDepartamento.find(nuevaActividad.getDepartamento());
        if (departamento == null) {
            throw new CampoInvalidoException("Departamento invalido: " + nuevaActividad.getDepartamento());
        }

        Map<String, Categoria> categorias = new HashMap<>();

        for (String nombreCategoria : nuevaActividad.getCategorias()) {
            Categoria categoria = manejadorCategoria.find(nombreCategoria);
            if (categoria == null) {
                throw new CampoInvalidoException("Nombre de categoria invalido: " + nombreCategoria);
            }
            categorias.put(nombreCategoria, categoria);
        }

        ActividadTuristica actividad = new ActividadTuristica();

        // Creo links de la actividad a las otras entidades
        actividad.setProveedor(proveedor);
        actividad.setDepartamento(departamento);
        actividad.setCategorias(categorias);

        // Lleno los datos de la actividad
        actividad.setNombre(nuevaActividad.getNombre());
        actividad.setDescripcion(nuevaActividad.getDescripcion());
        actividad.setDuracionHrs(nuevaActividad.getDuracionHrs());
        actividad.setCostoPorPersona(nuevaActividad.getCostoPorPersona());
        actividad.setCiudad(nuevaActividad.getCiudad());
        actividad.setFechaAlta(LocalDate.parse(nuevaActividad.getFechaAlta()));
        actividad.setEstado(nuevaActividad.getEstado());
        if (nuevaActividad.getImagen() == null || nuevaActividad.getImagen().isEmpty()) {
            actividad.setImagen("actividad/_default.jpg");
        } else {
            actividad.setImagen(nuevaActividad.getImagen());
        }
        if(nuevaActividad.getUrlVideo() == null || nuevaActividad.getUrlVideo().isEmpty()) {
            actividad.setUrlVideo("");
        }else {
            actividad.setUrlVideo(nuevaActividad.getUrlVideo());
        }

        // Link de majeador a Actividad
        manejadorActividadTuristica.add(actividad);

        // Creo links de las otras entidades a la actividad
        proveedor.agregarActividadTuristica(actividad);
        manejadorUsuario.update(proveedor);

        departamento.agregarActividadTuristica(actividad);
        manejadorDepartamento.update(departamento);

        for (Categoria categoria : categorias.values()) {
            categoria.agregarActividad(actividad);
            manejadorCategoria.update(categoria);
        }
    }

    @Override
    public ArrayList<String> listarActividadesAsociadasADepartamento(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {


        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre de departamento invalido");
        }

        if (!manejadorDepartamento.contains(nombre)) {
            throw new CampoInvalidoException("No existe ese departamento en el sistema");
        }

        ActividadTuristica[] actividades = manejadorDepartamento.getAllActividadesAsociadasADepartamento(nombre);

        if (actividades == null) {
            throw new NoHayEntidadesParaListarException("No hay actividades asociadas a departamento " + nombre);
        }

        ArrayList<String> nombreActs = new ArrayList<>();

        for (int i = 0; i < actividades.length; i++) {
            nombreActs.add(actividades[i].getNombre());
        }

        return nombreActs; 
    }


    public ArrayList<String> listarActividadesAsociadasADepartamentoConfirmadas(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre de departamento invalido");
        }

        if (!manejadorDepartamento.contains(nombre)) {
            throw new CampoInvalidoException("No existe ese departamento en el sistema");
        }

        ActividadTuristica[] actividades = manejadorDepartamento.getAllActividadesAsociadasADepartamento(nombre);

        if (actividades == null) {
            throw new NoHayEntidadesParaListarException("No hay actividades asociadas a departamento " + nombre);
        }

        List<ActividadTuristica> actividadesConfirmadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividades) {
            if (actividad.esConfirmada()) {
                actividadesConfirmadas.add(actividad);
            }
        }

        if (actividadesConfirmadas.size() == 0) {
            throw new NoHayEntidadesParaListarException("No hay actividades confirmadas asociadas a departamento");
        }
             
        ArrayList<String> nombreActividadesConfirmadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividadesConfirmadas) {
        	nombreActividadesConfirmadas.add(actividad.getNombre());
        }

        return nombreActividadesConfirmadas; 
    }

    public ArrayList<String> listarActividadesAsociadasACategoriaConfirmadas(String nombre)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre de categoría invalido");
        }

        if (!manejadorCategoria.contains(nombre)) {
            throw new CampoInvalidoException("No existe esa categoría en el sistema");
        }

        ActividadTuristica[] actividades = manejadorCategoria.getAllActividadesAsociadasACategoria(nombre);

        if (actividades == null) {
            throw new NoHayEntidadesParaListarException("No hay actividades asociadas a " + nombre);
        }

        List<ActividadTuristica> actividadesConfirmadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividades) {
            if (actividad.esConfirmada()) {
                actividadesConfirmadas.add(actividad);
            }
        }

        if (actividadesConfirmadas.isEmpty()) {
            throw new NoHayEntidadesParaListarException("No hay actividades asociadas a " + nombre);
        }
    
        ArrayList<String> nombreActividadesConfirmadas = new ArrayList<>();

        for (ActividadTuristica actividad : actividadesConfirmadas) {
        	nombreActividadesConfirmadas.add(actividad.getNombre());
        }

        return nombreActividadesConfirmadas;
    }

    @Override
    public DtActividadTuristica getActividadTuristica(String nombre)
            throws EntidadNoExisteException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre actividad invalido: " + nombre);
        }

        if (!manejadorActividadTuristica.contains(nombre)) {
            throw new EntidadNoExisteException("No existe actividad con ese nombre");
        }

        return manejadorActividadTuristica.find(nombre).newDataType();
    }

    @Override
    public ArrayList<String> listarActividadesAsocadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete)
            throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombreDepto) || nullEmptyOrBlank(nombrePaquete)) {
            throw new CampoInvalidoException("Campo invalido");
        }

        ActividadTuristica[] actividadesNoEnPaquete = manejadorDepartamento
                .getAllActividadesAsociadasADepartamentoNoEnPaquete(nombreDepto, nombrePaquete);

        if (actividadesNoEnPaquete == null) {
            throw new NoHayEntidadesParaListarException("No hay actividades para listar que no esten en el paquete " + nombrePaquete);
        }
     
        ArrayList<String> nombreActividadesNoEnPaquete = new ArrayList<>();

        for (int i = 0; i < actividadesNoEnPaquete.length; i++) {
        	nombreActividadesNoEnPaquete.add(actividadesNoEnPaquete[i].getNombre());
        }

        return nombreActividadesNoEnPaquete;

    }


    public ArrayList<String> listarAllActividades() throws NoHayEntidadesParaListarException {
        ActividadTuristica[] actividades = manejadorActividadTuristica.getAll();
        if (actividades == null || actividades.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay actividades	para listar");
        }

        ArrayList<String> nombreActividades = new ArrayList<>();

        for (int i = 0; i < actividades.length; i++) {
        	nombreActividades.add(actividades[i].getNombre());
        }

        return nombreActividades;

    }

    @Override
    public ArrayList<String> listarActividadesEnEstadoAgregada() throws NoHayEntidadesParaListarException {
        ActividadTuristica[] actividades = manejadorActividadTuristica.getAllActividadesEnEstadoAgregada();

        if (actividades == null || actividades.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay actividades en estado agregada");
        }

        ArrayList<String> nombreActividades = new ArrayList<>();

        for (int i = 0; i < actividades.length; i++) {
        	nombreActividades.add(actividades[i].getNombre());
        }

        return nombreActividades;
    }


    public ArrayList<String> listarActividadesEnEstadoConfirmada() throws NoHayEntidadesParaListarException {
        ActividadTuristica[] actividades = manejadorActividadTuristica.getAllActividadesEnEstadoConfirmada();

        if (actividades == null || actividades.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay actividades en estado confirmada");
        }

        ArrayList<String> nombreActividades = new ArrayList<>();

        for (int i = 0; i < actividades.length; i++) {
        	nombreActividades.add(actividades[i].getNombre());
        }

        return nombreActividades;
    }


    public ArrayList<String> listarActividadesDeProveedorNoConfirmadas(String nombreProveedor) throws NoHayEntidadesParaListarException, CampoInvalidoException {

        if (nullEmptyOrBlank(nombreProveedor)) {
            throw new CampoInvalidoException("Nombre de proveedor invalido");
        }

        if (!manejadorUsuario.contains(nombreProveedor)) {
            throw new CampoInvalidoException("No existe proveedor con ese nombre");
        }

        Proveedor proveedor = (Proveedor) manejadorUsuario.find(nombreProveedor);
        if (proveedor == null){
            throw new CampoInvalidoException("No existe proveedor con ese nombre");
        }

        List<ActividadTuristica> actividadesDelProveedor = proveedor.getActividadesTuristicas();

        if (actividadesDelProveedor == null || actividadesDelProveedor.isEmpty()){
            throw new NoHayEntidadesParaListarException("No hay actividades para listar");
        }

        ArrayList<String> actividadesNoConfirmadas = new ArrayList<>();
        for (ActividadTuristica actividad : actividadesDelProveedor) {
            if (!actividad.esConfirmada())
                actividadesNoConfirmadas.add(actividad.getNombre());
        }

        if (actividadesNoConfirmadas.isEmpty()){
            throw new NoHayEntidadesParaListarException("No hay actividades para listar");
        }
        
        return actividadesNoConfirmadas;
    }

    @Override
    public void darDeAltaCategoria(String nombre) throws EntidadRepetidaException, CampoInvalidoException {
        if (validador.campoInvalidoAltaCategoria(nombre)) {
            throw new CampoInvalidoException("Nombre de categoria invalido");
        }

        if (manejadorCategoria.contains(nombre)) {
            throw new EntidadRepetidaException("Ya existe una categoria con ese nombre");
        }

        Categoria categoria = new Categoria(nombre);
        manejadorCategoria.add(categoria);
    }

    @Override
    public void rechazarAceptarActividad(String nombre, boolean aceptada)
            throws EntidadNoExisteException, CampoInvalidoException {
        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre de actividad invalido");
        }
        if (!manejadorActividadTuristica.contains(nombre)) {
            throw new EntidadNoExisteException("No existe actividad con ese nombre");
        }

        ActividadTuristica actividad = manejadorActividadTuristica.find(nombre);
        if (aceptada) {
            actividad.aceptar();
        } else {
            actividad.rechazar();
        }

        manejadorActividadTuristica.update(actividad);
    }

    @Override
    public ArrayList<String> listarTodasLasCategorias() throws NoHayEntidadesParaListarException {

        Categoria[] categorias = manejadorCategoria.getAll();

        if (categorias == null || categorias.length == 0) {
            throw new NoHayEntidadesParaListarException("No hay categorias para listar");
        }
        
        ArrayList<String> nombreCategorias = new ArrayList<>();

        for (int i = 0; i < categorias.length; i++) {
        	nombreCategorias.add(categorias[i].getNombre());
        }

        return nombreCategorias;
    }

    @Override
    public void finalizarActividad(String nombre) throws EntidadNoExisteException, CampoInvalidoException {
        if (nullEmptyOrBlank(nombre)) {
            throw new CampoInvalidoException("Nombre de actividad invalido");
        }

        if (!manejadorActividadTuristica.contains(nombre)) {
            throw new EntidadNoExisteException("No existe actividad con ese nombre");
        }

        ActividadTuristica actividad = manejadorActividadTuristica.find(nombre);
        if (!actividad.esConfirmada()) {
            throw new EntidadNoExisteException("La actividad no esta confirmada");
        }

        // chequear que no tenga salidas vigentes
        if(actividad.tieneSalidasVigentes()) {
            throw new EntidadNoExisteException("La actividad tiene salidas vigentes");
        }

        actividad.finalizar();
        manejadorActividadTuristica.update(actividad);
    }

    @Override
    public boolean existeActividadConNombre(String nombre) {
        if (nullEmptyOrBlank(nombre)){
            return false;
        }

        return manejadorActividadTuristica.contains(nombre);
    }

    // ---------------------------------------------------------------------------------------------

    private Boolean nullEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

}
