package com.helpers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;


@WebListener
public class ContextListenerConfig implements ServletContextListener {
    public static Properties cfg = new Properties();

    public ContextListenerConfig() {
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        cargarConfig(sce);
    }

    public static void cargarConfig(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String home = System.getProperty("user.home");

        File cfgfolder = new File(home + "/.turismoUy");
        Properties config = cfg;

        config.setProperty("hostIP", ctx.getInitParameter("hostIP"));
        config.setProperty("hostPort", ctx.getInitParameter("hostPort"));

        if (cfgfolder.mkdir()) {
            System.out.println("Carpetas de configuracion no encontradas... creando carpetas de configuracion por defecto en " + home);
            try {
                config.store(new FileOutputStream(home + "/.turismoUy/config.properties"), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File prp = new File(home + "/.turismoUy/config.properties");
            if (!(prp.exists())) {
                System.out.println("Archivo de configuracion no encontrado... generando archivo de configuracion por defecto en " + prp);
                try {
                    config.store(new FileOutputStream(home + "/.turismoUy/config.properties"), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        config = new Properties();
        try {
            config.load(new FileInputStream(home + "/.turismoUy/config.properties"));
            for (Entry<Object, Object> x : config.entrySet()) {
                if (x.getValue() == null)
                    config.setProperty((String) x.getKey(), ctx.getInitParameter((String) x.getKey()));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("Configuracion cargada: " + config.toString());
        cfg = config;
    }
}
