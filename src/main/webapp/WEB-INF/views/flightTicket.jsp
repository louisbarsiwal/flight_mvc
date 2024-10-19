<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            background-color: #4CAF50; /* Green */
            color: white; /* White text */
            border: none; /* No borders */
            padding: 10px 15px; /* Some padding */
            cursor: pointer; /* Pointer/hand icon */
        }
        .container button:hover {
            background-color: #45a049; /* Darker green on hover */
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
                <td>New Delhi to Kathmandu</td>
            </tr>
            <tr>
                <td>Date</td>
                <td>Wed, 12 Jun 2024</td>
            </tr>
            <tr>
                <td>Airline</td>
                <td>Vistara</td>
            </tr>
            <tr>
                <td>Flight Number</td>
                <td>UK - 157</td>
            </tr>
            <tr>
                <td>Fare Type</td>
                <td>Eco Value</td>
            </tr>
            <tr>
                <td>Departure</td>
                <td>DEL 07:55 (New Delhi - Indira Gandhi Airport, Terminal 3)</td>
            </tr>
            <tr>
                <td>Arrival</td>
                <td>KTM 10:30 (Kathmandu - Tribuvan Terminal I)</td>
            </tr>
            <tr>
                <td>Duration</td>
                <td>2h 20min</td>
            </tr>
            <tr>
                <td>Class</td>
                <td>Economy</td>
            </tr>
            <tr>
                <td>Payment Method</td>
                <td>UPI</td>
            </tr>
        </table>

        <h3>Fare Breakup</h3>
        <table>
            <tr>
                <td>Base Fare</td>
                <td>Rs. 3,500</td>
            </tr>
            <tr>
                <td>Discounts and Cashbacks</td>
                <td>Rs. -1,100</td>
            </tr>
            <tr>
                <td>Taxes and Fees</td>
                <td>Rs. 2,042</td>
            </tr>
            <tr>
                <td>GST (Airline)</td>
                <td>Rs. 179</td>
            </tr>
            <tr class="header">
                <td>Total Fare</td>
                <td>Rs. 4,621</td>
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
