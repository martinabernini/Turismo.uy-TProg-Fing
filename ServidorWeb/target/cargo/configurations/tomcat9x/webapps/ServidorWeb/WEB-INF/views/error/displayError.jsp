<%@page contentType="text/html" pageEncoding="UTF-8" %>


<% if (request.getParameter("error") == null) {%>
<jsp:include page="error500.jsp"/>
<%
} else {
%>

<%
    if (request.getParameter("error").equals("401")) { %>
<jsp:include page="error401.jsp"/>
<%
    }
%>

<%
    if (request.getParameter("error").equals("404")) { %>
<jsp:include page="error404.jsp"/>
<%
    }
%>

<%
    // si no es ninguno de los anteriores, se muestra el error 500
    if (!request.getParameter("error").equals("401") && !request.getParameter("error").equals("404")) { %>
<jsp:include page="error500.jsp"/>
<%
        }
    }
%>
