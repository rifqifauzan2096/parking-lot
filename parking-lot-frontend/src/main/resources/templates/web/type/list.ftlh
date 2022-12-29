<#include "/layout/header.ftlh">
<body class="d-flex">
    <#include "/layout/sidebar.ftlh">
    <div class="main-body">
        <div class="container">
            <div class="text-center mb-4 mt-3">
                <h2><@spring.message "form.titleType" /></h2>
            </div>
            <div class="mb-3 d-grid d-md-flex justify-content-between">
                <div>
                    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="<@spring.message "form.menuSearch" />">
                </div>
                <button type="button" class="btn btn-parking" data-bs-toggle="modal" data-bs-target="#addModal"><@spring.message "button.addTable" />
                </button>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><@spring.message "table.no" /></th>
                    <th scope="col"><@spring.message "table.typeVehicle" /></th>
                    <th scope="col"><@spring.message "table.pricePerHour" /></th>
                    <th scope="col"><@spring.message "table.actionTable" /></th>
                </tr>
                </thead>
                <tbody>
                    <#list typeList as data>
                    <tr>
                        <td>${data?index+1}</td>
                        <td>${data.typeVehicle}</td>
                        <td>Rp. ${data.pricePerHour}</td>
                        <td>
                            <a href="?id=${data.id}" class="text-decoration-none">
                                <button class="btn btn-parking" data-bs-toggle="modal" data-bs-target="#addModal"><@spring.message "button.actionEdit" />
                                </button>
                            </a>
                            <a href="delete?id=${data.id}" class="text-decoration-none">
                                <button class="btn btn-light"><@spring.message "button.actionDelete" /></button>
                            </a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Add-->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="labelAdd" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="labelAdd"><@spring.message "modal.titleType" /></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <main class="form-signin">
                        <form action="#" method="post" id="formAdd">
                            <div class="mb-3">
                                <label class="form-label"><@spring.message "table.typeVehicle" /></label>
                                <input type="text" class="form-control" name="typeVehicle" value="${model.typeVehicle!""}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><@spring.message "table.pricePerHour" /></label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text" style="border-radius: 10% 0 0 10%">Rp</div>
                                    </div>
                                    <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="ex: 5000"
                                           name="pricePerHour" value="${model.pricePerHour!""}">
                                </div>
                            </div>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn-light mx-2" data-bs-dismiss="modal">Close</button>
                                <input type="button" id="btnSave" class="btn btn-parking" value="Save">
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
                    $("#addModal").modal("hide");
                    window.location = "/type/list"
                    alert(result.success)
                }).fail(function (failResult) {
                    alert(failResult.statusText)
                });
            })
            $(document).ready(function () {
                var url = window.location;
                $('ul.nav a[href="' + url + '"]').parent().addClass('active-navbar', 'text-white');
                $('ul.nav a').filter(function () {
                    return this.href == url;
                }).parent().addClass('active-navbar');
            });
        })
    </script>
</body>
<#include "/layout/footer.ftlh">