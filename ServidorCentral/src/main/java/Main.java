import logica.controladores.ControladorImagen;
import logica.interfaces.IPublicador;
import presentacion.Principal;
import utils.ConfigHelper;
import webservices.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class Main {

    public static Properties configuracion;

    public static void main(String[] args) {

        Set<String> flags = new HashSet<>();
        flags.addAll(Arrays.asList(args));

        ConfigHelper.cargarConfiguracion();
        ControladorImagen.cargarImagenesDefecto();

        if(flags.contains("--solo-estacion")) {
            levantarEstacionTrabajo();
        }

        if(flags.contains("--solo-servidor")) {
            levantarServidorCentral();
        }

        if(!flags.contains("--solo-estacion") && !flags.contains("--solo-servidor")) {
            levantarEstacionTrabajo();
            levantarServidorCentral();
        }
//        levantarEstacionTrabajo();

    }

    public static void levantarServidorCentral() {
        System.out.println("Levantando servidor central...");

        List<IPublicador> publicadores = new ArrayList<>();
        publicadores.add(new PublicadorControladorActividad());
        publicadores.add(new PublicadorControladorSalida());
        publicadores.add(new PublicadorControladorPaquete());
        publicadores.add(new PublicadorControladorDepartamento());
        publicadores.add(new PublicadorControladorUsuario());
        publicadores.add(new PublicadorCargaDatos());
        publicadores.add(new PublicadorControladorImagenes());

        for (IPublicador pub : publicadores) {
            pub.publicar();
        }
    }

    public static void levantarEstacionTrabajo() {
        System.out.println("Levantando estación...");
        Principal.runGUI();
    }


}