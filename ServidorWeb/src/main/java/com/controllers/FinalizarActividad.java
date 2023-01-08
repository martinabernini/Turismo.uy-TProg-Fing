package com.controllers;

import com.helpers.Endpoints;
import com.helpers.ErrorHandler;
import com.helpers.EstadoSesionHelper;
import com.helpers.FabricaWS;
import webservices.CampoInvalidoException_Exception;
import webservices.EntidadNoExisteException_Exception;
import webservices.PublicadorControladorActividad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(Endpoints.FINALIZAR_ACTIVIDAD_SERVLET)
public class FinalizarActividad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividad;

    public FinalizarActividad() {
        super();
        controladorActividad = FabricaWS.getInstance().getControladorActividad();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        // redirigir a consultar usuario mi perfil
        if (!EstadoSesionHelper.hayUsuarioLogueado(request) || !EstadoSesionHelper.hayProveedorLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }

        if (request.getParameter("nombreActividad") == null) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
            return;
        }

        String nick = EstadoSesionHelper.getUsuarioLogueado(request).getNickname();
        try {
            controladorActividad.finalizarActividad(request.getParameter("nombreActividad"));
        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 500);
            System.out.println("Error al finalizar actividad: " + e.getMessage());
            return;
        }
        response.sendRedirect(request.getContextPath() + "/" + Endpoints.CONSULTAR_USUARIO + "?nickname=" + nick);
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // redirigir a consultar usuario mi perfil
        if (!EstadoSesionHelper.hayUsuarioLogueado(request) || !EstadoSesionHelper.hayProveedorLogueado(request)) {
            response.setStatus(401);
            response.getWriter().write("{\"error\": \"No hay usuario logueado\"}");
            return;
        }

        if (request.getParameter("nombreActividad") == null) {
            // return json error
            response.setStatus(400);
            response.getWriter().write("{\"error\": \"No se ha especificado el nombre de la actividad\"}");
            return;
        }

        String nick = EstadoSesionHelper.getUsuarioLogueado(request).getNickname();
        try {
            controladorActividad.finalizarActividad(request.getParameter("nombreActividad"));
        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\": \"Error al finalizar actividad\"}");
            return;
        }
        response.setStatus(200);
        response.getWriter().write("{\"mensaje\": \"Actividad finalizada correctamente\"}");
        return;
    }

}
