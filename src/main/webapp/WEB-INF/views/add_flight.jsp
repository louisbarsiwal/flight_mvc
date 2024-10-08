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
	   };
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
			        <label for="flightNo">Flight No</label>
					<select id="airlineNumber" name="flightNo">
                    <% 
                        List<String> airlineNumbers = (List<String>) request.getAttribute("airlineNumbers");
                        if (airlineNumbers != null) {
                            for (String number : airlineNumbers) {
                    %>
                        <option value="<%= number %>"><%= number %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
			    </div>
			    <div class="form-group">
			        <label for="flightModel">Flight Model</label>
					<select id="modelNumber" name="flightModel">
                    <% 
                        List<String> modelNumbers = (List<String>) request.getAttribute("modelNumbers");
                        if (modelNumbers != null) {
                            for (String model : modelNumbers) {
                    %>
                        <option value="<%= model %>"><%= model %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
			    </div>
			    <div class="form-group">
			        <label for="from">From</label>
			        <select id="from" name="fromLocation"> <!-- Changed here -->
			            <option value="NYC">New York City</option>
			            <option value="LAX">Los Angeles</option>
			            <option value="CHI">Chicago</option>
			            <option value="ATL">Atlanta</option>
			            <option value="LHR">London</option>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="departure">Departure (Date & Time)</label>
			        <input type="datetime-local" id="departure" name="departureDateTime"> <!-- Changed here -->
			    </div>
			    <div class="form-group">
			        <label for="to">To</label>
			        <select id="to" name="toLocation"> <!-- Changed here -->
			            <option value="NYC">New York City</option>
			            <option value="LAX">Los Angeles</option>
			            <option value="CHI">Chicago</option>
			            <option value="ATL">Atlanta</option>
			            <option value="LHR">London</option>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="arrival">Arrival (Date & Time)</label>
			        <input type="datetime-local" id="arrival" name="arrivalDateTime"> <!-- Changed here -->
			    </div>
			    <div class="form-group">
			        <label for="totalSeats">Total Seats Available</label>
			        <input type="number" id="totalSeats" name="totalSeats">
			    </div>
			    <div class="form-group">
			        <label for="economySeats">Seats in Economy Class</label>
			        <input type="number" id="economySeats" name="economySeats">
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
    </div>
</body>
</html>
