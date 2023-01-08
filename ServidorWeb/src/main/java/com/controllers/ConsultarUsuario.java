package com.controllers;

import com.helpers.*;
import webservices.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(Endpoints.CONSULTAR_USUARIO_SERVLET)
public class ConsultarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividad;
    private final PublicadorControladorSalida controladorSalida;
    private final PublicadorControladorUsuario controladorUsuario;


    public ConsultarUsuario() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorActividad = fabrica.getControladorActividad();
        controladorSalida = fabrica.getControladorSalida();
        controladorUsuario = fabrica.getControladorUsuario();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String nick = request.getParameter("nickname"); // viene del query string

        boolean esMiPerfil = false;
        boolean loSigo = false;
        if (EstadoSesionHelper.hayUsuarioLogueado(request)) {
            esMiPerfil = EstadoSesionHelper.getUsuarioLogueado(request).getNickname().equals(nick);
            String loggeado = EstadoSesionHelper.getUsuarioLogueado(request).getNickname();
            try {
                DtUsuario usuarioLoggeado = controladorUsuario.getUsuario(loggeado);

                if (!esMiPerfil) {
                    loSigo = usuarioLoggeado.getSeguidos().contains(nick); // TODO REVISAR
                }
                request.setAttribute("dtUserLoggeado", usuarioLoggeado);

            } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            }
        }
        System.out.println("SIGO AL USUARIO " + nick + "?" + loSigo);
        request.setAttribute(RequestKeys.SIGO_AL_USUARIO, loSigo);
        request.setAttribute(RequestKeys.ES_MI_PERFIL, esMiPerfil);

        try {
            DtUsuario usuarioAConsultar = controladorUsuario.getUsuario(nick);

            usuarioAConsultar.setImagen(ImagePathHelper.conPrefijo(usuarioAConsultar.getImagen()));

            request.setAttribute("usuarioConsultar", usuarioAConsultar);

            String[] seguidores = usuarioAConsultar.getSeguidores().toArray(new String[0]);
            List<DtUsuario> listaSeguidores = new ArrayList<>(); //los seguidores que muestro
            Map<String, Boolean> listaSigoSeguidores = new HashMap<>(); //map para ver si sigo a los seguidores

            for (String seguidor : seguidores) {
                listaSeguidores.add(controladorUsuario.getUsuario(seguidor));
                if (EstadoSesionHelper.hayUsuarioLogueado(request)) {
                    // TODO REVISAR
                    listaSigoSeguidores.put(seguidor, (((DtUsuario) request.getAttribute("dtUserLoggeado")).getSeguidos().contains(seguidor)));
                } else {
                    listaSigoSeguidores.put(seguidor, false);
                }
            }                        


            String[] seguidos = usuarioAConsultar.getSeguidos().toArray(new String[0]);
            List<DtUsuario> listaSeguidos = new ArrayList<>(); //los seguidos que muestro
            Map<String, Boolean> listaSigoSeguidos = new HashMap<>(); // map para ver si sigo a los seguidos
            for (String seguido : seguidos) {
                listaSeguidos.add(controladorUsuario.getUsuario(seguido));
                if (EstadoSesionHelper.hayUsuarioLogueado(request)) {
                    // TODO REVISAR
                    listaSigoSeguidos.put(seguido, (((DtUsuario) request.getAttribute("dtUserLoggeado")).getSeguidos().contains(seguido)));
                } else {
                    listaSigoSeguidos.put(seguido, false);
                }
            }
            
            
            
            request.setAttribute(RequestKeys.LISTA_SEGUIDORES, listaSeguidores);
            request.setAttribute(RequestKeys.LISTA_SIGO_SEGUIDORES, listaSigoSeguidores);
            
            request.setAttribute(RequestKeys.LISTA_SEGUIDOS, listaSeguidos);
            request.setAttribute(RequestKeys.LISTA_SIGO_SEGUIDOS, listaSigoSeguidos);


            if (usuarioAConsultar instanceof DtTurista) {

                // TODO REVISAR ESTO

                //yo lo que quiero al final del dia es, un list de dtsalidas y un list de dtinscripcion

                List<DtSalidaTuristica> listaSalidas = new ArrayList<>();
                WrapperArrayList listaSalidasWrapper = ((DtTurista) usuarioAConsultar).getNombreSalidasALasQueEstaInscripto();
                for (Object salida : listaSalidasWrapper.getLista()) {
                    String nombreSalida = (String) salida;
                    listaSalidas.add(controladorSalida.getSalidaTuristica(nombreSalida));
                }

                Map<String, DtInscripcionSalida> inscripciones = new HashMap<>();
                WrapperDtInscripcion listaInscripcionesWrapper = ((DtTurista) usuarioAConsultar).getDtInscripcionesASalidas();
                for (Object inscripcion : listaInscripcionesWrapper.getLista()) {
                    DtInscripcionSalida dtInscripcionSalida = (DtInscripcionSalida) inscripcion;
                    inscripciones.put(dtInscripcionSalida.getNombreSalidaTuristica(), dtInscripcionSalida);
                }





//            	//LAS SALIDAS A LAS QUE ME INSCRIBI!!!!
//                List<Entry> salidasEntry = ((DtTurista) usuarioAConsultar).getInscripcionesASalidas().getInscripcionesASalidas().getEntry();
//                List<Entry> inscripcionSalidasEntry = ((DtTurista) usuarioAConsultar).getInscripcionesASalidas().getInscripcionesASalidas().getEntry();
//
//             //   DtInscripcionSalida[] inscripcionSalidas = ((DtTurista) usuarioAConsultar).getInscripcionesASalidas().getInscripcionesASalidas().getEntry().toArray(new DtInscripcionSalida[0]);
//
//
//
//                List<String> salidas = new ArrayList<>();
//                for(Entry salidaEntry : salidasEntry) {
//                	salidas.add(salidaEntry.getValue().getNombreSalidaTuristica());
//                }


                // TODO: hacerrr
                // queremos una lista de nombres de salidas
                // queremos una lista de DtInscripcionSalida

//                Map<String, DtInscripcionSalida> inscripciones = new HashMap<>();
//
//                for (String salida : salidas) {
//                    listaSalidas.add(controladorSalida.getSalidaTuristica(salida));
//                }
//                //TODO chequiar null
//                for (DtInscripcionSalida inscripcion : inscripcionSalidas) {
//                    inscripciones.put(inscripcion.getNombreSalidaTuristica(), inscripcion);
//                }

                request.setAttribute(RequestKeys.SALIDAS_PERFIL_A_CONSULTAR, listaSalidas);
                request.setAttribute(RequestKeys.INSCRIPCIONES_SALIDAS_PERFIL_A_CONSULTAR, inscripciones);

                if (esMiPerfil) {
                    List<DtCompraPaquete> listaPaquetes = new ArrayList<>();

                    List<WrapperMapCompraPaquete.MapCompraPaquete.Entry> paqueteEntry = ((DtTurista) usuarioAConsultar).getComprasPaquetes().getMapCompraPaquete().getEntry();
                    for(WrapperMapCompraPaquete.MapCompraPaquete.Entry paquete: paqueteEntry) {
                        listaPaquetes.add(paquete.getValue());
                    }

                    request.setAttribute(RequestKeys.LISTA_PAQUETES, listaPaquetes);

                }

                // SI SOY UN PROVEEDOR

            } else if (usuarioAConsultar instanceof DtProveedor) {

                String[] actividades = ((DtProveedor) usuarioAConsultar).getActividadesTuristicas().toArray(new String[0]);

                List<DtActividadTuristica> listaActividades = new ArrayList<>();
                List<DtSalidaTuristica> listaSalidas = new ArrayList<>();

                for (String actividad : actividades) {

                    if (controladorActividad.getActividadTuristica(actividad).getEstado().equals(EnumEstadoActividad.CONFIRMADA)) {
                        listaActividades.add(controladorActividad.getActividadTuristica(actividad));
                    }

                    try {
                        String[] salidas =
                                controladorSalida.listarSalidasVigentesAsociadasAActividadTuristica(actividad).getLista().toArray(new String[0]);

                        String[] test =
                                controladorSalida.listarSalidasAsociadasAActividadTuristica(actividad).getLista().toArray(new String[0]);

                        for (String salida : salidas) {
                            listaSalidas.add(controladorSalida.getSalidaTuristica(salida));
                        }
                    } catch (NoHayEntidadesParaListarException_Exception e) {
                        // Nada
                    }
                }

                if (esMiPerfil) {

                    List<DtActividadTuristica> listaActividadesNoConfirmadas = new ArrayList<>();
                    try {

                        String[] actividadesNoConfirmadas =
                                controladorActividad.listarActividadesDeProveedorNoConfirmadas(nick).getLista().toArray(new String[0]);
                        for (String actividad : actividadesNoConfirmadas) {
                            listaActividadesNoConfirmadas.add(controladorActividad.getActividadTuristica(actividad));
                        }
                    } catch (NoHayEntidadesParaListarException_Exception e) {
                        // Nada o TODO chequear
                    }

                    request.setAttribute(RequestKeys.ACTIVIDADES_MI_PERFIL_PROVEEDOR, listaActividadesNoConfirmadas);
                }

                request.setAttribute(RequestKeys.ACTIVIDADES_PERFIL_A_CONSULTAR_PROVEEDOR, listaActividades);
                request.setAttribute(RequestKeys.SALIDAS_PERFIL_A_CONSULTAR, listaSalidas);
            }

        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 500);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/usuarios/perfil/perfil.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

