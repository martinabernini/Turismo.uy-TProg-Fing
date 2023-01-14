<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Seleccionar departamento">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Seleccionar departamento"/>
    </jsp:include>
    
    <jsp:include page="/WEB-INF/views/actividades/formDptoAltaSalida.jsp"/>

</div>
</t:wrapper>