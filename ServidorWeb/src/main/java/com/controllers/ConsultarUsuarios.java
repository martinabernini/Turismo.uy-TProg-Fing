package com.controllers;

import com.helpers.*;
import webservices.CampoInvalidoException_Exception;
import webservices.DtUsuario;
import webservices.EntidadNoExisteException_Exception;
import webservices.NoHayEntidadesParaListarException_Exception;
import webservices.PublicadorControladorUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(Endpoints.CONSULTAR_USUARIOS_SERVLET)
public class ConsultarUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;

    public ConsultarUsuarios() {
        super();
        controladorUsuario = FabricaWS.getInstance().getControladorUsuario();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        try {
            String[] usuarios = controladorUsuario.listarUsuarios().getLista().toArray(new String[0]);

            List<DtUsuario> listaUsuarios = new ArrayList<>();

            for (String usuario : usuarios) {
                DtUsuario usuarioDt = controladorUsuario.getUsuario(usuario);
                usuarioDt.setImagen(ImagePathHelper.conPrefijo(usuarioDt.getImagen())); // CONSEGUIR LA IMAGEN NUEVA CON
                // PREFIJO
                listaUsuarios.add(usuarioDt);
            }

            request.setAttribute(RequestKeys.LISTA_USUARIOS, listaUsuarios);
            request.getRequestDispatcher("/WEB-INF/views/usuarios/consultarUsuarios.jsp").forward(request, response);

        } catch (NoHayEntidadesParaListarException_Exception e) {
            request.getRequestDispatcher("/WEB-INF/views/error/noContent.jsp").forward(request, response);
            return;
        } catch (EntidadNoExisteException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
        } catch (CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
