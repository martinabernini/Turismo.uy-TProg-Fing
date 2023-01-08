<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Alta de Usuario Start -->
<div class="container-fluid bg-secondary w-75">
    <form class="form-group py-4" enctype="multipart/form-data"
          id="formAltaUsuario"
          action="<%= Endpoints.ALTA_USUARIO %>?tipoUsuario=<%=request.getParameter("tipoUsuario")%>"
          method="post">
        <!-- Section Title Start -->
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <%
                        if (request.getParameter("tipoUsuario").equals("Turista")) {
                    %>
                    <h1 class="text-black-50 h3 pb-2"> Datos turista </h1>
                    <%
                        }
                    %>
                    <%
                        if (request.getParameter("tipoUsuario").equals("Proveedor")) {
                    %>
                    <h1 class="text-black-50 h3 pb-2">Datos proveedor</h1>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>

        <div class="text-left">
            <h1 class="text-black-50 h6 pb-2">Los campos señalados con (*)
                son obligatorios</h1>
        </div>

        <!-- Ingresar nickname del usuario -->
        <label for="nicknameUsuario">Nickname*:</label> <input type="text"
                                                               class="form-control mb-4" id="nicknameUsuario"
                                                               name="nicknameUsuario"
                                                               placeholder="Nickname"/>
        <!-- Ingresar nombre del usuario -->
        <label for="nombreUsuario">Nombre*:</label> <input type="text"
                                                           class="form-control mb-4" id="nombreUsuario"
                                                           name="nombreUsuario"
                                                           placeholder="Nombre"/>
        <!-- Ingresar apellido del usuario -->
        <label for="apellidoUsuario">Apellido*:</label> <input type="text"
                                                               class="form-control mb-4" id="apellidoUsuario"
                                                               name="apellidoUsuario"
                                                               placeholder="Apellido"/>
        <!-- Ingresar email del usuario -->
        <label for="emailUsuario">Email*:</label> <input type="text"
                                                         class="form-control mb-4" id="emailUsuario" name="emailUsuario"
                                                         placeholder="Email"/>

        <!-- Ingresar contraseña del usuario -->
        <label for="contrasenaUsuario">Contraseña*:</label> <input
            type="password" class="form-control mb-4" id="contrasenaUsuario"
            name="contrasenaUsuario" placeholder="Contraseña"/>
        <!-- Ingresar confirmacion contraseña del usuario -->
        <label for="contrasenaUsuarioConfimracion">Repita la
            contraseña*:</label> <input type="password" class="form-control mb-4"
                                        id="contrasenaUsuarioConfimracion"
                                        name="contrasenaUsuarioConfimracion"
                                        placeholder="Ingrese de nuevo la contraseña"/>
        <!-- Ingresar fecha de nacimiento del usuario -->
        <label for="fechaNacimiento">Fecha de nacimiento*:</label> <input
            type="date" class="form-control mb-4" id="fechaNacimiento"
            name="fechaNacimiento" placeholder="Fecha de nacimiento"
    />

        <!-- Ingresar imagen del usuario -->
        <label for="imagenUsuario">Imagen:</label> <input type="file"
                                                          class="form-control h-auto p-2 mb-4" id="imagenUsuario"
                                                          name="imagenUsuario" placeholder="Imagen" accept="image/*"/>

        <%
            if (request.getParameter("tipoUsuario").equals("Turista")) {
        %>

        <!-- Ingresar nacionalidad del usuario -->
        <label for="nacionalidadUsuario">Nacionalidad*:</label> <input
            type="text" class="form-control mb-4" id="nacionalidadUsuario"
            name="nacionalidadUsuario" placeholder="Nacionalidad"/>

        <%
            }
        %>

        <%
            if (request.getParameter("tipoUsuario").equals("Proveedor")) {
        %>

        <!-- Ingresar descripcion general del usuario -->

        <label for="descripcionUsuario">Descripcion General*:</label>
        <textarea class="form-control" name="descripcionUsuario" id="descripcionUsuario" rows="3"></textarea>
        <!--   <input
                type="text"
                class="form-control mb-4"
                id="descripcionUsuario"
                name="descripcionUsuario"
                placeholder="Descripcion general"
        /> -->
        <!-- Ingresar sitio web del usuario -->
        <label for="sitiowebUsuario">Sitio Web:</label> <input type="text"
                                                               class="form-control mb-4" id="sitiowebUsuario"
                                                               name="sitiowebUsuario"
                                                               placeholder="Sitio web"/>
        <%
            }
        %>

        <%
            String signUpError = (String) request.getAttribute("signup-error");

            if (signUpError != null && signUpError.equals("ContrasenaNoCoincide")) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> Las contraseñas no coinciden
        </div>
        <%
            }
        %>

        <%
            if (signUpError != null && signUpError.equals("EntidadRepetida")) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> El nickname o el email ya estan en uso
        </div>

        <%
            }
            if (signUpError != null && signUpError.equals("CampoInvalido")) {
        %>
        <!-- mensaje de error -->
        <div class="alert alert-danger" role="alert">
            <strong>Oh no!</strong> Revise que los campos sean correctos
        </div>

        <%
            }
        %>


        <!-- Enviar -->
        <span class="ml-auto"><input type="submit"
                                     class="btn btn-block btn-primary" id="alta-usuario-form-registar"
                                     value="Registrar"></span>


    </form>
