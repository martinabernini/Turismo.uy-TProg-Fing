<%@page import="java.util.List"%>
<%@page import="webservices.DtPaqueteActividades"%>
<%@page import="com.helpers.ImagePathHelper"%>
<%@page import="com.helpers.RequestKeys" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<%		 DtPaqueteActividades paqueteConsulta = (DtPaqueteActividades) request.getAttribute(RequestKeys.PAQUETE_CONSULTA);
	
	String img = ImagePathHelper.conPrefijo(paqueteConsulta.getImagen());
	String nombre = paqueteConsulta.getNombre();
	String descripcion = paqueteConsulta.getDescripcion();
	String validez = String.valueOf(paqueteConsulta.getValidezEnDias());
	
	int desc = (int) (paqueteConsulta.getDescuento()*100);
	String descuento = String.valueOf(desc) + "%";
	
	String fechaAlta = paqueteConsulta.getFechaAlta().toString(); 
	
	List<String> categorias = paqueteConsulta.getCategorias();
	String cats = "";
	for (String cate: categorias){
		cats= cats + cate + ", ";
	}
	cats =  (cats.subSequence(0, cats.length() - 2).toString()) + ".";
	

%>	



<jsp:include page="paquete.jsp">
    <jsp:param name="img" value="<%= img %>"/>
    <jsp:param name="nombre" value="<%= nombre %>"/>
    <jsp:param name="descripcion" value="<%= descripcion %>"/>
    <jsp:param name="validez" value="<%= validez %>"/>
    <jsp:param name="descuento" value="<%= descuento %>"/>
    <jsp:param name="fechaAlta" value="<%= fechaAlta %>"/>
    <jsp:param name="cats" value="<%= cats %>"/>
</jsp:include>

	
	 
	
	
	