import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.controladores.ControladorUsuario;

import logica.datatypes.DtProveedor;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IManejadorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.Validador;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Fabrica;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorUsuarioTest {

    // Sistema que se va a testear
    public static IControladorUsuario sistemaBajoPrueba = null;

    // Sus dependencias
    public static IManejadorUsuario manejadorUsuario = null;
    public static IValidador validadorMock = null;

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        Fabrica.getInstance().setLogManejadores(false);
        // Creo las dependencias
        manejadorUsuario = Fabrica.getInstance().getIManejadorUsuario();
        validadorMock = mock(Validador.class);

        // Creo el sistema bajo prueba
        sistemaBajoPrueba = new ControladorUsuario(manejadorUsuario, validadorMock);
    }

    @BeforeEach
    void setUp() {
        manejadorUsuario = Fabrica.getInstance().getIManejadorUsuario();
        validadorMock = mock(Validador.class);

        // Creo el sistema bajo prueba
        sistemaBajoPrueba = new ControladorUsuario(manejadorUsuario, validadorMock);
    }

    @AfterEach
    void tearDown() {
        manejadorUsuario.removeAll();
    }

    // Registrar un nuevo usuario y luego listar todos
    @Test
    public void registrarUsuarioYListarTodos() {
        DtTurista turista = newTuristaRandom();

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(false);
        when(validadorMock.isValidNickname(anyString())).thenReturn(true);
        when(validadorMock.isEmail(anyString())).thenReturn(true);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        try {
            String[] usuarios = sistemaBajoPrueba.listarUsuarios().getColeccionString();
            assertEquals(1, usuarios.length);
            assertEquals(turista.getNickname(), usuarios[0]);

        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        try {
            String[] usuarios = sistemaBajoPrueba.listarUsuarios().getColeccionString();
            assertEquals(2, usuarios.length);

        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }

    // Registrar un nuevo usuario y obtener ese usuario específico
    @Test
    public void registrarUsuarioYObtenerEseUsuario() {
        DtTurista turista = newTuristaRandom();

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(false);
        when(validadorMock.isValidNickname(anyString())).thenReturn(true);
        when(validadorMock.isEmail(anyString())).thenReturn(true);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        try {
            DtUsuario usuario = sistemaBajoPrueba.getUsuario(turista.getNickname());
            assertEquals(turista, usuario);

            DtProveedor proveedor = newProveedorRandom();

            assertDoesNotThrow(() -> {
                sistemaBajoPrueba.darDeAltaUsuario(proveedor);
            });

            usuario = sistemaBajoPrueba.getUsuario(proveedor.getNickname());
            assertEquals(proveedor, usuario);

        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }

    // Registrar un nuevo usuario sin dar campos opcionales
    @Test
    public void registrarUsuarioSinDarCamposOpcionales() {
        DtTurista turista = newTuristaRandom();
        turista.setImagen(null);

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(false);
        when(validadorMock.campoInvalidoModificarUsuario(any())).thenCallRealMethod();
        when(validadorMock.isValidNickname(anyString())).thenReturn(true);
        when(validadorMock.isEmail(anyString())).thenReturn(true);

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        try {
            DtUsuario usuario = sistemaBajoPrueba.getUsuario(turista.getNickname());
            assertEquals(turista, usuario);

            DtProveedor proveedor = newProveedorRandom();
            proveedor.setImagen(null);

            assertDoesNotThrow(() -> {
                sistemaBajoPrueba.darDeAltaUsuario(proveedor);
            });

            usuario = sistemaBajoPrueba.getUsuario(proveedor.getNickname());
            assertEquals(proveedor, usuario);

        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }

    // Registrar un nuevo usuario con datos inválidos
    @Test
    public void registrarUsuarioConDatosInvalidos() {

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(true);

        DtTurista turista = newTuristaRandom();

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        DtProveedor proveedor = newProveedorRandom();
        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

    }

    // Intentar obtener un usuario específico que no exista
    @Test
    public void obtenerUsuarioQueNoExiste() {

        assertThrows(EntidadNoExisteException.class, () -> {
            sistemaBajoPrueba.getUsuario("usuarioQueNoExiste");
        });

    }

    // Intentar registrar un usuario con un nombre de usuario que ya existe
    @Test
    public void registrarUsuarioConNombreDeUsuarioRepetido() {

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(false);

        DtTurista turista = newTuristaRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        DtTurista turistaRepetido = newTuristaRandom();
        turistaRepetido.setNickname(turista.getNickname());
        assertThrows(EntidadRepetidaException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(turistaRepetido);
        });

        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        DtProveedor proveedorRepetido = newProveedorRandom();
        proveedorRepetido.setNickname(proveedor.getNickname());
        assertThrows(EntidadRepetidaException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedorRepetido);
        });

    }


    // Intentar registrar un usuario con un email que ya existe
    @Test
    public void registrarUsuarioConEmailRepetido() {

        when(validadorMock.campoInvalidoAltaUsuario(any())).thenReturn(false);

        DtTurista turista = newTuristaRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        DtTurista turistaRepetido = newTuristaRandom();
        turistaRepetido.setEmail(turista.getEmail());
        assertThrows(EntidadRepetidaException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(turistaRepetido);
        });

        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        DtProveedor proveedorRepetido = newProveedorRandom();
        proveedorRepetido.setEmail(proveedor.getEmail());
        assertThrows(EntidadRepetidaException.class, () -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedorRepetido);
        });
    }


    // Intentar listar todos los usuarios cuando no haya usuarios registrados
    @Test
    public void listarUsuariosCuandoNoHay() {

        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarUsuarios();
        });

    }


    // Agregar tres usuarios, listar todos los usuarios y verificar que sean los esperados
    @Test
    public void agregarUsuariosYListarTodos() {

        Set<String> nicknamesEsperados = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            DtTurista turista = newTuristaRandom();
            assertDoesNotThrow(() -> {
                sistemaBajoPrueba.darDeAltaUsuario(turista);
            });
            nicknamesEsperados.add(turista.getNickname());
        }

        for (int i = 0; i < 3; i++) {
            DtProveedor proveedor = newProveedorRandom();
            assertDoesNotThrow(() -> {
                sistemaBajoPrueba.darDeAltaUsuario(proveedor);
            });
            nicknamesEsperados.add(proveedor.getNickname());
        }

        try {
            String[] usuariosObtenidos = sistemaBajoPrueba.listarUsuarios().getColeccionString();

            assertEquals(nicknamesEsperados.size(), usuariosObtenidos.length);

            Set<String> nicknamesObtenidos = new HashSet<>(Arrays.asList(usuariosObtenidos));

            assertEquals(nicknamesEsperados, nicknamesObtenidos);

        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }


    // Listar todos los usuarios que son turistas
    @Test
    public void listarTuristas() {
        // Cuando no hay turistas ni proveedores
        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarTuristas();
        });

        // Cuando hay turistas pero no hay proveedores
        DtTurista turista = newTuristaRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        try {
            String[] turistasObtenidos = sistemaBajoPrueba.listarTuristas().getColeccionString();
            assertEquals(1, turistasObtenidos.length);
            assertEquals(turista.getNickname(), turistasObtenidos[0]);
        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

        // Cuando hay turistas y proveedores
        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        try {
            String[] turistasObtenidos = sistemaBajoPrueba.listarTuristas().getColeccionString();
            assertEquals(1, turistasObtenidos.length);
            assertEquals(turista.getNickname(), turistasObtenidos[0]);
        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

    }

    // Listar todos los usuarios que son proveedores
    @Test
    public void listarProveedores() {

        // Cuando no hay turistas ni proveedores
        assertThrows(NoHayEntidadesParaListarException.class, () -> {
            sistemaBajoPrueba.listarProveedores();
        });

        // Cuando hay proveedores pero no hay turistas
        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        try {
            String[] proveedoresObtenidos = sistemaBajoPrueba.listarProveedores().getColeccionString();
            assertEquals(1, proveedoresObtenidos.length);
            assertEquals(proveedor.getNickname(), proveedoresObtenidos[0]);
        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }

        // Cuando hay turistas y proveedores
        DtTurista turista = newTuristaRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        try {
            String[] proveedoresObtenidos = sistemaBajoPrueba.listarProveedores().getColeccionString();
            assertEquals(1, proveedoresObtenidos.length);
            assertEquals(proveedor.getNickname(), proveedoresObtenidos[0]);
        } catch (NoHayEntidadesParaListarException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());
        }
    }

    // Intentar iniciar sesión con un nombre de usuario y una contraseña válidos
    @Test
    public void loginConNombreDeUsuarioYContrasenaValidos() {

        when(validadorMock.isValidNickname(anyString())).thenCallRealMethod();
        when(validadorMock.isEmail(anyString())).thenCallRealMethod();

        DtTurista turista = newTuristaRandom();
        turista.setNickname("usuarioNicolásÑ");

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        assertTrue(sistemaBajoPrueba.esLoginValido(turista.getNickname(), turista.getPassword()));
        assertTrue(sistemaBajoPrueba.esLoginValido(turista.getEmail(), turista.getPassword()));
    }

    // Intentar iniciar sesión con un nombre de usuario o contraseña inválidos
    @Test
    public void loginConNombreDeUsuarioOContrasenaInvalidos() {

        when(validadorMock.isValidNickname(anyString())).thenCallRealMethod();
        when(validadorMock.isEmail(anyString())).thenCallRealMethod();

        DtTurista turista = newTuristaRandom();

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        assertFalse(sistemaBajoPrueba.esLoginValido(turista.getNickname() + "a", turista.getPassword()));
        assertFalse(sistemaBajoPrueba.esLoginValido(turista.getEmail(), turista.getPassword() + "a"));
        assertFalse(sistemaBajoPrueba.esLoginValido(turista.getPassword(), turista.getPassword() + "a"));

    }

    // Intentar obtener un usuario desde el login
    @Test
    public void testLogin() {

        // Llamar a los métodos reales del validador
        when(validadorMock.isValidNickname(anyString())).thenCallRealMethod();
        when(validadorMock.isEmail(anyString())).thenCallRealMethod();

        DtTurista turista = newTuristaRandom();
        turista.setNickname("Nicolásñ");

        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.getUsuarioLogin(turista.getNickname());
        });
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.getUsuarioLogin(turista.getEmail());
        });
        assertThrows(EntidadNoExisteException.class, () -> {
            sistemaBajoPrueba.getUsuarioLogin("dasdasdsadasda");
        });
        assertThrows(EntidadNoExisteException.class, () -> {
            sistemaBajoPrueba.getUsuarioLogin("sdfsdfsdfsfd");
        });

        try {
            DtUsuario usuarioObtenidoViaNick = sistemaBajoPrueba.getUsuarioLogin(turista.getNickname());
            assertEquals(turista, usuarioObtenidoViaNick);

            DtUsuario usuarioObtenidoViaEmail = sistemaBajoPrueba.getUsuarioLogin(turista.getEmail());
            assertEquals(turista, usuarioObtenidoViaEmail);

        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            fail("No debería haber lanzado excepción: " + e.getMessage());

        }

    }

    // Modificar un usuario con todos los campos requeridos
    @Test
    public void modificarUsuarioConTodosLosCamposRequeridos() {
        /*
            Para un turista solo se puede modificar
            Nombre
            Apellido
            FechaDeNacimiento
            Nacionalidad
            Imagen
            Password

            Para un proveedor solo se puede modificar
            Nombre
            Apellido
            FechaNacimiento
            Imagen
            Password
            Descripcion <-
            UrlSitioWeb <-


            -----------------------------------------------------------------------------
            Mandar campos inválidos que no se pueden modificar NO DEBERIA LANZAR EXCEPCION
            -----------------------------------------------------------------------------

        */
        when(validadorMock.isValidNickname(anyString())).thenCallRealMethod();
        when(validadorMock.isEmail(anyString())).thenCallRealMethod();

        IControladorUsuario sistemaBajoPruebaConValidadorReal = new ControladorUsuario(manejadorUsuario, new Validador());

        DtTurista turista = newTuristaRandom();
        turista.setNickname("Nicoertlásñasd-asddsa");

        assertDoesNotThrow(() -> {
            sistemaBajoPruebaConValidadorReal.darDeAltaUsuario(turista);
        });

        DtTurista turistaModificado = newTuristaRandom();
        turistaModificado.setNickname(turista.getNickname());
        turistaModificado.setEmail(turista.getEmail());

        assertDoesNotThrow(() -> {
            sistemaBajoPruebaConValidadorReal.modificarUsuario(turistaModificado);
        });


        // Ahora modifico un proveedor
        DtProveedor proveedor = newProveedorRandom();
        proveedor.setNickname("Nic_olásñasd-asddsa");

        assertDoesNotThrow(() -> {
            sistemaBajoPruebaConValidadorReal.darDeAltaUsuario(proveedor);
        });

        DtProveedor proveedorModificado = newProveedorRandom();
        proveedorModificado.setNickname(proveedor.getNickname());
        proveedorModificado.setEmail(proveedor.getEmail());

        assertDoesNotThrow(() -> {
            sistemaBajoPruebaConValidadorReal.modificarUsuario(proveedorModificado);
        });

    }

    // Intentar modificar un usuario que no exista
    @Test
    public void modificarUsuarioQueNoExiste() {

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.modificarUsuario(newTuristaRandom());
        });

    }

    // Intentar modificar a un turista proporcionando información para un proveedor
    @Test
    public void modificarTuristaConInformacionParaProveedor() {

        DtTurista turista = newTuristaRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(turista);
        });

        DtProveedor proveedor = newProveedorRandom();
        proveedor.setNickname(turista.getNickname());

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.modificarUsuario(proveedor);
        });

    }

    // Intentar modificar a un proveedor proporcionando información para un turista
    @Test
    public void modificarProveedorConInformacionParaTurista() {

        DtProveedor proveedor = newProveedorRandom();
        assertDoesNotThrow(() -> {
            sistemaBajoPrueba.darDeAltaUsuario(proveedor);
        });

        DtTurista turista = newTuristaRandom();
        proveedor.setNickname(proveedor.getNickname());

        assertThrows(CampoInvalidoException.class, () -> {
            sistemaBajoPrueba.modificarUsuario(turista);
        });

    }

    // ---------------------------------------------------------------------------------------------

    private DtTurista newTuristaRandom() {
        DtTurista usuario = new DtTurista();
        usuario.setNickname("usuario" + new Random().nextInt());
        usuario.setNombre("usuario");
        usuario.setEmail("email" + UUID.randomUUID().toString().substring(0, 4) + "@gmail.com");
        usuario.setApellido("apellido");
        usuario.setPassword("password" + UUID.randomUUID().toString());
        usuario.setFechaNacimiento(new LocalDate(1990, 1, 1));
        usuario.setImagen("imagen" + UUID.randomUUID().toString());
        usuario.setNacionalidad("nacionalidad" + UUID.randomUUID().toString());
        return usuario;
    }

    private DtProveedor newProveedorRandom() {
        DtProveedor usuario = new DtProveedor();
        usuario.setNickname("usuario" + new Random().nextInt());
        usuario.setNombre("usuario");
        usuario.setEmail("email" + UUID.randomUUID().toString().substring(0, 4) + "@gmail.com");
        usuario.setApellido("apellido");
        usuario.setPassword("password" + UUID.randomUUID().toString());
        usuario.setFechaNacimiento(new LocalDate(System.currentTimeMillis()));
        usuario.setImagen("imagen" + UUID.randomUUID().toString());
        usuario.setDescripcion("descripcion" + UUID.randomUUID().toString());
        usuario.setUrlSitioWeb("sitioWeb" + UUID.randomUUID().toString());
        return usuario;
    }

}
