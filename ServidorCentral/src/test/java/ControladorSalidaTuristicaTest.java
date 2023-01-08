import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import logica.controladores.ControladorSalidaTuristica;
import logica.datatypes.DtActividadTuristica;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtSalidaTuristica;
import logica.datatypes.EnumEstadoActividad;
import logica.entidades.ActividadTuristica;
import logica.entidades.SalidaTuristica;
import logica.entidades.Turista;
import logica.interfaces.*;
import logica.manejadores.ManejadorSalidaTuristica;
import logica.validacion.Validador;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Fabrica;

import org.joda.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControladorSalidaTuristicaTest {

    // Sistema que se va a testear
    private static IControladorSalidaTuristica sistemaBajoPrueba;

    // Sus dependencias
    private static IManejadorSalidaTuristica manejadorSalidaTuristica = null;
    private static IManejadorUsuario manejadorUsuarioMock = null;
    private static IManejadorInscripcionSalida manejadorInscripcionSalidaMock = null;
    private static IManejadorActividadTuristica manejadorActividadTuristicaMock = null;
    private static IValidador validadorMock = null;

    @BeforeEach
    void setUp() {

        Fabrica fabrica = Fabrica.getInstance();
        fabrica.setLogManejadores(false);

        // Creo los mocks antes de cada test
        validadorMock = mock(Validador.class);
        manejadorUsuarioMock = mock(IManejadorUsuario.class);
        manejadorInscripcionSalidaMock = mock(IManejadorInscripcionSalida.class);
        manejadorActividadTuristicaMock = mock(IManejadorActividadTuristica.class);
        manejadorSalidaTuristica = ManejadorSalidaTuristica.getInstance(manejadorActividadTuristicaMock);

        // Creo el sistema bajo prueba
        sistemaBajoPrueba = new ControladorSalidaTuristica(manejadorSalidaTuristica, manejadorUsuarioMock, manejadorInscripcionSalidaMock, manejadorActividadTuristicaMock, validadorMock);

        manejadorSalidaTuristica.removeAll();
    }

    @AfterEach
    void tearDown() {
        manejadorSalidaTuristica.removeAll();
    }

    // Intentar registrar una salida turística con información invalida
    @Test
    public void registrarSalidaTuristicaInvalida() {
        when(validadorMock.campoInvalidoAltaSalida(any())).thenReturn(true);

        DtSalidaTuristica nuevaSalida = newSalidaTuristicaRandom();

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.darDeAltaSalidaTuristica(nuevaSalida);
        });
    }

    // Intentar obtener una salida turística específica que no existe
    @Test
    public void obtenerSalidaTuristicaInexistente() {

        assertThrows(EntidadNoExisteException.class, () -> {
            sistemaBajoPrueba.getSalidaTuristica("salidaInexistente");
        });

    }

    // Intentar obtener una salida turística que existe
    @Test
    public void obtenerSalidaTuristicaExistente() {
    	
        // Creo una actividad turística
        ActividadTuristica actividadMock = mock(ActividadTuristica.class);
        when(actividadMock.getNombre()).thenReturn("actividadMock");
        when(actividadMock.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
        when(manejadorActividadTuristicaMock.contains(any())).thenReturn(true);
        when(manejadorActividadTuristicaMock.find(any())).thenReturn(actividadMock);


        // Creo una salida turística
        DtSalidaTuristica salida = newSalidaTuristicaRandom();
        salida.setNombreActividad("actividadMock");

        when(validadorMock.campoInvalidoAltaSalida(any())).thenReturn(false);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaSalidaTuristica(salida);
        });
        // Obtengo la salida turística

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.getSalidaTuristica(salida.getNombreSalida());
        });

        try {
            DtSalidaTuristica salidaObtenida = sistemaBajoPrueba.getSalidaTuristica(salida.getNombreSalida());
            assertEquals(salida.getNombreSalida(), salidaObtenida.getNombreSalida());

        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

    }

    
    // Intentar listar todas las salidas turísticas para una actividad que no existe
    @Test
    public void listarSalidasTuristicasInexistentes() {
    	
        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarSalidasAsociadasAActividadTuristica("actividadInexistente");
        });

        DtSalidaTuristica nuevaSalida = newSalidaTuristicaRandom();

        ActividadTuristica actividadTuristica = mock(ActividadTuristica.class);
        when(actividadTuristica.getNombre()).thenReturn(nuevaSalida.getNombreActividad());

        when(validadorMock.campoInvalidoAltaSalida(any())).thenReturn(false);
        when(manejadorActividadTuristicaMock.contains(any())).thenReturn(true);
        when(manejadorActividadTuristicaMock.find(any())).thenReturn(actividadTuristica);

        assertDoesNotThrow(() -> sistemaBajoPrueba.darDeAltaSalidaTuristica(nuevaSalida));

    }
    
    
    @Test
    public void testEquals() {

        DtSalidaTuristica nuevaSalida = newSalidaTuristicaRandom();
        DtSalidaTuristica comp = new DtSalidaTuristica();
        comp.setNombreActividad(nuevaSalida.getNombreActividad());
        comp.setNombreSalida(nuevaSalida.getNombreSalida());
        comp.setCantidadMaximaTuristas(nuevaSalida.getCantidadMaximaTuristas());
        comp.setFechaAlta(nuevaSalida.getFechaAlta());
        comp.setFechaSalida(nuevaSalida.getFechaSalida());
        comp.setLugarSalida(nuevaSalida.getLugarSalida());
        comp.setImagen(nuevaSalida.getImagen());
        
        assertTrue(nuevaSalida.equals(comp));

        
        

    }


    // Intentar listar todas las salidas turísticas para una actividad que existe
    @Test
    public void listarSalidasTuristicasExistentes() {
    	

        // Creo una salida turística
        DtSalidaTuristica nuevaSalida = newSalidaTuristicaRandom();
        String actividadAsociada = nuevaSalida.getNombreActividad();
        
        String testToString = nuevaSalida.toString();
        Boolean testEquals = nuevaSalida.equals(nuevaSalida);

        when(validadorMock.campoInvalidoAltaSalida(nuevaSalida)).thenReturn(false);
        when(manejadorActividadTuristicaMock.contains(actividadAsociada)).thenReturn(true);

        SalidaTuristica salidaEntidadMock = mock(SalidaTuristica.class);
        ActividadTuristica mockActividad = mock(ActividadTuristica.class);
        when(mockActividad.getNombre()).thenReturn(actividadAsociada);

        Map<String, SalidaTuristica> salidasAsociadas = new HashMap<>();
        salidasAsociadas.put(nuevaSalida.getNombreSalida(), salidaEntidadMock);

        when(mockActividad.getSalidasAsociadas()).thenReturn(salidasAsociadas);

        when(manejadorActividadTuristicaMock.find(nuevaSalida.getNombreActividad())).thenReturn(mockActividad);

        when(salidaEntidadMock.getNombre()).thenReturn(nuevaSalida.getNombreSalida());

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaSalidaTuristica(nuevaSalida);
        });

        try {
            String[] obtenidas = sistemaBajoPrueba.listarSalidasAsociadasAActividadTuristica(nuevaSalida.getNombreActividad()).getColeccionString();

            assertEquals(1, obtenidas.length);
            assertEquals(nuevaSalida.getNombreSalida(), obtenidas[0]);

        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }


    // Intentar listar todas las salidas turísticas activas para una actividad que no existe
    @Test
    public void listarSalidasTuristicasVigentesInexistentes() {

        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarSalidasVigentesAsociadasAActividadTuristica("actividadInexistente");
        });

        LocalDateTime fechaVieja = new LocalDateTime(0);
        LocalDateTime fechaFutura = new LocalDateTime(System.currentTimeMillis() + 1000000000);

        String nombreActividad = "actividad";
        String nombreSalidaVigente = "salidaVigente";
        String nombreSalidaNoVigente = "salidaNoVigente";

        // Creo una actividad turística asociada a la salida turística
        ActividadTuristica actividadTuristica = mock(ActividadTuristica.class);
        when(actividadTuristica.getNombre()).thenReturn(nombreActividad);

        // Creo la entidad de la salida turística vigente
        SalidaTuristica salidaVigenteEntidadMock = mock(SalidaTuristica.class);
        when(salidaVigenteEntidadMock.getNombre()).thenReturn(nombreSalidaVigente);
        when(salidaVigenteEntidadMock.getFechaSalida()).thenReturn(fechaFutura);

        // Creo la entidad de la salida turística no vigente
        SalidaTuristica salidaNoVigenteEntidadMock = mock(SalidaTuristica.class);
        when(salidaNoVigenteEntidadMock.getNombre()).thenReturn(nombreSalidaNoVigente);
        when(salidaNoVigenteEntidadMock.getFechaSalida()).thenReturn(fechaVieja);

        // Creo un mapa de salidas turísticas asociadas a la actividad turística
        Map<String, SalidaTuristica> salidasAsociadas = new HashMap<>();
        salidasAsociadas.put(nombreSalidaVigente, salidaVigenteEntidadMock);
        salidasAsociadas.put(nombreSalidaNoVigente, salidaNoVigenteEntidadMock);
        when(actividadTuristica.getSalidasAsociadas()).thenReturn(salidasAsociadas);

        // Hago que las dependencias externas devuelvan los valores esperados
        when(validadorMock.campoInvalidoAltaSalida(any())).thenReturn(false);
        when(manejadorActividadTuristicaMock.contains(any())).thenReturn(true);
        when(manejadorActividadTuristicaMock.find(any())).thenReturn(actividadTuristica);

        when(actividadTuristica.getSalidasAsociadas()).thenReturn(salidasAsociadas);
        when(actividadTuristica.getFechaAlta()).thenReturn(fechaVieja.toLocalDate());

        manejadorSalidaTuristica.add(salidaVigenteEntidadMock);
        manejadorSalidaTuristica.add(salidaNoVigenteEntidadMock);

        // obtengo las salidas vigentes
        try {
            String[] salidasVigentes = sistemaBajoPrueba.listarSalidasVigentesAsociadasAActividadTuristica(nombreActividad).getColeccionString();
            assertEquals(1, salidasVigentes.length);
            assertEquals(nombreSalidaVigente, salidasVigentes[0]);

        } catch (NoHayEntidadesParaListarException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }


    // Intentar inscribir a un turista en una salida turística que no existe
    @Test
    public void inscribirTuristaEnSalidaTuristicaInexistente() {

        // Chequeo de campos inválido
        when(validadorMock.campoInvalidoInscripcionASalida(any())).thenReturn(true);
        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.inscribirTuristaASalidaTuristica(null);
        });

        // Creo un turista
        Turista turista = mock(Turista.class);
        when(turista.getNombre()).thenReturn("turista");

        when(validadorMock.campoInvalidoInscripcionASalida(any())).thenReturn(true);

        when(manejadorActividadTuristicaMock.contains(any())).thenReturn(true);

        DtInscripcionSalida inscripcion = new DtInscripcionSalida();
        inscripcion.setFechaInscripcion(new LocalDate(System.currentTimeMillis() - 1000000000));
        inscripcion.setCantidadTuristas(1);
        inscripcion.setNickname("turista");
        inscripcion.setNombreSalidaTuristica("salidaInexistente");
        
        DtInscripcionSalida inscripcion2 = new DtInscripcionSalida();
        inscripcion2.setFechaInscripcion(new LocalDate(System.currentTimeMillis() - 1000000000));
        inscripcion2.setCantidadTuristas(1);
        inscripcion2.setNickname("turista");
        inscripcion2.setNombreSalidaTuristica("salidaInexistente");
        
        String testToString = inscripcion.toString();
        Boolean testEquals = inscripcion.equals(inscripcion2);

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.inscribirTuristaASalidaTuristica(inscripcion);
        });

    }

