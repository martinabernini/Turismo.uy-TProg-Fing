package com.controllers;

import com.helpers.Endpoints;
import com.helpers.ErrorHandler;
import com.helpers.EstadoSesionHelper;
import com.helpers.FabricaWS;
import webservices.CampoInvalidoException_Exception;
import webservices.EntidadNoExisteException_Exception;
import webservices.PublicadorControladorUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Endpoints.DEJAR_DE_SEGUIR_USUARIO_SERVLET)
public class DejarDeSeguirUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;

    public DejarDeSeguirUsuario() {
        super();
        controladorUsuario = FabricaWS.getInstance().getControladorUsuario();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        System.out.println("ENTRE AL GET ");
        // redirigir a consultar usuario mi perfil
        if (!EstadoSesionHelper.hayUsuarioLogueado(request) || !EstadoSesionHelper.hayProveedorLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }

        if (request.getParameter("usuario") == null) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
            return;
        }

        String nickSeguidor = EstadoSesionHelper.getUsuarioLogueado(request).getNickname();
        String nickSeguido = request.getParameter("usuario");
        try {
            controladorUsuario.relacionDejarDeSeguirUsuario(nickSeguido, nickSeguidor);
        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 500);
            System.out.println("Error al dejar de seguir usuario: " + e.getMessage());
            return;
        }
        response.sendRedirect(request.getContextPath() + "/" + Endpoints.CONSULTAR_USUARIO + "?nickname=" + nickSeguidor);
        return;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        System.out.println("ENTRE AL POST ");
        // redirigir a consultar usuario mi perfil
        if (!EstadoSesionHelper.hayUsuarioLogueado(request)) {
            response.setStatus(401);
            response.getWriter().write("{\"error\": \"No hay usuario logueado\"}");
            return;
        }

        if (request.getParameter("usuario") == null) {
            // return json error
            response.setStatus(400);
            response.getWriter().write("{\"error\": \"No se ha especificado el nombre del usuario a dejar de seguir\"}");
            return;
        }

        String nickSeguidor = EstadoSesionHelper.getUsuarioLogueado(request).getNickname();
        String nickSeguido = request.getParameter("usuario");

        try {
            controladorUsuario.relacionDejarDeSeguirUsuario(nickSeguido, nickSeguidor);
        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 500);
            System.out.println("Error al dejar de seguir usuario: " + e.getMessage());
            return;
        }
        response.setStatus(200);
        return;
    }

}
