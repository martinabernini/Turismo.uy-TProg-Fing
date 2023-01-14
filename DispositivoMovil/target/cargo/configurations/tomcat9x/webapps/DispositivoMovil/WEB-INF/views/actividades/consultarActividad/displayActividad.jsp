<%@page import="java.util.List"%>
<%@page import="webservices.DtActividadTuristica"%>
<%@page import="com.helpers.RequestKeys" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    DtActividadTuristica actividadConsulta = (DtActividadTuristica) request.getAttribute(RequestKeys.ACTIVIDAD_CONSULTA);

	String img = actividadConsulta.getImagen();
	String nombre = actividadConsulta.getNombre();
	String descripcion= actividadConsulta.getDescripcion();
	String duracion = String.valueOf(actividadConsulta.getDuracionHrs());
	String costo = "$" + String.valueOf(actividadConsulta.getCostoPorPersona());
	String departamento = actividadConsulta.getDepartamento();
	String ciudad = actividadConsulta.getCiudad();
	String fecha = actividadConsulta.getFechaAlta();
	
	List<String> categorias = actividadConsulta.getCategorias();
	String cats = "";
	for (String cate: categorias){
		cats= cats + cate + ", ";
	}
	cats =  (cats.subSequence(0, cats.length() - 2).toString()) + ".";
    

%>

<jsp:include page="actividad.jsp">
    <jsp:param name="img" value="<%= img %>"/>
    <jsp:param name="nombre" value="<%= nombre %>"/>
    <jsp:param name="descripcion" value="<%= descripcion %>"/>
    <jsp:param name="duracion" value="<%= duracion %>"/>
    <jsp:param name="costo" value="<%= costo %>"/>
    <jsp:param name="departamento" value="<%= departamento %>"/>
    <jsp:param name="ciudad" value="<%= ciudad %>"/>
    <jsp:param name="fecha" value="<%= fecha %>"/>
    <jsp:param name="cats" value="<%= cats %>"/>
</jsp:include>

	
	 
	
	
	