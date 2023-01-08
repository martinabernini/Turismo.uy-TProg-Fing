<%@tag pageEncoding="UTF-8"%>
<%@tag description="Main html body"%>
<%@attribute name="pageTitle" required="true"%>

<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous" />
	<title>Turismo.Uy - ${pageTitle}</title>
	<%@ include file="/WEB-INF/template/header.jspf"%>
</head>
<body>

	<jsp:include page="/WEB-INF/template/navbar/navbar.jsp" />
	
	<jsp:doBody />

	<%@ include file="/WEB-INF/template/footer.jspf"%>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>