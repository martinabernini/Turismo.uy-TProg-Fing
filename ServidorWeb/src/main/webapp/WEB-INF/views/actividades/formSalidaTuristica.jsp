<%@page import="webservices.DtSalidaTuristica"%>
<%@page import="java.util.List" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<!-- Inscripción Salida Turística Start -->
<div class="container-fluid bg-secondary w-75">

    <form class="form-group py-4" action="InscripcionSalidaTuristica" method="post">

        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <h1 class="text-black-50 h8 pb-2">Datos de las Salidas
                        Turísticas</h1>
                </div>
            </div>
        </div>

        <!-- Mostrar las salidas Start -->

        <div class="container-fluid bg-secondary text-dark mt-0 pt-0">
            <div class="row px-xl-5 pt-5">
                <%
                    Map<String, DtSalidaTuristica> salidasMap = (Map<String, DtSalidaTuristica>) request.getAttribute(RequestKeys.LISTA_SALIDAS);
                    for (DtSalidaTuristica salida : salidasMap.values()) {
                        String nombre = salida.getNombreSalida();
                        int cantMaxTuristas = salida.getCantidadMaximaTuristas();
                        String img = salida.getImagen();
                        String fecha = salida.getFechaSalida().toString();
                        String lugar = salida.getLugarSalida();
                %>
                <div class="col-md-4 mb-5">
                    <h5 class="font-weight-bold text-dark mb-4">
                        <center><%=nombre%>
                        </center>
                        <img class="img-fluid" src="<%= img %>" alt="Image"/>
                    </h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-dark mb-2"><i class="fa fa-angle-right mr-2"></i>Cantidad
                            maxima de turista: <%= cantMaxTuristas %>
                        </a> <a class="text-dark mb-2"><i
                            class="fa fa-angle-right mr-2"></i>Fecha de la salida turistica:
                        <%=fecha%>
                    </a>
                        <a class="text-dark mb-2"><i
                                class="fa fa-angle-right mr-2"></i>Costo por persona:
                            <%=(float) request.getAttribute(RequestKeys.COSTO_SALIDA)%>
                        </a>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <!-- Mostrar las salidas End -->

        <label for="salidaSeleccionada">Seleccionar salida Turística: </label>
        <select class="form-control mb-4" id="salidaSeleccionada" name="salidaSeleccionada">
            <%
                for (String nombreSalida : salidasMap.keySet()) {
            %>
            <option>
                <%= nombreSalida %>
            </option>
            <%
                }
            %>
        </select>

        <label for="cantTuristas">Cantidad de Turistas:</label>
        <input
                type="number" class="form-control mb-4" id="cantTuristas" name="cantTuristas"
                min="1" required placeholder=""
        />

        <label for="formaPago">Forma de pago:</label>
        <select class="form-control mb-4" id="formaPago" name="formaPago">
            <option>General</option>
            <option>Por paquete</option>
        </select>


        <%
            if (ErrorHandler.hayErrorEnRequest(request)) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> No se puedo realizar la inscripción: <%= ErrorHandler.getErrorMessage(request) %>
        </div>
        <%
            }
        %>


        <!-- Enviar -->
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </div>
    </form>
</div>

<%--<script defer type="text/javascript">--%>

<%--    // let cantidadTuristasField = document.getElementById("cantTuristas");--%>

<%--    // cantidadTuristasField.addEventListener("change", validarCantTuristas);--%>

<%--    &lt;%&ndash;function validarCantTuristas() {&ndash;%&gt;--%>
<%--    &lt;%&ndash;    let cantidadTuristas = cantidadTuristasField.value;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    if (cantidadTuristas < 1) {&ndash;%&gt;--%>
<%--    &lt;%&ndash;        cantidadTuristasField.value = 1;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    }&ndash;%&gt;--%>
<%--    &lt;%&ndash;    // let maxCantidadTuristas = {&ndash;%&gt;--%>
<%--    &lt;%&ndash;    //     a: 10,&ndash;%&gt;--%>
<%--    &lt;%&ndash;    //     b: 20,&ndash;%&gt;--%>
<%--    &lt;%&ndash;    // }&ndash;%&gt;--%>

<%--    &lt;%&ndash;    let maxCantidadTuristas = {&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <% for (DtSalidaTuristica salida : salidasMap.values()) { %>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        "<%= salida.getNombreSalida() %>": <%= salida.getCantidadMaximaTuristas() %>,&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <% } %>&ndash;%&gt;--%>
<%--    &lt;%&ndash;    }&ndash;%&gt;--%>

<%--    &lt;%&ndash;    let salidaSeleccionada = document.getElementById("salidaSeleccionada").value;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    let maxCantidadTuristasSalida = maxCantidadTuristas[salidaSeleccionada];&ndash;%&gt;--%>
<%--    &lt;%&ndash;    if (cantidadTuristas > maxCantidadTuristasSalida) {&ndash;%&gt;--%>
<%--    &lt;%&ndash;        cantidadTuristasField.value = maxCantidadTuristasSalida;&ndash;%&gt;--%>
<%--    &lt;%&ndash;        alert("La cantidad de turistas no puede ser mayor a " + maxCantidadTuristasSalida + " para la salida seleccionada");&ndash;%&gt;--%>
<%--    &lt;%&ndash;    }&ndash;%&gt;--%>

<%--    &lt;%&ndash;    // TODO implementer la logica del precio&ndash;%&gt;--%>
<%--    &lt;%&ndash;    &lt;%&ndash;let cantidadTuristas = cantidadTuristasField.value;&ndash;%&gt;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    &lt;%&ndash;let costoSalida = parseFloat("<%= (float) request.getAttribute(RequestKeys.COSTO_SALIDA) %>");&ndash;%&gt;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    &lt;%&ndash;let costoTotal = cantidadTuristas * costoSalida;&ndash;%&gt;&ndash;%&gt;--%>
<%--    &lt;%&ndash;    &lt;%&ndash;document.getElementById("costoTotal").innerHTML = costoTotal;&ndash;%&gt;&ndash;%&gt;--%>
<%--    &lt;%&ndash;}&ndash;%&gt;--%>

<%--    // function onSubmit(event) {--%>
<%--    //     if (!validarFormulario()) {--%>
<%--    //         event.preventDefault();--%>
<%--    //     }--%>
<%--    // }--%>

<%--    // function validarFormulario() {--%>
<%--    //     return validarCantTuristas();--%>
<%--    // }--%>

<%--</script>--%>