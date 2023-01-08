package com.controllers;

import com.helpers.EstadoSesionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Error")
public class Error extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Error() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
    }

}
