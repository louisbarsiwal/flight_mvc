<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancel Flight</title>
    <link rel="stylesheet" type="text/css" href="/CSS/cancel_flight.css">
</head>
<body>

    <div class="cancel-flight-container">
        <h2>Cancel Flight</h2>
        <form action="/flight/cancel" method="post">
            
            <label for="flightNumber">Flight Number:</label>
            <input type="text" id="flightNumber" name="flightNumber" placeholder="Enter flight number" required>

            <label for="reason">Reason for Cancellation:</label>
            <input type="text" id="reason" name="reason" placeholder="Enter reason for cancellation" required>

            <button type="submit">Cancel Flight</button>

        </form>
    </div>

</body>
</html>
