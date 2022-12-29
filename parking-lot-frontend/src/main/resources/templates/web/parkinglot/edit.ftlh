<#include "/layout/header.ftlh">

<body>
<div class="container d-flex justify-content-center">
    <div style="width: 450px">
        <div class="text-center mb-3 mt-5">
            <h2>Payment Parking</h2>
        </div>
        <main class="form-signin">
            <form action="#" method="post" id="formEdit">
                <input type="hidden" name="id" value="${model.id}"/>
                <input type="hidden" name="vehicleTypeId" value="${model.vehicleTypeId}"/>
                <input type="hidden" name="completed" value="yes"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.noVehicle" /></label>
                    <input type="text" readonly class="form-control" name="vehicleNumber" value="${model.vehicleNumber!""}">
                </div>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.typeVehicle" /></label>
                    <input type="text" readonly class="form-control" name="vehicleTypeName" value="${model.vehicleTypeName!""}">
                </div>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.startTime" /></label>
                    <input type="text" readonly class="form-control" value="${model.startTime?string["hh:mm:ss a"]}">
                </div>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.endTime" /></label>
                    <input type="text" readonly class="form-control" value="${endTime}">
                </div>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.totalPrice" /></label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text" style="border-radius: 10% 0 0 10%">Rp</div>
                        </div>
                        <input type="text" readonly class="form-control" id="inlineFormInputGroup" name="totalPrice" value="${totalPrice?c}">
                    </div>
                </div>
                <div>
                    <a href="/parking/list" class="btn btn-light text-decoration-none"><@spring.message "button.actionCancel" /></a>
                    <input type="button" id="btnEdit" class="btn btn-parking" value="<@spring.message "button.actionUpdate" />">
                </div>
            </form>
        </main>
    </div>
</div>

<!-- jQuery -->
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#btnEdit").click(function () {
            var data = $("#formEdit").serialize();
            $.ajax({
                url: "update",
                type: "POST",
                data: data
            }).done(function (result) {
                window.location = "/parking/list"
                alert(result.success)
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
    })
</script>
</body>

<#include "/layout/footer.ftlh">
