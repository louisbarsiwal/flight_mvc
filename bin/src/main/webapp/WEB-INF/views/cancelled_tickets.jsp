<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cancelled Bookings</title>
    <link rel="stylesheet" type="text/css" href="/CSS/deleted_airline.css">
</head>
<body>
    <h1>Cancelled Bookings</h1>
    <table>
        <thead>
            <tr>
                <th>airline_name</th>
                <th>flight_no</th>
                <th>flight_model</th>
                <th>from_location</th>
				<th>to_location</th>
				<th>departure_datetime</th>
				<th>arrival_datetime</th>
				<th>economy_seats</th>
				<th>economy_price</th>
				<th>business_seats</th>
				<th>business_price</th>
				<th>total_price</th>										
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, Object>> cancelledBookings = (List<Map<String, Object>>) request.getAttribute("cancelledBookings");
                if (cancelledBookings != null && !cancelledBookings.isEmpty()) {
                    for (Map<String, Object> booking : cancelledBookings) {
            %>
            <tr>
                <td><%= booking.get("airline_name") %></td>
                <td><%= booking.get("flight_no") %></td>
                <td><%= booking.get("flight_model") %></td>
                <td><%= booking.get("from_location") %></td>
                <td><%= booking.get("to_location") %></td>
                <td><%= booking.get("departure_datetime") %></td>
                <td><%= booking.get("arrival_datetime") %></td>
                <td><%= booking.get("economy_seats") %></td>
                <td><%= booking.get("economy_price") %></td>
                <td><%= booking.get("business_seats") %></td>
                <td><%= booking.get("business_price") %></td>
                <td><%= booking.get("total_price") %></td>
           
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No cancelled bookings found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
