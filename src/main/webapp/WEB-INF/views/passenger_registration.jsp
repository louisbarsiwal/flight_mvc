<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passenger Registration</title>
    <link rel="stylesheet" type="text/css" href="/CSS/passenger_registration.css">

    <script>
        // Main function to validate the form
        function validateForm(event) {
            event.preventDefault(); // Prevent form submission if validation fails

            // Get field values
            const firstName = document.getElementById('firstname').value.trim();
            const lastName = document.getElementById('lastname').value.trim();
            const email = document.getElementById('email').value.trim();
            const mobile = document.getElementById('mobile').value.trim();
            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('newpassword').value;
            const confirmPassword = document.getElementById('confirmpassword').value;
            const gender = document.querySelector('input[name="gender"]:checked'); // Get selected gender

            // Regular expressions for validation
            const namePattern = /^[A-Za-z\s]{3,20}$/; // Alphabets, 3-20 characters
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // Standard email format
            const mobilePattern = /^\d{10}$/; // Exactly 10 digits
            const usernamePattern = /^[a-zA-Z0-9_]{6,20}$/; // Alphanumeric with underscore, 6-20 characters
            const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/; // Strong password

            // First Name validation
            if (!namePattern.test(firstName)) {
                alert("First name must be alphabets only and between 3-20 characters.");
                return false;
            }

            // Last Name validation
            if (!namePattern.test(lastName)) {
                alert("Last name must be alphabets only and between 3-20 characters.");
                return false;
            }

            // Email validation
            if (!emailPattern.test(email)) {
                alert("Please enter a valid email address (e.g., example@gmail.com).");
                return false;
            }

            // Mobile Number validation
            if (!mobilePattern.test(mobile)) {
                alert("Mobile number must be exactly 10 digits.");
                return false;
            }

            // Gender validation
            if (!gender) {
                alert("Please select a gender.");
                return false;
            }

            // Username validation
            if (!usernamePattern.test(username)) {
                alert("Username must be alphanumeric, can include underscores, and be 6-20 characters long.");
                return false;
            }

            // Password validation
            if (!passwordPattern.test(password)) {
                alert("Password must be at least 8 characters, include one uppercase, one lowercase, one number, and one special character.");
                return false;
            }

            // Confirm Password validation
            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false;
            }

            // If all validations pass, submit the form
            alert("Registration successful!");
            document.getElementById('registrationForm').submit(); // Submit the form
        }
    </script>
</head>

<body>

<div class="registration-container">
    <form id="registrationForm" action="/user/passengerregister" method="post" enctype="multipart/form-data" onsubmit="validateForm(event)">
        <h1 style="color: #457B9D;">Passenger Registration</h1>

        <label for="firstname">First Name</label>
        <input type="text" id="firstname" name="firstName" placeholder="Enter your First Name" required>

        <label for="lastname">Last Name</label>
        <input type="text" id="lastname" name="lastName" placeholder="Enter your Last Name" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="emailId" placeholder="Enter your email" required>

        <label for="mobile">Mobile Number</label>
        <input type="tel" id="mobile" name="mobileNo" placeholder="Enter your mobile number" required>
        <small>Format: 9XX5XX8XXX (10 digits)</small>

        <label for="age">Age</label>
        <input type="number" id="age" name="age" placeholder="Enter your Age" required min="0" max="120">

        <div class="gender-container">
            <label>Gender</label><br>
            <label><input type="radio" name="gender" value="male" required> Male</label>
            <label><input type="radio" name="gender" value="female"> Female</label>
            <label><input type="radio" name="gender" value="other"> Other</label>
        </div>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your Username" required>

        <label for="newpassword">New Password</label>
        <input type="password" id="newpassword" name="password" placeholder="Enter your Password" required>

        <label for="confirmpassword">Confirm Password</label>
        <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm your Password" required>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>
