<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="flightmanagement.app.entities.AddedFlight" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Management System</title>
</head>
<body>
				
			
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
</head>
<body>
	<!--h1>Flight  Application</h1-->
<div class="header" id="header">
 
  <span id="companyName">TravelWings</span>
   <div class="profile-icon" onclick="toggleDropdown()">
     <img src="images/profile-icon.png" alt="Profile Icon">
     <div id="dropdown" class="dropdown-content">
       <a href="/user/openBoLoginPage">Business Owner</a>
       <a href="/user/openFmLoginPage">Flight Manager</a>
       <a href="/user/openPassengerLogin">Passenger</a>
       
    </div>
  </div>
</div>



<div id="main">
  <div class="booking-form">
    <form action="/" method= "GET" onsubmit="searchFlights(event)">
		<select id="source" name="source" required>
		    <option value="" disabled selected>Source</option>
		    <option value="DEL">New Delhi</option>
		    <option value="BOM">Mumbai</option>
			<option value="MAA">Chennai</option>
			<option value="HYD">Hyderabad</option>
			<option value="BLR">Bangalore</option>

		</select>

		<select id="destination" name="destination" required>
		    <option value="" disabled selected>Destination</option>
		    <option value="DEL">New Delhi</option>
		    <option value="BOM">Mumbai</option>
			<option value="MAA">Chennai</option>
			<option value="HYD">Hyderabad</option>
			<option value="BLR">Bangalore</option>

		</select>


      <select name="tripType" id="tripType" onchange="toggleReturnDate()" required>
        <option value="oneway">One Way</option>
      </select>

		<input type="date" id="departureDate" name="departureDate" required>
      <input type="date" name="returnDate" id="returnDate" style="display:none;">

      <input type="number" name="passengers" min="1" value="1" placeholder=" no.of seats" required>

      <select name="class" required>
        <option value="economy">Economy</option>
        <option value="business">Business</option>
      </select>

      <button type="submit" onClick="searchFlights(event)">Search</button>
    </form>
  </div>
  
  <div class="available-flights" id="availableFlights">
	<%
	          List<AddedFlight> flights = (List<AddedFlight>) request.getAttribute("flights");
	          if (flights != null && !flights.isEmpty()) {
	              for (AddedFlight flight : flights) {
	      %>
	                  <div class="flight-card">
	                      <p><strong>From:</strong> <%= flight.getFromLocation() %></p>
	                      <p><strong>To:</strong> <%= flight.getToLocation() %></p>
	                      <p><strong>Departure Time:</strong> <%= flight.getDepartureDateTime() %></p>
	                  </div>
					  
	      <%
	              }
	          }
	     %>
            </div>
</div>

<script>
function openNav() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("header").style.marginLeft = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("header").style.marginLeft = "0";
  document.getElementById("main").style.marginLeft = "0";
}

function toggleDropdown() {
  document.getElementById("dropdown").classList.toggle("show");
}

function toggleReturnDate() {
  var tripType = document.getElementById("tripType").value;
  var returnDate = document.getElementById("returnDate");
  if (tripType === "return") {
    returnDate.style.display = "inline-block";
  } else {
    returnDate.style.display = "none";
  }
}

function setMinDate() {
  var today = new Date().toISOString().split('T')[0];
  document.getElementById("departureDate").setAttribute('min', today);
  document.getElementById("returnDate").setAttribute('min', today);
}

window.onload = setMinDate;

window.onclick = function(event) {
  if (!event.target.matches('.profile-icon img')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}


document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector(".booking-form form"); // Ensure this targets your form correctly
    form.addEventListener("submit", searchFlights);
});


function displayFlights(flights) {
    var availableFlightsDiv = document.getElementById("availableFlights");
    availableFlightsDiv.innerHTML = ""; // Clear previous results

    if (flights.length === 0) {
        availableFlightsDiv.innerHTML = `<p>No flights available for the selected criteria.</p>`;
        return;
    }

    flights.forEach(flight => {
        var flightCard = document.createElement("div");
        flightCard.className = "flight-card";
        flightCard.innerHTML = `
            <div class="flight-details">
                <h3>${flight.airlineName} (${flight.flightNo})</h3>
                <p>Model: ${flight.flightModel}</p>
                <p>From: ${flight.fromLocation} - To: ${flight.toLocation}</p>
                <p>Departure: ${flight.departureDateTime} - Arrival: ${flight.arrivalDateTime}</p>
                <p>Economy Price: ₹${flight.economyPrice} - Business Price: ₹${flight.businessPrice}</p>
            </div>
            <button class="book-now" onclick="bookFlight('${flight.flightNo}')">Book Now</button>
        `;
        availableFlightsDiv.appendChild(flightCard);
    });
}




</script>
</body>
</html>
