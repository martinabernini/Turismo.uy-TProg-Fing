package com.helpers;

import webservices.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FabricaWS {

    private static FabricaWS instance = null;

    public static FabricaWS getInstance() {
        if (instance == null) {
            instance = new FabricaWS();
        }
        return instance;
    }

    private FabricaWS() {
    }


    public PublicadorControladorActividad getControladorActividad() {

        String url = ConfigHelper.getControladorActividadURL();
        URL controladorActividadURL = null;
        try {
            controladorActividadURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorActividadService serviceActividad = new PublicadorControladorActividadService(controladorActividadURL);
        PublicadorControladorActividad port = serviceActividad.getPublicadorControladorActividadPort();
        return port;
    }

    public PublicadorControladorDepartamento getControladorDepartamento() {

        String url = ConfigHelper.getControladorDepartamentoURL();
        URL controladorDepartamentoURL = null;
        try {
            controladorDepartamentoURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorDepartamentoService serviceDpto = new PublicadorControladorDepartamentoService(controladorDepartamentoURL);
        PublicadorControladorDepartamento port = serviceDpto.getPublicadorControladorDepartamentoPort();
        return port;
    }

    public PublicadorCargaDatos getCargaDatos() {
        String url = ConfigHelper.getCargaDatosURL();
        URL cargaDatosURL = null;
        try {
            cargaDatosURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorCargaDatosService serviceCargaDatos = new PublicadorCargaDatosService(cargaDatosURL);
        PublicadorCargaDatos port = serviceCargaDatos.getPublicadorCargaDatosPort();
        return port;
    }

    public PublicadorControladorUsuario getControladorUsuario() {
        String url = ConfigHelper.getControladorUsuarioURL();
        URL controladorUsuarioURL = null;
        try {
            controladorUsuarioURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorUsuarioService serviceUsuario = new PublicadorControladorUsuarioService(controladorUsuarioURL);
        PublicadorControladorUsuario port = serviceUsuario.getPublicadorControladorUsuarioPort();
        return port;
    }

    public PublicadorControladorPaquete getControladorPaquete() {
        String url = ConfigHelper.getControladorPaqueteURL();
        URL controladorPaqueteURL = null;
        try {
            controladorPaqueteURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorPaqueteService servicePaquete = new PublicadorControladorPaqueteService(controladorPaqueteURL);
        PublicadorControladorPaquete port = servicePaquete.getPublicadorControladorPaquetePort();
        return port;
    }

    public PublicadorControladorSalida getControladorSalida() {
        String url = ConfigHelper.getControladorSalidaURL();
        URL controladorSalidaURL = null;
        try {
            controladorSalidaURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorSalidaService serviceSalida = new PublicadorControladorSalidaService(controladorSalidaURL);
        PublicadorControladorSalida port = serviceSalida.getPublicadorControladorSalidaPort();
        return port;
    }

    public PublicadorControladorImagenes getControldaroImagenes(){
        String url = ConfigHelper.getControladorImagenesURL();
        URL controladorImagenesURL = null;
        try {
            controladorImagenesURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PublicadorControladorImagenesService serviceImagenes = new PublicadorControladorImagenesService(controladorImagenesURL);
        PublicadorControladorImagenes port = serviceImagenes.getPublicadorControladorImagenesPort();
        return port;

    }
}
