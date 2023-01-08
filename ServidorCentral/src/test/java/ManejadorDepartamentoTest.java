import logica.datatypes.EnumEstadoActividad;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.interfaces.IManejadorDepartamento;
import logica.manejadores.ManejadorDepartamento;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManejadorDepartamentoTest {

    // Sistema que se va a testear
    public static IManejadorDepartamento sistemaBajoPrueba = ManejadorDepartamento.getInstance();

    @BeforeEach
    public void limpiarSistema() {
        // Limpiar el sistema bajo prueba
        sistemaBajoPrueba.removeAll();
    }

    @AfterEach
    public void cargarDatos() {
        sistemaBajoPrueba.removeAll();
    }

    // Registrar un nuevo departamento y luego listar todos
    @Test
    public void registrarDepartamentoYListarTodos() {
        Departamento departamento = newDepartamentoRandom();
        sistemaBajoPrueba.add(departamento);

        assertEquals(1, sistemaBajoPrueba.getAll().length);
        Departamento[] departamentos = sistemaBajoPrueba.getAll();
        assertEquals(departamento, departamentos[0]);

    }

    // Registrar un nuevo departamento y obtener ese departamento específico
    @Test
    public void registrarDepartamentoYObtenerEseDepartamento() {
        Departamento departamento = newDepartamentoRandom();
        sistemaBajoPrueba.add(departamento);
        assertEquals(departamento, sistemaBajoPrueba.find(departamento.getNombre()));
    }

    // Registrar varios departamentos y luego listar todas las actividades asociadas a un departamento
    @Test
    public void registrarVariosDepartamentosYListarTodasLasActividadesAsociadasADepartamento() {

        // me fijo que devuelva null si no existe el departamento
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento que no existe"));

        // Creo departamento
        Departamento departamento = new Departamento("departamento1", "descripcion1", "url");

        // Lo agrego al sistema y me fijo que devuelva null si no tiene actividades
        sistemaBajoPrueba.add(departamento);
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento1"));

        // Creo actividades y las agrego al departamento
        List<ActividadTuristica> actividades = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            ActividadTuristica actividad = new ActividadTuristica();
            actividad.setNombre("actividadX" + i);
            actividad.setDepartamento(departamento);
            departamento.agregarActividadTuristica(actividad);
            sistemaBajoPrueba.update(departamento);
            actividades.add(actividad);
        }

        // Me fijo que devuelva las actividades que le agregué
        ActividadTuristica[] obtenidas =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento1");

        assertEquals(5, obtenidas.length);

        // Creo otro departamento y le agrego actividades
        Departamento departamento2 = new Departamento("departamento2", "descripcion2", "url");
        sistemaBajoPrueba.add(departamento2);

        // Me fijo que devuelva null si no tiene actividades
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento2"));


        // Creo actividades y las agrego al departamento
        List<ActividadTuristica> actividades2 = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            ActividadTuristica actividad = new ActividadTuristica();
            actividad.setNombre("actividadY" + i);
            actividad.setDepartamento(departamento2);
            departamento2.agregarActividadTuristica(actividad);
            sistemaBajoPrueba.update(departamento);
            actividades2.add(actividad);
        }

        // Me fijo que devuelva las actividades que le agregué
        ActividadTuristica[] obtenidas2 =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento2");

        assertEquals(7, obtenidas2.length);

        // Me fijo que devuelva las actividades del primer departamento y no las del segundo
        ActividadTuristica[] obtenidas3 =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento1");

        assertEquals(5, obtenidas3.length);

        // Me fijo que devuelva las actividades del segundo departamento y no las del primero
        ActividadTuristica[] obtenidas4 =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento2");

        assertEquals(7, obtenidas4.length);

        // Me fijo que devuelva null si no existe el departamento
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamento("departamento que no existe"));

    }
    /// Registrar un departamento con actividades asociadas y luego listo todas las actividades asociadas a ese
    // departamento pero que no estén en un paquete específico
    @Test
    public void registrarDepartamentoConActividadesAsociadasYListarTodasLasActividadesAsociadasADepartamentoQueNoEstenEnUnPaqueteEspecifico() {

        // Creo departamento
        Departamento departamento = new Departamento("departamento1", "descripcion1", "url");

        // Lo agrego al sistema y me fijo que devuelva null si no tiene actividades
        sistemaBajoPrueba.add(departamento);
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamentoNoEnPaquete("departamento1", "paquete que no existe"));

        // Creo actividades y las agrego al departamento
        List<ActividadTuristica> actividades = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            ActividadTuristica actividad = new ActividadTuristica();
            actividad.setNombre("actividadX" + i);
            actividad.setDepartamento(departamento);
            departamento.agregarActividadTuristica(actividad);
            sistemaBajoPrueba.update(departamento);
            actividades.add(actividad);
        }

        // Me fijo que devuelva las actividades que le agregué
        ActividadTuristica[] obtenidas =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamentoNoEnPaquete("departamento1", "paquete que no existe");

        assertEquals(5, obtenidas.length);

        // Creo otro departamento y le agrego actividades
        Departamento departamento2 = new Departamento("departamento2", "descripcion2", "url");
        sistemaBajoPrueba.add(departamento2);

        // Me fijo que devuelva null si no tiene actividades
        assertNull(sistemaBajoPrueba.getAllActividadesAsociadasADepartamentoNoEnPaquete("departamento2", "paquete que no existe"));

        // Creo actividades y las agrego al departamento
        List<ActividadTuristica> actividades2 = new LinkedList<>();

        for (int i = 0; i < 7; i++) {
            ActividadTuristica actividad = new ActividadTuristica();
            actividad.setNombre("actividadY" + i);
            actividad.setDepartamento(departamento2);
            departamento2.agregarActividadTuristica(actividad);
            sistemaBajoPrueba.update(departamento);
            actividades2.add(actividad);
        }

        // Me fijo que devuelva las actividades que le agregué
        ActividadTuristica[] obtenidas2 =
                sistemaBajoPrueba.getAllActividadesAsociadasADepartamentoNoEnPaquete("departamento2", "paquete que no existe");

    }




    // -------------------------------------------------------------------------

    private static Departamento newDepartamentoRandom() {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char c = (char) (Math.random() * 26 + 'a');
            randomString.append(c);
        }
        return new Departamento(
                randomString.toString(),
                "descripcion" + Math.random(),
                "url" + Math.random()
        );
    }

    private static ActividadTuristica newActividadRandom() {
        ActividadTuristica actividad = new ActividadTuristica();
        actividad.setDescripcion("Descripcion");
        actividad.setDuracionHrs((int) (Math.random() * 100));
        actividad.setCostoPorPersona((float) (Math.random() * 100));
        actividad.setCiudad("Ciudad " + Math.random());
        actividad.setFechaAlta(new LocalDate("01/01/2020"));
        actividad.setImagen("Imagen ");
        actividad.setEstado(EnumEstadoActividad.AGREGADA);
        return actividad;
    }
}
