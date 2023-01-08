<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="d-none d-lg-block p-4 "></div>
<div class="navbar-nav py-0 justify-content-end">
    <div class="d-flex align-items-center">
        <img
                style="width: 2.2rem; height: 2.2rem; object-fit: cover; border-radius: 10%; margin-left: 1rem;"
                src="MostrarImagen?id=${param.profilePic}" alt="Image"/>
        <div class="btn-group">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${param.nickname}</button>

            <div class="dropdown-menu">
                <a class="dropdown-item" href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=${param.nickname}">Mi
                    perfil</a> <a class="dropdown-item" href="<%= Endpoints.LOG_OUT %>" >Cerrar
                sessión</a>
            </div>
        </div>

    </div>
</div>