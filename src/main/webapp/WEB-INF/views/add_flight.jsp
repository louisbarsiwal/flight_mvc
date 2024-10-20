<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Flight</title>
    <link rel="stylesheet" type="text/css" href="/CSS/add_flight.css">
	
	<script>
		window.onload = function() {
					const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
					if (message) {
						alert(message);
					}
					setMinDepartureDate();
				};
	   function setMinDepartureDate() {
	   			const now = new Date();
	   			const minDate = now.toISOString().slice(0, 16); // Get the current date and time in local format
	   			document.getElementById('departure').setAttribute('min', minDate);
	   		}

	   		function updateArrivalDate() {
	   			const departureDate = document.getElementById('departure').value;
	   			document.getElementById('arrival').setAttribute('min', departureDate);
	   		}

			function calculateBusinessSeats() {
			    const totalSeats = parseInt(document.getElementById('totalSeats').value) || 0;
			    const economySeats = parseInt(document.getElementById('economySeats').value) || 0;
			    
			    const businessSeats = totalSeats - economySeats;
			    document.getElementById('businessSeats').value = businessSeats >= 0 ? businessSeats : 0; // Ensure it doesn't go negative

			    // Check if the sum of economy and business seats equals total seats
			    if (economySeats + businessSeats !== totalSeats) {
			        alert("Error: The sum of Economy and Business seats must equal Total Seats.");
			    }
			}
	</script>
</head>
<body>
    <div class="form-container">
        <h2>Add Flight</h2> <!-- Added a title for clarity -->
        <form action="/flight/addFlight" method="post">
			<div class="form-group">
				<input type="hidden" id="flightId" name="flightId"  required>
			        <label for="airline">Airline Name</label>
					<select id="airline" name="airlineName">
                    <% 
                        List<String> airlineNames = (List<String>) request.getAttribute("airlineNames");
                        if (airlineNames != null) {
                            for (String airline : airlineNames) {
                    %>
                        <option value="<%= airline %>"><%= airline %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
			    </div>
			    <div class="form-group">
					<label for="flightNo">Flight Number</label>
					<input type="text" id="airlineNumber" name="flightNo" required> <!-- Changed from select to text input -->
			    </div>
				<div class="form-group">
		            <label for="flightModel">Flight Model:</label>
		            <input type="text" id="modelNumber" name="flightModel" required>
		        </div>
			    <div class="form-group">
			        <label for="from">From</label>
			        <select id="from" name="fromLocation"> <!-- Changed here -->
			            <option value="DEL">New Delhi</option>
						<option value="BOM">Mumbai</option>
						<option value="KOAA">Chennai</option>
						<option value="HYD">Hyderabad</option>
						<option value="BLR">Bangalore</option>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="departure">Departure (Date & Time)</label>
			        <input type="datetime-local" id="departure" name="departureDateTime" onchange="updateArrivalDate()" > <!-- Changed here -->
			    </div>
			    <div class="form-group">
			        <label for="to">To</label>
			        <select id="to" name="toLocation"> <!-- Changed here -->
						<option value="DEL">New Delhi</option>
						<option value="BOM">Mumbai</option>
						<option value="KOAA">Chennai</option>
						<option value="HYD">Hyderabad</option>
						<option value="BLR">Bangalore</option>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="arrival">Arrival (Date & Time)</label>
			        <input type="datetime-local" id="arrival" name="arrivalDateTime"> <!-- Changed here -->
			    </div>
			    <div class="form-group">
			        <label for="totalSeats">Total Seats Available</label>
			        <input type="number" id="totalSeats" name="totalSeats" oninput="calculateBusinessSeats()">
			    </div>
			    <div class="form-group">
			        <label for="economySeats">Seats in Economy Class</label>
			        <input type="number" id="economySeats" name="economySeats" oninput="calculateBusinessSeats()">
			    </div>
			    <div class="form-group">
			        <label for="economyPrice">Price for Economy</label>
			        <input type="number" id="economyPrice" name="economyPrice">
			    </div>
			    <div class="form-group">
			        <label for="businessSeats">Seats in Business Class</label>
			        <input type="number" id="businessSeats" name="businessSeats">
			    </div>
			    <div class="form-group">
			        <label for="businessPrice">Price for Business</label>
			        <input type="number" id="businessPrice" name="businessPrice">
			    </div>
			    <div class="form-group">
			        <input type="submit" value="Add Flight">
			    </div>
				<% if (request.getAttribute("message") != null) { %>
						    <div><%= request.getAttribute("message") %></div>
						<% } %>
        </form>
		<centre>
		<a  href="/openDisplayFlightPage">View Flights</a>
        </centre>
    </div>
</body>
</html>
