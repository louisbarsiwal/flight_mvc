<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deleted Flights</title>
    <link rel="stylesheet" type="text/css" href="/CSS/deleted_flight.css">
    
</head>
<body>
    <h1>Deleted Flights</h1>
	<div class="search-form">
		    <form method="get" action="/filterDeletedFlights">
		        <input type="text" name="searchTerm" placeholder="Search by Airline Name or Number..." required>
		        <input type="submit" value="Filter">
		    </form>
		</div>

    <table>
        <thead>
            <tr>
                <th>Airline Name</th>
                <th>Flight No</th>
                <th>Flight Model</th>
                <th>From</th>
                <th>Destination</th>
                <th>Departure Date & Time</th>
                <th>Arrival Date & Time</th>
                <th>Total Seats</th>
                <th>Economy Seats</th>
                <th>Business Seats</th>
                <th>Economy Price</th>
                <th>Business Price</th>
                <th>Action</th> <!-- New column for action -->
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, Object>> deletedFlights = (List<Map<String, Object>>) request.getAttribute("deletedFlights");
                if (deletedFlights != null && !deletedFlights.isEmpty()) {
                    for (Map<String, Object> flight : deletedFlights) {
            %>
            <tr>
                <td><%= flight.get("airline_name") %></td>
                <td><%= flight.get("flight_no") %></td>
                <td><%= flight.get("flight_model") %></td>
                <td><%= flight.get("from_location") %></td>
                <td><%= flight.get("to_location") %></td>
                <td><%= flight.get("departure_datetime") %></td>
                <td><%= flight.get("arrival_datetime") %></td>
                <td><%= flight.get("total_seats") %></td>
                <td><%= flight.get("economy_seats") %></td>
                <td><%= flight.get("business_seats") %></td>
                <td><%= flight.get("economy_price") %></td>
                <td><%= flight.get("business_price") %></td>
                <td>
                    <form action="/restoreFlight" method="post" style="display:inline;">
                        <input type="hidden" name="flightId" value="<%= flight.get("flight_id") %>">
						<input type="hidden" name="airlineName" value="<%= flight.get("airline_name") %>">
												
                        <input type="submit" value="Add Back" class="action-button" onclick="return confirm('Are you sure you want to add back this flight?');">
                    </form>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="13">No deleted flights available.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
	<div class="button-container">

		<a  href="/openDisplayFlightPage">View Flights</a>
		</div>
</body>
</html>
