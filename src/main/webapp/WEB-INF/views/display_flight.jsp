<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Flights</title>
    <link rel="stylesheet" type="text/css" href="/CSS/display_flight.css">
</head>
<body>
    <h1>Available Flights</h1>
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
                <th>Total Seats Available</th>
                <th>Seats Available in Economy Class</th>
                <th>Seats Available in Business Class</th>
                <th>Price for Economy</th>
                <th>Price for Business</th>
                <th>Action</th>
                <th>Edit</th> <!-- New column for edit -->
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, Object>> flights = (List<Map<String, Object>>) request.getAttribute("flights");
                if (flights != null && !flights.isEmpty()) {
                    for (Map<String, Object> flight : flights) {
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
                    <form action="/deleteFlight" method="post" style="display:inline;">
                        <input type="hidden" name="flightId" value="<%= flight.get("flight_id") %>">
                        <input type="submit" value="Delete" class="delete-button" onclick="return confirm('Are you sure you want to delete this flight?');">
                    </form>
                </td>
                <td>
                    <form action="/openEditFlightPage" method="get" style="display:inline;">
                        <input type="hidden" name="flightId" value="<%= flight.get("flight_id") %>">
                        <input type="submit" value="Edit" class="edit-button">
                    </form>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="14">No flights available.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
