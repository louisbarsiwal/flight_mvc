<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Passenger Login Page</title>
    <link rel="stylesheet" type="text/css" href="/CSS/passenger_login.css">
	<script>
		        window.onload = function() {
		            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
		            if (message) {
		                alert(message);
		            }
		        };
		    </script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <form action="/user/passengerlogin" method="post">
        <h2 class="login-title">Passenger Login</h2>
        <input type="text" name="username" class="input-field" placeholder="Enter your Username" required /><br/>
        <div class="password-container">
            <input type="password" id="password" name="password" class="input-field" placeholder="Enter your password" required />
            <span class="eye-icon" onclick="togglePassword()">
                <i class="fa fa-eye-slash" id="toggleIcon"></i>
            </span>
        </div><br/>
        <button type="submit">Login</button><br/>
		<p><a href="/user/PassengerForgotPage">forgot password ?</a></p>
        <p>Don't have an account? <a href="/user/openPassengerRegistration">Register here</a></p>
    </form>

    <script>
        function togglePassword() {
            var passwordField = document.getElementById("password");
            var toggleIcon = document.getElementById("toggleIcon");
            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
                // Automatically hide the password after 0.5 second
                setTimeout(function() {
                    passwordField.type = "password";
                    toggleIcon.classList.remove("fa-eye");
                    toggleIcon.classList.add("fa-eye-slash");
                }, 500);
            } else {
                passwordField.type = "password";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            }
        }
    </script>
</body>
</html>
