package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IControladorPaqueteActividades;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IValidador;
import utils.Fabrica;
import presentacion.actividadturistica.AceptarRechazarAct;
import presentacion.actividadturistica.AltaActTuristica;
import presentacion.actividadturistica.ConsultaActTur;
import presentacion.paqueteactividades.AgregarActTurAPaquete;
import presentacion.paqueteactividades.ConsultaPaqActTur;
import presentacion.paqueteactividades.CrearPaqueteActividades;
import presentacion.salidaturistica.AltaSalidaTur;
import presentacion.salidaturistica.ConsultaSalTur;
import presentacion.salidaturistica.InscripcionSalTur;
import presentacion.usuario.ConsultarUsuario;
import presentacion.usuario.CrearUsuario;
import presentacion.usuario.ModificarUsuario;
import presentacion.categoria.AltaCategoria;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

    private static JFrame frmGestionDeUsuarios;
    private static CrearUsuario altaUsusarioIFrame;
    private static ConsultarUsuario consultaUsusarioIFrame;
    private static ModificarUsuario modificarUsuarioIFrame;
    private static AltaActTuristica altaActividadTurisitcaIFrame;
    private static ConsultaActTur consultaActividadTuristicaIFrame;
    private static AltaSalidaTur altaSalidaTuristicaIFrame;
    private static InscripcionSalTur inscripcionSalidaTuristicaIFrame;
    private static CrearPaqueteActividades crearPaqueteActividadesIFrame;
    private static AgregarActTurAPaquete agregarActividadTuristicaAPaqueteIFrame;
    private static ConsultaPaqActTur consultaPaqueteIFrame;
    private static ConsultaSalTur consultaSalidaTuristicaIFrame;
    private static AltaCategoria altaCategoriaIFrame;
    private static AceptarRechazarAct aceptarRechazarActividad;

    // ----------------------------------------------------------------

    private static IControladorActividadTuristica controladorActividad;
    private static IControladorDepartamento controladorDepartamento;
    private static IControladorPaqueteActividades controladorPaquete;
    private static IControladorSalidaTuristica controladorSalida;
    private static IControladorUsuario controladorUsuario;
    private static IValidador validador;

    private static ICargaDatos cargaDatos;

    /**
     * Launch the application.
     */
    public static void runGUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmGestionDeUsuarios.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Principal() {
        initialize();

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();

        controladorActividad = fabrica.getIControladorActividadTuristica();
        controladorDepartamento = fabrica.getIControladorDepartamento();
        controladorPaquete = fabrica.getIControladorPaqueteActividades();
        controladorSalida = fabrica.getIControladorSalidaTuristica();
        controladorUsuario = fabrica.getIControladorUsuario();
        validador = fabrica.getIValidador();
        cargaDatos = fabrica.getICargaDatos();

        // ----------------------------------------------------------------

        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la
        // ejecución.
        // Cada InternasslFrame usa un layout diferente, simplemente para mostrar
        // distintas opciones.

        frmGestionDeUsuarios.getContentPane().setLayout(null);

        altaUsusarioIFrame = new CrearUsuario(controladorUsuario, validador);

        consultaActividadTuristicaIFrame = new ConsultaActTur();

        altaSalidaTuristicaIFrame = new AltaSalidaTur();
        // altaSalidaTuristicaIFrame = new
        // AltaSalidaTur(controladorDepartamento,controladorActividad);
        consultaSalidaTuristicaIFrame = new ConsultaSalTur();

        crearPaqueteActividadesIFrame = new CrearPaqueteActividades(controladorPaquete, validador);
        agregarActividadTuristicaAPaqueteIFrame = new AgregarActTurAPaquete(controladorPaquete, controladorDepartamento,
                controladorActividad);

        frmGestionDeUsuarios.getContentPane().add(altaUsusarioIFrame);

        frmGestionDeUsuarios.getContentPane().add(consultaActividadTuristicaIFrame);

        frmGestionDeUsuarios.getContentPane().add(altaSalidaTuristicaIFrame);
        frmGestionDeUsuarios.getContentPane().add(consultaSalidaTuristicaIFrame);

        frmGestionDeUsuarios.getContentPane().add(crearPaqueteActividadesIFrame);
        frmGestionDeUsuarios.getContentPane().add(agregarActividadTuristicaAPaqueteIFrame);

        altaUsusarioIFrame.setVisible(false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(200, 100, 1000, 800);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un
        // evento asociado que permite realizar una acción una vez se seleccionan.
        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE SISTEMA Y DEPARTAMENTO
        // ---------------------------------------------------------------------------------------------------

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmGestionDeUsuarios.setVisible(false);
                frmGestionDeUsuarios.dispose();
            }
        });

        JMenuItem menuCargarDatos = new JMenuItem("Cargar datos");
        menuCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cargaDatos.cargar();
            }
        });

        menuSistema.add(menuCargarDatos);
        menuSistema.add(menuSalir);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE USUARIOS
        // ---------------------------------------------------------------------------------------------------

        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);

        JMenuItem menuItemRegistrar = new JMenuItem("Alta de usuario");
        menuItemRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
                altaUsusarioIFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemRegistrar);

        JMenuItem menuItemVerInfo = new JMenuItem("Consulta de usuario");
        menuItemVerInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultaUsusarioIFrame = new ConsultarUsuario(controladorUsuario);
                consultaUsusarioIFrame.setLocation(260, 0);
                frmGestionDeUsuarios.getContentPane().add(consultaUsusarioIFrame);
                consultaUsusarioIFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemVerInfo);

        JMenuItem menuItemModInfo = new JMenuItem("Modificar datos usuario");
        menuItemModInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarUsuarioIFrame = new ModificarUsuario(controladorUsuario);
                frmGestionDeUsuarios.getContentPane().add(modificarUsuarioIFrame);
                modificarUsuarioIFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemModInfo);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE ACTIVIDAD TURISTICA
        // ---------------------------------------------------------------------------------------------------

        JMenu menuActTur = new JMenu("Actividad turistica");
        menuBar.add(menuActTur);

        JMenuItem menuItemAltaActTur = new JMenuItem("Alta de actividad turistica");
        menuItemAltaActTur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                altaActividadTurisitcaIFrame = new AltaActTuristica(controladorUsuario, controladorDepartamento,
                        controladorActividad, validador);
                frmGestionDeUsuarios.getContentPane().add(altaActividadTurisitcaIFrame);
                altaActividadTurisitcaIFrame.setVisible(true);
            }
        });
        menuActTur.add(menuItemAltaActTur);

        JMenuItem menuItemConsActTur = new JMenuItem("Consulta de actividad turistica");
        menuItemConsActTur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver información de un usuario
                crearConsultaActividadInternalFrame();
            }

        });
        menuActTur.add(menuItemConsActTur);

        JMenuItem menuItemAceptarRechazar = new JMenuItem("Aceptar/Rechazar Actividad Turistica");
        menuItemAceptarRechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearAceptarRechazarActividaddInternalFrame();
                //aceptarRechazarActividad = new AceptarRechazarAct(controladorActividad,validador);
                //aceptarRechazarActividad.setVisible(true);
            }

        });
        menuActTur.add(menuItemAceptarRechazar);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE SALIDA TURISTICA
        // ---------------------------------------------------------------------------------------------------

        JMenu menuSalTur = new JMenu("Salida turistica");
        menuBar.add(menuSalTur);

        JMenuItem menuItemAltaSalTur = new JMenuItem("Alta de salida turistica");
        menuItemAltaSalTur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                altaSalidaTuristicaIFrame = new AltaSalidaTur(controladorSalida, controladorDepartamento,
                        controladorActividad, validador);
                frmGestionDeUsuarios.getContentPane().add(altaSalidaTuristicaIFrame);
                altaSalidaTuristicaIFrame.setVisible(true);
            }
        });
        menuSalTur.add(menuItemAltaSalTur);

        JMenuItem menuItemConsSalTur = new JMenuItem("Consulta de salida turistica");
        menuItemConsSalTur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearConsultaSalidaInternalFrame();
            }
        });
        menuSalTur.add(menuItemConsSalTur);

        JMenuItem menuItemInscSalTur = new JMenuItem("Inscripcion a salida turistica");
        menuItemInscSalTur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inscripcionSalidaTuristicaIFrame = new InscripcionSalTur(controladorDepartamento, controladorSalida,
                        controladorActividad, controladorUsuario, validador);
                frmGestionDeUsuarios.getContentPane().add(inscripcionSalidaTuristicaIFrame);
                inscripcionSalidaTuristicaIFrame.setVisible(true);
            }
        });
        menuSalTur.add(menuItemInscSalTur);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE PAQUETE DE A ACTIVIDADES TURISTICAS
        // ---------------------------------------------------------------------------------------------------

        JMenu menuPaqActTur = new JMenu("Paquete actividades turisticas");
        menuBar.add(menuPaqActTur);

        JMenuItem menuItemCrearPaq = new JMenuItem("Crear paquete");
        menuItemCrearPaq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearPaqueteActividadesIFrame.setVisible(true);
            }
        });
        menuPaqActTur.add(menuItemCrearPaq);

        JMenuItem menuItemAgregarActPaq = new JMenuItem("Agregar actividad a paquete");
        menuItemAgregarActPaq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarActividadTuristicaAPaqueteIFrame.setVisible(true);
            }
        });
        menuPaqActTur.add(menuItemAgregarActPaq);

        JMenuItem menuItemConsultaPaq = new JMenuItem("Consulta de paquete");
        menuItemConsultaPaq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearConsultaPaquetesInternalFrame();
            }
        });
        menuPaqActTur.add(menuItemConsultaPaq);

        // ---------------------------------------------------------------------------------------------------
        // MENU DE CATEGORIA
        // ---------------------------------------------------------------------------------------------------

        JMenu menuAltaCat = new JMenu("Categoria");
        menuBar.add(menuAltaCat);

        JMenuItem menuItemAltaCat = new JMenuItem("Alta de categoria");
        menuItemAltaCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearAltaCategoriaInternalFrame();
