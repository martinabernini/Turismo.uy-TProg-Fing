import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import logica.controladores.ControladorPaqueteActividades;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtPaqueteActividades;
import logica.datatypes.WrapperString;
import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.PaqueteActividades;
import logica.entidades.Turista;
import logica.interfaces.*;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Fabrica;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorPaquetesTest {


    // Sistema que se va a testear
    public static IControladorPaqueteActividades sistemaBajoPrueba = null;

    // Sus dependencias
    private static IManejadorPaqueteActividades manejadorPaqueteActividades = null;
    private static IManejadorActividadTuristica manejadorActividadTuristica = null;

    private static IManejadorUsuario manejadorUsuarioMock = null;

    private static IManejadorCategoria manejadorCategoriaMock = null;
    public static IValidador validadorMock = null;

    @BeforeEach
    public void setUp() {

        Fabrica fabrica = Fabrica.getInstance();
        fabrica.setLogManejadores(false);

        manejadorPaqueteActividades = fabrica.getIManejadorPaqueteActividades();

        // Crear las dependencias
        validadorMock = mock(IValidador.class);
        manejadorActividadTuristica = mock(IManejadorActividadTuristica.class);
        manejadorUsuarioMock = mock(IManejadorUsuario.class);
        manejadorCategoriaMock = mock(IManejadorCategoria.class);

        // Crear el sistema bajo prueba
        sistemaBajoPrueba = new ControladorPaqueteActividades(manejadorPaqueteActividades, manejadorActividadTuristica, manejadorUsuarioMock, manejadorCategoriaMock, validadorMock);

        // Limpiar el manejador de actividades turísticas
        manejadorPaqueteActividades.removeAll();

    }

    @AfterEach
    public void tearDown() {
        // Limpiar el manejador de actividades turísticas
        manejadorActividadTuristica.removeAll();
    }

    // Intentar registrar un paquete de actividades con información inválida
    @Test
    public void testRegistrarPaqueteActividadesInvalida() {

        when(validadorMock.campoInvalidoAltaPaquete(any())).thenReturn(true);
        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.darDeAltaPaquete(mock(DtPaqueteActividades.class));
        });

    }

    // Listar todos los paquetes de actividades cuando no hay ninguno
    @Test
    public void testListarPaquetesActividadesVacio() {

        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarPaquetes();
        });

    }

    // Listar todos los paquetes de actividades cuando hay alguno
    @Test
    public void testListarPaquetesActividades() {

        DtPaqueteActividades paquete1 = newPaqueteActividadesRandom();
        DtPaqueteActividades paquete2 = newPaqueteActividadesRandom();

        when(validadorMock.campoInvalidoAltaPaquete(any())).thenReturn(false);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaPaquete(paquete1);
            sistemaBajoPrueba.darDeAltaPaquete(paquete2);
        });

        Set<String> esperados = new HashSet<>();
        esperados.add(paquete1.getNombre());
        esperados.add(paquete2.getNombre());

        try {
            String[] paquetes = sistemaBajoPrueba.listarPaquetes().getColeccionString();
            assertEquals(2, paquetes.length);

            Set<String> obtenidos = new HashSet<>();
            Collections.addAll(obtenidos, paquetes);

            assertEquals(esperados, obtenidos);

        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción");
        }

    }

    // Obtener un paquete que no exista
    @Test
    public void testObtenerPaqueteNoExiste() {

        assertThrows(EntidadNoExisteException.class, () -> {
            sistemaBajoPrueba.find("paquete");
        });

    }

    // Comprar un paquete de actividades que no existe

//    @Test
//    public void testComprarPaqueteNoExiste() {
//
//        when(validadorMock.campoInvalidoAltaPaquete(any())).thenReturn(false);
//
//
//        DtPaqueteActividades paquete = newPaqueteActividadesRandom();
//        assertDoesNotThrow(() -> {
//            sistemaBajoPrueba.darDeAltaPaquete(paquete);
//        });
//
//        Usuario usuario = mock(Usuario.class);
//        when(usuario.getNickname()).thenReturn("usuario");
//        when(manejadorUsuario.find(anyString())).thenReturn(usuario);
//        when(manejadorUsuario.contains(anyString())).thenReturn(true);
//
//        when(manejadorUsuario.find(anyString())).thenReturn(usuario);
//        when(manejadorPaqueteActividades.find(anyString())).thenReturn(null);
//
//    	assertThrows(CampoInvalidoException.class, () -> {
//            sistemaBajoPrueba.comprarPaquete(mock(DtCompraPaquete.class));
//        });
//
//    }


    // Ingresar una actividad turística a un paquete exitosamente
    @Test
    public void testIngresarUnaActividad() {

        PaqueteActividades paqueteMock = mock(PaqueteActividades.class);
        when(paqueteMock.getNombre()).thenReturn("paquete");
        when(paqueteMock.contieneActividad(anyString())).thenReturn(false);
        manejadorPaqueteActividades.add(paqueteMock);

        ActividadTuristica actividadMock = mock(ActividadTuristica.class);
        when(manejadorActividadTuristica.contains(anyString())).thenReturn(true);
        when(manejadorActividadTuristica.find(anyString())).thenReturn(actividadMock);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.ingresarActividadTuristicaAPaquete("actividad", "paquete");
        });

    }

    // Comprar un paquete de actividades que existe exitosamente
    @Test
    public void testComprarPaqueteExiste() {

        when(validadorMock.campoInvalidoAltaPaquete(any())).thenReturn(false);

        PaqueteActividades paqueteMock = mock(PaqueteActividades.class);
        when(paqueteMock.getNombre()).thenReturn("paquete");
        when(paqueteMock.contieneActividad(anyString())).thenReturn(false);
        manejadorPaqueteActividades.add(paqueteMock);
        when(manejadorActividadTuristica.contains(anyString())).thenReturn(true);
        when(manejadorActividadTuristica.find(anyString())).thenReturn(mock(ActividadTuristica.class));

        Turista turista = mock(Turista.class);
        when(turista.getNickname()).thenReturn("turista");
        when(turista.yaComproPaquete(anyString())).thenReturn(false);
        when(manejadorUsuarioMock.contains(anyString())).thenReturn(true);
        when(manejadorUsuarioMock.find(anyString())).thenReturn(turista);

        manejadorPaqueteActividades.add(paqueteMock);

        DtCompraPaquete compraMock = mock(DtCompraPaquete.class);
        when(compraMock.getNombreTurista()).thenReturn("turista");
        when(compraMock.getNombrePaquete()).thenReturn("paquete");
        when(compraMock.getFechaCompra()).thenReturn(new LocalDate());

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.comprarPaquete(compraMock);
        });

    }


    // Obtener un paquete que exista
    @Test
    public void testObtenerPaqueteExiste() {

//        PaqueteActividades paqueteMock = mock(PaqueteActividades.class);
//        when(paqueteMock.getNombre()).thenReturn("paquete");
//
//        manejadorPaqueteActividades.add(paqueteMock);
//
//        assertDoesNotThrow(() -> {
//            DtPaqueteActividades paquete = sistemaBajoPrueba.find("paquete");
//            assertEquals(paqueteMock, paquete);
//        });
//
    }

    // Ingresar existosamente una categoria a un paquete
    @Test
    public void testIngresarCategoria() {

        String nombrePaquete = "paquete";
        String nombreCategoria = "categoria";

        when(manejadorCategoriaMock.contains("categoria")).thenReturn(true);
        when(manejadorCategoriaMock.find("categoria")).thenReturn(mock(Categoria.class));

        PaqueteActividades paqueteMock = mock(PaqueteActividades.class);

        when(paqueteMock.getNombre()).thenReturn(nombrePaquete);
        when(paqueteMock.contieneCategoria(nombreCategoria)).thenReturn(false);

        manejadorPaqueteActividades.add(paqueteMock);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.ingresarCategoriaAPaquete(nombreCategoria, nombrePaquete);
        });

    }

