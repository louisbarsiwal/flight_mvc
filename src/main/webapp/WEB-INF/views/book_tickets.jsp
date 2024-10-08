<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book a Ticket</title>
    <link rel="stylesheet" type="text/css" href="/CSS/book_tickets.css">
</head>
<body>
    <div class="booking-form">
        <h1>Book Your Flight Ticket</h1>
        <form action="bookTicket" method="post" enctype="multipart/form-data">
            <label for="pnrNumber">PNR Number</label>
            <input type="text" id="pnrNumber" name="pnrNumber" required>

            <label for="customerName">Customer Name</label>
            <input type="text" id="customerName" name="customerName" required>

            <label for="phoneNumber">Phone Number</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" pattern="[0-9]{10}" required placeholder="10-digit number">

            <label for="passportNumber">Passport Number</label>
            <input type="text" id="passportNumber" name="passportNumber" required>

            <label for="email">Email ID</label>
            <input type="email" id="email" name="email" required>

            <label for="passengerPhoto">Passenger Photo</label>
            <input type="file" id="passengerPhoto" name="passengerPhoto" accept="image/*" required>

            <label for="bookingDateTime">Booking Date & Time</label>
            <input type="datetime-local" id="bookingDateTime" name="bookingDateTime" required>

            <label for="origin">From</label>
            <input type="text" id="origin" name="origin" required>

            <label for="destination">Destination</label>
            <input type="text" id="destination" name="destination" required>

            <label for="class">Class</label>
            <select id="class" name="class" required>
                <option value="Economy">Economy</option>
                <option value="Business">Business</option>
            </select>

            <label for="seats">No. of Seats</label>
            <input type="number" id="seats" name="seats" required min="1">

            <button type="submit">Book Ticket</button>
            
        </form>
    </div>
</body>
</html>
