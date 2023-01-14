<%@page import="webservices.DtPaqueteActividades"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.SessionKeys" %>

<div class="container-fluid pt-5">
     <div class="row px-xl-5 pb-2">
        <%
            List<DtPaqueteActividades> paquetes = (List<DtPaqueteActividades>) request.getAttribute(RequestKeys.LISTA_PAQUETES_CONSULTA);
            if (paquetes != null) {
                for (DtPaqueteActividades paquete : (List<DtPaqueteActividades>) request.getAttribute(RequestKeys.LISTA_PAQUETES_CONSULTA)) {
       	
                    String link = "ConsultarPaquete" + "?paquete=" + paquete.getNombre();
                    String img = paquete.getImagen();
                    String alt = "Foto de " + paquete.getNombre();
                    String titulo = paquete.getNombre();
                    int descuento = (int) (paquete.getDescuento()*100);
                    String detalle = String.valueOf(descuento) + "% OFF";
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
            }
        %>
    </div> 
</div>