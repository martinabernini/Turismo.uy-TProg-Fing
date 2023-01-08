package com.controllers;

import com.helpers.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import webservices.CampoInvalidoException_Exception;
import webservices.DtSalidaTuristica;
import webservices.EntidadRepetidaException_Exception;
import webservices.NoHayEntidadesParaListarException_Exception;
import webservices.PublicadorControladorActividad;
import webservices.PublicadorControladorDepartamento;
import webservices.PublicadorControladorSalida;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@WebServlet(Endpoints.ALTA_SALIDA_SERVLET)
@MultipartConfig
public class AltaSalida extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividad;
    private final PublicadorControladorDepartamento controladorDepartamento;
    private final PublicadorControladorSalida controladorSalida;


    public AltaSalida() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorActividad = fabrica.getControladorActividad();
        controladorDepartamento = fabrica.getControladorDepartamento();
        controladorSalida = fabrica.getControladorSalida();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ErrorHandler.guardarErrorDelQueryEnAttributeDelRequest(request);
        request.setCharacterEncoding("UTF-8");

        // Si no hay un usuario logueado, redirigir al login
        if (!EstadoSesionHelper.hayUsuarioLogueado(request)) {
            ErrorHandler.redirigirAlLogin(request, response, Endpoints.ALTA_SALIDA);
            return;
        }

        // Si hay un usuario logueado pero es un cliente, redirigir a la página de error con 401
        if (EstadoSesionHelper.hayTuristaLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }

        // Si hay un usuario logueado y es un proveedor
        // Dar a elegir en cual departamento quiere dar de alta una salida turística
        if (request.getParameter("departamento") == null) {
            try {
                String[] dptos = controladorDepartamento.listarDepartamentos().getLista().toArray(new String[0]);

                request.setAttribute(RequestKeys.LISTA_DEPARTAMENTOS, dptos);
                request.getRequestDispatcher("/WEB-INF/views/actividades/dptoAltaSalida.jsp").forward(request, response);

            } catch (NoHayEntidadesParaListarException_Exception e) {
                ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
            }
            return;
        }

        try {
            String nombreDpto = request.getParameter("departamento");
            String[] actividades =
                    controladorActividad.listarActividadesAsociadasADepartamentoConfirmadas(nombreDpto).getLista().toArray(new String[0]);

            request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, actividades);
            request.getRequestDispatcher("/WEB-INF/views/actividades/altaSalida.jsp").forward(request, response);

        } catch (NoHayEntidadesParaListarException_Exception | CampoInvalidoException_Exception e) {
            ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (!EstadoSesionHelper.hayProveedorLogueado(request)) {
            response.sendRedirect(request.getContextPath() + Endpoints.INICIAR_SESION_SERVLET);
            return;
        }

        String nombreAct = request.getParameter("nombreActividad");
        String nombre = request.getParameter("nombreSalida");
        int cantidad = 0;
        if (request.getParameter("cantTuristas") != null) {
            try {
                cantidad = Integer.parseInt(request.getParameter("cantTuristas"));
            } catch (NumberFormatException e) {
                ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, "revisa la cantidad de turistas");
                return;
            }
        }

        String fechaSalidaStr = request.getParameter("fechaSalida");
        System.out.println("MARTINA LA FECHA DE SALIDA ESSSSSS: "+ fechaSalidaStr);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   //   LocalDate fechaSalidaAuxiliar = LocalDate.parse(fechaSalidaStr, formato);
        LocalDateTime fechaSalida = LocalDateTime.parse(fechaSalidaStr);
    //  int horaSalida = Integer.parseInt(request.getParameter("horaSalida"));
    //  fechaSalida = fechaSalida.withHour(horaSalida);

        String lugar = request.getParameter("lugarSalida");
        Part partImagen = request.getPart("imagenSalida");

        DtSalidaTuristica nuevaSalida = new DtSalidaTuristica();

        
        nuevaSalida.setNombreActividad(nombreAct);
        nuevaSalida.setNombreSalida(nombre);
        nuevaSalida.setCantidadMaximaTuristas(cantidad);
        nuevaSalida.setFechaAlta(LocalDate.now().format(formato));
        nuevaSalida.setFechaSalida(fechaSalida.toString());
        nuevaSalida.setLugarSalida(lugar);

        nuevaSalida.setImagen(AlmacenarImagenHelper.alamcenarImagenSalida(partImagen, nombre));

        try {
            controladorSalida.darDeAltaSalidaTuristica(nuevaSalida);
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);

        } catch (CampoInvalidoException_Exception | EntidadRepetidaException_Exception e) {
            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
        }
    }
}
