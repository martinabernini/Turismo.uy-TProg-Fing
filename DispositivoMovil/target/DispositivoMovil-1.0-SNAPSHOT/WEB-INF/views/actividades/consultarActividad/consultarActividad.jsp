<%@ page import="com.helpers.SessionKeys" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Consultar perfil">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Consulta de actividad"/>
    </jsp:include>
    
	<jsp:include page="displayActividad.jsp"/>

	</div>
</t:wrapper>