<#include "/layout/header.ftlh">
<body>
<div class="container d-flex justify-content-center">
    <div style="width: 350px">
        <div class="text-center mb-3 mt-5">
            <h3>Welcome back</h3>
            <p class="text-muted">System Management Parking</p>
        </div>
        <main class="form-signin">
            <form action="/login" method="post" id="loginForm">
                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" class="form-control" name="username" placeholder="Username">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <button type="submit" class="w-100 btn btn-primary">Login</button>
                <div class="text-center mt-3 mb-3">
                    <p class="text-muted">&copy; Parking Lot by Rifqi 2022</p>
                </div>
            </form>
        </main>
    </div>

</div>


<!-- jQuery -->
<script type="text/javascript" src="js/jquery/jquery.js"></script>


<#include "/layout/footer.ftlh">