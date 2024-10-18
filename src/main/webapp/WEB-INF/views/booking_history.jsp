<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History</title>
    <link rel="stylesheet" type="text/css" href="/CSS/booking_history.css">
</head>
<body>

<h2>Booking History</h2>
<table>
    <thead>
        <tr>
			<th>PNR Number</th>
            <th>Airline Name</th>
            <th>Flight Number</th>
			<th>Flight Model</th>
			<th>From</th>
            <th>To</th>
			<th>Booking DateTime</th>
            <th>Departure DateTime</th>
            <th>Arrival DateTime</th>
            <th>Business Seats Booked</th>
			<th>Business Class Price</th>
			<th>Economy Seats Booked</th>
			<th>Economy Class Price</th>
			<th>Total Fare</th>
			
            <th>Action</th>
        </tr>
    </thead>
	<tbody>
	            <% 
	                List<Map<String, Object>> bookings = (List<Map<String, Object>>) request.getAttribute("bookings");
	                if (bookings != null && !bookings.isEmpty()) {
	                    for (Map<String, Object> booking : bookings) {
	            %>
	            <tr>
	                <td><%= booking.get("airline_name") %></td>
	                <td><%=booking.get("flight_no") %></td>
	                <td><%= booking.get("flight_model") %></td>
	                <td><%= booking.get("from_location") %></td>
	                <td><%=booking.get("to_location") %></td>
	                <td><%= booking.get("departure_datetime") %></td>
	                <td><%= booking.get("arrival_datetime") %></td>
					<td><%= booking.get("economy_seats") %></td>
					<td><%= booking.get("economy_price") %></td>
	                <td><%= booking.get("business_seats") %></td>
	                <td><%= booking.get("business_price") %></td>
					<td><%= booking.get("total_price") %></td>
					<td><%= booking.get("airline_pnr_no") %></td>


	                <td>
	                    <form action="/cancelBooking" method="post" style="display:inline;">
	                        <input type="hidden" name="bookingId" value="<%= booking.get("booking_id") %>">
							<input type="hidden" name="airlineName" value="<%= booking.get("airline_name") %>">
													
	                        <input type="submit" value="Cancel" class="cancel-button" onclick="return confirm('Are you sure you want to cancel your booking?');">
	                    </form>
	                </td>
</table>

<script>
    function confirmCancel() {
        if (confirm("Are you sure you want to cancel this booking?")) {
            // Implement cancel booking action
        }
    }
</script>

</body>
</html>

