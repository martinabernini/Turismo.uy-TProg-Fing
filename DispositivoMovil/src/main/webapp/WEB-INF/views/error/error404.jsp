<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="container-fluid py-1">
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-3">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <img class="w-100 h-100" src="<%= Endpoints.DISPLAY_IMAGE %>?identificador=media/img/error404.jpg" alt="Image">
            </div>
        </div>

        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold" style="font-size:7.5rem">404</h3>

            <div class="d-flex mb-0.5">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:3rem"> ¡OH NO! Estas perdido</p>
            </div>

            <div class="d-flex mb-0">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:1.5rem"> La pagina que estas
                    buscando no existe. </p>
            </div>
            <div class="d-flex mb-0.5">
                <p class="text-dark font-weight-medium mb-0 mr-3" style="font-size:1.5rem"> Pero puedes hacer click
                    <a class=" font-weight-medium mb-0" style="font-size:1.5rem ; color:#0b51c5"
                       href="<%= Endpoints.HOME %>">aqui</a>
                    para regresarte al inicio.</p>
            </div>

        </div>
    </div>

</div>

