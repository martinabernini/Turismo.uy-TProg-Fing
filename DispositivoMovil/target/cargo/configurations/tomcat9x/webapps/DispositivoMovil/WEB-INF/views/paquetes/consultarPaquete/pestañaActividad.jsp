<%@page contentType="text/html" pageEncoding="UTF-8"%>

	<div class="d-flex mb-1">
		<p class="text-dark font-weight-medium mb-0 mr-3">Nombre de la actividad: 
			<a class="text-dark font-weight-semi-bold" href="ConsultarActividad?actividad=${param.nombreActividad}">${param.nombreActividad}</a>
		</p>
	</div>
	
	<div class="d-flex mb-0">
		<p class="text-dark font-weight-medium mb-0 mr-3">Descripcion:</p>
		<p class="mb-1">${param.descripcion}</p>
	</div>
	
	<div class="d-flex mb-0">
		<p class="text-dark font-weight-medium mb-0 mr-3">Duracion (hs):</p>
		<p class="mb-1">${param.duracion}</p>
	</div>
	
	<div class="d-flex mb-0">
		<p class="text-dark font-weight-medium mb-0 mr-3">Costo por
			persona:</p>
		<p class="mb-1">${param.costo}</p>
	</div>
	
	
	<div class="d-flex mb-0">
		<p class="text-dark font-weight-medium mb-0 mr-3">Ciudad:</p>
		<p class="mb-4">${param.ciudad}</p>
	</div>
	
