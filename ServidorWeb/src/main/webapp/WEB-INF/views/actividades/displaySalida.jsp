<%@page import="webservices.DtSalidaTuristica"%>
<%@page import="org.joda.time.LocalDateTime"%>
<%@page import="org.joda.time.LocalDate"%>
<%@page import="com.helpers.RequestKeys" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    DtSalidaTuristica salidaConsulta = (DtSalidaTuristica) request.getAttribute(RequestKeys.SALIDA_CONSULTA);

	String nombreAct = salidaConsulta.getNombreActividad();
	String nombreSal = salidaConsulta.getNombreSalida();
	int cantidad = salidaConsulta.getCantidadMaximaTuristas();
	String fechaAlta = salidaConsulta.getFechaAlta().toString();
	String fechaSalida = salidaConsulta.getFechaSalida().toString();
	String lugar = salidaConsulta.getLugarSalida();
	String img = salidaConsulta.getImagen();

%>

<jsp:include page="salida.jsp">
    <jsp:param name="img" value="<%= img %>"/>
    <jsp:param name="nombreSal" value="<%= nombreSal %>"/>
    <jsp:param name="nombreAct" value="<%= nombreAct %>"/>
    <jsp:param name="cantidad" value="<%= cantidad %>"/>
    <jsp:param name="lugar" value="<%= lugar %>"/>
    <jsp:param name="fechaAlta" value="<%= fechaAlta %>"/>
    <jsp:param name="fechaSalida" value="<%= fechaSalida %>"/>
</jsp:include>

	
	 
	
	
	