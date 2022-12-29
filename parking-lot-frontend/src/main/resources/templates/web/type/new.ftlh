<#include "/layout/header.ftlh">

<body>
<div class="container d-flex justify-content-center">
    <div style="width: 450px">
        <div class="text-center mb-3 mt-5">
            <h2>Add Type Vehicle</h2>
        </div>
        <main class="form-signin">
            <form action="#" method="post" id="formAdd">
                <div class="mb-3">
                    <label class="form-label">Type Vehicle</label>
                    <input type="text" class="form-control" name="typeVehicle" value="${model.typeVehicle!""}">
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                </div>
                <div class="mb-3">
                    <label class="form-label">Price Per Hour</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text" style="border-radius: 10% 0 0 10%">Rp</div>
                        </div>
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="ex: 5000" name="pricePerHour" value="${model.pricePerHour!""}">
                    </div>
                </div>
                <div>
                    <a href="/type/list" class="btn btn-light text-decoration-none">Cancel</a>
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
                window.location = "/type/list"
                alert(result.success)
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
    })
</script>
</body>

<#include "/layout/footer.ftlh">