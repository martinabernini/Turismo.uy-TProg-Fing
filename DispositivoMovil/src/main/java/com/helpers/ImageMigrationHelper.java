package com.helpers;

public class ImageMigrationHelper {

    public static String getImgPath(String id) {
        return "mostrarImagen?id=" + id;
    }

    public static String withPrefix(String prefix, String id) {
        // if id contains prefix, return id, it means that the image is already migrated
        if (id.startsWith(prefix)) {
            return id;
        }
        return prefix + id;
    }
}
