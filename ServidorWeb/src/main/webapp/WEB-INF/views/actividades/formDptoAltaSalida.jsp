<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.Endpoints" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container-fluid bg-secondary w-75">
    <form class="form-group py-4" enctype="multipart/form-data"
          action="<%= Endpoints.ALTA_SALIDA %>" method="get">

        <!-- Section Title Start -->
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <h1 class="text-black-50 h3 pb-2">Seleccione un departamento para la salida turística</h1>
                </div>
            </div>
        </div>

        <label for="departamento">Departamento*:</label>
        <select class="form-control mb-4" name="departamento" id="departamento">
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

        <!-- Enviar -->
        <span class="ml-auto"><input type="submit"
                                     class="btn btn-block btn-primary" id="alta-actividad-form-registar"
                                     value="Ingresar"></span>


    </form>
</div>