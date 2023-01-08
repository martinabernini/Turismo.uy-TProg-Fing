<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.helpers.ErrorHandler" %>

<%-- remember form data afer refresh --%>

<div
        class="btn-group bg-secondary border-bottom border-top border-left border-right mx-1 my-1">
    <button type="button" class="btn dropdown-toggle"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Filtrar por departamento
    </button>
    <div class="dropdown-menu">

        <a class="dropdown-item"
           href="${param.filtro}?departamento=Artigas">Artigas</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Canelones">Canelones</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Cerro Largo">Cerro Largo</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Colonia">Colonia</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Durazno">Durazno</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Florida">Florida</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Flores">Flores</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Lavalleja">Lavalleja</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Maldonado">Maldonado</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Montevideo">Montevideo</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Paysandu">Paysandu</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Rio Negro">Rio Negro</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Rivera">Rivera</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Rocha">Rocha</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Salto">Salto</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=San Jose">San Jose</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Soriano">Soriano</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Tacuarembo">Tacuarembo</a>
        <a class="dropdown-item"
           href="${param.filtro}?departamento=Treinta y Tres">Treinta y Tres</a>


    </div>
</div>

<div
        class="btn-group bg-secondary border-bottom border-top border-left border-right mx-1 my-1">
    <button type="button" class="btn dropdown-toggle"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Filtrar por categoria
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item"
           href="${param.filtro}?categoria=Aventura y Deporte">Aventura y Deporte</a> <a class="dropdown-item"
                                                                                         href="${param.filtro}?categoria=Campo y Naturaleza">Campo
        y Naturaleza</a> <a class="dropdown-item"
                            href="${param.filtro}?categoria=Turismo Playas">Turismo Playas</a> <a class="dropdown-item"
                                                                                                  href="${param.filtro}?categoria=Cultura y Patrimonio">Cultura
        y Patrimonio</a> <a class="dropdown-item"
                            href="${param.filtro}?categoria=Gastronomia">Gastronomia</a>
    </div>
</div>

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