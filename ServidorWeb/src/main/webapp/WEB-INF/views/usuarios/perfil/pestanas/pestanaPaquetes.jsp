<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>


		<div class="d-flex mb-1">
			<p class="text-dark font-weight-medium mb-0 mr-3">Nombre del paquete:  
				<a class="text-dark font-weight-semi-bold" href="ConsultarPaquete?paquete=${param.nombrePaquete}">${param.nombrePaquete}</a>		
			</p>
		</div>


		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Fecha compra:</p>
			<p class="mb-1"> ${param.fechaCompra} </p>
		</div>
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Cantidad de turistas:</p>
			<p class="mb-1"> ${param.cantidadTur} </p>
		</div>
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Costo:</p>
			<p class="mb-1"> $${param.costo} </p>
		</div>
		
		<div class="d-flex mb-0">
			<p class="text-dark font-weight-medium mb-0 mr-3">Vencimiento:</p>
			<p class="mb-4">  ${param.fechaVencimiento} </p>
		</div>

		