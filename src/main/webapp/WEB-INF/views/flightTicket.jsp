<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightmanagement.app.entities.BookingFlight" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmation</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        .header { background-color: #f2f2f2; font-weight: bold; }
        .container button {
            border-radius: 4px;
            background-color: #4CAF50; 
            color: white; 
            border: none; 
            padding: 10px 15px; 
            cursor: pointer; 
        }
        .container button:hover {
            background-color: #45a049; 
        }
    </style>
    <script>
        window.onload = function() {
            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
            if (message) {
                alert(message);
            }
        };
    </script>
</head>
<body>
    <div class="container">
        <h2>Booking Confirmation</h2>
        <a class="download-btn" href="${pageContext.request.contextPath}/downloadFile">
            <button>Download PDF</button>
        </a>
        <table>
            <tr class="header">
                <td>Trip ID</td>
                <td>240603261338</td>
            </tr>
            <tr>
                <td>Passenger</td>
                <td>Mr Louis Barsiwal</td>
            </tr>
            <tr>
                <td>Baggage Allowance</td>
                <td>Check-in: 20kg, Cabin: 7kg</td>
            </tr>
        </table>

        <h3>Flight Details</h3>
        <table>
            <tr class="header">
                <td>Route</td>
                <td><%= request.getParameter("fromLocation") %> to <%= request.getParameter("toLocation") %></td>
            </tr>
            <tr>
                <td>Date</td>
                <td><%= request.getParameter("departureDatetime") %></td>
            </tr>
            <tr>
                <td>Airline</td>
                <td><%= request.getParameter("airlineName") %></td>
            </tr>
            <tr>
                <td>Flight Number</td>
                <td><%= request.getParameter("flightNo") %></td>
            </tr>
            <tr>
                <td>Fare Type</td>
                <td>Eco Value</td>
            </tr>
            <tr>
                <td>Departure</td>
                <td><%= request.getParameter("departureDatetime") %> (Departure Airport)</td>
            </tr>
            <tr>
                <td>Arrival</td>
                <td>(Arrival Airport)</td>
            </tr>
            <tr>
                <td>Duration</td>
                <td>(Duration)</td>
            </tr>
            <tr>
                <td>Class</td>
                <td>Economy</td>
            </tr>
            <tr>
                <td>Payment Method</td>
                <td><%= request.getParameter("paymentMethod") %></td>
            </tr>
        </table>

		<h3>Fare Breakup</h3>
		<table>
		    <tr>
		        <td>Base Fare</td>
		        <td>Rs. <%= request.getParameter("economyPrice") != null ? request.getParameter("economyPrice") : "0" %></td>
		    </tr>
		    <tr>
		        <td>Discounts and Cashbacks</td>
		        <td>Rs. -<%= request.getParameter("totalBusinessPrice") != null ? request.getParameter("totalBusinessPrice") : "0" %></td>
		    </tr>
		    <tr>
		        <td>Taxes and Fees</td>
		        <td>Rs. <%= request.getParameter("totalEconomyPrice") != null ? request.getParameter("totalEconomyPrice") : "0" %></td>
		    </tr>
		    <tr>
		        <td>GST (Airline)</td>
		        <td>Rs. (GST Amount)</td>
		    </tr>
		    <tr class="header">
		        <td>Total Fare</td>
		        <td>Rs. <%= 
		            Double.parseDouble(request.getParameter("economyPrice") != null ? request.getParameter("economyPrice") : "0") + 
		            Double.parseDouble(request.getParameter("totalBusinessPrice") != null ? request.getParameter("totalBusinessPrice") : "0") + 
		            Double.parseDouble(request.getParameter("totalEconomyPrice") != null ? request.getParameter("totalEconomyPrice") : "0") 
		        %></td>
		    </tr>
		</table>


        <h3>Additional Information</h3>
        <p>Airline PNR: 5YDLEC</p>
        <p>Please reach the airport 4 hours before the departure time. Check-in counters at the airport close 60 minutes before departure.</p>
        <p>Your carry-on baggage shouldn't weigh more than 7kg.</p>
        <p>Carry photo identification; you will need it as proof of identity while checking in.</p>
        <p>If cancellation or amendment is done, Cleartrip will charge Rs. 700/- per passenger per flight.</p>
        <p>Kindly ensure that you have the relevant visa, immigration clearance, and travel with a passport valid for at least 6 months.</p>
    </div>
</body>
</html>