//				altaCategoriaIFrame = new AltaCategoria(controladorActividad, controladorUsuario);
//				frmGestionDeUsuarios.getContentPane().add(altaCategoriaIFrame);
//				altaCategoriaIFrame.setVisible(true);
            }
        });
        menuAltaCat.add(menuItemAltaCat);

    }

    // ---------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------

    private void crearAltaCategoriaInternalFrame() {
        altaCategoriaIFrame = new AltaCategoria(controladorActividad);
        frmGestionDeUsuarios.getContentPane().add(altaCategoriaIFrame);
        altaCategoriaIFrame.setVisible(true);
    }


    private void crearConsultaSalidaInternalFrame() {
        consultaSalidaTuristicaIFrame = new ConsultaSalTur(controladorDepartamento, controladorActividad,
                controladorSalida);
        frmGestionDeUsuarios.getContentPane().add(consultaSalidaTuristicaIFrame);
        consultaSalidaTuristicaIFrame.setVisible(true);
    }

    public static void crearConsultaSalidaConSalidaSeleccionaInternalFrame(String nombreSalida) {
        consultaSalidaTuristicaIFrame = new ConsultaSalTur(controladorDepartamento, controladorActividad,
                controladorSalida, nombreSalida);
        frmGestionDeUsuarios.getContentPane().add(consultaSalidaTuristicaIFrame);
        consultaSalidaTuristicaIFrame.setVisible(true);
    }

    private void crearConsultaActividadInternalFrame() {
        consultaActividadTuristicaIFrame = new ConsultaActTur(controladorDepartamento, controladorActividad);
        frmGestionDeUsuarios.getContentPane().add(consultaActividadTuristicaIFrame);
        consultaActividadTuristicaIFrame.setVisible(true);
    }

    public static void crearConsultaActividadConActividadSeleccionadaInternalFrame(String nombreActividad) {
        consultaActividadTuristicaIFrame = new ConsultaActTur(controladorDepartamento, controladorActividad,
                nombreActividad);
        frmGestionDeUsuarios.getContentPane().add(consultaActividadTuristicaIFrame);
        consultaActividadTuristicaIFrame.setVisible(true);
    }

    private void crearConsultaPaquetesInternalFrame() {
        consultaPaqueteIFrame = new ConsultaPaqActTur(controladorPaquete);
        frmGestionDeUsuarios.getContentPane().add(consultaPaqueteIFrame);
        consultaPaqueteIFrame.setVisible(true);
    }


    private void crearAceptarRechazarActividaddInternalFrame() {
        aceptarRechazarActividad = new AceptarRechazarAct(controladorActividad, validador);
        frmGestionDeUsuarios.getContentPane().add(aceptarRechazarActividad);
        aceptarRechazarActividad.setVisible(true);
    }

    public static void crearConsultaPaquetesConPaqueteSeleccionadoInternalFrame(String nombrePaquete) {
        // TODO: cambiar constructor cuando se implemente
        consultaPaqueteIFrame = new ConsultaPaqActTur(controladorPaquete);
        frmGestionDeUsuarios.getContentPane().add(consultaPaqueteIFrame);
        consultaPaqueteIFrame.setVisible(true);
    }

}
