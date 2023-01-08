<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper pageTitle="Inicio">
	<!-- Los que usan jsp:include es porque puede que los hagamos dinamicos 
	y los que usan %@ include porque son siempre iguales -->

	<jsp:include page="/WEB-INF/views/home/components/carrousel.jsp" />

	<%@ include file="/WEB-INF/views/home/components/featured.jsp"%>

	<jsp:include page="/WEB-INF/views/home/components/products.jsp" />

	<%@ include file="/WEB-INF/views/home/components/subscribe.jsp"%>

	<%@ include file="/WEB-INF/views/home/components/empresasAsociadas.jsp"%>
	
</t:wrapper>