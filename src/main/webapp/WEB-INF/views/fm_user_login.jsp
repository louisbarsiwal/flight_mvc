<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flight Manager Login Page</title>
	<link rel="stylesheet" href="${pageContent.request.contentPath}/CSS/fm_login.css">
</head>
<body>

    <form action="/flightManager/login" method="post">
        <input type="text" name="username" placeholder="Flight Manager Username" required /><br/>
        <input type="password" name="password" placeholder="Password" required /><br/>
        <button type="submit">Submit</button><br/>
        <p>Don't have an account?<a href="/user/openfmuserregistrationPage">Register here</a></p>
    </form>

</body>
</html>

