package com.helpers;

import java.util.Properties;

public class ConfigHelper {


    public static String getWebServiceBaseURL() {
        Properties prop = ContextListenerConfig.cfg;
        String url = "http://" + prop.getProperty("hostIP") + ":" + prop.getProperty("hostPort") + "/webservices";
        return url;
    }

    public static String getControladorActividadURL() {
        return getWebServiceBaseURL() + "/ControladorActividad?wsdl";
    }

    public static String getControladorDepartamentoURL() {
        return getWebServiceBaseURL() + "/ControladorDepartamento?wsdl";
    }

    public static String getControladorUsuarioURL() {
        return getWebServiceBaseURL() + "/ControladorUsuario?wsdl";
    }

    public static String getControladorPaqueteURL() {
        return getWebServiceBaseURL() + "/ControladorPaquete?wsdl";
    }

    public static String getControladorSalidaURL() {
        return getWebServiceBaseURL() + "/ControladorSalida?wsdl";
    }

    public static String getCargaDatosURL() {
        return getWebServiceBaseURL() + "/CargaDatos?wsdl";
    }

    public static String getControladorImagenesURL() {
        return getWebServiceBaseURL() + "/ControladorImagenes?wsdl";
    }

}
