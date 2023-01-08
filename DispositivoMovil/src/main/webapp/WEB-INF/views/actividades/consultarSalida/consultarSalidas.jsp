<%@ page import="com.helpers.Endpoints" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Consultar Actividades">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Consultar salidas"/>
    </jsp:include> 

    <div class="py-3">

    	 <jsp:include page="displayAllSalidas.jsp"/>
         
    </div>


</div>
</t:wrapper>