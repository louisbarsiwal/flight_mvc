<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flight Manager Login</title>
</head>
<body>
    <h2>Flight Manager Login</h2>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required /><br>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required /><br>
        
        <input type="submit" value="Login">
    </form>
    <% 
        String message = (String) request.getAttribute("message");
        if (message != null) { 
    %>
        <p style="color: red;"><%= message %></p>
    <% } %>
</body>
</html>
