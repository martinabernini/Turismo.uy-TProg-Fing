package com.controllers;

import com.helpers.Endpoints;
import com.helpers.EstadoSesionHelper;
import com.helpers.FabricaWS;
import com.helpers.RequestKeys;
import webservices.CampoInvalidoException_Exception;
import webservices.DtActividadTuristica;
import webservices.DtPaqueteActividades;
import webservices.EntidadNoExisteException_Exception;
import webservices.PublicadorControladorActividad;
import webservices.PublicadorControladorPaquete;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(Endpoints.CONSULTAR_PAQUETE_SERVLET)
public class
ConsultarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividadTuristica;
    private final PublicadorControladorPaquete controladorPaquete;

    public ConsultarPaquete() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorActividadTuristica = fabrica.getControladorActividad();
        controladorPaquete = fabrica.getControladorPaquete();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String nombrePaquete = request.getParameter("paquete"); // viene del query string

        try {
            DtPaqueteActividades paquete = controladorPaquete.find(nombrePaquete);

            request.setAttribute(RequestKeys.PAQUETE_CONSULTA, paquete);

            String[] actividades = paquete.getNombreActividades().toArray(new String[0]);
            List<DtActividadTuristica> listaActividades = new ArrayList<>();

            if (actividades != null && actividades.length != 0) {
                for (String actividad : actividades) {
                    DtActividadTuristica dtActividad = controladorActividadTuristica.getActividadTuristica(actividad);
                    listaActividades.add(dtActividad);
                }
                request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, listaActividades);
            }


            request.getRequestDispatcher("/WEB-INF/views/paquetes/consultarPaquete/consultarPaquete.jsp").forward(request, response);

        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
