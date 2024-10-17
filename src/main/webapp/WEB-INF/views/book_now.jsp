<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="flightmanagement.app.entities.AddedFlight" %>
<%
    // Retrieve the flight details from the request attribute
    AddedFlight flight = (AddedFlight) request.getAttribute("flight");
    if (flight == null) {
        out.println("<p>No flight details available.</p>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Flight</title>
	<link rel="stylesheet" type="text/css" href="/CSS/book_now.css">
    <script>
        // JavaScript to calculate the total fare dynamically
        function calculateFare() {
            let businessSeats = document.getElementById('businessSeats').value || 0;
            let businessPrice = <%= flight.getBusinessPrice() %>;

            let economySeats = document.getElementById('economySeats').value || 0;
            let economyPrice = <%= flight.getEconomyPrice() %>;

            let businessTotal = businessSeats * businessPrice;
            let economyTotal = economySeats * economyPrice;

            document.getElementById('businessClassPrice').value = businessTotal;
            document.getElementById('economyClassPrice').value = economyTotal;

            let totalFare = businessTotal + economyTotal;
            document.getElementById('totalFare').value = totalFare;
        }
    </script>
</head>
<body>
    <div class="flight-details">
        <h2>Flight Details</h2>
        <p><strong>Airline Name:</strong> <%= flight.getAirlineName() %></p>
        <p><strong>Flight Number:</strong> <%= flight.getFlightNo() %></p>
        <p><strong>Flight Model:</strong> <%= flight.getFlightModel() %></p>
        <p><strong>From:</strong> <%= flight.getFromLocation() %></p>
        <p><strong>To:</strong> <%= flight.getToLocation() %></p>
        <p><strong>Departure Time:</strong> <%= flight.getDepartureDateTime() %></p>
        <p><strong>Arrival Time:</strong> <%= flight.getArrivalDateTime() %></p>
        <p><strong>Business Seats Available:</strong> <%= flight.getBusinessSeats() %></p>
        <p><strong>Business Class Price per Seat:</strong> $<%= flight.getBusinessPrice() %></p>
        <p><strong>Economy Seats Available:</strong> <%= flight.getEconomySeats() %></p>
        <p><strong>Economy Class Price per Seat:</strong> $<%= flight.getEconomyPrice() %></p>
    </div>

    <form action="proceedToPayment" method="post" class="fare-form">
        <input type="hidden" name="flightId" value="<%= flight.getFlightId() %>">

        <label for="businessSeats">Enter Number of Business Seats:</label>
        <input type="number" id="businessSeats" name="businessSeats" min="0" oninput="calculateFare()" required>

        <label for="businessClassPrice">Business Class Price:</label>
        <input type="text" id="businessClassPrice" name="businessClassPrice" readonly>

        <label for="economySeats">Enter Number of Economy Seats:</label>
        <input type="number" id="economySeats" name="economySeats" min="0" oninput="calculateFare()" required>

        <label for="economyClassPrice">Economy Class Price:</label>
        <input type="text" id="economyClassPrice" name="economyClassPrice" readonly>

        <label for="totalFare">Total Fare:</label>
        <input type="text" id="totalFare" name="totalFare" readonly>

        <button type="submit" class="btn btn-primary">Proceed to Payment</button>
    </form>
</body>
</html>
