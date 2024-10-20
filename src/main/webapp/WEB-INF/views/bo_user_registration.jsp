<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="/CSS/user_registration.css">
</head>
<body>
 
<div class="registration-container">
<form action="/user/Boregister" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
 
		
<label for="Profileimage">Upload Image:</label>
<input type="file" name="profileImage" accept=".jpg, .jpeg, .png, .pdf" required>
 
 
        <label for="firstname">First Name:</label>
<input type="text" id="firstname" name="firstName" required>
 
        <label for="lastname">Last Name:</label>
<input type="text" id="lastname" name="lastName" required>
 
        <label for="email">Email:</label>
<input type="email" id="email" name="emailId" required>
 
        <label for="mobile">Mobile Number:</label>
<input type="tel" id="mobile" name="mobileNo" required>
 
        <label for="dob">Date of Birth:</label>
<input type="date" id="dob" name="dateOfBirth" required>
 
        <div class="gender-container">
<label>Gender:</label>
<label><input type="radio" name="gender" value="male" required> Male</label>
<label><input type="radio" name="gender" value="female"> Female</label>
<label><input type="radio" name="gender" value="other"> Other</label>
</div>
 
        <label for="username">Username:</label>
<input type="text" id="username" name="username" required>
 
        <label for="newpassword">New Password:</label>
<input type="password" id="newpassword" name="password" required>
 
        <label for="confirmpassword">Confirm Password:</label>
<input type="password" id="confirmpassword" name="confirmPassword" required>
 
        <button type="submit" value="submit">Register</button>
</form>
</div>
 
<script>
function validateForm() {
    const firstName = document.getElementById("firstname").value;
    const lastName = document.getElementById("lastname").value;
    const email = document.getElementById("email").value;
    const mobile = document.getElementById("mobile").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("newpassword").value;
    const confirmPassword = document.getElementById("confirmpassword").value;
    const namePattern = /^[a-zA-Z]{3,20}$/;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const mobilePattern = /^\d{10}$/;
    const usernamePattern = /^[a-zA-Z0-9_]{6,15}$/;
 
    if (!namePattern.test(firstName)) {
        alert("First name must be between 3-20 characters and contain only alphabets.");
        return false;
    }
    if (!namePattern.test(lastName)) {
        alert("Last name must be between 3-20 characters and contain only alphabets.");
        return false;
    }
    if (!emailPattern.test(email)) {
        alert("Email must be in the format of example@gmail.com.");
        return false;
    }
    if (!mobilePattern.test(mobile)) {
        alert("Phone number must be 10 digits.");
        return false;
    }
    if (!usernamePattern.test(username)) {
        alert("Username must be between 6-15 characters and contain only alphabets, numbers, and underscores.");
        return false;
    }
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    return true;
}
</script>
 
 
</body>
</html>