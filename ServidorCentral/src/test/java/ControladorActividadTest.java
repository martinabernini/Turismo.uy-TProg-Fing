import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.controladores.ControladorActividadTuristica;
import logica.datatypes.DtActividadTuristica;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.EnumEstadoActividad;
import logica.datatypes.WrapperString;
import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.Departamento;
import logica.entidades.Proveedor;
import logica.interfaces.*;
import logica.manejadores.ManejadorActividadTuristica;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.Fabrica;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Testear la implementación concreta del controlador de actividad turística

public class ControladorActividadTest {

    // Sistema que se va a testear
    public static IControladorActividadTuristica sistemaBajoPrueba = null;

    // Sus dependencias
    private static IManejadorActividadTuristica manejadorActividadTuristica = null;
    public static IValidador validadorMock = null;
    private static IManejadorDepartamento manejadorDepartamentoMock = null;
    private static IManejadorUsuario manejadorUsuarioMock = null;
    private static IManejadorCategoria manejadorCategoriaMock = null;

    @BeforeEach
    public void setUp() {

        Fabrica fabrica = Fabrica.getInstance();
        fabrica.setLogManejadores(false);

        // Crear las dependencias
        validadorMock = mock(IValidador.class);
        manejadorDepartamentoMock = mock(IManejadorDepartamento.class);
        manejadorUsuarioMock = mock(IManejadorUsuario.class);
        manejadorCategoriaMock = mock(IManejadorCategoria.class);
        manejadorActividadTuristica = ManejadorActividadTuristica.getInstance(manejadorDepartamentoMock);

        // Crear el sistema bajo prueba
        sistemaBajoPrueba = new ControladorActividadTuristica(manejadorActividadTuristica, manejadorDepartamentoMock, manejadorUsuarioMock, manejadorCategoriaMock, validadorMock);

        // Limpiar el manejador de actividades turísticas
        manejadorActividadTuristica.removeAll();
    }

    @AfterEach
    public void tearDown() {
        // Limpiar el manejador de actividades turísticas
        manejadorActividadTuristica.removeAll();
    }

