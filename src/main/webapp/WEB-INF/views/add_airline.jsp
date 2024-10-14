<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Airline</title>
    <link rel="stylesheet" type="text/css" href="/CSS/add_airline.css">
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
    <h1>Add Airline</h1>
    <form action="/airline/addAirline" method="post">
        <div>
			
			
            <label for="airlineName">Airline Name:</label>
            <input type="text" id="airlineName" name="airlineName" required>
        </div>
        <div>
            <label for="airlineNumber">Airline Number:</label>
            <input type="text" id="airlineNumber" name="airlineNumber" required>
        </div>
        <div>
            <label for="modelNumber">Model Number:</label>
            <input type="text" id="modelNumber" name="modelNumber" required>
        </div>
        <div>
            <button type="submit">Add Airline</button>
        </div>
		<% if (request.getAttribute("message") != null) { %>
		    <div><%= request.getAttribute("message") %></div>
		<% } %>
    </form>
    <a  href="/openDisplayAirlinePage">View Airlines</a>
</body>
</html>
