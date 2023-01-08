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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(Endpoints.ALTA_ACTIVIDAD_SERVLET)
@MultipartConfig
public class AltaActividad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividad;
    private final PublicadorControladorDepartamento controladorDepartamento;


    public AltaActividad() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorActividad = fabrica.getControladorActividad();
        controladorDepartamento = fabrica.getControladorDepartamento();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");
        ErrorHandler.guardarErrorDelQueryEnAttributeDelRequest(request);

        if (EstadoSesionHelper.hayProveedorLogueado(request)) {
            try {

                String[] categorias = controladorActividad.listarTodasLasCategorias().getLista().toArray(new String[0]);
                String[] departamentos = controladorDepartamento.listarDepartamentos().getLista().toArray(new String[0]);

                request.setAttribute(RequestKeys.LISTA_CATEGORIAS, categorias);
                request.setAttribute(RequestKeys.LISTA_DEPARTAMENTOS, departamentos);

                request.getRequestDispatcher("/WEB-INF/views/actividades/altaActividad.jsp").forward(request, response);

            } catch (NoHayEntidadesParaListarException_Exception e) {
                //TODO tiene que saltar un error
                response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
            }
            return;
        }

        if (EstadoSesionHelper.hayTuristaLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }

        response.sendRedirect(request.getContextPath() + Endpoints.INICIAR_SESION_SERVLET);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        if (!EstadoSesionHelper.hayProveedorLogueado(request)) {
            response.sendRedirect(request.getContextPath() + Endpoints.INICIAR_SESION_SERVLET);
            return;
        }

        String[] categoriasSeleccionadas = request.getParameterValues("categoria");
        String departamento = request.getParameter("departamento");
        String descripcion = request.getParameter("DescripcionActividad");
        String ciudad = request.getParameter("nombreCiudad");
        String nombre = request.getParameter("nombreActividad");
        Part partImagen = request.getPart("imagenActividad");

        // TODO Chequiar que no sean null y que sean parseables
        int duracion = 0;
        if (request.getParameter("DuracionActividad") != null) {
            try {
                duracion = Integer.parseInt(request.getParameter("DuracionActividad"));
            } catch (NumberFormatException e) {
                request.setAttribute(ErrorHandler.getRequestErrorKey(), "La duración debe ser un número entero");
                doGet(request, response);
                return;
            }
        }
        int costo = 0;
        if (request.getParameter("costoActividad") != null) {
            try {
                costo = Integer.parseInt(request.getParameter("costoActividad"));

            } catch (NumberFormatException e) {
                request.setAttribute(ErrorHandler.getRequestErrorKey(), e.getMessage());
                doGet(request, response);
                return;
            }
        }

        DtProveedor proveedor = (DtProveedor) EstadoSesionHelper.getUsuarioLogueado(request);

        DtActividadTuristica nuevaActividad = new DtActividadTuristica();
        nuevaActividad.setProovedor(proveedor.getNickname());
        nuevaActividad.setNombre(nombre);


        for (String categoria : categoriasSeleccionadas) {
            nuevaActividad.getCategorias().add(categoria);
        }

        nuevaActividad.setDepartamento(departamento);
        nuevaActividad.setDescripcion(descripcion);
        nuevaActividad.setDuracionHrs(duracion);
        nuevaActividad.setCostoPorPersona(costo);
        nuevaActividad.setCiudad(ciudad);
        nuevaActividad.setFechaAlta(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        nuevaActividad.setImagen(AlmacenarImagenHelper.alamcenarImagenActividad(partImagen, nombre));
        nuevaActividad.setUrlVideo(request.getParameter("urlVideo"));

        try {
            controladorActividad.darDeAltaActividadTuristica(nuevaActividad);
            // TODO ver si te redirige a donde
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);

        } catch (CampoInvalidoException_Exception | EntidadRepetidaException_Exception e) {
            request.setAttribute(ErrorHandler.getRequestErrorKey(), e.getMessage());
            doGet(request, response);
        }
    }
}
