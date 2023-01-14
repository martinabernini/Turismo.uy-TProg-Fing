<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper pageTitle="Inicio">
  <!-- Los que usan jsp:include es porque puede que los hagamos dinamicos
  y los que usan %@ include porque son siempre iguales -->

  <!-- no hay elementos para listar entonces se muestra el mensaje -->
    <h4 class="pl-5"> No hay elementos para listar</h4>

</t:wrapper>