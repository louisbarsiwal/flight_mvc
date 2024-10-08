<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="flightmanagement.app.entities.AddedFlight" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Flight</title>
    <link rel="stylesheet" type="text/css" href="/CSS/update_flight.css">
	<%
			AddedFlight addedFlight = 
							(AddedFlight) request.getAttribute("addedFlight");
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
    <div class="form-container">
        <h2>Edit Flight</h2>
        <form action="/editFlight" method="post">
            <input type="hidden" name="flightId" value="<%= addedFlight.getFlightId()%>"> 
            
            <div class="form-group">
                <label for="airline">Airline Name</label>
                <select id="airline" name="airlineName">
                    <% 
                        List<String> airlineNames = (List<String>) request.getAttribute("airlineNames");
                        if (airlineNames != null) {
                            for (String airline : airlineNames) {
                    %>
                        <option value="<%= airline %>" <%= airline.equals(addedFlight.getAirlineName()) ? "selected" : "" %>><%= airline %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="flightNo">Flight No</label>
                <select id="flightNumber" name="flightNo">
                    <% 
                        List<String> airlineNumbers = (List<String>) request.getAttribute("airlineNumbers");
                        if (airlineNumbers != null) {
                            for (String number : airlineNumbers) {
                    %>
                        <option value="<%= number %>" <%= number.equals(addedFlight.getFlightNo()) ? "selected" : "" %>><%= number %></option>
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
                        <option value="<%= model %>" <%= model.equals(addedFlight.getFlightModel()) ? "selected" : "" %>><%= model %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="from">From</label>
                <select id="from" name="fromLocation">
                    <option value="NYC" <%= "NYC".equals(addedFlight.getFromLocation()) ? "selected" : "" %>>New York City</option>
                    <option value="LAX" <%= "LAX".equals(addedFlight.getFromLocation()) ? "selected" : "" %>>Los Angeles</option>
                    <option value="CHI" <%= "CHI".equals(addedFlight.getFromLocation()) ? "selected" : "" %>>Chicago</option>
                    <option value="ATL" <%= "ATL".equals(addedFlight.getFromLocation()) ? "selected" : "" %>>Atlanta</option>
                    <option value="LHR" <%= "LHR".equals(addedFlight.getFromLocation()) ? "selected" : "" %>>London</option>
                </select>
            </div>

            <div class="form-group">
                <label for="departure">Departure (Date & Time)</label>
                <input type="datetime-local" id="departure" name="departureDateTime" value="<%= addedFlight.getDepartureDateTime().toString().substring(0, 16)%>">
            </div>

            <div class="form-group">
                <label for="to">To</label>
                <select id="to" name="toLocation">
                    <option value="NYC" <%= "NYC".equals(addedFlight.getToLocation()) ? "selected" : "" %>>New York City</option>
                    <option value="LAX" <%= "LAX".equals(addedFlight.getToLocation()) ? "selected" : "" %>>Los Angeles</option>
                    <option value="CHI" <%= "CHI".equals(addedFlight.getToLocation()) ? "selected" : "" %>>Chicago</option>
                    <option value="ATL" <%= "ATL".equals(addedFlight.getToLocation()) ? "selected" : "" %>>Atlanta</option>
                    <option value="LHR" <%= "LHR".equals(addedFlight.getToLocation()) ? "selected" : "" %>>London</option>
                </select>
            </div>

            <div class="form-group">
                <label for="arrival">Arrival (Date & Time)</label>
                <input type="datetime-local" id="arrival" name="arrivalDateTime" value="<%= addedFlight.getArrivalDateTime().toString().substring(0, 16)%>">
            </div>

            <div class="form-group">
                <label for="totalSeats">Total Seats Available</label>
                <input type="number" id="totalSeats" name="totalSeats" value="<%=addedFlight.getTotalSeats() %>">
            </div>

            <div class="form-group">
                <label for="economySeats">Seats in Economy Class</label>
                <input type="number" id="economySeats" name="economySeats" value="<%= addedFlight.getEconomySeats() %>">
            </div>

            <div class="form-group">
                <label for="economyPrice">Price for Economy</label>
                <input type="number" id="economyPrice" name="economyPrice" value="<%= addedFlight.getEconomyPrice()%>">
            </div>

            <div class="form-group">
                <label for="businessSeats">Seats in Business Class</label>
                <input type="number" id="businessSeats" name="businessSeats" value="<%= addedFlight.getBusinessSeats()%>">
            </div>

            <div class="form-group">
                <label for="businessPrice">Price for Business</label>
                <input type="number" id="businessPrice" name="businessPrice" value="<%= addedFlight.getBusinessPrice()%>">
            </div>

            <div class="form-group">
                <input type="submit" value="Save Changes">
            </div>
            <% if (request.getAttribute("message") != null) { %>
                <div><%= request.getAttribute("message") %></div>
            <% } %>
        </form>
    </div>
</body>
</html>
