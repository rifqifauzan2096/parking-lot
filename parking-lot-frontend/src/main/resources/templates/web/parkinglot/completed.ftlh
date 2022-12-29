<#include "/layout/header.ftlh">
<body>
<#include "/layout/navbar.ftlh">
<div class="container">
    <div class="text-center mb-4 mt-3">
        <h2><@spring.message "form.titleCompleted" /></h2>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><@spring.message "table.no" /></th>
            <th scope="col"><@spring.message "table.noVehicle" /></th>
            <th scope="col"><@spring.message "table.typeVehicle" /></th>
            <th scope="col"><@spring.message "table.startTime" /></th>
            <th scope="col"><@spring.message "table.endTime" /></th>
            <th scope="col"><@spring.message "table.totalPrice" /></th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
                <#list listByStatus as data>
                <tr>
                    <td>${data?index+1}</td>
                    <td>${data.vehicleNumber!""}</td>
                    <td>${data.vehicleTypeName!""}</td>
                    <td>${data.startTime?string["hh:mm:ss a"]}</td>
                    <td>${data.endTime?string["hh:mm:ss a"]}</td>
                    <td>${data.totalPrice}</td>
                    <td>
                        <a href="edit?id=${data.id}" class="text-decoration-none"><button class="btn btn-parking"><@spring.message "button.actionDetail" /></button></a>
                    </td>
                </tr>
                </#list>
        </tbody>
    </table>
</div>


<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


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
               location.reload();
                $("#addModal").modal("hide");
                alert(result.success)
            }).fail(function (failResult) {
                alert(failResult.statusText)
            });
        })
        $(document).ready(function () {
            var url = window.location;
            $('ul.nav a[href="'+ url +'"]').parent().addClass('active-navbar');
            $('ul.nav a').filter(function() {
                return this.href == url;
            }).parent().addClass('active-navbar');
        });
    })
</script>
</body>

<#include "/layout/footer.ftlh" >