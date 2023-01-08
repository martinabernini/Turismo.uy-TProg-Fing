package com.controllers;

import com.helpers.Endpoints;
import com.helpers.FabricaWS;
import webservices.PublicadorControladorActividad;
import webservices.PublicadorControladorSalida;
import webservices.PublicadorControladorUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(Endpoints.API_SERVLET)
public class Api extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;
    private final PublicadorControladorActividad controladorActividad;
    private final PublicadorControladorSalida controladorSalida;

    public Api() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorUsuario = fabrica.getControladorUsuario();
        controladorActividad = fabrica.getControladorActividad();
        controladorSalida = fabrica.getControladorSalida();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("estaEnUsoNick") != null) {
            String nick = request.getParameter("estaEnUsoNick");
            boolean existe = controladorUsuario.estaEnUsoNick(nick);
            response.getWriter().write("{\"estaEnUsoNick\": " + existe + "}");
            return;
        }

        if (request.getParameter("estaEnUsoEmail") != null) {
            String email = request.getParameter("estaEnUsoEmail");
            boolean existe = controladorUsuario.estaEnUsoEmail(email);
            response.getWriter().write("{\"estaEnUsoEmail\": " + existe + "}");
            return;
        }

        if (request.getParameter("existeActividadConNombre") != null) {
            String nombre = request.getParameter("existeActividadConNombre");
            boolean existe = controladorActividad.existeActividadConNombre(nombre);
            response.getWriter().write("{\"existeActividadConNombre\": " + existe + "}");
            return;

        }

        if (request.getParameter("existeSalidaConNombre") != null) {
            String nombre = request.getParameter("existeSalidaConNombre");
            boolean existe = controladorSalida.existeSalidaConNombre(nombre);
            response.getWriter().write("{\"existeSalidaConNombre\": " + existe + "}");
            return;
        }

        response.getWriter().append("");
        return;
    }

}
