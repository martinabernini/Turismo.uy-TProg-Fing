package com.controllers;

import com.helpers.*;
import webservices.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Endpoints.INSCRIPCION_SALIDA_TURISTICA_SERVLET)
public class InscripcionSalidaTuristica extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorDepartamento controladorDepartamento;
    private final PublicadorControladorSalida controladorSalidaTuristica;
    private final PublicadorControladorActividad controladorActividadTuristica;


    public InscripcionSalidaTuristica() {
        super();
        FabricaWS fabrica = FabricaWS.getInstance();
        controladorDepartamento = fabrica.getControladorDepartamento();
        controladorSalidaTuristica = fabrica.getControladorSalida();
        controladorActividadTuristica = fabrica.getControladorActividad();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        ErrorHandler.guardarErrorDelQueryEnAttributeDelRequest(request);

        if (EstadoSesionHelper.hayTuristaLogueado(request)) {

            // Los posibles filtros de la búsqueda se reciben como parámetros
            // categoria, departamento, actividad

            // si no hay filtros -> se muesta la pagina de filstros de busqueda categorias y
            // departamentos
            // si hay categoria -> se muestra la lista de actividades de esa categoria
            // si hay departamento -> se muestra la lista de actividades de ese departamento
            // si hay actividad -> se muestra la lista de salidas de esa actividad para
            // inscribirse

            // si no hay filtros
            if (request.getParameter("categoria") == null && request.getParameter("departamento") == null
                    && request.getParameter("actividad") == null) {

                // se muestra la pagina de filtros de busqueda categorias y departamentos
                try {
                    String[] departamentos = controladorDepartamento.listarDepartamentos().getLista().toArray(new String[0]);
                    String[] categorias = controladorActividadTuristica.listarTodasLasCategorias().getLista().toArray(new String[0]);

                    request.setAttribute(RequestKeys.FILTRO_INSCRIPCION_SALIDA_INICIAL, true);
                    request.setAttribute(RequestKeys.LISTA_DEPARTAMENTOS, departamentos);
                    request.setAttribute(RequestKeys.LISTA_CATEGORIAS, categorias);

                    request.getRequestDispatcher("/WEB-INF/views/actividades/filtrosIncripcionSalida.jsp")
                            .forward(request, response);
                    return;

                } catch (NoHayEntidadesParaListarException_Exception e) {
                    // TODO redireccionar a pagina de error
                    ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
                    return;
                }
            }

            // si hay solo categoria
            if (request.getParameter("categoria") != null && request.getParameter("departamento") == null
                    && request.getParameter("actividad") == null) {

                // se muestra la lista de actividades de esa categoria
                String categoria = request.getParameter("categoria");

                try {
                    String[] actividades = controladorActividadTuristica
                            .listarActividadesAsociadasACategoriaConfirmadas(categoria).getLista().toArray(new String[0]);

                    request.setAttribute(RequestKeys.FILTRO_INSCRIPCION_SALIDA_INICIAL, false);
                    request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, actividades);
                    request.getRequestDispatcher("/WEB-INF/views/actividades/filtrosIncripcionSalida.jsp")
                            .forward(request, response);
                    return;

                } catch (NoHayEntidadesParaListarException_Exception | CampoInvalidoException_Exception e) {
                    // TODO redireccionar a pagina de error
                    ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
                    return;
                }
            }

            // si hay solo departamento
            if (request.getParameter("departamento") != null && request.getParameter("categoria") == null
                    && request.getParameter("actividad") == null) {

                // se muestra la lista de actividades de ese departamento
                String departamento = request.getParameter("departamento");

                try {
                    String[] actividades = controladorActividadTuristica
                            .listarActividadesAsociadasADepartamentoConfirmadas(departamento).getLista().toArray(new String[0]);
                    request.setAttribute(RequestKeys.FILTRO_INSCRIPCION_SALIDA_INICIAL, false);
                    request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, actividades);
                    request.getRequestDispatcher("/WEB-INF/views/actividades/filtrosIncripcionSalida.jsp")
                            .forward(request, response);
                    return;
                } catch (NoHayEntidadesParaListarException_Exception | CampoInvalidoException_Exception e) {
                    // TODO redireccionar a pagina de error
                    ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
                    return;
                }
            }

            // si hay solo actividad
            if (request.getParameter("actividad") != null && request.getParameter("categoria") == null
                    && request.getParameter("departamento") == null) {

                ErrorHandler.guardarErrorDelQueryEnAttributeDelRequest(request);

                // se muestra la lista de salidas de esa actividad para inscribirse
                String actividad = request.getParameter("actividad");
                try {
                    String[] listaSalidas = controladorSalidaTuristica
                            .listarSalidasVigentesAsociadasAActividadTuristica(actividad).getLista().toArray(new String[0]);

                    String[] test = controladorSalidaTuristica.listarSalidasAsociadasAActividadTuristica(actividad).getLista().toArray(new String[0]);

                    System.out.println("HOLA MARTINA ME METI A LA INSCRIPCION");

                    DtActividadTuristica actTuristica = null;
                    try {
                        actTuristica = controladorActividadTuristica.getActividadTuristica(actividad);
                    } catch (EntidadNoExisteException_Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    Map<String, DtSalidaTuristica> salidas = new HashMap();

                    for (String salida : listaSalidas) {
                        try {
                            DtSalidaTuristica dtSalida = controladorSalidaTuristica.getSalidaTuristica(salida);
                            dtSalida.setImagen(ImagePathHelper.conPrefijo(dtSalida.getImagen()));
                            salidas.put(salida, dtSalida);

                        } catch (EntidadNoExisteException_Exception e) {
                            // TODO redireccionar a pagina de error o no se
                            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
                            return;
                        }
                    }

                    request.setAttribute(RequestKeys.COSTO_SALIDA, actTuristica.getCostoPorPersona());
                    request.setAttribute(RequestKeys.LISTA_SALIDAS, salidas);
                    request.getRequestDispatcher("/WEB-INF/views/actividades/inscripcionSalidaTuristica.jsp")
                            .forward(request, response);
                    return;
                } catch (NoHayEntidadesParaListarException_Exception | CampoInvalidoException_Exception e) {
                    // TODO redireccionar a pagina de error
                    ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
                    return;
                }
            }

        }

        if (EstadoSesionHelper.hayProveedorLogueado(request)) {
            ErrorHandler.redirigirAPaginaDeError(request, response, 401);
            return;
        }
        response.sendRedirect(request.getContextPath() + Endpoints.INICIAR_SESION_SERVLET);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String salidaTuristica = request.getParameter("salidaSeleccionada");
        int cantTuristas = 0;

        if (request.getParameter("cantTuristas") != null) {
            try {
                cantTuristas = Integer.parseInt(request.getParameter("cantTuristas"));
            } catch (NumberFormatException nfe) {
                ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, "La cantidad de turistas debe" +
                        " ser un numero");
                return;
            }
        }

        if (cantTuristas == 0) {
            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, "La cantidad de turistas debe ser mayor a 0");
            return;
        }

        DtInscripcionSalida inscripcion = new DtInscripcionSalida();
        inscripcion.setCantidadTuristas(cantTuristas);
        inscripcion.setNombreSalidaTuristica(salidaTuristica);
        inscripcion.setFechaInscripcion(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        inscripcion.setNickname(EstadoSesionHelper.getUsuarioLogueado(request).getNickname());

        try {
            controladorSalidaTuristica.inscribirTuristaASalidaTuristica(inscripcion);

        } catch (EntidadRepetidaException_Exception | CampoInvalidoException_Exception | MaximoDeTuristasAlcanzadoException_Exception e) {
            request.setAttribute(RequestKeys.ERROR, e.getMessage());
            ErrorHandler.redirigirConErrorMenteniendoQueryString(request, response, e.getMessage());
            return;
        }

        response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);

    }
}
