package com.controllers;

import com.helpers.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.time.LocalDate;
import webservices.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(Endpoints.MODIFICAR_USUARIO_SERVLET)
@MultipartConfig
public class ModificarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;

    public ModificarUsuario() {
        super();
        controladorUsuario = FabricaWS.getInstance().getControladorUsuario();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        if (!EstadoSesionHelper.hayUsuarioLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }

        try {
            DtUsuario usuario = controladorUsuario.getUsuario(EstadoSesionHelper.getUsuarioLogueado(request).getNickname());
            usuario.setImagen(ImagePathHelper.conPrefijo(usuario.getImagen()));

            request.setAttribute(RequestKeys.USUARIO_A_MODIFICAR, usuario);
            request.getRequestDispatcher("/WEB-INF/views/usuarios/modificarUsuario/modificarUsuario.jsp").forward(request, response);

        } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        try {

            if (!EstadoSesionHelper.hayUsuarioLogueado(request)) {
                ErrorHandler.redirigirAPaginaDeError(request, response, 401);
                return;
            }

            LocalDate fechaNacimiento;
            try {
                if (request.getParameter("fechaNacimiento") != null) {
                    Date fechaNacimientoAux = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaNacimiento"));
                    fechaNacimiento = LocalDate.fromDateFields(fechaNacimientoAux);
                } else {
                    request.setAttribute(ErrorHandler.getRequestErrorKey(), "Fecha de nacimiento invalida");
                    doGet(request, response);
                    return;
                }

            } catch (ParseException e1) {
                request.setAttribute(ErrorHandler.getRequestErrorKey(), "Fecha de nacimiento invalida");
                doGet(request, response);
                return;
            }

            DtUsuario usuarioAModificar = null;
            try {
                usuarioAModificar = controladorUsuario.getUsuario(EstadoSesionHelper.getUsuarioLogueado(request).getNickname());

            } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
                ErrorHandler.redirigirAPaginaDeError(request, response, 404);
                return;
            }

            usuarioAModificar.setNombre(request.getParameter("nombre"));
            usuarioAModificar.setApellido(request.getParameter("apellido"));
            usuarioAModificar.setFechaNacimiento(request.getParameter("fechaNacimiento")); // TODO implementar
            usuarioAModificar.setPassword(request.getParameter("password"));


            Part partImagen = request.getPart("imagenUsuario");
            String imagen = AlmacenarImagenHelper.alamcenarImagenUsuario(partImagen, usuarioAModificar.getNickname());
            usuarioAModificar.setImagen(imagen);

            if (EstadoSesionHelper.hayProveedorLogueado(request)) {
                DtProveedor proveedorModificado = (DtProveedor) usuarioAModificar;
                proveedorModificado.setDescripcion(request.getParameter("descripcion"));
                proveedorModificado.setUrlSitioWeb(request.getParameter("sitioWeb"));

                try {
                    controladorUsuario.modificarUsuario(proveedorModificado);
                } catch (CampoInvalidoException_Exception e) {
                    request.setAttribute(ErrorHandler.getRequestErrorKey(), e.getMessage());
                    doGet(request, response);
                    return;
                }
            }

            if (EstadoSesionHelper.hayTuristaLogueado(request)) {
                DtTurista turistaModificado = (DtTurista) usuarioAModificar;
                turistaModificado.setNacionalidad(request.getParameter("nacionalidad"));

                try {
                    controladorUsuario.modificarUsuario(usuarioAModificar);
                } catch (CampoInvalidoException_Exception e) {
                    request.setAttribute(ErrorHandler.getRequestErrorKey(), e.getMessage());
                    doGet(request, response);
                    return;
                }
            }

            try {
                DtUsuario usuarioActualizado =
                        controladorUsuario.getUsuario(EstadoSesionHelper.getUsuarioLogueado(request).getNickname());
                EstadoSesionHelper.setUsuarioLogueado(request, usuarioActualizado);
            } catch (EntidadNoExisteException_Exception | CampoInvalidoException_Exception e) {
                ErrorHandler.redirigirAPaginaDeError(request, response, 404);
                return;
            }
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);

        } catch (Exception e) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 500);
        }
    }

}