    // Intentar registrar una actividad con información inválida
    @Test
    public void registrarActividadDataInvalida() {
        when(validadorMock.campoInvalidoAltaActividad(any(DtActividadTuristica.class))).thenReturn(true);
        DtActividadTuristica actividadMock = mock(DtActividadTuristica.class);
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividadMock));
    }

    // Registrar una nueva actividad válida y luego listar todas las que se realizan en el departamento en el que se registra la actividad
    @Test
    public void registrarActividadValida() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
        // Registro la actividad
        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
    }

    // Intentar obtener una actividad que no existe
    @Test
    public void obtenerActividadNoExistente() {
        manejadorActividadTuristica.removeAll();
        assertThrows(EntidadNoExisteException.class, () -> sistemaBajoPrueba.getActividadTuristica("actividadNoExistente"));
    }

    // Intentar registrar una categoría que ya existe
    @Test
    public void registrarActividadRepetida() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
        // Registro la actividad
        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
        // Intento registrarla de nuevo
        assertThrows(EntidadRepetidaException.class, () -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
    }

    // Registrar una nueva actividad válida y luego listar todas
    @Test
    public void listarActividades() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
        // Registro la actividad
        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
        // Listo todas las actividades
        assertDoesNotThrow(() -> sistemaBajoPrueba.listarAllActividades());

        // Verifico que la actividad que registré esté en la lista
        try {
            String[] actividades = sistemaBajoPrueba.listarAllActividades().getColeccionString();
            assertEquals(1, sistemaBajoPrueba.listarAllActividades().getColeccionString().length);
            assertTrue(Arrays.asList(actividades).contains(actividad.getNombre()));

            // Registro otra actividad
            DtActividadTuristica actividad2 = simularRegistrarActividadRandomValida();
            assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad2));

            // Verifico que la actividad que registré esté en la lista
            try {
                String[] actividades2 = sistemaBajoPrueba.listarAllActividades().getColeccionString();

                assertEquals(2, actividades2.length);
                assertTrue(Arrays.asList(actividades2).contains(actividad.getNombre()));
                assertTrue(Arrays.asList(actividades2).contains(actividad2.getNombre()));

            } catch (NoHayEntidadesParaListarException e) {
                fail("No debería haber lanzado: " + e.getMessage());
            }

        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado: " + e.getMessage());
        }

    }

    // Registrar una nueva actividad válida y luego listar todas las que se realizan en el departamento en el que no se registró la actividad
    @Test
    public void listarActividadesDepartamentoNoRegistrado() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
        // Registro la actividad
        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
        // Intento listar las actividades de un departamento que no tiene actividades
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasADepartamento("departamentoNoRegistrado"));
    }


    // Obtener una actividad específica y asegúrarse de que devuelva la actividad correcta
    @Test
    public void obtenerActividadExistente() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();

        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));

        try {
            DtActividadTuristica actividadObtenida = sistemaBajoPrueba.getActividadTuristica(actividad.getNombre());
            assertEquals(actividad, actividadObtenida);
        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }

    }


    // listar todas las actividades que se realizan en un determinado departamento y asegúrarse de que solo se listan las actividades de ese departamento
    @Test
    public void listarActividadesDepartamentoRegistrado() {
        DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
        // Registro la actividad
        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));

        // Registro el departamento
        Departamento departamentoMock = mock(Departamento.class);
        when(manejadorDepartamentoMock.contains("departamentoX")).thenReturn(true);
        when(manejadorDepartamentoMock.find("departamentoX")).thenReturn(departamentoMock);
        when(departamentoMock.getActividadesTuristicas()).thenReturn(new HashMap<>());

        // Intento listar las actividades de un departamento que no tiene actividades
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasADepartamento("departamentoNoRegistrado"));
    }


    // listar todas las actividades que aún no han sido confirmadas y asegúrarse de que solo se listan las actividades que no han sido aprobadas
    @Test
    public void listarActividadesNoConfirmadasYConfirmadas() {

        // Chequeo throws
        assertThrows(NoHayEntidadesParaListarException.class, () -> sistemaBajoPrueba.listarActividadesEnEstadoConfirmada());
        assertThrows(NoHayEntidadesParaListarException.class, () -> sistemaBajoPrueba.listarActividadesEnEstadoAgregada());
        assertThrows(NoHayEntidadesParaListarException.class, () -> sistemaBajoPrueba.listarTodasLasCategorias());

        List<String> listaActividades = new ArrayList<String>();
        // registro 5
        for (int i = 0; i < 5; i++) {
            DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
            // Registro la actividad
            assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
            // Me guardo el nombre de la actividad
            listaActividades.add(actividad.getNombre());
        }

        // confirmo 2
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividades.get(0), true));
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividades.get(1), true));

        // rechazo 1
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividades.get(2), false));

        try {
            String[] obtenidasAgregadas = sistemaBajoPrueba.listarActividadesEnEstadoAgregada().getColeccionString();

            // Debería haber 2
            assertEquals(2, obtenidasAgregadas.length);

            // Debería estar la actividad 3 y la 4
            assertTrue(Arrays.asList(obtenidasAgregadas).contains(listaActividades.get(3)));
            assertTrue(Arrays.asList(obtenidasAgregadas).contains(listaActividades.get(4)));

            String[] obtenidasConfirmadas = sistemaBajoPrueba.listarActividadesEnEstadoConfirmada().getColeccionString();
            assertEquals(2, obtenidasConfirmadas.length);

            // Debería estar la actividad 1 y la 2
            assertTrue(Arrays.asList(obtenidasConfirmadas).contains(listaActividades.get(0)));
            assertTrue(Arrays.asList(obtenidasConfirmadas).contains(listaActividades.get(1)));


        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }
    }
    
    //listar actividades no confirmadas de proveedor
    @Test
    public void listarActividadesNoConfirmadasDeProveedor() {
    	
    	when(manejadorUsuarioMock.contains("nicole")).thenReturn(true);
    	
    	Proveedor prov = new Proveedor();
    	prov.setNombre("nicole");
    	
    	when(manejadorUsuarioMock.find("nicole")).thenReturn(prov);

        List<String> listaActividadesSinConfirmar = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
            actividad.setProovedor("nicole");
            // Registro la actividad
            assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
            // Me guardo el nombre de la actividad
            listaActividadesSinConfirmar.add(actividad.getNombre());
        }
        //prov.setActividadesTuristicas(listaActividadesSinConfirmar);

        // confirmo 2
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividadesSinConfirmar.get(0), true));
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividadesSinConfirmar.get(1), true));

        // rechazo 1
        assertDoesNotThrow(() -> sistemaBajoPrueba.rechazarAceptarActividad(listaActividadesSinConfirmar.get(2), false));

        try {
            String[] obtenidasSinConfirmar = sistemaBajoPrueba.listarActividadesDeProveedorNoConfirmadas("nicole").getColeccionString();

            // Debería haber 3 no confirmadas porque 2 fueron confirmadas y 1 rechazada
            assertEquals(3, obtenidasSinConfirmar.length);

            // Debería estar la actividad 3 y la 4
            assertTrue(Arrays.asList(obtenidasSinConfirmar).contains(listaActividadesSinConfirmar.get(3)));
            assertTrue(Arrays.asList(obtenidasSinConfirmar).contains(listaActividadesSinConfirmar.get(4)));


        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }
    }

    // Registrar una nueva categoría y luego obtener todas las actividades que se realizan en esta categoría
    @Test
    public void listarActividadesCategoria() {

        when(validadorMock.campoInvalidoAltaCategoria("Categoría 1")).thenReturn(false);
        // Chequeo throws
//        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.darDeAltaCategoria(null));
//        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.darDeAltaCategoria(""));
//        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.darDeAltaCategoria("    "));
        // TODO: capás cheaquear caracteres especiales, mismo en general

        Categoria categoria = new Categoria("categoriaX");

        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(false);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(null);

        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaCategoria(categoria.getNombre()));

        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(true);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(categoria);
        assertThrows(EntidadRepetidaException.class, () -> sistemaBajoPrueba.darDeAltaCategoria(categoria.getNombre()));
    
        
    }

    // Este test está medio tenso
    // Ver que se haga el pasamano correcto de las actividades por parte del manejadorDepartamento
    @Test
    public void listarActividadesDepartamentoPaquete() {

        List<String> listaActividades = new ArrayList<>();

        // registro 3 actividades
        for (int i = 0; i < 3; i++) {
            DtActividadTuristica actividad = simularRegistrarActividadRandomValida();
            assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaActividadTuristica(actividad));
            // Me guardo el nombre de la actividad
            listaActividades.add(actividad.getNombre());
        }

        // Me guardo las entidades de las actividades para devolverlas en el mock de manejador departamento
        List<ActividadTuristica> actividadesEntidades = new ArrayList<>();
        for (String nombre : listaActividades) {
            actividadesEntidades.add(manejadorActividadTuristica.find(nombre));
        }

        when(manejadorDepartamentoMock.getAllActividadesAsociadasADepartamentoNoEnPaquete("deptoX", "paqueteX")).thenReturn(actividadesEntidades.toArray(new ActividadTuristica[0]));

        try {
            assertEquals(3, sistemaBajoPrueba.listarActividadesAsocadasADepartamentoNoEnPaquete("deptoX", "paqueteX").getColeccionString().length);

        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }
    }


    // Listar actividades turísticas asociadas a departamento
    @Test
    public void listarActividadesAsociadasADepartamento() {
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasADepartamento(""));

        List<ActividadTuristica> actividadesDeptoX = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadx" + i);
            actividadesDeptoX.add(entidad);
        }
        when(manejadorDepartamentoMock.contains("deptoX")).thenReturn(true);
        when(manejadorDepartamentoMock.find("deptoX")).thenReturn(mock(Departamento.class));
        when(manejadorDepartamentoMock.getAllActividadesAsociadasADepartamento("deptoX")).thenReturn(actividadesDeptoX.toArray(new ActividadTuristica[0]));


        try {
            assertEquals(3, sistemaBajoPrueba.listarActividadesAsociadasADepartamento("deptoX").getColeccionString().length);

        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }
    }

    // Listar actividades turísticas asociadas a departamento confirmadas
    @Test
    public void listarActividadesAsociadasADepartamentoConfirmadas() {
        // Checkeo throws de parámetros
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasADepartamentoConfirmadas(null));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasADepartamentoConfirmadas(""));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("  "));

        // Registro 3 actividades con estado confirmada
        List<ActividadTuristica> actividadesDeptoX = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadx" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
            when(entidad.esConfirmada()).thenReturn(true);
            actividadesDeptoX.add(entidad);
        }

        // Registro 2 actividades con estado agregada
        for (int i = 0; i < 2; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividady" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.AGREGADA);
            when(entidad.esConfirmada()).thenReturn(false);
            actividadesDeptoX.add(entidad);
        }

        // Registro 2 actividades con estado rechazada
        for (int i = 0; i < 2; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadz" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.RECHAZADA);
            when(entidad.esConfirmada()).thenReturn(false);
            actividadesDeptoX.add(entidad);
        }

        when(manejadorDepartamentoMock.contains("deptoX")).thenReturn(true);
        when(manejadorDepartamentoMock.find("deptoX")).thenReturn(mock(Departamento.class));
        when(manejadorDepartamentoMock.getAllActividadesAsociadasADepartamento("deptoX")).thenReturn(actividadesDeptoX.toArray(new ActividadTuristica[0]));

        try {
            assertEquals(3, sistemaBajoPrueba.listarActividadesAsociadasADepartamentoConfirmadas("deptoX").getColeccionString().length);

        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción " + e.getMessage());
        }

    }


    // Listar actividades turísticas confirmadas asociadas a categoría
    @Test
    public void listarActividadesConfirmadasCategoria() {

        // Chequeo throws
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas(null));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas(""));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("     "));
        // TODO: capás cheaquear caracteres especiales, mismo en general

        // Chequeo que si el manejador no tiene la categoría, tire excepción
        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(false);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(null);

        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("categoriaX"));


        // Chequeo que si el manejador tiene la categoría, pero no tiene actividades asociadas, tire excepción
        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(true);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(mock(Categoria.class));
        when(manejadorCategoriaMock.getAllActividadesAsociadasACategoria("categoriaX")).thenReturn(null);

        assertThrows(NoHayEntidadesParaListarException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("categoriaX"));

        // Chequeo que si el manejador tiene la categoría, y tiene actividades asociadas pero no confirmadas, tire excepción

        // Creo 3 actividades agregadas
        List<ActividadTuristica> actividades = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadx" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
            when(entidad.esConfirmada()).thenReturn(false);
            actividades.add(entidad);
        }

        // Creo 3 actividades rechazadas
        for (int i = 0; i < 3; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadx" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.RECHAZADA);
            when(entidad.esConfirmada()).thenReturn(false);
            actividades.add(entidad);
        }

        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(true);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(mock(Categoria.class));
        when(manejadorCategoriaMock.getAllActividadesAsociadasACategoria("categoriaX")).thenReturn(actividades.toArray(new ActividadTuristica[0]));

        assertThrows(NoHayEntidadesParaListarException.class, () -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("categoriaX"));

        // Chequeo que si el manejador tiene la categoría, y tiene actividades asociadas y confirmadas, no tire excepción

        // Creo 3 actividades confirmadas
        for (int i = 0; i < 3; i++) {
            ActividadTuristica entidad = mock(ActividadTuristica.class);
            when(entidad.getNombre()).thenReturn("actividadx" + i);
            when(entidad.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
            when(entidad.esConfirmada()).thenReturn(true);
            actividades.add(entidad);
        }

        when(manejadorCategoriaMock.contains("categoriaX")).thenReturn(true);
        when(manejadorCategoriaMock.find("categoriaX")).thenReturn(mock(Categoria.class));
        when(manejadorCategoriaMock.getAllActividadesAsociadasACategoria("categoriaX")).thenReturn(actividades.toArray(new ActividadTuristica[0]));

        assertDoesNotThrow(() -> sistemaBajoPrueba.listarActividadesAsociadasACategoriaConfirmadas("categoriaX"));
    }

    // Chequeo que funcione el finalizar actividad turística
    @Test
    public void finalizarActividad() {

        // Chequeo throws
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.finalizarActividad(null));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.finalizarActividad(""));
        assertThrows(CampoInvalidoException.class, () -> sistemaBajoPrueba.finalizarActividad("     "));

        // creo una actividad turística con estado confirmada
        ActividadTuristica actividadTuristica = new ActividadTuristica();
        actividadTuristica.setNombre("actividadx");
        actividadTuristica.setEstado(EnumEstadoActividad.CONFIRMADA);

        // creo una actividad turística con estado rechazada
        ActividadTuristica actividadTuristica2 = new ActividadTuristica();
        actividadTuristica2.setNombre("actividadx2");
        actividadTuristica2.setEstado(EnumEstadoActividad.RECHAZADA);

        // creo una actividad turística con estado agregada
        ActividadTuristica actividadTuristica3 = new ActividadTuristica();
        actividadTuristica3.setNombre("actividadx3");
        actividadTuristica3.setEstado(EnumEstadoActividad.AGREGADA);

        manejadorActividadTuristica.add(actividadTuristica);
        manejadorActividadTuristica.add(actividadTuristica2);
        manejadorActividadTuristica.add(actividadTuristica3);

        // Chequeo que si la actividad turística no existe, tire excepción
        assertThrows(EntidadNoExisteException.class, () -> sistemaBajoPrueba.finalizarActividad("actividadx4"));

        // Chequeo que si la actividad turística existe pero no está confirmada, tire excepción
        assertThrows(EntidadNoExisteException.class, () -> sistemaBajoPrueba.finalizarActividad("actividadx2"));

        // Chequeo que si la actividad turística existe y está confirmada pero tiene salidas vigentes, tire excepción
        // la siguiente implementaciion es un bolazo, pero la dejo por si no fuera mala idea y para que no se me olvide
