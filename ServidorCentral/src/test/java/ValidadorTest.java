import logica.datatypes.*;
import logica.interfaces.IValidador;
import logica.validacion.Validador;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

// Testear el IValidador devuelto por la fábrica

public class ValidadorTest {

    // Sistema que se va a testear
    public static IValidador sistemaBajoPrueba = null;

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        sistemaBajoPrueba = new Validador();
    }

    // campoInvalidoAltaUsuario(DtUsuario usuario);
    @Test
    public void validadorAltaUsuario() {
    	DtTurista usuario = new DtTurista();
        usuario.setNickname("");
        usuario.setNombre("usuario");
        usuario.setEmail("email" + UUID.randomUUID().toString().substring(0, 4) + "@gmail.com");
        usuario.setApellido("apellido");
        usuario.setPassword("password" + UUID.randomUUID().toString());
        usuario.setFechaNacimiento(new LocalDate("01/01/1990"));
        usuario.setImagen("imagen" + UUID.randomUUID().toString());
        usuario.setNacionalidad("nacionalidad" + UUID.randomUUID().toString());


        assertTrue(sistemaBajoPrueba.campoInvalidoAltaUsuario(null));
        
        assertEquals(sistemaBajoPrueba.campoInvalidoAltaUsuario(usuario), true);
        usuario.setNickname("s");

        assertFalse(sistemaBajoPrueba.campoInvalidoAltaUsuario(usuario));

        assertFalse(sistemaBajoPrueba.campoInvalidoAltaUsuario(usuario));
        // campos nulos

        // campos vacíos

        // sin dar campos opcionales

        // con campos opcionales

        // con campos opcionales nulos


    }


//    // campoInvalidoModificarUsuario(DtUsuario usuario);
//    @Test
//    public void validadorModificarUsuario() {
//    	fail("Implementar");
//    }

    // campoInvalidoAltaActividad(DtActividadTuristica actividad);
    @Test
    public void validadorAltaActividad() {
    	DtActividadTuristica actividad = new DtActividadTuristica();
        actividad.setNombre("s");
        actividad.setDescripcion("Descripcion " + Math.random());
        actividad.setDuracionHrs((int) (Math.random() * 100));
        actividad.setCostoPorPersona(0);
        actividad.setCiudad("Ciudad " + Math.random());
        actividad.setFechaAlta(new LocalDate("01/01/2020"));
        actividad.setDepartamento("Departamento " + Math.random());
        actividad.setProovedor("Proovedor " + Math.random());
        actividad.setImagen("Imagen " + Math.random());
        WrapperString resCategorias= new WrapperString(new String[]{"Categoria " + Math.random()});
        actividad.setCategorias(resCategorias);
        actividad.setEstado(EnumEstadoActividad.AGREGADA);


        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(null));

//        // TODO chequear que onda este test
//        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
    	
    	actividad.setCostoPorPersona(10);
    	actividad.setNombre("");
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
        actividad.setNombre("s");

        actividad.setDescripcion("");
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
        actividad.setDescripcion("vds");
        
        actividad.setCiudad("");
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
        actividad.setCiudad("acs");
        
        actividad.setDepartamento("");
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
        actividad.setDepartamento("cas");
        
        actividad.setDepartamento("");
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaActividad(actividad));
        actividad.setDepartamento("ca");
    }

    // campoInvalidoAltaSalida(DtSalidaTuristica salida);
    @Test
    public void validadorAltaSalida() {
    	DtSalidaTuristica salidaTuristica = new DtSalidaTuristica();
        salidaTuristica.setNombreActividad("Actividad " + (int) (Math.random() * 100));
        salidaTuristica.setNombreSalida("Salida " + (int) (Math.random() * 100));
        salidaTuristica.setCantidadMaximaTuristas((int) (Math.random() * 100));
        salidaTuristica.setFechaAlta(new LocalDate("2020/01/01"));
        salidaTuristica.setFechaSalida(new LocalDateTime("2020/01/02"));
        salidaTuristica.setLugarSalida("Lugar " + (int) (Math.random() * 100));
        salidaTuristica.setImagen("Imagen " + (int) (Math.random() * 100));

        assertTrue(sistemaBajoPrueba.campoInvalidoAltaSalida(null));
        assertFalse(sistemaBajoPrueba.campoInvalidoAltaSalida(salidaTuristica));
    }

    // campoInvalidoInscripcionASalida(DtInscripcionSalida inscripcion);
    @Test
    public void validadorInscripcionASalida() {
        assertTrue(sistemaBajoPrueba.campoInvalidoInscripcionASalida(null));
    }

    // campoInvalidoAltaPaquete(DtPaqueteActividades paquete);
    @Test
    public void validadorAltaPaquete() {
    	 DtPaqueteActividades paqueteActividades = new DtPaqueteActividades();
         paqueteActividades.setNombre("Paquete " + (int) (Math.random() * 100));
         paqueteActividades.setDescripcion("Descripcion");
         paqueteActividades.setValidezEnDias(10);
         paqueteActividades.setFechaAlta(new LocalDate());
         WrapperString resActividades= new WrapperString(new String[]{"Actividad 1", "Actividad 2"});
         paqueteActividades.setNombreActividades(resActividades);
         paqueteActividades.setDescuento(0.5f);
         paqueteActividades.setImagen("Imagen");

        assertTrue(sistemaBajoPrueba.campoInvalidoAltaPaquete(null));
        assertFalse(sistemaBajoPrueba.campoInvalidoAltaPaquete(paqueteActividades));
    	
    	paqueteActividades.setDescuento(10000);
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaPaquete(paqueteActividades));
        assertTrue(sistemaBajoPrueba.campoInvalidoComprarPaquete(null, null));
    
    }

//    // usuarioYaEstaRegistradoASalida(Turista turista, DtInscripcionSalida nuevaInscripcion);
//    @Test
//    public void validadorUsuarioYaEstaRegistradoASalida() {
//        fail("Implementar");
//    }

    // campoInvalidoAltaDepartamento(String nombre, String descripcion, String url);
    @Test
    public void validadorAltaDepartamento() {
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento(null, null, null));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("", "descripcion", "url"));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("   ", "descripcion", "url"));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("sdfsdf%fsf", "descripcion", "url"));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("sdfsdf%fsf", "", "url"));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("asdasddsa", "sdfsdf%fsf", ""));
        assertTrue(sistemaBajoPrueba.campoInvalidoAltaDepartamento("sdfsdfsf", "sd  fsdf%fsf", "   "));
        assertFalse(sistemaBajoPrueba.campoInvalidoAltaDepartamento("sdfsdfsf", "sd  fsdf%fsf", "url"));
    }


}
