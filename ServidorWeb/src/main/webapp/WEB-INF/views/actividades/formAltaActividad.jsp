<%@page import="com.helpers.SessionKeys" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.Endpoints" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Alta Actividad Turistícas Start -->
<div class="container-fluid bg-secondary w-75">
    <form class="form-group py-4" enctype="multipart/form-data"
          id="formAltaActividadTuristica"
          action="<%= Endpoints.ALTA_ACTIVIDAD %>" method="post">
        <!-- Section Title Start -->
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <h1 class="text-black-50 h3 pb-2">Datos actividad</h1>
                </div>
            </div>
        </div>

        <div class="text-left">
            <h1 class="text-black-50 h6 pb-2">Los campos señalados con (*)
                son obligatorios</h1>
        </div>

        <label for="Categoria">Categoria(s)*:</label><br>

        <%
            String[] categorias = (String[]) request.getAttribute(RequestKeys.LISTA_CATEGORIAS);
            for (String categoria : categorias) {
        %>
        <input type="checkbox" id="categoria" name="categoria" value="<%= categoria %>">
        <label class="form-group" for="categoria"><%= categoria %>
        </label><br>
        <%
            }
        %>


        <!-- Seleccionar departamento -->
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

        <!-- Ingresar nombre de la actividad turistíca -->
        <label for="nombreActividad">Nombre de la actividad
            turistíca*:</label> <input type="text" class="form-control mb-4"
                                       id="nombreActividad" name="nombreActividad"
                                       placeholder="Mi actividad"/>

        <!-- Ingresar descripcion de la actividad turistíca -->

        <div class="form-group">
            <label for="DescripcionActividad">Descripcion*:</label>
            <textarea class="form-control" name="DescripcionActividad"
                      id="DescripcionActividad" rows="3"></textarea>
        </div>

        <!-- Ingresar duracion de la actividad -->
        <label for="DuracionActividad">Duracion de la actividad (en
            horas)*:</label> <input type="number" class="form-control mb-4"
                                    id="DuracionActividad" name="DuracionActividad"
                                    placeholder="Duracion" min="0"/>

        <!-- Ingresar costo de la actividad -->
        <label for="costoActividad">Costo de la actividad (pesos
            uruguayos)*:</label> <input type="number" class="form-control mb-4"
                                        id="costoActividad" name="costoActividad" placeholder="Costo" min="0"/>

        <!-- Ingresar ciudad de la actividad turistíca -->
        <label for="nombreCiudad">Ciudad de la actividad turistíca*:</label> <input
            type="text" class="form-control mb-4" id="nombreCiudad"
            name="nombreCiudad" placeholder="Ciudad"/>

        <!-- Ingresar imagen de la salida turistíca -->
        <label for="imagenActividad">Imagen:</label> <input type="file"
                                                            class="form-control h-auto p-2 mb-4" id="imagenActividad"
                                                            name="imagenActividad" placeholder="Imagen"
                                                            accept="image/*"/>


        <!-- Ingresar url del video actividad turistíca -->
        <label for="urlVideo">URL video asociado:</label> <input
            type="text" class="form-control mb-4" id="urlVideo"
            name="urlVideo" placeholder="https://youtu.be/dQw4w9WgXcQ"/>

        <%
            if (ErrorHandler.hayErrorEnRequest(request)) {
        %>

        <! -- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> Revise que los campos sean correctos
            <br/>
            <p>
                <%= ErrorHandler.getErrorMessage(request) %>
            </p>
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


<script defer type="text/javascript">

    let checkboxes = document.querySelectorAll('input[name="categoria"]');
    let nombreActividadField = document.getElementById('nombreActividad');
    let descripcionActividadField = document.getElementById('DescripcionActividad');
    let duracionActividadField = document.getElementById('DuracionActividad');
    let costoActividadField = document.getElementById('costoActividad');
    let nombreCiudadField = document.getElementById('nombreCiudad');

    nombreActividadField.addEventListener('input', validarNombreActividad);
    descripcionActividadField.addEventListener('input', validarDescripcionActividad);
    duracionActividadField.addEventListener('input', validarDuracionActividad);
    costoActividadField.addEventListener('input', validarCostoActividad);
    nombreCiudadField.addEventListener('input', validarNombreCiudad);

    nombreActividadField.addEventListener('focusout', validarUnicidadNombreActividad);

    document.getElementById("formAltaActividadTuristica").addEventListener("submit", onSubmit);

    // ---------------------------------------------------------------------------------------------

    function validarNombreActividad() {
        let nombreActividad = nombreActividadField.value;
        if (nombreActividad.length < 1) {
            nombreActividadField.style.borderColor = "red";
            return false;
        }

        // chequear si empieza o termina con espacios
        if (nombreActividad.startsWith(" ") || nombreActividad.endsWith(" ")) {
            nombreActividadField.style.borderColor = "red";
            return false;
        }

        let regex = /<%=
         "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$"
         %>/;
        if (!regex.test(nombreActividad)) {
            nombreActividadField.style.borderColor = "red";
            return false;
        }
        nombreActividadField.style.borderColor = "green";
        
        return true;

    }

    function validarDescripcionActividad() {
        let descripcionActividad = descripcionActividadField.value;
        if (descripcionActividad.length < 1) {
            descripcionActividadField.style.borderColor = "red";
            return false;
        }
        descripcionActividadField.style.borderColor = "green";
        return true;
    }

    function validarDuracionActividad() {
        let duracionActividad = duracionActividadField.value;
        if (duracionActividad.length < 1 || duracionActividad < 0) {
            duracionActividadField.style.borderColor = "red";
            return false;
        }
        duracionActividadField.style.borderColor = "green";
        return true;
    }

    function validarCostoActividad() {
        let costoActividad = costoActividadField.value;

        if (costoActividad.length < 1 || costoActividad < 0) {
            costoActividadField.style.borderColor = "red";
            return false;
        }
        costoActividadField.style.borderColor = "green";
        return true;
    }

    function validarNombreCiudad() {
        let nombreCiudad = nombreCiudadField.value;
        if (nombreCiudad.length < 1) {
            nombreCiudadField.style.borderColor = "red";
            return false;
        }
        nombreCiudadField.style.borderColor = "green";
        return true;
    }

    function validarCheckboxes() {
        for (let i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                return true;
            }
        }
        alert("Tiene que seleccionar al menos una categoria");
        return false;
    }

    function validarUnicidadNombreActividad() {
        var nombreActividad = nombreActividadField.value;
        if (!validarNombreActividad()) {
            nombreActividadField.setCustomValidity("El nombre de la actividad no es valido");
            return;
        }

        var url = "<%= Endpoints.API %>?" + "existeActividadConNombre" + "=" + nombreActividad;
        fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw "Error en la llamada Ajax";
            }
        }).then(function (data) {
            if (data.existeActividadConNombre) {
                // set the border color to red and validitity to false
                nombreActividadField.style.borderColor = "red";
                nombreActividadField.setCustomValidity("El nombre de la actividad ya está en uso");
                alert("El nombre de la actividad ya está en uso");
                nombreActividadField.checkValidity();
            } else {
                nombreActividadField.style.borderColor = "green";
                nombreActividadField.setCustomValidity("");
                nombreActividadField.checkValidity();
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    function validarFormulario() {
        return validarNombreActividad()
            && validarDescripcionActividad()
            && validarDuracionActividad()
            && validarCostoActividad()
            && validarNombreCiudad()
            && validarCheckboxes();
    }

    function onSubmit(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    }

</script>


