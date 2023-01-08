package com.helpers;

import javax.servlet.http.Part;
import java.io.*;

public class AlmacenarImagenHelper {

    public static String alamcenarImagenUsuario(Part partImagen, String nickname) {

        byte[] bytes = getByteArray(partImagen);
        if (bytes != null) {
            return FabricaWS.getInstance().getControldaroImagenes().almacenarImagenUsuario(getByteArray(partImagen), nickname, getFileExtension(partImagen));
        }
        return "usuario/_default.jpg";
    }

    public static String alamcenarImagenActividad(Part partImagen, String nombreActividad) {
        byte[] bytes = getByteArray(partImagen);
        if (bytes != null) {
            return FabricaWS.getInstance().getControldaroImagenes().almacenarImagenActividad(getByteArray(partImagen), nombreActividad, getFileExtension(partImagen));
        }
        return "actividad/_default.jpg";
    }

    public static String alamcenarImagenSalida(Part partImagen, String nombreSalida) {
        byte[] bytes = getByteArray(partImagen);
        if (bytes != null) {
            return FabricaWS.getInstance().getControldaroImagenes().almacenarImagenSalida(getByteArray(partImagen), nombreSalida, getFileExtension(partImagen));
        }
        return "salida/_default.jpg";
    }

    public static String alamcenarImagenPaquete(Part partImagen, String nombrePaquete) {
        byte[] bytes = getByteArray(partImagen);
        if (bytes != null) {
            return FabricaWS.getInstance().getControldaroImagenes().almacenarImagenPaquete(getByteArray(partImagen), nombrePaquete, getFileExtension(partImagen));
        }
        return "paquete/_default.jpg";
    }

    private static byte[] getByteArray(Part partImagen) {
        String extension = "";
        if (partImagen.getSize() != 0) {
            InputStream data = null;
            try {
                data = partImagen.getInputStream();
                // get byte array
                return org.apache.commons.io.IOUtils.toByteArray(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

    private static String getFileExtension(Part partImagen) {
        String extension = null;
        if (partImagen.getSize() != 0) {
            InputStream data = null;
            try {
                data = partImagen.getInputStream();
                extension = partImagen.getContentType().split("/")[1];
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }
}
