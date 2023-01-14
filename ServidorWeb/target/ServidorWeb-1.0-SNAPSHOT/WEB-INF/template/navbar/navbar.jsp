<%@ page import="webservices.NoHayEntidadesParaListarException_Exception" %>
<%@ page import="com.helpers.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Topbar Start -->
<div class="container-fluid">
    <!--    No se muestra esto pero queda por si se quiere agregar algo-->
    <div class="row  py-1 px-xl-5">
        <div class="col-lg-6 d-none d-lg-block"></div>
        <div class="col-lg-6 text-center text-lg-right">
            <div class="d-inline-flex align-items-center"></div>
        </div>
    </div>

    <!-- Titulo y barra de busqueda -->
    <div class="row align-items-center px-xl-5 py-1 ">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="<%= Endpoints.HOME %>" class="text-decoration-none">
                <h1 class="m-0 display-5 font-weight-semi-bold">
                    <span class="text-primary font-weight-bold px-2 ">Turismo</span>Uy
                </h1>
            </a>
        </div>
        <div class="col-lg-6 col-12 text-left w-100">
            <form action="">
                <div class="input-group">
                    <input type="text" class="form-control"
                           placeholder="Actividades turísticas, paquetes"/>
                    <div class="input-group-append">
						<span class="input-group-text bg-transparent text-primary">
							<i class="fa fa-search"></i>
						</span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right"></div>
    </div>
</div>
<!-- Topbar End -->

<!-- Navbar Start -->
<div class="container-fluid mb-3">
    <div class="row pt-1 px-xl-5  bg-light">

        <div class="col-lg-11">
            <nav class="navbar navbar-expand-lg navbar-light py-3 py-lg-0 px-0">
                <a href="<%= Endpoints.HOME %>" class="text-decoration-none d-block d-lg-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold">
                        <span class="text-primary font-weight-bold px-2 ">Turismo</span>Uy
                    </h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse"
                        data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between"
                     id="navbarCollapse">
                    <a class="navbar-nav mr-auto  py-0"> <a href="<%= Endpoints.HOME %>"
                                                            class="nav-item nav-link active d-none">Inicio</a>

                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">Usuarios
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%= Endpoints.CONSULTAR_USUARIOS %>">Consultar
                                    usuario</a>
                                <%
                                    if (EstadoSesionHelper.hayUsuarioLogueado(request)) {
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.MODIFICAR_USUARIO %>">Modificar
                                    usuario</a>
                                <%
                                    }
                                %>
                            </div>
                        </div>


                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">Actividades
                            </button>
                            <div class="dropdown-menu">
                                <%
                                    if (EstadoSesionHelper.hayProveedorLogueado(request)) {
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.ALTA_ACTIVIDAD %>">Alta de
                                    actividad</a>
                                <%
                                    }
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.CONSULTAR_ACTIVIDADES %>">Consultar
                                    actividad</a>
                            </div>
                        </div>

                        <div class="btn-group">
                            <button type="button"
                                    class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false"
                            >
                                Salidas
                            </button>
                            <div class="dropdown-menu">
                                <%
                                    if (EstadoSesionHelper.hayProveedorLogueado(request)) {
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.ALTA_SALIDA %>">Alta de salida</a>
                                <%
                                    }
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.CONSULTAR_SALIDAS %>">Consultar salida</a>
                                <%
                                    if (EstadoSesionHelper.hayTuristaLogueado(request)) {
                                %>
                                <a class="dropdown-item"
                                   href="<%= Endpoints.INSCRIPCION_SALIDA_TURISTICA %>">Inscripcion a salida</a>
                                <%
                                    }
                                %>
                            </div>
                        </div>

                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">Paquetes
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%=Endpoints.CONSULTAR_PAQUETES %>">Consultar paquete</a>
                                <%
                                    if (EstadoSesionHelper.hayTuristaLogueado(request)) {
                                %>
                                <a class="dropdown-item" href="<%= Endpoints.COMPRA_PAQUETE %>">Comprar paquete</a>
                                <%
                                    }
                                %>

                            </div>
                        </div>

                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">Categorias
                            </button>
                            <div class="dropdown-menu">
                                <%
                                    try {
                                        String[] categorias =
                                                FabricaWS.getInstance().getControladorActividad().listarTodasLasCategorias().getLista().toArray(new String[0]);
                                        for (String categoria : categorias) {
                                %>
                                <a class="dropdown-item"
                                   href="<%= Endpoints.CONSULTAR_ACTIVIDADES %>?categoria=<%= categoria %>">
                                    <%= categoria %>
                                </a>
                                <%
                                        }
                                    } catch (NoHayEntidadesParaListarException_Exception e) {
                                %>
                                <a class="dropdown-item"> No hay categorias </a>
                                <%
                                    }
                                 %>

                            </div>
                        </div>

                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">Departamento
                            </button>

                            <div class="dropdown-menu mr-2rem">
                                <%
                                    try {

                                        String[] departamentos =
                                                FabricaWS.getInstance().getControladorDepartamento().listarDepartamentos().getLista().toArray(new String[0]);
                                        for (String dpto : departamentos) {
                                %>
                                <a class="dropdown-item"
                                   href="<%= Endpoints.CONSULTAR_ACTIVIDADES %>?departamento=<%= dpto %>"><%= dpto %>
                                </a>
                                <%
                                        }
                                    } catch (NoHayEntidadesParaListarException_Exception e) {
                                %>
                                <a class="dropdown-item"> No hay departamentos </a>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </a>
                    <!-- separador -->
                    <div class="dropdown-divider d-block d-lg-none"></div>

                    <!-- separador pantalla grande -->
                    <!-- if usuario logueado muestra navLoggeado, sino muestra navNoLoggeado JSP -->

                    <%
                        if (EstadoSesionHelper.hayUsuarioLogueado(request)) {
                    %>
                    <jsp:include page="/WEB-INF/template/navbar/navLogueado.jsp">
                        <jsp:param name="profilePic"
                                   value="${sessionScope.get(SessionKeys.USUARIO_LOGUEADO).getImagen()}"/>
                        <jsp:param name="nickname"
                                   value="${sessionScope.get(SessionKeys.USUARIO_LOGUEADO).getNickname()}"/>
                    </jsp:include>
                    <%
                    } else {
                    %>
                    <jsp:include page="/WEB-INF/template/navbar/navNoLogueado.jsp"/>
                    <%
                        }
                    %>

                </div>
            </nav>
        </div>
    </div>
</div>
<!-- Navbar End -->