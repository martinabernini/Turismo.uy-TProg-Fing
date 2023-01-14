<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="col-lg-4 col-md-6 col-sm-12 pb-1">
    <div class="card product-item border-0 mb-4">
        <a href="${param.link}" class="btn btn-sm text-dark p-0">
            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                    <img class="img-fluid" src="${param.img}" 	>
            </div>
            <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                <h6 class="text-truncate mb-3">${param.titulo}</h6>
                <div class="d-flex justify-content-center">
                    <h6>${param.detalle}</h6>
                </div>
            </div>
        </a>
    </div>
</div>

