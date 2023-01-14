<%@page import="webservices.DtUsuario"%>
<%@page import="com.helpers.RequestKeys"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.helpers.Endpoints"%>
<%@page import="com.helpers.EstadoSesionHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<li class="list-group-item">
	<div class="media">
		<div class="media-body">
			<div class="pull-right">
					<% 
						
					if (EstadoSesionHelper.hayUsuarioLogueado(request) && (!request.getParameter("nickname").equals(((DtUsuario) request.getAttribute("dtUserLoggeado")).getNickname()))){ %>
					
						<button id="${param.nickname}" type="button" style="float: right"
							onclick="cambiarEstadoSeguir('${param.nickname}')"
							
							
							<%
							Map<String, Boolean> mapLoSigo;
							if (request.getParameter("tipoLista").equals("Seguidores")){
								mapLoSigo = (Map<String, Boolean>)request.getAttribute(RequestKeys.LISTA_SIGO_SEGUIDORES);
							} else {
								mapLoSigo = (Map<String, Boolean>)request.getAttribute(RequestKeys.LISTA_SIGO_SEGUIDOS);
							}
							%>
							
							
							
							<%
							if(mapLoSigo.get(request.getParameter("nickname"))){
							%>		
								class="btn btn-success btn-default btn-sm waves-effect waves-light">  
								<i class="icon md-check" aria-hidden="true"></i>Siguiendo 
										
							<%
							} else {
							%>
								class="btn btn-info btn-sm waves-effect waves-light"					
								>Seguir</button>
									
							<% 
							}
							%>
														
						</button> 

					<% }%>
					
			</div>
			<div>
				<a class="name" href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=${param.nickname}">${param.nombre}</a>
			</div>
			<small>${param.nickname}</small>
		</div>
	</div>
</li>

