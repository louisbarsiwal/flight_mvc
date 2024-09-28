<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Flight Details</title>
	<link rel="stylesheet" type="text/css" href="/CSS/update_flight.css">

	</head>
<body>

    <div class="update-flight-container">
        <h2>Update Flight Information</h2>
        <form action="/flight/update" method="post">
            
            <label for="flightNumber">Flight Number:</label>
            <input type="text" id="flightNumber" name="flightNumber" placeholder="Enter flight number" required>

            <label for="departure">Departure Location:</label>
            <input type="text" id="departure" name="departure" placeholder="Enter departure location" required>

            <label for="arrival">Arrival Location:</label>
            <input type="text" id="arrival" name="arrival" placeholder="Enter arrival location" required>

            <label for="date">Flight Date:</label>
            <input type="date" id="date" name="date" required>

            <label for="time">Flight Time:</label>
            <input type="time" id="time" name="time" required>

            <label for="price">Ticket Price:</label>
            <input type="text" id="price" name="price" placeholder="Enter ticket price" required>

            <button type="submit">Update Flight</button>

        </form>
    </div>

</body>
</html>
