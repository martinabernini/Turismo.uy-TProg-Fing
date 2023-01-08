<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="com.helpers.RequestKeys" %>
<%@page import="com.model.EstadoSesion" %>


<div class="container-fluid py-1">
    <div class="row px-xl-5">

        <div class="col-lg-5 pb-3">
            <div id="product-carousel" class="carousel slide"
                 data-ride="carousel">
                <img class="w-100 h-100" src="${param.img}" alt="Image">
                <%-- PARAMETRO IMAGEN --%>
            </div>
        </div>

        <div class="col-lg-7 pb-5 top">
            <div class="container-fluid">
                <div class="tabs">
                    <div class="tab">
                        <input type="radio" name="css-tabs" id="tab-1" checked
                               class="tab-switch"> <label for="tab-1"
                                                          class="tab-label b">INFORMACION DE SALIDA </label>
                        <div class="tab-content">

                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Nombre de la salida:</p>
                                <p class="mb-4">${param.nombreSal}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Nombre de la actividad:</p>
                                <p class="mb-4">${param.nombreAct}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Cantidad Maxima de Turistas::</p>
                                <p class="mb-4">${param.cantidad}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Lugar:</p>
                                <p class="mb-4">${param.lugar}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Fecha Alta:</p>
                                <p class="mb-4">${param.fechaAltal}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Fecha Salida:</p>
                                <p class="mb-4">${param.fechaSalida}</p>
                            </div>
                            
                            <div class="d-flex mb-0">
                                <p class="text-dark font-weight-medium mb-0 mr-3">Imagen:</p>
                                <p class="mb-4">${param.img}</p>
                            </div>
                            
                      


                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
