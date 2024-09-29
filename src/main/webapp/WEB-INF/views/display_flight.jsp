<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Flights</title>
	<link rel="stylesheet" type="text/css" href="/CSS/display_flight.css">
	</head>
<body>
    <h1>Available Flights</h1>
    <table>
        <thead>
            <tr>
                <th>Flight No</th>
                <th>Flight Model</th>
                <th>From</th>
                <th>Destination</th>
                <th>Departure Date & Time</th>
                <th>Arrival Date & Time</th>
                <th>Total Seats Available</th>
                <th>Seats Available in Economy Class</th>
                <th>Seats Available in Business Class</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Sample data, replace with actual data from your database
                String[][] flights = {
                    {"AI101", "Boeing 777", "New Delhi", "New York", "2024-10-01 10:00", "2024-10-01 18:00", "300", "200", "100"},
                    {"BA202", "Airbus A380", "London", "Sydney", "2024-10-02 12:00", "2024-10-03 08:00", "500", "350", "150"}
                };
                for (String[] flight : flights) {
            %>
            <tr>
                <td><%= flight[0] %></td>
                <td><%= flight[1] %></td>
                <td><%= flight[2] %></td>
                <td><%= flight[3] %></td>
                <td><%= flight[4] %></td>
                <td><%= flight[5] %></td>
                <td><%= flight[6] %></td>
                <td><%= flight[7] %></td>
                <td><%= flight[8] %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
