<%@page import="webservices.DtUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.Endpoints" %>

<div class="container-fluid">
    <div class="row px-xl-5 pb-2">
        <%
            for (DtUsuario usuario : (List<DtUsuario>) request.getAttribute(RequestKeys.LISTA_USUARIOS)) {
                
            	String link = Endpoints.CONSULTAR_USUARIO + "?nickname=" + usuario.getNickname();
            	String img = usuario.getImagen();
            	String alt = "Foto de " + usuario.getNombre();
            	String titulo = usuario.getNickname();
            	String detalle = usuario.getNombre() + " " + usuario.getApellido();
        %>
            <jsp:include page="/WEB-INF/template/card.jsp">
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