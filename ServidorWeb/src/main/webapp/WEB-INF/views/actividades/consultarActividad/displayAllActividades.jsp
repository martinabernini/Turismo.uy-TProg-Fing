<%@page import="webservices.DtActividadTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.SessionKeys" %>

<div class="container-fluid pt-5">
    <div class="row px-xl-5 pb-2">
        <%
            List<DtActividadTuristica> actividades = (List<DtActividadTuristica>) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES);
            if (actividades != null) {
                for (DtActividadTuristica actividad : (List<DtActividadTuristica>) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES)) {
                    String link = "ConsultarActividad" + "?actividad=" + actividad.getNombre();
                    String img = actividad.getImagen();
                    String alt = "Foto de " + actividad.getNombre();
                    String titulo = actividad.getNombre();
                    String detalle = "$" + String.valueOf(actividad.getCostoPorPersona());
        %>
        <jsp:include page="/WEB-INF/template/cardProductos.jsp">
            <jsp:param name="link" value="<%= link %>"/>
            <jsp:param name="img" value="<%= img %>"/>
            <jsp:param name="alt" value="<%= alt %>"/>
            <jsp:param name="titulo" value="<%= titulo %>"/>
            <jsp:param name="detalle" value="<%= detalle %>"/>
        </jsp:include>
        <%
                }
            }
        %>
    </div>
</div>