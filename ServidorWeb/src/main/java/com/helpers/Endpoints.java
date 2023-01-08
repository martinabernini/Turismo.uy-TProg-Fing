package com.helpers;

public class Endpoints {

    public static final String ALTA_ACTIVIDAD = "AltaActividad";
    public static final String ALTA_SALIDA = "AltaSalida";
    public static final String ALTA_USUARIO = "AltaUsuario";
    public static final String ALTA_PROVEEDOR = ALTA_USUARIO + "?tipoUsuario=Proveedor";
    public static final String ALTA_TURISTA = ALTA_USUARIO + "?tipoUsuario=Turista";
    public static final String API = "Api";
    public static final String COMPRA_PAQUETE = "Home";// TODO: Cambiar a CompraPaquete cuando se cree el servlet
    public static final String CONSULTAR_ACTIVIDAD = "ConsultarActividad";
    public static final String CONSULTAR_ACTIVIDADES = "ConsultarActividades";
    public static final String CONSULTAR_PAQUETE = "ConsultarPaquete";
    public static final String CONSULTAR_PAQUETES = "ConsultarPaquetes";
    public static final String CONSULTAR_SALIDAS = "ConsultarSalidas";
    public static final String CONSULTAR_USUARIO = "ConsultarUsuario";
    public static final String CONSULTAR_USUARIOS = "ConsultarUsuarios";
    public static final String DISPLAY_IMAGE = "DisplayImage";
    public static final String FINALIZAR_ACTIVIDAD = "FinalizarActividad";
    public static final String SEGUIR_USUARIO = "SeguirUsuario";
    public static final String DEJAR_DE_SEGUIR_USUARIO = "DejarDeSeguirUsuario";
    public static final String HOME = "Home";
    public static final String INICIAR_SESION = "IniciarSesion";
    public static final String INSCRIPCION_SALIDA_TURISTICA = "InscripcionSalidaTuristica";
    public static final String LOG_OUT = "LogOut";
    public static final String MODIFICAR_USUARIO = "ModificarUsuario";

    // ---------------------------------------------------------------
    // Endpoints de errores
    // ---------------------------------------------------------------
    // TODO implementar, por ahora lleva al home
    public static final String ERROR_COUNT_EXCEEDED = "Home";
    public static final String ERROR = "Error";


    //----------------------------------------------------------------
    // Como no se puede usar funciones adentro del decorador usamos esto
    //----------------------------------------------------------------
    private static final String BASE = "/";

    public static final String ALTA_ACTIVIDAD_SERVLET = BASE + ALTA_ACTIVIDAD;
    public static final String ALTA_SALIDA_SERVLET = BASE + ALTA_SALIDA;
    public static final String ALTA_USUARIO_SERVLET = BASE + ALTA_USUARIO;
    public static final String API_SERVLET = BASE + API;
    public static final String COMPRA_PAQUETE_SERVLET = BASE + COMPRA_PAQUETE;
    public static final String CONSULTAR_ACTIVIDAD_SERVLET = BASE + CONSULTAR_ACTIVIDAD;
    public static final String CONSULTAR_ACTIVIDADES_SERVLET = BASE + CONSULTAR_ACTIVIDADES;
    public static final String CONSULTAR_PAQUETES_SERVLET = BASE + CONSULTAR_PAQUETES;
    public static final String CONSULTAR_PAQUETE_SERVLET = BASE + CONSULTAR_PAQUETE;
    public static final String CONSULTAR_SALIDAS_SERVLET = BASE + CONSULTAR_SALIDAS;
    public static final String CONSULTAR_USUARIOS_SERVLET = BASE + CONSULTAR_USUARIOS;
    public static final String CONSULTAR_USUARIO_SERVLET = BASE + CONSULTAR_USUARIO;
    public static final String DISPLAY_IMAGE_SERVLET = BASE + DISPLAY_IMAGE;
    public static final String ERROR_COUNT_EXCEEDED_SERVLET = BASE + ERROR_COUNT_EXCEEDED;
    public static final String ERROR_SERVLET = BASE + ERROR;
    public static final String FINALIZAR_ACTIVIDAD_SERVLET = BASE + FINALIZAR_ACTIVIDAD;
    public static final String SEGUIR_USUARIO_SERVLET = BASE + SEGUIR_USUARIO;
    public static final String DEJAR_DE_SEGUIR_USUARIO_SERVLET = BASE + DEJAR_DE_SEGUIR_USUARIO;
    public static final String HOME_SERVLET = BASE + HOME;
    public static final String INICIAR_SESION_SERVLET = BASE + INICIAR_SESION;
    public static final String INSCRIPCION_SALIDA_TURISTICA_SERVLET = BASE + INSCRIPCION_SALIDA_TURISTICA;
    public static final String LOG_OUT_SERVLET = BASE + LOG_OUT;
    public static final String MODIFICAR_USUARIO_SERVLET = BASE + MODIFICAR_USUARIO;
}
