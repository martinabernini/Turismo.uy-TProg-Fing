import logica.datatypes.EnumEstadoActividad;
import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.PaqueteActividades;
import logica.interfaces.IManejadorCategoria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.Fabrica;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ManejadorCategoriaTest {


    public static IManejadorCategoria sut;

    @BeforeAll
    public static void init() {
        sut = Fabrica.getInstance().getIManejadorCategoria();
    }

    @Test
    void test() {
        assertFalse(sut.contains("1"));
        assertNull(sut.getAll());
        assertNull(sut.find("1"));

        String nombreCategoria = "nombreCategoria";

        Categoria categoria = Mockito.mock(Categoria.class);
        when(categoria.getNombre()).thenReturn(nombreCategoria);

        sut.add(categoria);

        // Testeo que se haya agregado correctamente la categoria
        assertTrue(sut.contains(nombreCategoria));
        Categoria categoriaDevuelta = sut.find(nombreCategoria);

        assertEquals(categoria, categoriaDevuelta);
        assertEquals(1, sut.getAll().length);

        Categoria categoriaActualizada = Mockito.mock(Categoria.class);
        when(categoriaActualizada.getNombre()).thenReturn(nombreCategoria);

        sut.update(categoriaActualizada);

        assertTrue(sut.contains(nombreCategoria));
        Categoria actividadTuristicaPostActualizar = sut.find(nombreCategoria);

        assertEquals(categoriaActualizada, actividadTuristicaPostActualizar);
        assertEquals(1, sut.getAll().length);


        // Testeo obtener todas las actividades asociadas a una categoria

        ActividadTuristica actividadTuristica = Mockito.mock(ActividadTuristica.class);
        when(actividadTuristica.getNombre()).thenReturn("nombreActividad");

        ActividadTuristica actividadTuristica2 = Mockito.mock(ActividadTuristica.class);
        when(actividadTuristica2.getNombre()).thenReturn("nombreActividad2");

        ActividadTuristica actividadTuristica3 = Mockito.mock(ActividadTuristica.class);
        when(actividadTuristica3.getNombre()).thenReturn("nombreActividad3");

        ActividadTuristica actividadTuristica4 = Mockito.mock(ActividadTuristica.class);
        when(actividadTuristica4.getNombre()).thenReturn("nombreActividad4");


        when(actividadTuristica.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
        when(actividadTuristica2.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
        when(actividadTuristica3.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);
        when(actividadTuristica4.getEstado()).thenReturn(EnumEstadoActividad.CONFIRMADA);

        when(actividadTuristica.getNombre()).thenReturn("nombreActividad");
        when(actividadTuristica2.getNombre()).thenReturn("nombreActividad2");
        when(actividadTuristica3.getNombre()).thenReturn("nombreActividad3");
        when(actividadTuristica4.getNombre()).thenReturn("nombreActividad4");

        Map<String, ActividadTuristica> actividadesCategoria1 = new HashMap<>();
        actividadesCategoria1.put("nombreActividad", actividadTuristica);
        actividadesCategoria1.put("nombreActividad2", actividadTuristica2);

        Map<String, ActividadTuristica> actividadesCategoria2 = new HashMap<>();
        actividadesCategoria2.put("nombreActividad", actividadTuristica);
        actividadesCategoria2.put("nombreActividad2", actividadTuristica2);
        actividadesCategoria2.put("nombreActividad3", actividadTuristica3);
        actividadesCategoria2.put("nombreActividad4", actividadTuristica4);

        Categoria categoria1 = Mockito.mock(Categoria.class);
        when(categoria1.getNombre()).thenReturn("categoria1");
        when(categoria1.getActividades()).thenReturn(actividadesCategoria1);

        Categoria categoria2 = Mockito.mock(Categoria.class);
        when(categoria2.getNombre()).thenReturn("categoria2");
        when(categoria2.getActividades()).thenReturn(actividadesCategoria2);

        sut.add(categoria1);
        sut.add(categoria2);

        assertEquals(3, sut.getAll().length);

        assertEquals(2, sut.getAllActividadesAsociadasACategoria("categoria1").length);
        assertEquals(2, sut.getAllActividadesAsociadasACategoria("categoria1").length);

        assertEquals(4, sut.getAllActividadesAsociadasACategoria("categoria2").length);
        assertEquals(4, sut.getAllActividadesAsociadasACategoria("categoria2").length);

        // Testeo si las acitivdades que me devuelve son las correctas
        Set<ActividadTuristica> actividadesDevueltas = new HashSet<>(Arrays.asList(sut.getAllActividadesAsociadasACategoria("categoria1")));

        assertTrue(actividadesDevueltas.contains(actividadTuristica));
        assertTrue(actividadesDevueltas.contains(actividadTuristica2));

        assertFalse(actividadesDevueltas.contains(actividadTuristica3));
        assertFalse(actividadesDevueltas.contains(actividadTuristica4));


        // Testeo obtener todos los paquetes asociados a una categoria
        Categoria categoriaConPaquetes = Mockito.mock(Categoria.class);
        when(categoriaConPaquetes.getNombre()).thenReturn("categoriaConPaquetes");

        PaqueteActividades paqueteTuristico = Mockito.mock(PaqueteActividades.class);
        when(paqueteTuristico.getNombre()).thenReturn("nombrePaquete");

        PaqueteActividades paqueteTuristico2 = Mockito.mock(PaqueteActividades.class);
        when(paqueteTuristico2.getNombre()).thenReturn("nombrePaquete2");

        Map<String, PaqueteActividades> paquetes = new HashMap<>();
        paquetes.put(paqueteTuristico.getNombre(), paqueteTuristico);
        paquetes.put(paqueteTuristico2.getNombre(), paqueteTuristico2);

        when(categoriaConPaquetes.getPaquetes()).thenReturn(paquetes);

        sut.add(categoriaConPaquetes);

        assertEquals(2, sut.getAllPaquetesAsociadosACategoria("categoriaConPaquetes").length);
        assertEquals(2, sut.getAllPaquetesAsociadosACategoria("categoriaConPaquetes").length);

        // Testeo si los paquetes que me devuelve son los correctos

        Set<PaqueteActividades> paquetesDevueltos = new HashSet<>(Arrays.asList(sut.getAllPaquetesAsociadosACategoria("categoriaConPaquetes")));

        assertTrue(paquetesDevueltos.contains(paqueteTuristico));
        assertTrue(paquetesDevueltos.contains(paqueteTuristico2));



    }


}
