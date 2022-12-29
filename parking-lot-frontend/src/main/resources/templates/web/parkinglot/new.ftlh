<#include "/layout/header.ftlh">

<body>
<div class="container d-flex justify-content-center">
    <div style="width: 450px">
        <div class="text-center mb-3 mt-5">
            <h2>Add Parking </h2>
        </div>
        <main class="form-signin">
            <form action="#" method="post" id="formAdd">
                <div class="mb-3">
                    <label class="form-label">Vehicle Number</label>
                    <input type="text" class="form-control" name="vehicleNumber" value="${model.vehicleNumber!""}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Start Time</label>
                    <input type="text" readonly class="form-control" value="${startTime!""}">
                    <input type="hidden" class="form-control" name="totalPrice" value="0">
                <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                </div>
                <div class="mb-3">
                    <label class="form-label">Vehicle Type</label>
                    <select class="form-select" name="vehicleTypeId">
                        <option>Choose a Vehicle Type</option>
                        <#list vehicleType as type >
                            <option value="${type.id}">${type.typeVehicle}</option>
                        </#list>
                    </select>
                </div>
                <div>
                    <a href="/parking/list" class="btn btn-light text-decoration-none">Cancel</a>
                    <input type="button" id="btnSave" class="btn btn-parking" value="Save">
                </div>
            </form>
        </main>
    </div>
</div>

<!-- jQuery -->
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
                alert(result.success)
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
    })
</script>
</body>


<#include "/layout/footer.ftlh">