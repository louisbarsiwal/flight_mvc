<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            </tr>
        </thead>
        <tbody>
            <c:forEach var="flight" items="${flights}">
            <tr>
                <td>${flight.airline_name}</td>
                <td>${flight.flight_no}</td>
                <td>${flight.flight_model}</td>
                <td>${flight.from_location}</td>
                <td>${flight.to_location}</td>
                <td>${flight.departure_datetime}</td>
                <td>${flight.arrival_datetime}</td>
                <td>${flight.total_seats}</td>
                <td>${flight.economy_seats}</td>
                <td>${flight.business_seats}</td>
                <td>${flight.economy_price}</td>
                <td>${flight.business_price}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
