<%@ page contentType="text/html;charset=UTF-8" %> 
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
            let businessSeats = parseInt(document.getElementById('businessSeats').value) || 0;
            let businessPrice = <%= flight.getBusinessPrice() %>;

            let economySeats = parseInt(document.getElementById('economySeats').value) || 0;
            let economyPrice = <%= flight.getEconomyPrice() %>;

            let businessTotal = businessSeats * businessPrice;
            let economyTotal = economySeats * economyPrice;

            document.getElementById('businessClassPrice').value = businessTotal;
            document.getElementById('economyClassPrice').value = economyTotal;

            let totalFare = businessTotal + economyTotal;
            document.getElementById('totalFare').value = totalFare;

            // Set hidden fields for the calculated totals
            document.getElementById('totalBusinessPrice').value = businessTotal;
            document.getElementById('totalEconomyPrice').value = economyTotal;
        }

        
		// Validate seat inputs and show warning messages
		function validateSeats() {
		    let businessSeatsInput = document.getElementById('businessSeats');
		    let economySeatsInput = document.getElementById('economySeats');

		    let businessSeats = parseInt(businessSeatsInput.value) || 0;
		    let economySeats = parseInt(economySeatsInput.value) || 0;

		    let availableBusinessSeats = <%= flight.getBusinessSeats() %>;
		    let availableEconomySeats = <%= flight.getEconomySeats() %>;

		    // Check for negative seat inputs
		    if (businessSeats < 0) {
		        alert("You cannot enter negative numbers for Business Class seats.");
		        businessSeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    } else if (economySeats < 0) {
		        alert("You cannot enter negative numbers for Economy Class seats.");
		        economySeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    }

		    // Check for Business Class availability
		    if (availableBusinessSeats === 0 && businessSeats > 0) {
		        alert("No Business Class seats available.");
		        businessSeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    } else if (businessSeats > availableBusinessSeats) {
		        alert(`You cannot enter more than available Business Class seats.`);
		        businessSeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    }

		    // Check for Economy Class availability
		    if (availableEconomySeats === 0 && economySeats > 0) {
		        alert("No Economy Class seats available.");
		        economySeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    } else if (economySeats > availableEconomySeats) {
		        alert(`You cannot enter more than available Economy Class seats.`);
		        economySeatsInput.value = "0"; // Reset to zero
		        calculateFare(); // Recalculate fare based on the adjusted values
		        return false; // Prevent form submission
		    }

		    // Check if both classes have no available seats
		    if (availableBusinessSeats === 0 && availableEconomySeats === 0) {
		        alert("No seats are available in both Business and Economy Class. You cannot proceed to do payment.");
		        return false; // Prevent form submission
		    }


		    return true; // Allow form submission
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
        <p><strong>Business Class Price per Seat:</strong>₹<%= flight.getBusinessPrice() %></p>
        <p><strong>Economy Seats Available:</strong> <%= flight.getEconomySeats() %></p>
        <p><strong>Economy Class Price per Seat:</strong>₹<%= flight.getEconomyPrice() %></p>
    </div>

    <form action="/booking" method="post" class="fare-form" onsubmit="return validateSeats()">
        <input type="hidden" name="flightId" value="<%= flight.getFlightId() %>">
        <input type="hidden" name="flightNo" value="<%= flight.getFlightNo() %>">
        <input type="hidden" name="airlineName" value="<%= flight.getAirlineName() %>">
        <input type="hidden" name="flightModel" value="<%= flight.getFlightModel() %>">
        <input type="hidden" name="fromLocation" value="<%= flight.getFromLocation() %>">
        <input type="hidden" name="toLocation" value="<%= flight.getToLocation() %>">
        <input type="hidden" name="departureDatetime" value="<%= flight.getDepartureDateTime() %>">
        <input type="hidden" name="arrivalDatetime" value="<%= flight.getArrivalDateTime() %>">
        <input type="hidden" name="economyPrice" value="<%= flight.getEconomyPrice() %>">
        <input type="hidden" name="businessPrice" value="<%= flight.getBusinessPrice() %>">

	    <label for="businessSeats">Enter Number of Business Seats:</label>
	    <input type="number" id="businessSeats" name="businessSeats" min="0" oninput="calculateFare(); validateSeats()" required>

	    <label for="businessClassPrice">Business Class Price:</label>
	    <input type="text" id="businessClassPrice" name="businessClassPrice" readonly>

	    <label for="economySeats">Enter Number of Economy Seats:</label>
	    <input type="number" id="economySeats" name="economySeats" min="0" oninput="calculateFare(); validateSeats()" required>

	    <label for="economyClassPrice">Economy Class Price:</label>
	    <input type="text" id="economyClassPrice" name="economyClassPrice" readonly>

	    <label for="totalFare">Total Fare:</label>
	    <input type="text" id="totalFare" name="totalFare" readonly>

	    <!-- Hidden fields to store calculated prices -->
	    <input type="hidden" id="totalBusinessPrice" name="totalBusinessPrice">
	    <input type="hidden" id="totalEconomyPrice" name="totalEconomyPrice">

	    <button type="submit" class="btn btn-primary">Proceed to Payment</button>
	</form>



    <script>
        // Call validateSeats on page load to disable fields if needed
        validateSeats();
    </script>

</body>
</html>
