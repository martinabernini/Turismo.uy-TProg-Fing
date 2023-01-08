package utils;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;


public class ConfigHelper {

    public static Properties config;
    private static boolean seCargoConfiguracion = false;

    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String CONFIG_HOME_DIR = System.getProperty("user.home") + File.separator + ".turismoUy" + File.separator;

    public static void cargarConfiguracion() {
        if(seCargoConfiguracion) return;

        File carpetaConfigHome = new File(CONFIG_HOME_DIR);

        // Si no existe la carpeta de configuración, la creamos
        if (carpetaConfigHome.mkdir()) {
            System.out.println("Carpeta configuración no encontrada, creando en " + carpetaConfigHome.getAbsolutePath());

            File configFile = new File(CONFIG_HOME_DIR + CONFIG_FILE_NAME);
            System.out.println("No se encontró el archivo de configuración... generando configuración por defecto en " + configFile);

            try (InputStream defaultConfig = Main.class.getClassLoader().getResourceAsStream("" + CONFIG_FILE_NAME)) {
                Files.copy(defaultConfig, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = new Properties();
        File configFileHome = new File(CONFIG_HOME_DIR + CONFIG_FILE_NAME);
        try (InputStream s = Files.newInputStream(configFileHome.toPath())) {
            config.load(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lee propiedades que no estaban en las configuraciones del home/usuario/.turismoUy
        Properties properties = new Properties();
        try (InputStream configFileProject = Main.class.getClassLoader().getResourceAsStream("" + CONFIG_FILE_NAME)) {
            properties.load(configFileProject);

            for (Map.Entry<Object, Object> prop : properties.entrySet()) {

                if (config.getProperty((String) prop.getKey()) == null) {
                    config.setProperty((String) prop.getKey(), properties.getProperty((String) prop.getKey()));
                }
            }
            config.store(new FileOutputStream(CONFIG_HOME_DIR + CONFIG_FILE_NAME), "");

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        seCargoConfiguracion = true;
    }

    public static String getWebServiceBaseURL() {
        if (!seCargoConfiguracion) {
            cargarConfiguracion();
            seCargoConfiguracion = true;
        }
        return getHostURL() + "/webservices";
    }
    private static String getHostURL() {
        return "http://" + getHostIP() + ":" + getHostPort() ;
    }

    private static String getHostIP() {
        return config.getProperty("hostIP");
    }

    private static String getHostPort() {
        return config.getProperty("hostPort");
    }

}
