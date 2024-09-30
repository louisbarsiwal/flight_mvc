<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
	<link rel="stylesheet" type="text/css" href="/CSS/fm_registration.css">
</head>
<body>

<div class="registration-container">
    <form action="register" method="post" enctype="multipart/form-data">
		
		
        <label for="profileImage">Profile Image</label>
        <input type="file" id="profileImage" name="profileImage" accept="image/*">

        <label for="firstname">First Name</label>
        <input type="text" id="firstname" name="firstname" placeholder="Enter your Firstname"required>

        <label for="lastname">Last Name</label>
        <input type="text" id="lastname" name="lastname" placeholder="Enter your Lastname" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email"placeholder="Enter your email" required>

        <label for="mobile">Mobile Number</label>
        <input type="tel" id="mobile" name="mobile" placeholder="Enter your mobile number" required>

        <label for="dob">Date Of Birth</label>
        <input type="date" id="dob" name="dob"  required>

        <div class="gender-container">
            <label>Gender:</label>
            <label><input type="radio" name="gender" value="male" required> Male</label>
            <label><input type="radio" name="gender" value="female"> Female</label>
            <label><input type="radio" name="gender" value="other"> Other</label>
        </div>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>

        <label for="newpassword">New Password</label>
        <input type="password" id="newpassword" name="newpassword" placeholder="Enter your password" required>

        <label for="confirmpassword">Confirm Password</label>
        <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm your password" required>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>
