package com.controllers;

import com.helpers.Endpoints;
import com.helpers.EstadoSesionHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


@WebServlet(Endpoints.DISPLAY_IMAGE_SERVLET)
public class DisplayImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayImage() {
        super();
    }

    private static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EstadoSesionHelper.initSession(request);
        request.setCharacterEncoding("UTF-8");

        // set the content type to image/jpeg.
        // TODO ver lo de la extension que puede variar
        String id = request.getParameter("identificador");
        String filePath = "src/main/webapp/" + id;


        String extension = getExtension(id);
        response.setContentType("image/" + extension);

        ServletOutputStream out;

        // Writing this image
        // content as a response
        out = response.getOutputStream();

        // path of the image


        FileInputStream fin = new FileInputStream(filePath);

        // getting image in BufferedInputStream
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        int ch = 0;
        while ((ch = bin.read()) != -1) {
            // display image
            bout.write(ch);
        }

        // close all classes
        bin.close();
        fin.close();
        bout.close();
        out.close();
    }
}
