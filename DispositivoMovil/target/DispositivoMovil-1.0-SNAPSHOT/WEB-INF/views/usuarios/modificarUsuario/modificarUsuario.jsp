<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Modificar usuario">

        <jsp:include page="/WEB-INF/template/titulo.jsp">
            <jsp:param name="title" value="Modificar usuario"/>
        </jsp:include>

    <jsp:include page="/WEB-INF/views/usuarios/modificarUsuario/formModificarUsuario.jsp"/>

</t:wrapper>