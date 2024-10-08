<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History</title>
    <link rel="stylesheet" type="text/css" href="/CSS/cancelled_tickets.css">
</head>
<body>

<h2>Cancelled Tickets</h2>
<table>
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>PNR Number</th>
            <th>Booking Date & Time</th>
            <th>Flight No</th>
            <th>Airline Name</th>
            <th>Flight Model</th>
            <th>From</th>
            <th>Destination</th>
            <th>Departure Date & Time</th>
            <th>Arrival Date & Time</th>
            <th>Class</th>
            <th>Total Seats Booked</th>
        </tr>
    </thead>
    <tbody>
        <%-- Sample Data --%>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>PNR12345</td>
            <td>2024-10-01 14:30</td>
            <td>ind1</td>
            <td>Indigo</td>
            <td>zsa</td>
            <td>NYC</td>
            <td>LAX</td>
            <td>2024-10-07 12:27</td>
            <td>2024-10-07 16:30</td>
            <td>Economy</td>
            
            <td>3</td>
        </tr>
        <%-- Add dynamic rows here from your backend --%>
    </tbody>
</table>


</body>
</html>

