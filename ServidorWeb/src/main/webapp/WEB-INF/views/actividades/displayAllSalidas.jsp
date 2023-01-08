<%@page import="webservices.DtSalidaTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.SessionKeys" %>

<div class="container-fluid">
    <div class="row px-xl-5 pb-2">
        <%
            for (DtSalidaTuristica salida : (List<DtSalidaTuristica>) request.getAttribute(RequestKeys.SALIDAS_ACTIVIDAD_A_CONSULTAR)) {
            	
            	
                String link = "ConsultarSalida" + "?salida=" + salida.getNombreSalida().replace(" ", "_");
                String img = salida.getImagen();
                String alt = "Foto de " + salida.getNombreSalida();
                String titulo = salida.getNombreSalida();
                String detalle = " ";
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