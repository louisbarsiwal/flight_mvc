<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Successful</title>
	<style>
		body {
		    font-family: Arial, sans-serif;
		    background-color: #F1FAEE; /* Light background */
		    color: #1D3557; /* Dark blue text */
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		}

		.container {
		    background-color: #A8DADC; /* Light teal background */
		    border-radius: 8px;
		    padding: 30px;
		    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
		    text-align: center;
		}

		.dialog-box h1 {
		    color: #457B9D; /* Medium teal */
		}

		.dialog-box p {
		    margin: 20px 0;
		    font-size: 1.2em;
		}

		button {
		    background-color: #E63946; /* Red button */
		    color: white;
		    border: none;
		    border-radius: 5px;
		    padding: 10px 20px;
		    font-size: 1em;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		}

		button:hover {
		    background-color: #d62839; /* Darker red on hover */
		}

	</style>
    <link rel="stylesheet" type="text/css" href="/CSS/payment_success.css">
</head>
<body>
    <div class="container">
        <div class="dialog-box">
            <h1>Payment Successful!</h1>
            <p>Your booking is confirmed.</p>
            <button onclick="goToHome()">Go to Home</button>
        </div>
    </div>

    <script>
        function goToHome() {
            window.location.href = '/user/openPassengerDashboard'; // Replace with your home page URL
        }
    </script>
</body>
</html>
