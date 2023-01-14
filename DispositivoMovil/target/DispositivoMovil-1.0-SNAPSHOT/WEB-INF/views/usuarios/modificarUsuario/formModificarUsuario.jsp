
<%@page import="webservices.DtProveedor"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtTurista"%>
<%@ page import="com.helpers.Endpoints" %>
<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@ page import="com.helpers.EstadoSesionHelper" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="container-fluid bg-secondary w-75">
    <form
            id="formModificarUsuario"
            class="form-group py-4"
            enctype="multipart/form-data"
            action="<%= Endpoints.MODIFICAR_USUARIO %>"
            method="post"
    >

        <%
            DtUsuario usuario = (DtUsuario) request.getAttribute(RequestKeys.USUARIO_A_MODIFICAR);
        %>

        <div class="row">
            <img style="width: 16.149rem; height: 13rem;" src="<%= usuario.getImagen() %>" alt="Image"/>
        </div>

        <div class="text-left">
            <h1 class="text-black-50 h6 pb-2">Los campos señalados con (*) son obligatorios</h1>
        </div>

        <!-- Ingresar nombre del usuario -->
        <label for="nombre">Nombre*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="nombre"
                name="nombre"
                placeholder="Nombre"
                value="<%= usuario.getNombre() %>"
        />
        <!-- Ingresar apellido del usuario -->
        <label for="apellido">Apellido*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="apellido"
                name="apellido"
                placeholder="Apellido"
                value="<%= usuario.getApellido() %>"
        />

        <%
            if (usuario instanceof DtTurista) {
        %>
        <!-- Ingresar nacionalidad si es turista -->
        <label for="nacionalidad">Nacionalidad*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="nacionalidad"
                name="nacionalidad"
                value="<%= ((DtTurista) usuario).getNacionalidad() %>"
                placeholder="Nacionalidad"
        />
        <%
            }
        %>

        <%
            if (usuario instanceof DtProveedor) {
        %>
        <!-- Ingresar descripcion si es proveedor -->
        <label for="descripcion">Descripción*:</label>
        <textarea
                type="text"
                class="form-control mb-4"
                id="descripcion"
                name="descripcion"
                placeholder="Nacionalidad"
        ><%= ((DtProveedor) usuario).getDescripcion() %></textarea>

        <!-- Ingresar sitio web si es proveedor -->
        <label for="sitioWeb">Sitio Web*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="sitioWeb"
                name="sitioWeb"
                value="<%= ((DtProveedor) usuario).getUrlSitioWeb() %>"
                placeholder="Sitio Web"
        />
        <%
            }
        %>
        <!-- Ingresar contraseña del usuario -->
        <label for="password">Contraseña*:</label>
        <input
                type="password"
                class="form-control mb-4"
                id="password"
                name="password"
                placeholder="Contraseña"
                value=""
        />
        <!-- Ingresar fecha de nacimiento del usuario -->
        <label for="fechaNacimiento">Fecha de nacimiento*:</label>
        <input
                type="date"
                class="form-control mb-4"
                id="fechaNacimiento"
                name="fechaNacimiento"
                placeholder="Fecha de nacimiento"
                <%
                    //TODO implementar bien la fecha de nacimiento
                %>
                value="1927-02-23"
        />

        <!-- Ingresar imagen del usuario -->
        <label for="imagenUsuario">Imagen:</label>
        <input type="file" class="form-control h-auto p-2 mb-4" id="imagenUsuario"
               name="imagenUsuario" placeholder="Imagen" accept="image/*"
        />


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
        <span class="ml-auto">
            <input type="submit" class="btn btn-block btn-primary" id="modificarUsuario" value="Modificar">
        </span>
    </form>
</div>

<script defer type="text/javascript">
    let nombreField = document.getElementById("nombre");
    let apellidoField = document.getElementById("apellido");
    let fechaNacimientoField = document.getElementById("fechaNacimiento");

    let modificarUsuarioButton = document.getElementById("modificarUsuario");

    nombreField.addEventListener("input", validarNombre);
    apellidoField.addEventListener("input", validarApellido);
    fechaNacimientoField.addEventListener("input", validarFechaNacimiento);

    <%
        if (EstadoSesionHelper.hayProveedorLogueado(request)) {
    %>
    let descripcionField = document.getElementById("descripcion");
    descripcionField.addEventListener("input", validarDescripcion);

    function validarDescripcion () {
        if (descripcionField.value.length > 0) {
            descripcionField.classList.remove("is-invalid");
            descripcionField.classList.add("is-valid");
        } else {
            descripcionField.classList.remove("is-valid");
            descripcionField.classList.add("is-invalid");
        }
    }

    <%
        }
    %>

    <%
        if (EstadoSesionHelper.hayTuristaLogueado(request)) {
    %>
    let nacionalidadField = document.getElementById("nacionalidad");
    nacionalidadField.addEventListener("input", validarNacionalidad);

    function validarNacionalidad() {
        var nacionalidad = nacionalidadField.value;
        // regex para validar nacionalidad solo con letras, en español y con espacios entre medio
        var regex = /<%=
            "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$"
        %>/;
        if (regex.test(nacionalidad) && nacionalidad.length > 0) {
            nacionalidadField.style.borderColor = "green";
            return true;
        } else {
            nacionalidadField.style.borderColor = "red";
            return false;
        }
         
        return true;
        <%
           }
        %>
    }

    document.getElementById("formModificarUsuario").addEventListener("submit", onSubmit);

    // -----------------------------------------------------------------

    function validarNombre() {
        var nombre = nombreField.value;
        // regex para validar nombre solo con letras, en español y con espacios entre medio
        var regex = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;
        if (regex.test(nombre)) {
            nombreField.style.borderColor = "green";
            return true;
        } else {
            nombreField.style.borderColor = "red";
            return false;
        }
    }

    function validarApellido() {
        var apellido = apellidoField.value;
        // regex para validar apellido solo con letras, en español y con espacios entre medio
        var regex = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;
        if (regex.test(apellido)) {
            apellidoField.style.borderColor = "green";
            return true;
        } else {
            apellidoField.style.borderColor = "red";
            return false;
        }
    }

    function validarFechaNacimiento() {
        // validar que la fecha de nacimiento sea menor a la fecha actual
        var fechaNacimiento = fechaNacimientoField.value;
        var fechaActual = new Date();
        var fechaNacimientoDate = new Date(fechaNacimiento);

        if (fechaNacimientoDate instanceof Date && !isNaN(fechaNacimientoDate)) {
            if (fechaNacimientoDate < fechaActual) {
                fechaNacimientoField.style.borderColor = "green";
                return true;
            } else {
                fechaNacimientoField.style.borderColor = "red";
                return false;
            }
        } else {
            fechaNacimientoField.style.borderColor = "red";
            return false;
        }
    }

    function validarFormulario() {
        var esValidoCompartido =
            validarNombre() &&
            validarApellido() &&
            validarFechaNacimiento();
        <%
            if (EstadoSesionHelper.hayTuristaLogueado(request)) {
        %>
        return esValidoCompartido && validarNacionalidad();
        <%
            }
        %>
        <%
            if (EstadoSesionHelper.hayProveedorLogueado(request)) {
        %>
        return esValidoCompartido && validarDescripcion();
        <%
            }
        %>
    }

    function onSubmit(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    }


</script>