<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="container-fluid py-1">
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-8">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <img class="w-100 h-100" src="<%= Endpoints.DISPLAY_IMAGE %>?identificador=media/img/error500.jpg" alt="Image">
            </div>
        </div>

        <div class="col-lg-7 pb-8">
            <h3 class="font-weight-semi-bold" style="font-size:7.5rem">500</h3>

            <div class="d-flex mb-0.5">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:3rem"> ¡OH NO! Ha ocurrido un
                    error</p>
            </div>

            <div class="d-flex mb-0">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:1.5rem"> Parece que ha
                    ocurrido un error cargando esta pagina. </p>
            </div>

            <div class="d-flex mb-0.5">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:1.5rem"> Puedes volver al
                    inicio haciendo click
                    <a class=" font-weight-medium mb-0" style="font-size:1.5rem ; color:#0b51c5" href="<%= Endpoints.HOME %>">aqui</a>.
                </p>
            </div>

        </div>
    </div>
</div>
