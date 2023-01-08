import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.controladores.ControladorDepartamento;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IManejadorDepartamento;
import logica.interfaces.IValidador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Fabrica;


public class ControladorDepartamentoTest {

    // Sistema que se va a testear
    public static IControladorDepartamento sistemaBajoPrueba = null;

    // Sus dependencias
    public static IManejadorDepartamento manejadorDepartamento = null;
    public static IValidador validadorMock = null;

    @AfterEach
    void tearDown() {
        manejadorDepartamento.removeAll();
    }

    @BeforeEach
    void setUp() {

        Fabrica fabrica = Fabrica.getInstance();
        fabrica.setLogManejadores(false);

        // Creo las dependencias
        manejadorDepartamento = fabrica.getIManejadorDepartamento();
        validadorMock = mock(IValidador.class);

        // Creo el sistema bajo prueba
        sistemaBajoPrueba = new ControladorDepartamento(manejadorDepartamento, validadorMock);

        manejadorDepartamento.removeAll();
    }

    // ----------------------------------------------------------------
    // ----------------------------------------------------------------

    // Intentar registrar un departamento con información inválida
    @Test
    public void registrarDepartamentoDataInvalida() {
        when(validadorMock.campoInvalidoAltaDepartamento(anyString(), anyString(), anyString())).thenReturn(true);

        assertThrows(Exception.class, () -> {
            sistemaBajoPrueba.darDeAltaDepartamento("a", "b", "c");
        });
    }

    // Intentar registrar un departamento con información válida
    @Test
    public void registrarDepartamentoDataValida() {
        when(validadorMock.campoInvalidoAltaDepartamento(any(), any(), any())).thenReturn(false);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre", "descripcion", "url");
        });
    }

    // Intentar registrar un departamento que ya existe
    @Test
    public void registrarDepartamentoRepetido() {
        when(validadorMock.campoInvalidoAltaDepartamento(any(), any(), any())).thenReturn(false);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre", "descripcion", "url");
        });

        assertThrows(EntidadRepetidaException.class, () -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre", "descripcion", "url");
        });
    }

    // Intentar listar todos los departamentos cuando hay departamentos registrados
    @Test
    public void listarDepartamentosCuandoHay() {

        when(validadorMock.campoInvalidoAltaDepartamento(any(), any(), any())).thenReturn(false);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre", "descripcion", "url");
        });

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre2", "descripcion2", "url2");
        });

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaDepartamento("nombre3", "descripcion3", "url3");
        });

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.listarDepartamentos();
        });

        try {
            assertEquals(3, sistemaBajoPrueba.listarDepartamentos().getColeccionString().length);
        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

    }

    // Intentar listar todos los departamentos cuando no hay departamentos registrados
    @Test
    public void listarDepartamentosCuandoNoHay() {
        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarDepartamentos();
        });
    }
}
