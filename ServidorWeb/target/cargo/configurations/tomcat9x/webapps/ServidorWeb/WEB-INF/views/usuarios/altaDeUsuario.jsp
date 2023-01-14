<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Alta de Usuario">

<div class="container-fluid">

    <jsp:include page="/WEB-INF/template/titulo.jsp">
        <jsp:param name="title" value="Alta de Usuario"/>
    </jsp:include>

    <jsp:include page="/WEB-INF/views/usuarios/formAltaDeUsuario.jsp"/>

</div>
</t:wrapper>