<%@page import="com.helpers.RequestKeys"%>
<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="card-for-${param.nombreActividad.replace(' ', '-')}">


    <div class=" d-flex mb-1">
        <p class="text-dark font-weight-medium mb-0 mr-3">Nombre de la actividad:
            <a class="text-dark font-weight-semi-bold"
               href="<%= Endpoints.CONSULTAR_ACTIVIDAD %>?actividad=${param.nombreActividad}">${param.nombreActividad}</a>
        </p>
    </div>


    <div class="d-flex mb-0">
        <p class="text-dark font-weight-medium mb-0 mr-3">Descripcion:</p>
        <p class="mb-1">${param.descripcion}</p>
    </div>

    <div class="d-flex mb-0">
        <p class="text-dark font-weight-medium mb-0 mr-3">Duracion (hs):</p>
        <p class="mb-1">${param.duracion}</p>
    </div>

    <div class="d-flex mb-0">
        <p class="text-dark font-weight-medium mb-0 mr-3">Costo por
            persona:</p>
        <p class="mb-1">${param.costo}</p>
    </div>


    <div class="d-flex mb-0">
        <p class="text-dark font-weight-medium mb-0 mr-3">Ciudad:</p>
        <p class="mb-1">${param.ciudad}</p>
    </div>


    <%
        String tieneVigentes = request.getParameter("tieneSalidasVigentes");
        if (tieneVigentes != null && tieneVigentes.equals("false") && (Boolean) request.getAttribute(RequestKeys.ES_MI_PERFIL)) {
    %>
    <div class="d-flex mb-0">
        <%--Boton para finalizar actividad --%>
        <a class="btn btn-primary btn-sm" id="btnFinalizar-${param.nombreActividad.replace(" ", "-")}"
           href="<%= Endpoints.FINALIZAR_ACTIVIDAD %>?nombreActividad=${param.nombreActividad}">Finalizar</a>

        <div id="modal-confirmacion-finalizacion-${param.nombreActividad.replace(' ', '-')}" class="d-none">
            <%-- button of confirmation--%>
            <btn class= "btn btn-danger btn-sm" id="btn-confirmar-finalizacion-${param.nombreActividad.replace(' ', '-')}"
            >Confirmar finalización
            </btn>
        </div>
    </div>
    <%
        }
    %>

    <div class="mb-4"></div>
</div>

<script defer type="text/javascript">

    // Funcion para mostrar el modal de confirmacion de finalizacion de actividad
    document.getElementById("btnFinalizar-${param.nombreActividad.replace(" ", "-")}").addEventListener("click", event => {
        event.preventDefault();
        // get "card-for-${param.nombreActividad.replace(' ', '-')}" element and put a gray overlay
        document.getElementById("card-for-${param.nombreActividad.replace(" ", "-")}").classList.add("bg-warning");

        // get "modal-confirmacion-finalizacion" element and show it
        document.getElementById("modal-confirmacion-finalizacion-${param.nombreActividad.replace(' ', '-')}").classList.remove("d-none");
    });

    document.getElementById("btn-confirmar-finalizacion-${param.nombreActividad.replace(" ", "-")}").addEventListener("click", event => {
        event.preventDefault();

        let url = "<%= Endpoints.FINALIZAR_ACTIVIDAD %>?nombreActividad=${param.nombreActividad}";

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify({})
        }).then(response => {
            if (response.ok) {
                // borrar de la pagina id "card-for-${param.nombreActividad.replace(' ', '-')}"
                document.getElementById("card-for-${param.nombreActividad.replace(" ", "-")}").remove();
                // return response.json();
            } else {
                throw new Error("Error al finalizar la actividad");
            }
        });
        //     .then(data => {
        //     console.log(data);
        //     if (data.status === "OK") {
        //         // Mostrar modal de confirmacion
        //         $('#modalConfirmacionFinalizacion').modal('show');
        //     } else {
        //         // Mostrar modal de error
        //         $('#modalErrorFinalizacion').modal('show');
        //     }
        // }).catch(error => {
        //     console.log(error);
        //     // Mostrar modal de error
        //     $('#modalErrorFinalizacion').modal('show');
        // });

        <%--// get "card-for-${param.nombreActividad.replace(' ', '-')}" element and remove the gray overlay--%>
        <%--document.getElementById("card-for-${param.nombreActividad.replace(" ", "-")}").classList.remove("overlay");--%>
        <%--// get "modal-confirmacion-finalizacion" element and hide it--%>
        <%--document.getElementById("modal-confirmacion-finalizacion-${param.nombreActividad.replace(' ', '-')}").classList.add("d-none");--%>
    });


</script>