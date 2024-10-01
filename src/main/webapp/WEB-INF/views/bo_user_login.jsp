<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login Page</title>
    <link rel="stylesheet" type="text/css" href="/CSS/user_login.css">
	<script>
	        window.onload = function() {
	            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
	            if (message) {
	                alert(message);
	            }
	        };
	    </script>

</head>
<body>

    <form action="/user/Bologin" method="post">
        <img src="/images/profile-icon.png" alt="Profile Icon" class="profile-icon" />
        <input type="text" name="username" placeholder="Username" required /><br/>
        <input type="password" name="password" placeholder="Password" required /><br/>
        <button type="submit">Submit</button><br/>
        <p><a href="/user/openBoRegistrationPage">Don't have an account? Register here</a></p>
    </form>

</body>
</html>