//
//        when(validadorMock.campoInvalidoAltaSalida(any())).thenReturn(false);
//        when(manejadorActividadTuristicaMock.contains(any())).thenReturn(true);
//
//        String nombreSalida = "salidaInexistente";
//        String nombreTurista = "turista";
//        String nombreActividad = "actividad";
//
//        // Creo una actividad turística asociada a la salida turística
//        ActividadTuristica actividadTuristica = mock(ActividadTuristica.class);
//        when(actividadTuristica.getNombre()).thenReturn(nombreActividad);
//
//        // Creo la entidad de la salida turística
//        SalidaTuristica salidaTuristicaEntidadMock = mock(SalidaTuristica.class);
//        when(salidaTuristicaEntidadMock.getNombre()).thenReturn(nombreSalida);
//
//        // Creo un mapa de salidas turísticas asociadas a la actividad turística
//        Map<String, SalidaTuristica> salidasAsociadas = new HashMap<>();
//        salidasAsociadas.put(nombreSalida, salidaTuristicaEntidadMock);
//        when(actividadTuristica.getSalidasAsociadas()).thenReturn(salidasAsociadas);


    // Intentar inscribir a un turista en una salida turística que existe
    @Test
    public void inscribirTuristaEnSalidaTuristicaExistente() {

        // seteo el mock de la validacion
        when(validadorMock.campoInvalidoInscripcionASalida(any())).thenReturn(false);

        // Creo un turista
        Turista turista = mock(Turista.class);
        when(turista.getNombre()).thenReturn("turista");
        when(manejadorUsuarioMock.contains(any())).thenReturn(true);
        when(manejadorUsuarioMock.find(any())).thenReturn(turista);

        // Creo una actividad turística asociada a la salida turística
        ActividadTuristica actividadTuristica = mock(ActividadTuristica.class);
        when(actividadTuristica.getNombre()).thenReturn("actividad");

        // Creo la entidad de la salida turística
        SalidaTuristica salidaTuristicaEntidadMock = mock(SalidaTuristica.class);
        when(salidaTuristicaEntidadMock.getNombre()).thenReturn("salida");
        when(salidaTuristicaEntidadMock.getActividad()).thenReturn(actividadTuristica);
        when(salidaTuristicaEntidadMock.hayCuposDisponibles()).thenReturn(true);
        when(salidaTuristicaEntidadMock.cuposDisponibles()).thenReturn(1);

        // Creo un mapa de salidas turísticas asociadas a la actividad turística
        Map<String, SalidaTuristica> salidasAsociadas = new HashMap<>();
        salidasAsociadas.put("salida", salidaTuristicaEntidadMock);
        when(actividadTuristica.getSalidasAsociadas()).thenReturn(salidasAsociadas);

        // Creo una fecha de alta de la salida turística
        LocalDate fechaAlta = new LocalDate(System.currentTimeMillis() - 1000000000);
        when(salidaTuristicaEntidadMock.getFechaAlta()).thenReturn(fechaAlta);

        // Creo una fecha de salida de la salida turística
        LocalDateTime fechaSalida = new LocalDateTime(System.currentTimeMillis() + 1000000000);
        when(salidaTuristicaEntidadMock.getFechaSalida()).thenReturn(fechaSalida);

        manejadorSalidaTuristica.add(salidaTuristicaEntidadMock);

        // Creo una inscripción a la salida turística
        DtInscripcionSalida inscripcion = new DtInscripcionSalida();
        inscripcion.setFechaInscripcion(new LocalDate(System.currentTimeMillis()));
        inscripcion.setCantidadTuristas(1);
        inscripcion.setNickname("turista");
        inscripcion.setNombreSalidaTuristica("salida");

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.inscribirTuristaASalidaTuristica(inscripcion);
        });

        // Verifico que se haya agregado la inscripción a la salida turística
        verify(salidaTuristicaEntidadMock, times(1)).incrementarCantidadInscriptos();

        // Verifico que se haya agregado la salida turística a la lista de salidas turísticas del turista
        verify(turista, times(1)).agregarInscripcionASalida(any());
    }

    // ------------------------------------------------------------------------------------------------

    private DtSalidaTuristica newSalidaTuristicaRandom() {

        DtSalidaTuristica salidaTuristica = new DtSalidaTuristica();
        salidaTuristica.setNombreActividad("Actividad " + (int) (Math.random() * 100));
        salidaTuristica.setNombreSalida("Salida " + (int) (Math.random() * 100));
        salidaTuristica.setCantidadMaximaTuristas((int) (Math.random() * 100));
        salidaTuristica.setFechaAlta(new LocalDate("2020-01-01"));
        salidaTuristica.setFechaSalida(new LocalDateTime("2020-01-02"));
        salidaTuristica.setLugarSalida("Lugar " + (int) (Math.random() * 100));
        salidaTuristica.setImagen("Imagen " + (int) (Math.random() * 100));
        return salidaTuristica;
    }

	/*
	 * private DtActividadTuristica newActividadTuristicaRandom() {
	 * DtActividadTuristica nuevaActividad = new DtActividadTuristica();
	 * 
	 * nuevaActividad.setNombre("nombre " + (int) (Math.random() * 100));
	 * nuevaActividad.setDescripcion("descripcion " + (int) (Math.random() * 100));
	 * nuevaActividad.setDuracionHrs((int) (Math.random() * 100));
	 * nuevaActividad.setCostoPorPersona((int) (Math.random() * 100));
	 * nuevaActividad.setCiudad("ciudad " + (int) (Math.random() * 100));
	 * nuevaActividad.setFechaAlta(new LocalDate("2020-01-01"));
	 * nuevaActividad.setDepartamento("departamento " + (int) (Math.random() *
	 * 100)); nuevaActividad.setProovedor("proovedor " + (int) (Math.random() *
	 * 100)); nuevaActividad.setImagen("imagen " + (int) (Math.random() * 100));
	 * nuevaActividad.setCategorias(new String[]{"categoria " + (int) (Math.random()
	 * * 100)}); nuevaActividad.setEstado(EnumEstadoActividad.AGREGADA);
	 * 
	 * return nuevaActividad;
	 * 
	 * }
	 */
    
     


}
