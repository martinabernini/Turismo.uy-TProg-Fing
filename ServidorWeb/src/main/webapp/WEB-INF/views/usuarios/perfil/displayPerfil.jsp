<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtProveedor"%>
<%@page import="webservices.DtTurista"%>
<%@ page import="org.joda.time.LocalDate" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    DtUsuario usuarioConsulta = (DtUsuario) request.getAttribute("usuarioConsultar");
    String img = usuarioConsulta.getImagen();
    String nickname = usuarioConsulta.getNickname();
    String nombre = usuarioConsulta.getNombre();
    String apellido = usuarioConsulta.getApellido();
    String email = usuarioConsulta.getEmail();

    String tipo = "";
    String descripcion = "No hay descripcion";
    String url = "";
    String nacionalidad = "";
    if (usuarioConsulta instanceof DtTurista) {
        tipo = "Turista";
        DtTurista tur = (DtTurista) usuarioConsulta;
        nacionalidad = tur.getNacionalidad();
    } else {
        tipo = "Proveedor";
        DtProveedor prov = (DtProveedor) usuarioConsulta;
        descripcion = prov.getDescripcion();
        url = prov.getUrlSitioWeb();

    }
%>

<jsp:include page="perfilBasico.jsp">
    <jsp:param name="tipo" value="<%= tipo %>"/>
    <jsp:param name="img" value="<%= img %>"/>
    <jsp:param name="nickname" value="<%= nickname %>"/>
    <jsp:param name="nombre" value="<%= nombre %>"/>
    <jsp:param name="apellido" value="<%= apellido %>"/>
    <jsp:param name="email" value="<%= email %>"/>
    <jsp:param name="fecha" value="<%= usuarioConsulta.getFechaNacimiento() %>"/>
    <jsp:param name="descripcion" value="<%= descripcion %>"/>
    <jsp:param name="url" value="<%= url %>"/>
    <jsp:param name="nacionalidad" value="<%= nacionalidad %>"/>

</jsp:include>

<jsp:include page="carrouselUsuarios.jsp"/>
	
	
	
	