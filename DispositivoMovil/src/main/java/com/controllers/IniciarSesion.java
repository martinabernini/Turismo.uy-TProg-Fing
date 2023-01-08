package com.controllers;

import com.helpers.Endpoints;
import com.helpers.EstadoSesionHelper;
import com.helpers.FabricaWS;
import com.helpers.SessionKeys;
import com.model.EstadoSesion;
import webservices.CampoInvalidoException_Exception;
import webservices.DtTurista;
import webservices.DtUsuario;
import webservices.EntidadNoExisteException_Exception;
import webservices.PublicadorControladorUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(Endpoints.INICIAR_SESION_SERVLET)
public class IniciarSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;

    public IniciarSesion() {
        super();
        controladorUsuario = FabricaWS.getInstance().getControladorUsuario();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionKeys.ESTADO_SESION) == EstadoSesion.LOGIN_CORRECTO) {
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
        } else {
            request.setAttribute("login-error", false);
            request.setAttribute("login-error-proveedor", false);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String usernameOrEmail = request.getParameter("login-form-nickname-email");
        String password = request.getParameter("login-form-password");

        HttpSession session = request.getSession();

        if (controladorUsuario.esLoginValido(usernameOrEmail, password)) {
            

            DtUsuario usuario = null;
            try {
                usuario = controladorUsuario.getUsuarioLogin(usernameOrEmail);
            } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception ignored) {
            }

            request.setAttribute("login-error", false);
            if(usuario instanceof DtTurista) {
            	EstadoSesionHelper.setEstado(request, EstadoSesion.LOGIN_CORRECTO);
            	session.setAttribute(SessionKeys.USUARIO_LOGUEADO, usuario);
                response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
            } else {
            	request.setAttribute("login-error-proveedor", true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            // aca no uso el SessionKeys porque es una variable que quiero que viva solo duante este request
            // TODO refactorizar esto para que utilize el ErrorHandler
            request.setAttribute("login-error", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }
}
