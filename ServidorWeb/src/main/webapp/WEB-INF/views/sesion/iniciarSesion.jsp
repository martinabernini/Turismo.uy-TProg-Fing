<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper pageTitle="Inicio">
	<!-- Los que usan jsp:include es porque puede que los hagamos dinamicos 
	y los que usan %@ include porque son siempre iguales -->
	
	<jsp:include page="/WEB-INF/template/titulo.jsp" >
		<jsp:param name="title" value="Iniciar Sesion"/>
	</jsp:include>	

	<jsp:include page="/WEB-INF/views/sesion/loginForm.jsp" />

</t:wrapper>