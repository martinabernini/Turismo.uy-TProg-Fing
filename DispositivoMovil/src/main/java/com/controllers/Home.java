package com.controllers;

import com.helpers.Endpoints;
import com.helpers.EstadoSesionHelper;
import com.helpers.FabricaWS;
import webservices.PublicadorCargaDatos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;


@WebServlet(Endpoints.HOME_SERVLET)
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PublicadorCargaDatos cargaDatos;

    public Home() {
        super();
        cargaDatos = FabricaWS.getInstance().getCargaDatos();

        // TODO Chequear tema carga datos por defecto, hay que sacarlo de aca
        cargaDatos.cargarDatos();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/views/home/home.jsp").forward(request, response);
    }
}