//        when(manejadorSalidaMock.contains("actividadx")).thenReturn(true);
//        when(manejadorSalidaMock.getAllSalidasVigentesDeActividad("actividadx")).thenReturn(new Salida[1]);
    }


    // ----------------------------------------------------------------
    // Metodos auxiliares
    // ----------------------------------------------------------------
    private DtActividadTuristica newActividadRandom() {
        DtActividadTuristica actividad = new DtActividadTuristica();
        actividad.setNombre("Actividad " + Math.random());
        actividad.setDescripcion("Descripcion " + Math.random());
        actividad.setDuracionHrs((int) (Math.random() * 100));
        actividad.setCostoPorPersona((float) (Math.random() * 100));
        actividad.setCiudad("Ciudad " + Math.random());
        actividad.setFechaAlta(new LocalDate("2020-01-01"));
        actividad.setDepartamento("Departamento " + Math.random());
        actividad.setProovedor("Proovedor " + Math.random());
        actividad.setImagen("Imagen " + Math.random());
        WrapperString resCategorias= new WrapperString(new String[]{"Categoria " + Math.random()});
        actividad.setCategorias(resCategorias);
        actividad.setEstado(EnumEstadoActividad.AGREGADA);
        // Cuando se crea por primera vez una actividad no tiene salidas ni paquetes
        return actividad;
    }

	/*
	 * private DtPaqueteActividades newPaqueteActividadesRandom() { // Creo un
	 * paquete DtPaqueteActividades paquete = new DtPaqueteActividades();
	 * 
	 * paquete.setNombre("Paquete " + Math.random());
	 * paquete.setDescripcion("Descripcion " + Math.random());
	 * paquete.setValidezEnDias((int) (Math.random() * 100));
	 * paquete.setFechaAlta(new LocalDate("2020-01-01"));
	 * paquete.setNombreActividades(new String[]{"Actividad " + Math.random()});
	 * paquete.setDescuento((float) (Math.random() * 100));
	 * paquete.setImagen("Imagen " + Math.random());
	 * 
	 * return paquete; }
	 */

    private DtActividadTuristica simularRegistrarActividadRandomValida() {
        return simularRegistrarActividadValida(newActividadRandom());
    }

    private DtActividadTuristica simularRegistrarActividadValida(DtActividadTuristica actividad) {
        when(validadorMock.campoInvalidoAltaActividad(actividad)).thenReturn(false);

        // Hago que las demás dependencias devuelvan valores válidos
        Departamento departamentoMock = mock(Departamento.class);
        when(departamentoMock.getNombre()).thenReturn(actividad.getDepartamento());
        when(manejadorDepartamentoMock.contains(actividad.getDepartamento())).thenReturn(true);
        when(manejadorDepartamentoMock.find(actividad.getDepartamento())).thenReturn(departamentoMock);

        Proveedor proveedorMock = mock(Proveedor.class);
        when(proveedorMock.getNickname()).thenReturn(actividad.getProovedor());
        when(manejadorUsuarioMock.contains(actividad.getProovedor())).thenReturn(true);
        when(manejadorUsuarioMock.find(actividad.getProovedor())).thenReturn(proveedorMock);

        for (String categoria : actividad.getCategorias().getColeccionString()) {
            Categoria categoriaMock = mock(Categoria.class);
            when(categoriaMock.getNombre()).thenReturn(categoria);
            when(manejadorCategoriaMock.contains(categoria)).thenReturn(true);
            when(manejadorCategoriaMock.find(categoria)).thenReturn(categoriaMock);
        }
        return actividad;
    }

//    private DtPaqueteActividades registroUnPaqueteRandomValido(String[] nombreActividades) {
//        // Creo un paquete
//        DtPaqueteActividades paquete = newPaqueteActividadesRandom();
//        when(validadorMock.campoInvalidoAltaPaquete(paquete)).thenReturn(false);
//
//        paquete.setNombreActividades(nombreActividades);
//
//        // Hago que las demás dependencias devuelvan valores válidos
//        for (String actividad : paquete.getNombreActividades()) {
//            ActividadTuristica actividadMock = mock(ActividadTuristica.class);
//            when(actividadMock.getNombre()).thenReturn(actividad);
//            manejadorActividadTuristica.add(actividadMock);
//        }
//        return paquete;
//    }

}


