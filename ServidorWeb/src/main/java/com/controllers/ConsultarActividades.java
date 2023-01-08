package com.controllers;

import com.helpers.*;
import webservices.CampoInvalidoException_Exception;
import webservices.DtActividadTuristica;
import webservices.EntidadNoExisteException_Exception;
import webservices.NoHayEntidadesParaListarException_Exception;
import webservices.PublicadorControladorActividad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(Endpoints.CONSULTAR_ACTIVIDADES_SERVLET)
public class ConsultarActividades extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PublicadorControladorActividad controladorActividad;

    public ConsultarActividades() {
        super();
        controladorActividad = FabricaWS.getInstance().getControladorActividad();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        // Los posibles filtros de la búsqueda se reciben como parámetros
        // categoria, departamento, actividad

        // si no hay filtros -> se muesta la pagina de filstros de busqueda categorias y departamentos
        // si hay categoria -> se muestra la lista de actividades de esa categoria
        // si hay departamento -> se muestra la lista de actividades de ese departamento
        // si hay actividad -> se muestra la lista de salidas de esa actividad para
        // inscribirse

        ErrorHandler.guardarErrorDelQueryEnAttributeDelRequest(request);

        /*	-------------- SI NO HAY FILTROS 	--------------  */
        if (request.getParameter("categoria") == null && request.getParameter("departamento") == null) {
            try {
                String[] actividades = controladorActividad.listarActividadesEnEstadoConfirmada().getLista().toArray(new String[0]);

                List<DtActividadTuristica> listaActividades = new ArrayList<>();

                for (String actividad : actividades) {
                    DtActividadTuristica actividadDt = controladorActividad.getActividadTuristica(actividad);
                    actividadDt.setImagen(ImagePathHelper.conPrefijo(actividadDt.getImagen()));
                    listaActividades.add(actividadDt);
                }
                request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, listaActividades);
                request.getRequestDispatcher("/WEB-INF/views/actividades/consultarActividad/consultarActividades.jsp").forward(request, response);
                return;

            } catch (NoHayEntidadesParaListarException_Exception e) {
                request.getRequestDispatcher("/WEB-INF/views/error/noContent.jsp").forward(request, response);
                return;
            }catch(EntidadNoExisteException_Exception | CampoInvalidoException_Exception e){
                ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
                return;
            }
        }

        /*	-------------- SI FILTRO POR CATEGORIA 	--------------  */
        if (request.getParameter("categoria") != null && request.getParameter("departamento") == null) {
            try {
                String categoria = request.getParameter("categoria");
                String[] actividades = controladorActividad.listarActividadesAsociadasACategoriaConfirmadas(categoria).getLista().toArray(new String[0]);
                List<DtActividadTuristica> listaActividades = new ArrayList<>();

                for (String actividad : actividades) {
                    DtActividadTuristica actividadDt = controladorActividad.getActividadTuristica(actividad);
                    actividadDt.setImagen(ImagePathHelper.conPrefijo(actividadDt.getImagen()));
                    listaActividades.add(actividadDt);
                }

                request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, listaActividades);

                request.getRequestDispatcher("/WEB-INF/views/actividades/consultarActividad/consultarActividades.jsp").forward(request, response);
                return;
            } catch (NoHayEntidadesParaListarException_Exception | EntidadNoExisteException_Exception |
                     CampoInvalidoException_Exception e) {
                ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
                return;
            }
        }

        /*	-------------- SI FILTRO POR DEPARTAMENTO 	--------------  */
        if (request.getParameter("departamento") != null && request.getParameter("categoria") == null) {
            try {
                String departamento = request.getParameter("departamento");
                String[] actividades =
                        controladorActividad.listarActividadesAsociadasADepartamentoConfirmadas(departamento).getLista().toArray(new String[0]);
                List<DtActividadTuristica> listaActividades = new ArrayList<>();

                for (String actividad : actividades) {
                    DtActividadTuristica actividadDt = controladorActividad.getActividadTuristica(actividad);
                    actividadDt.setImagen(ImagePathHelper.conPrefijo(actividadDt.getImagen()));
                    listaActividades.add(actividadDt);
                }

                request.setAttribute(RequestKeys.LISTA_ACTIVIDADES, listaActividades);
                request.getRequestDispatcher("/WEB-INF/views/actividades/consultarActividad/consultarActividades" +
                        ".jsp").forward(request, response);
                return;
            } catch (NoHayEntidadesParaListarException_Exception | EntidadNoExisteException_Exception |
                     CampoInvalidoException_Exception e) {
                ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
                return;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
