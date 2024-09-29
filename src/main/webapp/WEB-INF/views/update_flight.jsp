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

	<div class="form-container">
	        <h2>Update Flight</h2> <!-- Added a title for clarity -->
			<form action="/openupdateflight" method="post">
	            <div class="form-group">
	                <label for="airline">Airline Name</label>
	                <select id="airline" name="airline">
	                    <option value="airline1">Airline 1</option>
	                    <option value="airline2">Airline 2</option>
	                    <!-- Add more options as needed -->
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="flightNo">Flight No</label>
	                <select id="flightNo" name="flightNo">
	                    <option value="flight1">Flight 1</option>
	                    <option value="flight2">Flight 2</option>
	                    <!-- Add more options as needed -->
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="flightModel">Flight Model</label>
	                <select id="flightModel" name="flightModel">
	                    <option value="model1">Model 1</option>
	                    <option value="model2">Model 2</option>
	                    <!-- Add more options as needed -->
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="from">From</label>
	                <select id="from" name="from">
	                    <option value="NYC">New York City</option>
	                    <option value="LAX">Los Angeles</option>
	                    <option value="CHI">Chicago</option>
	                    <option value="ATL">Atlanta</option>
	                    <option value="LHR">London</option>
	                    <!-- Add more options as needed -->
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="departure">Departure (Date & Time)</label>
	                <input type="datetime-local" id="departure" name="departure">
	            </div>
	            <div class="form-group">
	                <label for="to">To</label>
	                <select id="to" name="to">
	                    <option value="NYC">New York City</option>
	                    <option value="LAX">Los Angeles</option>
	                    <option value="CHI">Chicago</option>
	                    <option value="ATL">Atlanta</option>
	                    <option value="LHR">London</option>
	                    <!-- Add more options as needed -->
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="arrival">Arrival (Date & Time)</label>
	                <input type="datetime-local" id="arrival" name="arrival">
	            </div>
	            <div class="form-group">
	                <label for="totalSeats">Total Seats Available</label>
	                <input type="number" id="totalSeats" name="totalSeats">
	            </div>
	            <div class="form-group">
	                <label for="economySeats">Seats in Economy Class</label>
	                <input type="number" id="economySeats" name="economySeats">
	            </div>
	            <div class="form-group">
	                <label for="economyPrice">Price for Economy</label>
	                <input type="number" id="economyPrice" name="economyPrice">
	            </div>
	            <div class="form-group">
	                <label for="businessSeats">Seats in Business Class</label>
	                <input type="number" id="businessSeats" name="businessSeats">
	            </div>
	            <div class="form-group">
	                <label for="businessPrice">Price for Business</label>
	                <input type="number" id="businessPrice" name="businessPrice">
	            </div>
	            <div class="form-group">
	                <input type="submit" value="Update Flight">
	            </div>
	        </form>
	    </div>

</body>
</html>