</div>


<script defer type="text/javascript">
    <%
    // este script se podria poner en un archivo aparte y llamarlo desde aca
    // pero como usamos scriptlets para hacer condicionales entre los tipos de usuario
    // mejor lo dejamos aca
    %>

    let nicknameField = document.getElementById("nicknameUsuario");
    let nombreField = document.getElementById("nombreUsuario");
    let apellidoField = document.getElementById("apellidoUsuario");
    let emailField = document.getElementById("emailUsuario");
    let contrasenaField = document.getElementById("contrasenaUsuario");
    let contrasenaConfirmacionField = document.getElementById("contrasenaUsuarioConfimracion");
    let fechaNacimientoField = document.getElementById("fechaNacimiento");

    nicknameField.addEventListener("input", validarNickname);
    emailField.addEventListener("input", validarEmail);
    nombreField.addEventListener("input", validarNombre);
    apellidoField.addEventListener("input", validarApellido);
    contrasenaField.addEventListener("input", validarContrasena);
    contrasenaConfirmacionField.addEventListener("input", validarContrasenaConfirmacion);
    fechaNacimientoField.addEventListener("input", validarFechaNacimiento);

    // validacion de que no hay otro nickname igual
    nicknameField.addEventListener("focusout", validarUnicidadNick);
    emailField.addEventListener("focusout", validarUnicidadEmail);


    <%
        if (request.getParameter("tipoUsuario").equals("Turista")) {
    %>
    document.getElementById("nacionalidadUsuario").addEventListener("input", validarNacionalidad);
    <%
        }
    %>

    <%
        if (request.getParameter("tipoUsuario").equals("Proveedor")) {
    %>
    document.getElementById("descripcionUsuario").addEventListener("input", validarDescripcion);
    <%
        }
    %>

    document.getElementById("formAltaUsuario").addEventListener("submit", onSubmit);

    // ---------------------------------------------------------------------------------------------

    function validarNickname() {
        var nickname = document.getElementById("nicknameUsuario").value;

        if (esNicknameBienFormado(nickname)) {
            nicknameField.style.borderColor = "green";
            nicknameValido = true;
            return true;
        }
        nicknameField.style.borderColor = "red";
        nicknameValido = false;
        return false;
    }

    function esNicknameBienFormado(nickname) {
        var regexNickName = /<%=
        "^[a-zA-Z0-9_\\-\\.\\u00f1\\u00d1\\u00e1\\u00c1\\u00e9\\u00c9\\u00ed" +
            "\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc]+$"
        %>/;
        return regexNickName.test(nickname);
    }

    function validarNombre() {
        var nombre = nombreField.value;
        // regex para validar nombre solo con letras, en español y con espacios entre medio
        var regex = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;
        if (regex.test(nombre)) {
            nombreField.style.borderColor = "green";
            return true;
        }
        nombreField.style.borderColor = "red";
        return false;
    }

    function validarApellido() {
        var apellido = document.getElementById("apellidoUsuario").value;
        // regex para validar apellido solo con letras, en español y con espacios entre medio
        var regex = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;
        if (regex.test(apellido)) {
            apellidoField.style.borderColor = "green";
            return true;
        }
        apellidoField.style.borderColor = "red";
        return false;
    }

    function validarEmail() {
        var email = document.getElementById("emailUsuario").value;

        if (esEmailBienFormado(email)) {
            emailField.style.borderColor = "green";
            return true;
        }
        emailField.style.borderColor = "red";
        return false;
    }

    function esEmailBienFormado(email) {

        var regexEmail = /<%= "^[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+@[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+\\.[A-Za-z0-9" +
            "._-áéíóúñÁÉÍÓÚÑ]+$" %>/;

            console.log("dasdsadas " + regexEmail);
        console.log("asdasdasopjp -- " + regexEmail.test(email));
        return regexEmail.test(email);
    }

    function validarContrasena() {
        // contraseña debe tener al menos 1 caracter
        var contrasena = contrasenaField.value;
        if (contrasena.length > 0) {
            contrasenaField.style.borderColor = "green";
            return true;
        }
        contrasenaField.style.borderColor = "red";
        return false;
    }

    function validarContrasenaConfirmacion() {
        // validar que las contraseñas coincidan
        var contrasena = contrasenaField.value;
        var contrasenaConfirmacion = contrasenaConfirmacionField.value;
        if (contrasena === contrasenaConfirmacion && contrasena.length > 0) {
            contrasenaField.style.borderColor = "green";
            contrasenaConfirmacionField.style.borderColor = "green";
            return true;
        }
        contrasenaField.style.borderColor = "red";
        contrasenaConfirmacionField.style.borderColor = "red";
        return false;
    }

    function validarFechaNacimiento() {
        // validar que la fecha de nacimiento sea menor a la fecha actual
        var fechaNacimiento = fechaNacimientoField.value;
        var fechaActual = new Date();
        var fechaNacimientoDate = new Date(fechaNacimiento);

        if (!(fechaNacimientoDate instanceof Date && !isNaN(fechaNacimientoDate))) {
            fechaNacimientoField.style.borderColor = "red";
            return false;
        }
        if (fechaNacimientoDate >= fechaActual) {
            fechaNacimientoField.style.borderColor = "red";
            return false;
        }
        fechaNacimientoField.style.borderColor = "green";
        return true;
    }

    function validarNacionalidad() {
        var nacionalidad = document.getElementById("nacionalidadUsuario").value;
        // regex para validar nacionalidad solo con letras, en español y con espacios entre medio
        var regex = /<%=
            "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$"
        %>/;
        if (regex.test(nacionalidad) && nacionalidad.length > 0) {
            document.getElementById("nacionalidadUsuario").style.borderColor = "green";
            return true;
        }
        document.getElementById("nacionalidadUsuario").style.borderColor = "red";
        return false;
    }

    function validarUnicidadNick() {
        // check in the api if the nickname is already taken
        var nickname = nicknameField.value;
        if (!esNicknameBienFormado(nickname)) {
            nicknameField.setCustomValidity("El nickname no es valido");
        } else {
            var url = "<%= Endpoints.API %>?" + "estaEnUsoNick" + "=" + nickname;
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
                if (data.estaEnUsoNick) {
                    // set the border color to red and validitity to false
                    nicknameField.style.borderColor = "red";
                    nicknameField.setCustomValidity("El nickname ya está en uso");
                    alert("El nickname ya está en uso");
                    emailField.checkValidity();
                } else {
                    nicknameField.style.borderColor = "green";
                    nicknameField.setCustomValidity("");
                    nicknameField.checkValidity();
                }
            }).catch(function (error) {
                console.log(error);
            });
        }
    }

    function validarUnicidadEmail() {
        var email = emailField.value;
        if (!esEmailBienFormado(email)) {
            emailField.setCustomValidity("El email no es valido");
            return;
        }
        var url = "<%= Endpoints.API %>?" + "estaEnUsoEmail" + "=" + email;
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
            if (data.estaEnUsoEmail) {
                // set the border color to red and validitity to false
                emailField.style.borderColor = "red";
                emailField.setCustomValidity("El email ya está en uso");
                // show validation message to the user
                alert("El email ya está en uso");
                emailField.checkValidity();

            } else {
                emailField.style.borderColor = "green";
                emailField.setCustomValidity("");
                emailField.checkValidity();
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    function validarDescripcion() {
        var descripcion = document.getElementById("descripcionUsuario").value;
        // regex para validar descripcion solo con letras, en español y con espacios entre medio
        if (descripcion.length > 0) {
            document.getElementById("descripcionUsuario").style.borderColor = "green";
            return true;
        }
        document.getElementById("descripcionUsuario").style.borderColor = "red";
        return false;
    }


    function onSubmit(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    }

    function validarFormulario() {
        var esValidoCompartido = validarNickname() &&
            validarNombre() &&
            validarApellido() &&
            validarEmail() &&
            validarContrasena() &&
            validarContrasenaConfirmacion() &&
            validarFechaNacimiento();

        <%
            String tipoUsuario = (String) request.getParameter("tipoUsuario");
            if (tipoUsuario != null && tipoUsuario.equals("Turista")) {
        %>
        return esValidoCompartido && validarNacionalidad();
        <%
            }
        %>
        <%
            if (tipoUsuario !=null && tipoUsuario.equals("Proveedor")) {
        %>
        return esValidoCompartido && validarDescripcion();
        <%
            }
        %>
    }

</script>