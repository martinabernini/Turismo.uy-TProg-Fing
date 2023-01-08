package logica.controladores;

import com.sun.tools.javac.Main;
import logica.interfaces.IControladorImagen;
import org.apache.commons.io.IOUtils;
import org.zeroturnaround.zip.ZipUtil;
import utils.ConfigHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ControladorImagen implements IControladorImagen {

    private static final String IMAGES_HOME_DIR = System.getProperty("user.home") + File.separator + ".turismoUy" + File.separator;

    private static boolean seCargoImagenes = false;

    public static void cargarImagenesDefecto() {

        ConfigHelper.cargarConfiguracion();

        if (seCargoImagenes) return;

        File carpetaImagesHome = new File(IMAGES_HOME_DIR + "img" + File.separator);

        // if user.home/.turismoUy/img exist return
        if (carpetaImagesHome.exists()) {
            return;
        }

        try (InputStream defaultImages = Main.class.getClassLoader().getResourceAsStream("img.zip")) {
            File zipFile = new File(IMAGES_HOME_DIR + "img.zip");
            if (!zipFile.exists()) {
                Files.copy(defaultImages, zipFile.toPath());
            }
            ZipUtil.unpack(zipFile, new File(IMAGES_HOME_DIR));
            zipFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] obtenerImagen(String identificador) {
        if (!seCargoImagenes) {
            cargarImagenesDefecto();
            seCargoImagenes = true;
        }

        byte[] byteArray = null;

        // get image from user.home/.turismoUy/img folder
        try (InputStream image = Files.newInputStream(new File(IMAGES_HOME_DIR + "img" + File.separator + identificador).toPath())) {
            byteArray = IOUtils.toByteArray(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    /*
     * @param dirPath: directorio donde se almacenará la imagen desde la carpeta img, ej: "usuario/"
     * @param imagen: imagen a almacenar
     * @param nombre: nombre de la imagen a almacenar que se le asignará al archivo
     * @param extension: extensión de la imagen a almacenar
     * */
    private String almacenarImagen(byte[] imagen, String dirPath, String nombre, String extension) {
        if (!seCargoImagenes) {
            cargarImagenesDefecto();
            seCargoImagenes = true;
        }

        String defaultReturn = dirPath + "_default.jpg";
        if (imagen.length == 0) {
            return defaultReturn;
        }

        String imageName = nombre + "." + extension;
        String imagePath = IMAGES_HOME_DIR + "img" + File.separator + dirPath + imageName;
        // if file already exists overwrite it
        try {
            Files.write(new File(imagePath).toPath(), imagen);
        } catch (IOException e) {
            e.printStackTrace();
            return defaultReturn;
        }
        return dirPath + imageName;
    }


    @Override
    public String almacenarImagenUsuario(byte[] imagen, String nick, String extension) {
        return almacenarImagen(imagen, "usuario/", nick, extension);
    }

    @Override
    public String almacenarImagenActividad(byte[] imagen, String nombre, String extension) {
        return almacenarImagen(imagen, "actividad/", nombre, extension);
    }

    @Override
    public String almacenarImagenSalida(byte[] imagen, String nombre, String extension) {
        return almacenarImagen(imagen, "salida/", nombre, extension);
    }

    @Override
    public String almacenarImagenPaquete(byte[] imagen, String nombre, String extension) {
        return almacenarImagen(imagen, "paquete/", nombre, extension);
    }


}
