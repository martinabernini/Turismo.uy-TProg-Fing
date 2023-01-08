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

		<div class="col-lg-7 pb-5 top">
			<div class="container-fluid">

				<div class="container responsive-tabs">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a id="tab-A" href="#pane-A"
							class="nav-link active" data-bs-toggle="tab" role="tab">INFORMACION
								DE SALIDA</a></li>
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
										INFORMACION DE SALIDA </a>
								</h5>
							</div>
							<div id="collapse-A" class="collapse show"
								data-bs-parent="#content" role="tabpanel"
								aria-labelledby="heading-A">
								<div class="card-body">

									<div class="mb-2">
										<strong>Nombre de la salida: </strong> ${param.nombre}
									</div>

									<div class="mb-2">
										<strong>Fecha de salida: </strong> ${param.fechaSalida}
									</div>

									<div class="mb-2">
										<strong>Lugar de salida: </strong> ${param.lugar}
									</div>


									<div class="mb-2">
										<strong>Cantidad maxima de inscriptos: </strong> ${param.cantInscriptos}
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
