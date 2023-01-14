<%@page import="webservices.DtActividadTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="com.helpers.RequestKeys" %>
<%@page import="com.model.EstadoSesion" %>


<div class="container-fluid py-1">
    <div class="row px-xl-5">

        <div class="col-lg-5 pb-3">
            <div id="product-carousel" class="carousel slide"
                 data-ride="carousel">
                <img class="w-100 h-100" src="${param.img}" alt="Image">
                <%-- PARAMETRO IMAGEN --%>
            </div>
        </div>

        <div class="col-lg-7 pb-5 top">
            <div class="container-fluid">
            
            
            
            <div class="container responsive-tabs">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a id="tab-A" href="#pane-A"
							class="nav-link active" data-bs-toggle="tab" role="tab">INFORMACION
								DE PAQUETE</a></li>
						<li class="nav-item"><a id="tab-B" href="#pane-B"
							class="nav-link" data-bs-toggle="tab" role="tab">ACTIVIDADES
								INCLUIDAS</a></li>
					</ul>

					<!------------------------------------------------------------------------------------------------------------------------>
					<!----------------------------	TAB DE INFO BASICA	-------------------------->

					<div id="content" class="tab-content" role="tablist">
						<div id="pane-A" class="card tab-pane show active" role="tabpanel"
							aria-labelledby="tab-A">
							<div class="card-header h-auto" role="tab" id="heading-A">
								<h5 class="mb-0">
									<a data-bs-toggle="collapse" href="#collapse-A"
										aria-expanded="true" aria-controls="collapse-A">
										INFORMACION DE PAQUETE </a>
								</h5>
							</div>
							<div id="collapse-A" class="collapse show"
								data-bs-parent="#content" role="tabpanel"
								aria-labelledby="heading-A">
								<div class="card-body">

									<div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Nombre del paquete:</p>
		                                <p class="mb-4">${param.nombre}</p>
		                            </div>
		
		                            <div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Descripcion:</p>
		                                <p class="mb-4">${param.descripcion}</p>
		                            </div>
		
		                            <div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Validez en dias:</p>
		                                <p class="mb-4">${param.validez}</p>
		                            </div>
		
		
									<div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Descuento:</p>
		                                <p class="mb-4">${param.descuento}</p>
		                            </div>
		                            
		                            <div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Fecha de alta:</p>
		                                <p class="mb-4">${param.fechaAlta}</p>
		                            </div>
		                            
		                            <div class="d-flex mb-0">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">Categorias:</p>
		                                <p class="mb-4"> ${param.cats}</p>
		                            </div>

								</div>
							</div>
						</div>


						<!------------------------------------------------------------------------------------------------------------------------>
						<!----------------------------	TAB DE ACTIVIDADES	-------------------------->

						<div id="pane-B" class="card tab-pane" role="tabpanel"
							aria-labelledby="tab-B">
							<div class="card-header h-auto" role="tab" id="heading-B">
								<h5 class="mb-0">
									<a class="collapsed" data-bs-toggle="collapse"
										href="#collapse-B" aria-expanded="false"
										aria-controls="collapse-B"> ACTIVIDADES ASOCIADAS </a>
								</h5>
							</div>
							<div id="collapse-B" class="collapse" data-bs-parent="#content"
								role="tabpanel" aria-labelledby="heading-B">
								<div class="card-body">

									<%
	                                List<DtActividadTuristica> actividades = (List<DtActividadTuristica>) request.getAttribute(RequestKeys.LISTA_ACTIVIDADES);
	                                if (actividades != null && actividades.size() > 0) {
	                                    for (DtActividadTuristica actividad : actividades) {
	                                        String nombreActividad = actividad.getNombre();
	                                        String link= nombreActividad;
	                                        String descripcion = actividad.getDescripcion();
	                                        String duracion = String.valueOf(actividad.getDuracionHrs());
	                                        String costo = "$" + String.valueOf(actividad.getCostoPorPersona());
	                                        String ciudad = actividad.getCiudad();
	                                        
	                            	%>
		                            <jsp:include page="pestañaActividad.jsp">
		                            	<jsp:param name="link" value="<%=link%>"/>
		                                <jsp:param name="nombreActividad" value="<%=nombreActividad%>"/>
		                                <jsp:param name="descripcion" value="<%=descripcion%>"/>
		                                <jsp:param name="duracion" value="<%=duracion%>"/>
		                                <jsp:param name="costo" value="<%=costo%>"/>
		                                <jsp:param name="ciudad" value="<%=ciudad%>"/>
		                            </jsp:include>
		                            <%
		                                    }
		                                }
		                            %>
		
		                            <%
		                                if (actividades == null || actividades.size() == 0) {
		                            %>
		                            <div class="d-flex mb-4">
		                                <p class="text-dark font-weight-medium mb-0 mr-3">No hay actividades</p>
		                            </div>
		                            <%
		                                }
		                            %>

								</div>
							</div>
						</div>

					</div>
				</div>         
            </div>
        </div>
    </div>
</div>
