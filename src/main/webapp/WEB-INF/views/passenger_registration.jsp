<!--%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
	<link rel="stylesheet" type="text/css" href="/CSS/passenger_registration.css">
</head>
<body>

<div class="registration-container">
    <form action="/user/passengerregister" method="post" enctype="multipart/form-data">
		<h1 style="color: #457B9D;">Passenger Registration</h1>
        <label for="profileImage">Profile Image</label>
        <input type="file" id="profileImage" name="profileImage" accept="image/*">

        <label for="firstname">First Name</label>
        <input type="text" id="firstname" name="firstName" placeholder="Enter your Firstname"required>

        <label for="lastname">Last Name</label>
        <input type="text" id="lastname" name="lastName" placeholder="Enter your Lastname" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="emailId"placeholder="Enter your email" required>

        <label for="mobile">Mobile Number</label>
        <input type="tel" id="mobile" name="mobileNo" placeholder="Enter your mobile number" required pattern="[0-9]{10}" required>
		<small>format:9XX5XX8XXX please enter 10 digits</small>

        <label for="age">Age</label>
        <input type="number" id="age" name="age" placeholder="Enter your Age" required>

        <div class="gender-container">
            <label>Gender</label><br>
            <label><input type="radio" name="gender" value="male" required> Male</label>
            <label><input type="radio" name="gender" value="female"> Female</label>
            <label><input type="radio" name="gender" value="other"> Other</label>
        </div>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>

        <label for="newpassword">New Password</label>
        <input type="password" id="newpassword" name="password" placeholder="Enter your password" required>

        <label for="confirmpassword">Confirm Password</label>
        <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm your password" required>

        <button type="submit">Click Here To Register</button>
    </form>
</div>

</body>
</html-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passenger Registration</title>
    <link rel="stylesheet" type="text/css" href="/CSS/passenger_registration.css">
</head>
<body>

<div class="registration-container">
    <form action="/user/passengerregister" method="post" enctype="multipart/form-data">
        <h1 style="color: #457B9D;">Passenger Registration</h1>

        <label for="profileImage">Profile Image</label>
        <input type="file" id="profileImage" name="profileImage" accept="image/*">

        <label for="firstname">First Name</label>
        <input type="text" id="firstname" name="firstName" placeholder="Enter your First Name" required 
               pattern="[A-Za-z]{3,20}" title="First name must be 3-20 alphabets only.">

        <label for="lastname">Last Name</label>
        <input type="text" id="lastname" name="lastName" placeholder="Enter your Last Name" required 
               pattern="[A-Za-z]{3,20}" title="Last name must be 3-20 alphabets only.">

        <label for="email">Email</label>
        <input type="email" id="email" name="emailId" placeholder="Enter your email" required 
               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" 
               title="Please enter a valid email address (e.g., example@gmail.com).">

        <label for="mobile">Mobile Number</label>
        <input type="tel" id="mobile" name="mobileNo" placeholder="Enter your mobile number" required 
               pattern="\d{10}" title="Phone number must be exactly 10 digits.">
        <small>Format: 9XX5XX8XXX (10 digits)</small>

        <label for="age">Age</label>
        <input type="number" id="age" name="age" placeholder="Enter your Age" required min="0" max="120" 
               title="Age must be between 0 and 120.">

        <div class="gender-container">
            <label>Gender</label><br>
            <label><input type="radio" name="gender" value="male" required> Male</label>
            <label><input type="radio" name="gender" value="female"> Female</label>
            <label><input type="radio" name="gender" value="other"> Other</label>
        </div>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your Username" required>

        <label for="newpassword">New Password</label>
        <input type="password" id="newpassword" name="password" placeholder="Enter your Password" required 
               minlength="6" title="Password must be at least 6 characters long.">

        <label for="confirmpassword">Confirm Password</label>
        <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm your Password" required>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>

