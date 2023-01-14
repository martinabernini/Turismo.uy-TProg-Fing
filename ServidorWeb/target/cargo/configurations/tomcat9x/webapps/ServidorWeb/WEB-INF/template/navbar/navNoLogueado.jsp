<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<div class="d-none d-md-block p-4"></div>
<a href="<%= Endpoints.INICIAR_SESION %>" class="nav-item nav-link">Iniciar
    sesion</a>
<div class="btn-group">
    <button type="button" style="color: #0b51c5;"
            class="btn dropdown-toggle" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">Registrarme
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="<%= Endpoints.ALTA_TURISTA %>">Registrar
            turista</a> <a class="dropdown-item" href="<%= Endpoints.ALTA_PROVEEDOR %>">Registrar
        proveedor</a>
    </div>
</div>