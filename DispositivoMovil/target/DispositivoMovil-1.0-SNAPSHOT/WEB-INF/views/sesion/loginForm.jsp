<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="contents order-2 order-md-1">
    <div class="contents order-2 order-md-1">
        <div class="container col-6">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <form action="#" method="post">

                        <div class="form-group first">
                            <label for="login-form-nickname-email">Nickname o email</label> <input
                                type="text" class="form-control" id="login-form-nickname-email"
                                name="login-form-nickname-email">

                        </div>
                        <div class="form-group last mb-3">
                            <label for="login-form-password">Contraseña</label> <input type="password"
                                                                                       class="form-control"
                                                                                       id="login-form-password"
                                                                                       name="login-form-password">

                        </div>

                        <div class="d-flex mb-2 align-items-center">
                            <label class="control control--checkbox mb-0"><span
                                    class="caption">Recuerdame</span> <input type="checkbox"
                                                                             checked="checked"/>
                                <div class="control__indicator"></div>
                            </label>
                        </div>

                        <%
                            if ((boolean) request.getAttribute("login-error")) {
                        %>
                        <!-- mensaje de error -->
                        <div class="alert alert-danger" role="alert">
                            <strong>Oh no!</strong> El usuario o la contraseña son incorrectos.
                            Intenta de nuevo.
                        </div>
                        <%
                            } else {
                        %>

                        <%
                            if ((boolean) request.getAttribute("login-error-proveedor")) {
                        %>
                        <!-- mensaje de error -->
                        <div class="alert alert-danger" role="alert">
                            <strong>Oh no!</strong> Parece que estas intentando ingresar con un proveedor.
                                Acceso restringido a turistas.
                        </div>
                        <%
                            }
                        }
                        %>

                       


                        <div class="text-center mt-4">
								<span class="ml-auto">
								<input type="submit" class="btn btn-block btn-primary" id="login-form-iniciar-sesion"
                                       value="Iniciar sesion"></span>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script defer>

    // add event listener to the form on change
    let nicknameEmailField = document.getElementById("login-form-nickname-email");
    let passwordField = document.getElementById("login-form-password");
    let loginButton = document.getElementById("login-form-iniciar-sesion");

    nicknameEmailField.addEventListener("change", validarNickEmail);
    passwordField.addEventListener("change", validarPassword);
    loginButton.addEventListener("click", onSubmit);

    function validarNickEmail() {
    	
    	
    	//TODO
    	
        let nickEmail = nicknameEmailField.value;

        // regex para validar email y nickname
        let regexNick = /<%= "^[a-zA-Z0-9_\\-\\.\\u00f1\\u00d1\\u00e1\\u00c1\\u00e9\\u00c9\\u00ed" +
            "\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc]+$" %>/;
            let
        regexEmail = /<%= "^[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+@[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+\\.[A-Za-z0-9" +
            "._-áéíóúñÁÉÍÓÚÑ]+$" %>/;

        if (nickEmail.length === 0
            || !nickEmail.trim()
            || (!regexNick.test(nickEmail) && !regexEmail.test(nickEmail))
        ) {
            nicknameEmailField.style.borderColor = "red";
            return false;
        }
        nicknameEmailField.style.borderColor = "green";

        return true; 
    }

    function validarPassword() {
        let password = passwordField.value;

        if (password.length === 0 || !password.trim()) {
            passwordField.style.borderColor = "red";
            return false;
        }
        passwordField.style.borderColor = "green";
        return true;
    }

    function onSubmit(event) {
        if (!validarNickEmail() || !validarPassword()) {
            event.preventDefault();
        }
    }


</script>