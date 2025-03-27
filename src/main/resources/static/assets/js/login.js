let loginToDashboard = ()=>{
    console.log("Login form submitted");
    $.ajax({
        url: `http://localhost:8080/api/v1/auth/authenticate`,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            email: $("#email").val(),
            password: $("#password").val(),
        }),
        success: function (response) {
            console.log(response);
            localStorage.setItem("jwt_token", response.data.token);
            localStorage.setItem("user_role", response.data.role); // Store role in local storage
            console.log("Login successful:", response);
            window.location.href = "dashboard.html";
            // $("#loginForm")[0].reset();

            // Redirect based on user role
            if (response.data.role === "ADMIN") {
                window.location.href = "dashboard.html";
            } else if (response.data.role === "USER") {
                window.location.href = "index.html";
            } else {
                alert("Unauthorized role");
            }
        },
    });
}

$(document).ready(function () {
    $(".toggle-password").click(function () {
        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $(this).prev();
        if (input.attr("type") === "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });


});