package com.controllers;

import com.helpers.*;
import webservices.CampoInvalidoException_Exception;
import webservices.DtPaqueteActividades;
import webservices.EntidadNoExisteException_Exception;
import webservices.NoHayEntidadesParaListarException_Exception;
import webservices.PublicadorControladorPaquete;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(Endpoints.CONSULTAR_PAQUETES_SERVLET)
public class ConsultarPaquetes extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private final PublicadorControladorPaquete controladorPaquete;

    public ConsultarPaquetes() {
        super();
		controladorPaquete = FabricaWS.getInstance().getControladorPaquete();
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        String[] paquetes;
        try {
            paquetes = controladorPaquete.listarPaquetes().getLista().toArray(new String[0]);

            List<DtPaqueteActividades> listaPaquetes = new ArrayList<>();

            for (String paquete : paquetes) {

                DtPaqueteActividades paqueteDt = controladorPaquete.find(paquete);
                paqueteDt.setImagen(ImagePathHelper.conPrefijo(paqueteDt.getImagen())); //CONSEGUIR LA IMAGEN NUEVA CON PREFIJO
                listaPaquetes.add(paqueteDt);
            }

            request.setAttribute(RequestKeys.LISTA_PAQUETES_CONSULTA, listaPaquetes);
            request.getRequestDispatcher("/WEB-INF/views/paquetes/consultarPaquete/consultarPaquetes.jsp").forward(request, response);

        }    catch (NoHayEntidadesParaListarException_Exception e) {
            request.getRequestDispatcher("/WEB-INF/views/error/noContent.jsp").forward(request, response);
            return;
        }   catch(EntidadNoExisteException_Exception | CampoInvalidoException_Exception e){
            ErrorHandler.redirigirConErrorSinMantenerQueryString(request, response, e.getMessage());
            return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
