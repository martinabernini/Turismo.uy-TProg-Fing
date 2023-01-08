package com.controllers;

import com.helpers.FabricaWS;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MostrarImagen", value = "/MostrarImagen")
public class MostrarImagen extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String id = request.getParameter("id");
        byte[] img = null;

        try {

            img = FabricaWS.getInstance().getControldaroImagenes().obtenerImagen(id);
            response.setContentType("image/" + id.substring(id.lastIndexOf(".") + 1));
            response.setContentLength((int) img.length);
            OutputStream out = response.getOutputStream();
            out.write(img);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}