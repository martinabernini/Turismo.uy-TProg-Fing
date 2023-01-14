<%@page import="webservices.DtActividadTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.SessionKeys" %>
<%@ page import="com.helpers.Endpoints" %>


<div class="container-fluid bg-secondary w-75">
    <form class="form-group py-4" enctype="multipart/form-data"
          action="<%= Endpoints.CONSULTAR_SALIDAS %>" method="get">
        <!-- Section Title Start -->
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <h1 class="text-black-50 h3 pb-2">Seleccione una Salida Turística</h1>
                </div>
            </div>
        </div>

        <label for="salida">Salida*:</label>
        <select class="form-control mb-4" name="salida" id="salida">
            <%
                String[] salidas = (String[]) request.getAttribute(RequestKeys.SALIDAS_ACTIVIDAD_A_CONSULTAR);
                for (String sal : salidas) {
            %>
            <option><%= sal %>
            </option>
            <%
                }
            %>

        </select>


        <!-- Enviar -->
        <span class="ml-auto"><input type="submit"
                                     class="btn btn-block btn-primary" id="alta-actividad-form-registar"
                                     value="Ingresar"></span>


    </form>
</div>

<div class="container-fluid">
    <div class="row px-xl-5 pb-2">
        <%
            for (DtActividadTuristica actividad : (List<DtActividadTuristica>) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES)) {

                String test = "test";


                String link = Endpoints.CONSULTAR_SALIDAS + "?actividad=" + actividad.getNombre().replace(" ", "_");
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