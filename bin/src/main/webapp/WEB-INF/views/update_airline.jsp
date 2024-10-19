<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="flightmanagement.app.entities.AddedAirline" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Airline</title>
    <link rel="stylesheet" type="text/css" href="/CSS/add_airline.css">
	
	<%
		AddedAirline addedAirline = 
						(AddedAirline) request.getAttribute("addedAirline");
					%>
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
	<h1>Edit Airline</h1>
	<form action="/editAirline" method="post">
	    <input type="hidden" name="airLineId" value=" <%= addedAirline.getAirLineId()%>">
	    <div>
	        <label for="airlineName">Airline Name:</label>
	        <input type="text" id="airlineName" name="airlineName" value="<%= addedAirline.getAirlineName()%>" required>
	    </div>
	    <div>
	        <label for="airlineNumber">Airline Number:</label>
	        <input type="text" id="airlineNumber" name="airlineNumber" value="<%= addedAirline.getAirlineNumber()%>" required>
	    </div>
	    <div>
	        <label for="modelNumber">Model Number:</label>
	        <input type="text" id="modelNumber" name="modelNumber" value="<%= addedAirline.getModelNumber()%>" required>
	    </div>
	    <div>
	        <button type="submit">Save Changes</button>
	    </div>
	</form>

		<% if (request.getAttribute("message") != null) { %>
		    <div><%= request.getAttribute("message") %></div>
		<% } %>
    </form>
   
</body>
</html>
