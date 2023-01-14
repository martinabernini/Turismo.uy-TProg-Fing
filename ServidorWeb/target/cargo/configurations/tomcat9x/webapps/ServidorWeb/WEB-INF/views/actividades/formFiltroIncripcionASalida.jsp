<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container-fluid bg-secondary w-75">

    <div class="form-group py-4">
        <%
            Object filtro = request.getAttribute(RequestKeys.FILTRO_INSCRIPCION_SALIDA_INICIAL);

            if (filtro != null) {
                boolean inicial = (boolean) filtro;
                if (inicial) {
        %>
        <form action="InscripcionSalidaTuristica" method="get">
            <label for="departamento">Departamento:</label>
            <select class="form-control mb-4" name="departamento" id="departamento" onclick="this.form.submit()">
                <%
                    String[] departamentos = (String[]) request.getAttribute(RequestKeys.LISTA_DEPARTAMENTOS);
                    for (String departamento : departamentos) {
                %>
                <option><%= departamento %>
                </option>
                <%
                    }
                %>
            </select>
        </form>
        <form action="InscripcionSalidaTuristica" method="get">

            <label for="categoria">Categorias:</label>
            <select class="form-control mb-4" name="categoria" id="categoria" onclick="this.form.submit()">
                <%
                    String[] categorias = (String[]) request.getAttribute(RequestKeys.LISTA_CATEGORIAS);
                    for (String categoria : categorias) {
                %>
                <option><%= categoria %>
                </option>
                <%
                    }
                %>
            </select>
        </form>
        <%
            }

        %>
        <%
            if (!inicial) {
        %>
        <form action="InscripcionSalidaTuristica" method="get">

            <label for="actividad">Actividades:</label>
            <select class="form-control mb-4" name="actividad" id="actividad" onclick="this.form.submit()">
                <%
                    String[] actividades = (String[]) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES);
                    for (String actividad : actividades) {
                %>
                <option><%= actividad %>
                </option>
                <%
                    }
                %>
            </select>
        </form>

        <%
                }
            }
        %>


        <%
            if (ErrorHandler.hayErrorEnRequest(request)) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> <%= ErrorHandler.getErrorMessage(request) %>
        </div>
        <%
            }
        %>


    </div>
</div>
