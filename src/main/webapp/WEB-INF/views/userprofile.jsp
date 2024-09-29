<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
	<link rel="stylesheet" type="text/css" href="/CSS/userprofile.css">

	</head>
<body>
    <div class="profile-form">
        <h2>User Profile</h2>
        <form action="editProfile" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required><br>
			
			<label for="lastName">Last Name:</label>
			<input type="text" id="lastName" name="lastName" required><br>

            <label for="mobileNumber">Mobile Number:</label>
            <input type="text" id="mobileNumber" name="mobileNumber" required><br>

            <label for="emailId">Email ID:</label>
            <input type="email" id="emailId" name="emailId" required><br>

			<div class="gender-container">
			            <label>Gender:</label>
			            <label><input type="radio" name="gender" value="male" required> Male</label>
			            <label><input type="radio" name="gender" value="female"> Female</label>
			            <label><input type="radio" name="gender" value="other"> Other</label>
		   </div>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required><br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
			<center>
			<button onclick="window.location.href='/user/openusereditprofile'">Edit Profile</button>
			</center>
        </form>
    </div>
</body>
</html>
