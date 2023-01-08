<%@page import="com.helpers.Endpoints"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Consultar Actividades">

    <div class="container-fluid">

        <jsp:include page="/WEB-INF/template/titulo.jsp">
            <jsp:param name="title" value="Consultar salidas"/>
        </jsp:include>

		<h5 class="font-weight-semi-bold text-uppercase mb-1 ">
			<center>
				Seleccione una actividad para consultar sus salidas asociadas
			</center>
		</h5>
		
        <jsp:include page="/WEB-INF/template/filtro.jsp">
            <jsp:param name="filtro" value="<%= Endpoints.CONSULTAR_SALIDAS %>"/>
        </jsp:include>
		
        <div class="py-3">
            <jsp:include page="/WEB-INF/views/actividades/consultarSalida/displayAllActividades.jsp"/>
        </div>


    </div>
</t:wrapper>