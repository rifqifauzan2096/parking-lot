<#include "/layout/header.ftlh" >

<body>
<div class="container d-flex justify-content-center">
    <div style="width: 450px">
        <div class="text-center mb-3 mt-5">
            <h2>Edit <@spring.message "table.typeVehicle" /></h2>
        </div>
        <main class="form-signin">
            <form action="#" method="post" id="formEdit">
                <input type="hidden" name="id" value="${model.id}"/>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.typeVehicle" /></label>
                    <input type="text" class="form-control" name="typeVehicle" value="${model.typeVehicle!""}">
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                </div>
                <div class="mb-3">
                    <label class="form-label"><@spring.message "table.pricePerHour" /></label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text" style="border-radius: 10% 0 0 10%">Rp</div>
                        </div>
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="ex: 5000" name="pricePerHour" value="${model.pricePerHour?c!""}">
                    </div>
                </div>
                <div>
                    <a href="/type/list" class="btn btn-light text-decoration-none"><@spring.message "button.actionCancel" /></a>
                    <button type="submit" id="btnEdit" class="btn btn-parking"><@spring.message "button.actionUpdate" /></button>
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
                window.location = "/type/list"
                alert("Data has been updated successfully")
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
    })
</script>
</body>

<#include "/layout/footer.ftlh">

