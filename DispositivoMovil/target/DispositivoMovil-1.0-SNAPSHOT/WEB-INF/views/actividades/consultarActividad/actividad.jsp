<%@page import="webservices.DtPaqueteActividades"%>
<%@page import="webservices.DtSalidaTuristica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.helpers.RequestKeys"%>
<%@page import="com.model.EstadoSesion"%>



<div class="container-fluid py-1">
	<div class="row px-xl-5">

		<div class="col-lg-5 pb-3">
			<div id="product-carousel" class="carousel slide"
				data-ride="carousel">
				<img class="w-100 h-100" src="${param.img}" alt="Image">
				<%-- PARAMETRO IMAGEN --%>
			</div>
		</div>




		<!-- 	diseño de tabs nuevas			 -->


		<div class="col-lg-7 pb-5 top">
			<div class="container-fluid">

				<div class="container responsive-tabs">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a id="tab-A" href="#pane-A"
							class="nav-link active" data-bs-toggle="tab" role="tab">INFORMACION
								DE ACTIVIDAD</a></li>
						<li class="nav-item"><a id="tab-B" href="#pane-B"
							class="nav-link" data-bs-toggle="tab" role="tab">SALIDAS
								TURISTICAS</a></li>
						<li class="nav-item"><a id="tab-C" href="#pane-C"
							class="nav-link" data-bs-toggle="tab" role="tab">PAQUETES
								ASOCIADOS</a></li>
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
										INFORMACION DE ACTIVIDAD </a>
								</h5>
							</div>
							<div id="collapse-A" class="collapse show"
								data-bs-parent="#content" role="tabpanel"
								aria-labelledby="heading-A">
								<div class="card-body">

									<div class="mb-2">
										<strong>Nombre: </strong> ${param.nombre}
									</div>

									<div class="mb-2">	
										<strong>Descripcion: </strong> ${param.descripcion}
									</div>

									<div class="mb-2">
										<strong>Duracion: </strong> ${param.duracion}
									</div>

									<div class="mb-2">
										<strong>Costo: </strong> ${param.costo}
									</div>


									<div class="mb-2">
										<strong>Ciudad: </strong> ${param.ciudad}
									</div>


									<div class="mb-2">
										<strong>Fecha de alta</strong> ${param.fecha}
									</div>

									<div class="mb-2">
										<strong>Categorias: </strong> ${param.cats}
									</div>

								</div>
							</div>
						</div>


						<!------------------------------------------------------------------------------------------------------------------------>
						<!----------------------------	TAB DE SALIDAS	-------------------------->

						<div id="pane-B" class="card tab-pane" role="tabpanel"
							aria-labelledby="tab-B">
							<div class="card-header h-auto" role="tab" id="heading-B">
								<h5 class="mb-0">
									<a class="collapsed" data-bs-toggle="collapse"
										href="#collapse-B" aria-expanded="false"
										aria-controls="collapse-B"> SALIDAS TURISTICAS </a>
								</h5>
							</div>
							<div id="collapse-B" class="collapse" data-bs-parent="#content"
								role="tabpanel" aria-labelledby="heading-B">
								<div class="card-body">

									<!--- Este for es para mostrar las salidas turisticas adentro de la pestaña --->
									<%
									List<DtSalidaTuristica> salidas = (List<DtSalidaTuristica>) request
											.getAttribute(RequestKeys.SALIDAS_ACTIVIDAD_A_CONSULTAR);

									if (salidas != null && salidas.size() > 0) {
										for (DtSalidaTuristica salida : salidas) {
											String nombreSalida = salida.getNombreSalida();
									%>
									<!--- Hay una de estas para cada salida --->
									<div class="d-flex mb-1"> 
										<a class="text-dark font-weight-semi-bold" href="ConsultarSalida?salida=<%=nombreSalida%>"><%=nombreSalida%></a>
									</div>
									<%
									}
									}
									%>

									<%
									if (salidas == null || salidas.size() == 0) {
									%>
									<div class="d-flex mb-4">
										<p class="text-dark font-weight-medium mb-0 mr-3">No hay
											salidas</p>
									</div>
									<%
									}
									%>

								</div>
							</div>
						</div>


						<!------------------------------------------------------------------------------------------------------------------------>
						<!----------------------------	TAB DE PAQUETES	-------------------------->


						<div id="pane-C" class="card tab-pane" role="tabpanel"
							aria-labelledby="tab-C">
							<div class="card-header h-auto" role="tab" id="heading-C">
								<h5 class="mb-0">
									<a data-bs-toggle="collapse" href="#collapse-C"
										aria-expanded="true" aria-controls="collapse-C"> PAQUETES
										ASOCIADOS </a>
								</h5>
							</div>
							<div id="collapse-C" class="collapse" data-bs-parent="#content"
								role="tabpanel" aria-labelledby="heading-C">
								<div class="card-body">

									<!--- Este for es para mostrar los paquetes asociados adentro de la pestaña --->
									<%
									List<DtPaqueteActividades> paquetes = (List<DtPaqueteActividades>) request.getAttribute(RequestKeys.LISTA_PAQUETES_ACT);

									if (paquetes != null && paquetes.size() > 0) {
										for (DtPaqueteActividades paquete : paquetes) {
											String nombrePaquete = paquete.getNombre();
									%>
									<!--- Hay una de estas para cada paquete --->
									<div class="d-flex mb-1"> 
										<a class="text-dark font-weight-semi-bold"><%=nombrePaquete%></a>
									</div>
									<%
									}
									}
									%>

									<%
									if (salidas == null || salidas.size() == 0) {
									%>
									<div class="d-flex mb-4">
										<p class="text-dark font-weight-medium mb-0 mr-3">No hay
											paquetes</p>
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






















