<%@ page import="com.helpers.RequestKeys" %>
<%@ page import="com.helpers.Endpoints" %>
<%@ page import="com.helpers.ErrorHandler" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<!-- Alta Salida Turistica Start -->
<div class="container-fluid bg-secondary w-75">
    <form
            id="formAltaSalida"
            class="form-group py-4"
            enctype="multipart/form-data"
            action="<%= Endpoints.ALTA_SALIDA %>"
            method="post"
    >
        <!-- Section Title Start -->
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <h1 class="text-black-50 h3 pb-2">Datos salida turística</h1>
                </div>
            </div>
        </div>
        <%
            if (ErrorHandler.hayErrorEnRequest(request)) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> No se puedo dar de alta la salida: <%= ErrorHandler.getErrorMessage(request) %>
        </div>
        <%
            }
        %>

        <label for="nombreActividad">Actividades Turísticas:</label>
        <select class="form-control mb-4" name="nombreActividad" id="nombreActividad">
            <%
                String[] actividades = (String[]) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES);
                for (String act : actividades) {
            %>
            <option><%= act %>
            </option>
            <%
                }
            %>

        </select>


        <!-- Ingresar nombre de la actividad turistíca -->
        <label for="nombreSalida">Nombre de la salida Turística*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="nombreSalida"
                name="nombreSalida"
                placeholder="Mi Salida"
        />

        <!-- Ingresar la fecha de la salida turistica -->
        <label for="fechaSalida">Fecha de la salida turística*:</label> <input
            type="datetime-local" class="form-control mb-4" id="fechaSalida"
            name="fechaSalida" placeholder="Fecha de Salida"
    />


        <!-- Ingresar lugar de la salida turistica -->
        <label for="lugarSalida">Lugar de salida*:</label>
        <input
                type="text"
                class="form-control mb-4"
                id="lugarSalida"
                name="lugarSalida"
                placeholder="lugarSalida"
        />

        <!-- Ingresar cantidad maxima de turistas -->
        <label for="cantTuristas">Cantidad Máxima de Turistas*:</label>
        <input
                type="number"
                class="form-control mb-4"
                id="cantTuristas"
                name="cantTuristas"
                placeholder="Cantidad de turistas"
        />

        <!-- Ingresar imagen de la salida turistíca -->
        <label for="imagenSalida">Imagen:</label>
        <input
                type="file"
                class="form-control h-auto p-2 mb-4"
                id="imagenSalida"
                name="imagenSalida"
                placeholder="Imagen"
                accept="image/*"
        />

        <!-- Enviar -->
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary" id="alta-salida-form-registar"
                    value="Registrar">Ingresar
            </button>
        </div>
    </form>
</div>


<script defer type="text/javascript">

    let nombreSalidaField = document.getElementById("nombreSalida");
    let fechaSalidaField = document.getElementById("fechaSalida");
    let lugarSalidaField = document.getElementById("lugarSalida");
    let cantTuristasField = document.getElementById("cantTuristas");

    nombreSalidaField.addEventListener("change", validarNombreSalida);
    fechaSalidaField.addEventListener("change", validarFechaSalida);
    lugarSalidaField.addEventListener("change", validarLugarSalida);
    cantTuristasField.addEventListener("change", validarCantTuristas);

    nombreSalidaField.addEventListener("focusout", validarUnicidadNombreSalida);

    document.getElementById("formAltaSalida").addEventListener("submit", onSubmit);


    // ---------------------------------------------------------

    function validarNombreSalida() {
        let nombreSalida = nombreSalidaField.value;
        if (nombreSalida.length < 0) {
            nombreSalidaField.style.borderColor = "red";
            return false;
        }

        // chequear si empieza o termina con espacios
        if (nombreSalida.startsWith(" ") || nombreSalida.endsWith(" ")) {
            nombreSalidaField.style.borderColor = "red";
            return false;
        }

        let regex = /<%=
        "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$"
        %>/;
        if (!regex.test(nombreSalida)) {
            nombreSalidaField.style.borderColor = "red";
            return false;
        }

        nombreSalidaField.style.borderColor = "green";
        return true;
    }

    function validarFechaSalida() {
        let fechaSalida = fechaSalidaField.value;
        let fechaActual = Date.now();
        let fechaSalidaDate = new Date(fechaSalida);

        if (fechaSalidaDate < fechaActual) {
            fechaSalidaField.style.borderColor = "red";
            return false;
        }

        fechaSalidaField.style.borderColor = "green";
        return true;
    }


    function validarLugarSalida() {
        let lugarSalida = lugarSalidaField.value;
        if (lugarSalida.length < 0) {
            lugarSalidaField.style.borderColor = "red";
            return false;
        }

        // chequear si empieza o termina con espacios
        if (lugarSalida.startsWith(" ") || lugarSalida.endsWith(" ")) {
            lugarSalidaField.style.borderColor = "red";
            return false;
        }

        let regex = /<%=
            "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$"
        %>/;
        if (!regex.test(lugarSalida)) {
            lugarSalidaField.style.borderColor = "red";
            return false;
        }

        lugarSalidaField.style.borderColor = "green";
        return true;

    }

    function validarCantTuristas() {
        let cantTuristas = cantTuristasField.value;
        if (cantTuristas <  0) {
            cantTuristasField.style.borderColor = "red";
            return false;
        }
        cantTuristasField.style.borderColor = "green";
        return true;
    }

    function validarUnicidadNombreSalida() {
        var nombreSalida = nombreSalidaField.value;
        if (!validarNombreSalida()) {
            nombreSalidaField.setCustomValidity("El nombre de la salida no es válido");
            return;
        }

        var url = "<%= Endpoints.API %>?" + "existeSalidaConNombre" + "=" + nombreSalida;
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
            if (data.existeSalidaConNombre) {
                // set the border color to red and validitity to false
                nombreSalidaField.style.borderColor = "red";
                nombreSalidaField.setCustomValidity("El nombre de la salida ya existe");
                alert("El nombre de la salida ya existe");
                nombreSalidaField.checkValidity();
            } else {
                nombreSalidaField.style.borderColor = "green";
                nombreSalidaField.setCustomValidity("");
                nombreSalidaField.checkValidity();
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    function validarFormulario() {
        return validarNombreSalida()
            && validarFechaSalida()
            && validarLugarSalida()
            && validarCantTuristas();
    }

    function onSubmit(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    }


</script>