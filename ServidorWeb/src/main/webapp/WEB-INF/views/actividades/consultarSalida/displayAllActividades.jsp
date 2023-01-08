<%@page import="webservices.DtActividadTuristica"%>
<%@page import="com.helpers.RequestKeys" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.Endpoints" %>
<div class="container-fluid pt-5">
    <div class="row px-xl-5 pb-2">
        <%
            // si hay error no se muestra el contenido
            String errorFromRequest = (String) request.getAttribute(RequestKeys.ERROR);
            // si hay error se muestra el mensaje

                List<DtActividadTuristica> actividades = (List<DtActividadTuristica>) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES);
                for (DtActividadTuristica actividad : actividades) {
                    String link = Endpoints.CONSULTAR_SALIDAS + "?actividad=" + actividad.getNombre();
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
        %>
    </div>
</div>