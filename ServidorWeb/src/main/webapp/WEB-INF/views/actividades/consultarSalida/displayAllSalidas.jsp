<%@page import="webservices.DtSalidaTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>

<div class="container-fluid pt-5">

    <div class="row px-xl-5 pb-2">
        <%

            for (DtSalidaTuristica salida : (List<DtSalidaTuristica>) request.getAttribute(RequestKeys.LISTA_SALIDAS_CONSULTA)) {

                String link = "ConsultarSalida" + "?salida=" + salida.getNombreSalida();
                String img = salida.getImagen();
                String alt = "Foto de " + salida.getNombreSalida();
                String titulo = salida.getNombreSalida();
        %>
        <jsp:include page="/WEB-INF/template/cardProductos.jsp">
            <jsp:param name="link" value="<%= link %>"/>
            <jsp:param name="img" value="<%= img %>"/>
            <jsp:param name="alt" value="<%= alt %>"/>
            <jsp:param name="titulo" value="<%= titulo %>"/>
        </jsp:include>
        <%
            }
        %>
    </div>
</div>