<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Consultar Usuarios">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Consultar usuarios"/>
    </jsp:include>

    <jsp:include page="/WEB-INF/views/usuarios/displayUsuarios.jsp"/>

</div>
</t:wrapper>
