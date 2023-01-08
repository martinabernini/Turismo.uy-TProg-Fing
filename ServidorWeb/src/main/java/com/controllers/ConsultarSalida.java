package com.controllers;

import com.helpers.*;
import webservices.CampoInvalidoException_Exception;
import webservices.DtSalidaTuristica;
import webservices.EntidadNoExisteException_Exception;
import webservices.PublicadorControladorSalida;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ConsultarSalida")
public class ConsultarSalida extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorSalida controladorSalida;

    public ConsultarSalida() {
        super();
        controladorSalida = FabricaWS.getInstance().getControladorSalida();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String nombreSalida = request.getParameter("salida"); // viene del query string

        try {
            DtSalidaTuristica salida = controladorSalida.getSalidaTuristica(nombreSalida);
            salida.setImagen(ImagePathHelper.conPrefijo(salida.getImagen()));

            request.setAttribute(RequestKeys.SALIDA_CONSULTA, salida);
            request.getRequestDispatcher("/WEB-INF/views/actividades/consultarSalida/consultarSalida.jsp").forward(request, response);
            return;

        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
