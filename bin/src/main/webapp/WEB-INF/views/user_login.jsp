<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login Page</title>
    <link rel="stylesheet" type="text/css" href="/CSS/user_login.css">
</head>
<body>

    <form action="/user/login" method="post">
        <img src="/images/profile-icon.png" alt="Profile Icon" class="profile-icon" />
        <input type="text" name="username" placeholder="Username" required /><br/>
        <input type="password" name="password" placeholder="Password" required /><br/>
        <button type="submit">Submit</button><br/>
        <p><a href="/user/openRegistrationPage">Don't have an account? Register here</a></p>
    </form>

</body>
</html>