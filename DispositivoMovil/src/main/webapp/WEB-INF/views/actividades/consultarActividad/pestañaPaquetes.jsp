<%@page contentType="text/html" pageEncoding="UTF-8"%>

		<div class="d-flex mb-1">
			<p class="text-dark font-weight-medium mb-0 mr-3">Nombre del paquete:  
				<a class="text-dark font-weight-semi-bold" href="ConsultarPaquete?paquete=${param.nombrePaquete}">${param.nombrePaquete}</a>		
			</p>
		</div>	
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Descripcion:</p>
			<p class="mb-1">  ${param.descripcion} </p>
		</div>
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Descuento:</p>
			<p class="mb-1">  ${param.descuento} </p>
		</div>
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Validez en dias:</p>
			<p class="mb-4">  ${param.validez} </p>
		</div>