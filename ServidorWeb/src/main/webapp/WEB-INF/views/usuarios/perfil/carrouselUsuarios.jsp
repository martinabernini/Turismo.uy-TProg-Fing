<%@ page import="com.helpers.Endpoints" %>
<div class="container-fluid py-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Usuarios recomendados</span></h2>
    </div>
    <div class="row px-xl-5">
        <div class="col">
            <div class="owl-carousel related-carousel">


                <div class="card product-item border-0">
                    <a href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=isabelita" class="btn btn-sm text-dark p-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="media/img/isabelitaListar.jpg" alt="">
                        </div>
                        <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">isabelita</h6>
                        </div>
                    </a>
                </div>


                <div class="card product-item border-0">
                    <a href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=anibal" class="btn btn-sm text-dark p-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="media/img/anibalListar.jpg" alt="">
                        </div>
                        <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">anibal</h6>
                        </div>
                    </a>
                </div>

                <div class="card product-item border-0">
                    <a href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=washington" class="btn btn-sm text-dark p-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="media/img/washingtonListar.jpg" alt="">
                        </div>
                        <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">washington</h6>
                        </div>
                    </a>
                </div>

                <div class="card product-item border-0">
                    <a href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=eldiez" class="btn btn-sm text-dark p-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="media/img/eldiezListar.jpg" alt="">
                        </div>
                        <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">eldiez</h6>
                        </div>
                    </a>
                </div>

                <div class="card product-item border-0">
                    <a href="<%= Endpoints.CONSULTAR_USUARIO %>?nickname=meche" class="btn btn-sm text-dark p-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="media/img/mecheListar.jpg" alt="">
                        </div>
                        <div class="card-body border-left border-right border-bottom text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">meche</h6>
                        </div>
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>