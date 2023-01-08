<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Inscripcion a salida turistica">

    <div class="container-fluid">

        <jsp:include page="/WEB-INF/template/titulo.jsp">
            <jsp:param name="title" value="Inscripcion a salida turistica"/>
        </jsp:include>

        <jsp:include page="/WEB-INF/views/actividades/formSalidaTuristica.jsp"/>


    </div>
</t:wrapper>