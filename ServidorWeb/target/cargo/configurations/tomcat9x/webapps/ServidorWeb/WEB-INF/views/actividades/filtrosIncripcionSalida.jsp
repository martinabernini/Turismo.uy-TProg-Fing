<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Inscripcion a salida turistica">

    <div class="container-fluid">

        <jsp:include page="/WEB-INF/template/titulo.jsp">
            <jsp:param name="title" value="Seleccionar departamento o categoria"/>
        </jsp:include>

        <jsp:include page="/WEB-INF/views/actividades/formFiltroIncripcionASalida.jsp"/>

    </div>
</t:wrapper>