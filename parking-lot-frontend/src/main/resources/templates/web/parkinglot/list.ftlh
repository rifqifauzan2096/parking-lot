<#include "/layout/header.ftlh">
<body class="d-flex">
    <#include "/layout/sidebar.ftlh">
<div class="col">
    <div class="main-body">
        <div class="container">
            <div class="text-center mb-4 mt-3">
                <h2><@spring.message "form.title" /></h2>
            </div>
            <div class="d-flex mb-3">
                <a href="/parking/list" class="menu-label me-4 active-label"><@spring.message "form.menuParked" /></a>
                <a href="/parking/completed" class="menu-label"><@spring.message "form.menuCompleted" /></a>
            </div>
            <div class="mb-3 d-grid d-md-flex justify-content-between">
                <div>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           placeholder="<@spring.message "form.menuSearch" />">
                </div>
                <button type="button" class="btn btn-md btn-parking" data-bs-toggle="modal"
                        data-bs-target="#addModal"><@spring.message "button.addTable" />
                </button>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><@spring.message "table.no" /></th>
                    <th scope="col"><@spring.message "table.noVehicle" /></th>
                    <th scope="col"><@spring.message "table.typeVehicle" /></th>
                    <th scope="col"><@spring.message "table.startTime" /></th>
                    <th scope="col"><@spring.message "table.actionTable" /></th>
                </tr>
                </thead>
                <tbody>
                    <#list parkingList as data>
                    <tr>
                        <td>${data?index+1}</td>
                        <td>${data.vehicleNumber!""}</td>
                        <td>${data.vehicleTypeName!""}</td>
                        <td>${data.startTime?string["hh:mm:ss a"]}</td>
                        <td>
                            <a href="edit?id=${data.id}" class="text-decoration-none">
                                <button class="btn btn-danger"><@spring.message "button.actionOut" /></button>
                            </a>
                        </td>
                    </tr>
                        <#else >
                        <p>Data Kosong</p> <#-- kenapa p ini di render di atas table -->
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container">
        <p class="text-muted muted">this page process about ${resultTime}ms.</p>
    </div>
</div>





<#--<!-- Button trigger modal &ndash;&gt;-->
<#--<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">-->
<#--Launch demo modal-->
<#--</button>-->

<!-- Modal Add-->
<div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="labelAdd" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="labelAdd"><@spring.message "modal.titleParking" /></h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <main class="form-signin">
                    <form action="#" method="post" id="formAdd">
                        <input type="hidden" name="completed" value="no"/>
                        <input type="hidden" class="form-control" name="totalPrice" value="0">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message "table.noVehicle" /></label>
                            <input type="text" class="form-control" name="vehicleNumber"
                                   value="${model.vehicleNumber!""}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message "table.startTime" /></label>
                            <input type="text" readonly class="form-control" value="${startTime!""}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message "table.typeVehicle" /></label>
                            <select class="form-select" name="vehicleTypeId">
                                <option><@spring.message "form.selectTitle"/></option>
                                <#list vehicleType as type >
                                    <option value="${type.id}">${type.typeVehicle}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="d-flex justify-content-end">
                            <a href="/parking/list"
                               class="btn btn-light text-decoration-none"><@spring.message "button.actionCancel" /></a>
                            <input type="button" id="btnSave" class="btn btn-parking"
                                   value="<@spring.message "button.actionSave" />">
                        </div>
                    </form>
                </main>
            </div>
        </div>
    </div>
</div>

<#--<p>Page ${authorPage.getPageable().getPageNumber()+1} of ${authorPage.getTotalPages()!"error"} Pages</p>&ndash;&gt;

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#btnSave").click(function () {
            var data = $("#formAdd").serialize();
            $.ajax({
                url: "add",
                type: "POST",
                data: data
            }).done(function (result) {
                window.location = "/parking/list"
                $("#addModal").modal("hide");
                alert(result.success)
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
        $(document).ready(function () {
            var url = window.location;
            $('ul.nav a[href="' + url + '"]').parent().addClass('active-navbar');
            $('ul.nav a').filter(function () {
                return this.href == url;
            }).parent().addClass('active-navbar');
        });
    })
</script>
</body>

<#include "/layout/footer.ftlh" >