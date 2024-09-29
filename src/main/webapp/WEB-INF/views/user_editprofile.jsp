<!DOCTYPE html>
<html>
<head>
    <title>Edit User Profile</title>
	<link rel="stylesheet" type="text/css" href="/CSS/user_editprofile.css">
</head>
<body>
    <div class="profile-form">
        <h2>Edit User Profile</h2>
        <form action="saveProfile" method="post">
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

            <input type="submit" value="Save Details">
        </form>
    </div>
</body>
</html>
