<%@page import="webservices.DtSalidaTuristica"%>
<%@page import="com.helpers.RequestKeys" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
	DtSalidaTuristica salidaConsulta = (DtSalidaTuristica) request.getAttribute(RequestKeys.SALIDA_CONSULTA);

	String img = salidaConsulta.getImagen();
	
	String nombre = salidaConsulta.getNombreSalida();

	String fechaSalida = "";
	String fecha = salidaConsulta.getFechaSalida();
	String[] fechaArray = fecha.split("T");
	fechaSalida = fechaArray[0] + " a las " + fechaArray[1];
    String lugar= salidaConsulta.getLugarSalida();
    String cantInscriptos = String.valueOf(salidaConsulta.getCantidadMaximaTuristas());

%>

<jsp:include page="salida.jsp">
    <jsp:param name="img" value="<%= img %>"/>
    <jsp:param name="nombre" value="<%= nombre %>"/>
    <jsp:param name="fechaSalida" value="<%= fechaSalida %>"/>
    <jsp:param name="lugar" value="<%= lugar %>"/>
    <jsp:param name="cantInscriptos" value="<%= cantInscriptos %>"/>
</jsp:include>

	
	 
	
	
	