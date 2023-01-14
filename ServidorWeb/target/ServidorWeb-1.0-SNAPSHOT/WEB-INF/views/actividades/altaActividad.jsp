<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Alta de Actividad">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Alta de Actividad"/>
    </jsp:include>

    <jsp:include page="/WEB-INF/views/actividades/formAltaActividad.jsp"/>

</div>
</t:wrapper>