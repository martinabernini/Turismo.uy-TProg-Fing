package com.controllers;

import com.helpers.Endpoints;
import com.helpers.EstadoSesionHelper;
import com.model.EstadoSesion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(Endpoints.LOG_OUT_SERVLET)
public class LogOut extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogOut() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        EstadoSesionHelper.setEstado(request, EstadoSesion.NO_LOGIN);
        response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
    }

}
