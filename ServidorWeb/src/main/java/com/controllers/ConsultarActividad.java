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
import java.util.List;


@WebServlet(Endpoints.CONSULTAR_ACTIVIDAD_SERVLET)
public class ConsultarActividad extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private final PublicadorControladorActividad controladorActividad;
    private final PublicadorControladorSalida controladorSalida;
    private final PublicadorControladorPaquete controladorPaquete;

    public ConsultarActividad() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorActividad = fabrica.getControladorActividad();
        controladorSalida = fabrica.getControladorSalida();
        controladorPaquete = fabrica.getControladorPaquete();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String actividad = request.getParameter("actividad"); // viene del query string

        try {
            DtActividadTuristica act = controladorActividad.getActividadTuristica(actividad);
            act.setImagen(ImagePathHelper.conPrefijo(act.getImagen()));

            request.setAttribute(RequestKeys.ACTIVIDAD_CONSULTA, act);
            try {
                String[] salidas =
                        controladorSalida.listarSalidasAsociadasAActividadTuristica(actividad).getLista().toArray(new String[0]);

                List<DtSalidaTuristica> listaSalidas = new ArrayList<>();

                for (String salida : salidas) {
                    listaSalidas.add(controladorSalida.getSalidaTuristica(salida));
                }
                request.setAttribute(RequestKeys.SALIDAS_ACTIVIDAD_A_CONSULTAR, listaSalidas);

            } catch (NoHayEntidadesParaListarException_Exception ignored) {
            }

            String[] paquetes = act.getPaquetes().toArray(new String[0]);
            List<DtPaqueteActividades> listaPaquetes = new ArrayList<>();

            for (String paquete : paquetes) {
                try {
                    listaPaquetes.add(controladorPaquete.find(paquete));
                } catch (EntidadNoExisteException_Exception e) {
                    // TODO ver que hacer
                }
            }
            request.setAttribute(RequestKeys.LISTA_PAQUETES_ACT, listaPaquetes);

        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/actividades/consultarActividad/consultarActividad.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