//
//    // Listar todas las actividades que no estén en un cierto paquete
//    @Test
//    public void testListarActividadesNoEnPaquete() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad turística a un cierto paquete
//    @Test
//    public void testAgregarActividadTuristicaAPaquete() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad turística a un paquete que no exista
//    @Test
//    public void testAgregarActividadTuristicaAPaqueteNoExiste() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad turística inválida a un cierto paquete
//    @Test
//    public void testAgregarActividadTuristicaInvalidaAPaquete() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad turística a un cierto paquete que ya esté en él
//    @Test
//    public void testAgregarActividadTuristicaAPaqueteYaExiste() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad turística a un cierto paquete que ya esté lleno
//    @Test
//    public void testAgregarActividadTuristicaAPaqueteLleno() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad que no haya sido aprobada aún a cierto paquete
//    @Test
//    public void testAgregarActividadTuristicaNoAprobadaAPaquete() {
//        fail("Implementar");
//    }
//
//    // Agregar una actividad aprobada a cierto paquete
//    @Test
//    public void testAgregarActividadTuristicaAprobadaAPaquete() {
//        fail("Implementar");
//    }

    private DtPaqueteActividades newPaqueteActividadesRandom() {
        DtPaqueteActividades paqueteActividades = new DtPaqueteActividades();
        paqueteActividades.setNombre("Paquete " + Math.random());
        paqueteActividades.setDescripcion("Descripcion");
        paqueteActividades.setValidezEnDias(10);
        paqueteActividades.setFechaAlta(new LocalDate("2015-01-01"));
        WrapperString resActividades = new WrapperString(new String[]{"Actividad 1", "Actividad 2"});
        paqueteActividades.setNombreActividades(resActividades);
        paqueteActividades.setDescuento(0.5f);
        paqueteActividades.setImagen("Imagen");
        
        
        DtPaqueteActividades paqueteActividades2 = new DtPaqueteActividades();
        paqueteActividades2.setNombre("Paquete " + Math.random());
        paqueteActividades2.setDescripcion("Descripcion");
        paqueteActividades2.setValidezEnDias(10);
        paqueteActividades2.setFechaAlta(new LocalDate("2015-01-01"));
        WrapperString resActividades2 = new WrapperString(new String[]{"Actividad 1", "Actividad 2"});
        paqueteActividades2.setNombreActividades(resActividades2);
        paqueteActividades2.setDescuento(0.5f);
        paqueteActividades2.setImagen("Imagen");
        
        Boolean testEquals = paqueteActividades2.equals(paqueteActividades);
        String testToString = paqueteActividades.toString();
        
        int testHash = paqueteActividades.hashCode();
        
        return paqueteActividades;
    }
    
    @Test
    public void testDtCompraPaquete() {
       DtCompraPaquete compraPaquete = new DtCompraPaquete();
       compraPaquete.setCantidadTuristas(2);
       compraPaquete.setFechaCompra(null);
       compraPaquete.setNombrePaquete("La mejor compra");
       compraPaquete.setPrecio(800000000);
       compraPaquete.setValidoHasta(null);
       
       DtCompraPaquete compraPaquete2 = new DtCompraPaquete();
       compraPaquete2.setCantidadTuristas(2);
       compraPaquete2.setFechaCompra(null);
       compraPaquete2.setNombrePaquete("La mejor compra");
       compraPaquete2.setPrecio(800000000);
       compraPaquete2.setValidoHasta(null);
       
       
       String testToString = compraPaquete2.toString();
       Boolean testEquals = compraPaquete.equals(compraPaquete2);
    }


}
