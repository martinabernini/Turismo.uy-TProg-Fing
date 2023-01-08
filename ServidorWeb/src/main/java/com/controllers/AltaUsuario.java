package com.controllers;

import com.helpers.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@WebServlet(Endpoints.ALTA_USUARIO_SERVLET)
@MultipartConfig
public class AltaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorUsuario controladorUsuario;

    public AltaUsuario() {
        super();
        controladorUsuario = FabricaWS.getInstance().getControladorUsuario();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");


        if (request.getParameter("tipoUsuario") == null) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
            return;
        }

        String tipo = request.getParameter("tipoUsuario");
        if (!tipo.equals("Turista") && !tipo.equals("Proveedor")) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 404);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/usuarios/altaDeUsuario.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");


        String tipo = request.getParameter("tipoUsuario");

        String nickname = request.getParameter("nicknameUsuario");
        String nombre = request.getParameter("nombreUsuario");
        String apellido = request.getParameter("apellidoUsuario");
        String password = request.getParameter("contrasenaUsuario");
        String email = request.getParameter("emailUsuario");
        String passwordConfirmacion = request.getParameter("contrasenaUsuarioConfimracion");

        String nacionalidad = request.getParameter("nacionalidadUsuario");
        String descripcion = request.getParameter("descripcionUsuario");

        String sitioWeb = request.getParameter("sitiowebUsuario");

        String fechaNacimientostr = request.getParameter("fechaNacimiento");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientostr, formato);


        Part partImagen = request.getPart("imagenUsuario");

        if (!password.equals(passwordConfirmacion)) {
            request.setAttribute("signup-error", "ContrasenaNoCoincide");
            request.getRequestDispatcher("/WEB-INF/views/usuarios/altaDeUsuario.jsp").forward(request, response);
            return;
        }

        DtUsuario nuevoUsuario = null;

        if (tipo == null) {
            //TODO revisar esto
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
            return;
        }

        if (tipo.equals("Turista")) {

            nuevoUsuario = new DtTurista();

            nuevoUsuario.setNickname(nickname);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setEmail(email);

            nuevoUsuario.setFechaNacimiento(fechaNacimiento.toString());
            ((DtTurista) nuevoUsuario).setNacionalidad(nacionalidad);

        } else if (tipo.equals("Proveedor")) {

            nuevoUsuario = new DtProveedor();

            nuevoUsuario.setNickname(nickname);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setEmail(email);

            nuevoUsuario.setFechaNacimiento(fechaNacimiento.toString());
            ((DtProveedor) nuevoUsuario).setDescripcion(descripcion);
            ((DtProveedor) nuevoUsuario).setUrlSitioWeb(sitioWeb);

        }

        System.out.println(partImagen.getSize());

        nuevoUsuario.setImagen(AlmacenarImagenHelper.alamcenarImagenUsuario(partImagen, nickname));

        try {
            controladorUsuario.darDeAltaUsuario(nuevoUsuario);
            //TODO ver si te redirige a donde
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);

        } catch (EntidadRepetidaException_Exception e) {
            request.setAttribute("signup-error", "EntidadRepetida");
            request.getRequestDispatcher("/WEB-INF/views/usuarios/altaDeUsuario.jsp").forward(request, response);

        } catch (CampoInvalidoException_Exception e) {
            request.setAttribute("signup-error", "CampoInvalido");
            request.getRequestDispatcher("/WEB-INF/views/usuarios/altaDeUsuario.jsp").forward(request, response);
        }

    }

}
