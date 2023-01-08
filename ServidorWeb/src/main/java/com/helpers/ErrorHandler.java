
package com.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {

    public static void redirigirConErrorMenteniendoQueryString(HttpServletRequest request, HttpServletResponse response,
                                                               String errorMessage) throws IOException {

        // obtiene el query string original
        String referrer = request.getHeader("referer");

        // if no referrer, redirect to home
        if (referrer == null) {
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
            return;
        }

        // si no hay query string, redirige a la página con el error
        if (!referrer.contains("?")) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?" + getRequestErrorKey() + "=" + errorMessage);
            return;
        }

        // si no hay query string, redirige a la página de error
        String queryString = referrer.substring(referrer.indexOf("?") + 1);
        String[] queryParts = queryString.split("&");

        Map<String, String> queryMap = new HashMap<>();
        for (String queryPart : queryParts) {
            String[] queryPartParts = queryPart.split("=");
            queryMap.put(queryPartParts[0], queryPartParts[1]);
        }

        // get the error count from the que map
        int errorCountInt = 0;
        String errorCountFromQuery = queryMap.get(RequestKeys.ERROR_COUNT);

        if (errorCountFromQuery != null) {
            try {
                errorCountInt = Integer.parseInt(errorCountFromQuery) + 1;
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        if (errorCountInt > 3) {
            response.sendRedirect(request.getContextPath() + Endpoints.ERROR_COUNT_EXCEEDED_SERVLET);
            return;
        }

        queryMap.put(getRequestErrorKey(), errorMessage);
        queryMap.put(RequestKeys.ERROR_COUNT, String.valueOf(errorCountInt));


        // make a new query string from the map
        StringBuilder newQueryString = new StringBuilder();
        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            newQueryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // remove the last &
        newQueryString.deleteCharAt(newQueryString.length() - 1);

        // redirige a la misma pagina con el query string modificado
        response.sendRedirect(request.getContextPath() + request.getServletPath() + "?" + newQueryString.toString());

    }

    public static void redirigirConErrorSinMantenerQueryString(HttpServletRequest request, HttpServletResponse response,
                                                               String errorMessage) throws IOException {

        // obtiene el query string original
        String referrer = request.getHeader("referer");

        // if no referrer, redirect to home
        if (referrer == null) {
            response.sendRedirect(request.getContextPath() + Endpoints.HOME_SERVLET);
            return;
        }

        // si no hay query string, redirige a la página con el error
        if (!referrer.contains("?")) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?" + getRequestErrorKey() + "=" + errorMessage);
            return;
        }

        // si no hay query string, redirige a la página de error
        String queryString = referrer.substring(referrer.indexOf("?") + 1);
        String[] queryParts = queryString.split("&");

        Map<String, String> queryMap = new HashMap<>();
        for (String queryPart : queryParts) {
            String[] queryPartParts = queryPart.split("=");
            queryMap.put(queryPartParts[0], queryPartParts[1]);
        }

        // get the error count from the que map
        int errorCountInt = 0;
        String errorCountFromQuery = queryMap.get(RequestKeys.ERROR_COUNT);

        if (errorCountFromQuery != null) {
            try {
                errorCountInt = Integer.parseInt(errorCountFromQuery) + 1;
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        if (errorCountInt > 3) {
            response.sendRedirect(request.getContextPath() + Endpoints.ERROR_COUNT_EXCEEDED_SERVLET);
            return;
        }

        Map<String, String> newQueryMap = new HashMap<>();
        newQueryMap.put(getRequestErrorKey(), errorMessage);
        newQueryMap.put(RequestKeys.ERROR_COUNT, String.valueOf(errorCountInt));

        // make a new query string from the map
        StringBuilder newQueryString = new StringBuilder();
        for (Map.Entry<String, String> entry : newQueryMap.entrySet()) {
            newQueryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // remove the last &
        newQueryString.deleteCharAt(newQueryString.length() - 1);

        // redirige a la misma pagina con el query string modificado
        response.sendRedirect(request.getContextPath() + request.getServletPath() + "?" + newQueryString.toString());

    }

    public static void guardarErrorDelQueryEnAttributeDelRequest(HttpServletRequest request) {
        if(request.getParameter(getRequestErrorKey()) != null) {
            request.setAttribute(RequestKeys.ERROR, request.getParameter(getRequestErrorKey()));
        }
    }

    public static boolean hayErrorEnRequest(HttpServletRequest request) {
        if (request.getParameter(getRequestErrorKey()) != null) {
            return true;
        }

        return request.getAttribute(getRequestErrorKey()) != null;
    }

    public static String getErrorMessage(HttpServletRequest request) {
        if (request.getParameter(getRequestErrorKey()) != null) {
            return request.getParameter(ErrorHandler.getRequestErrorKey());
        }
        if(request.getAttribute(getRequestErrorKey()) != null) {
            return (String) request.getAttribute(ErrorHandler.getRequestErrorKey());
        }
        return "Hubo un error";
    }

    public static String getRequestErrorKey() {
        return RequestKeys.ERROR;
    }

    public static void redirigirAlLogin(HttpServletRequest request, HttpServletResponse response,
                                        String urlOrigen) throws IOException {
        response.sendRedirect(request.getContextPath() + Endpoints.INICIAR_SESION_SERVLET + "?" + getLoginRedirectKey() + "=" + urlOrigen);
    }

    private static String getLoginRedirectKey() {
        return "loginRedirect";
    }

    public static void redirigirAPaginaDeError(HttpServletRequest request, HttpServletResponse response, int numero) throws IOException {
        response.sendRedirect(request.getContextPath() + Endpoints.ERROR_SERVLET + "?error=" + numero);

    }

}
