<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
	<link rel="stylesheet" type="text/css" href="/CSS/userprofile.css">

	</head>
<body>
    <div class="profile-form">
        <h2>User Profile</h2>
        <form action="submitProfile" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required><br>

            <label for="mobileNumber">Mobile Number:</label>
            <input type="text" id="mobileNumber" name="mobileNumber" required><br>

            <label for="emailId">Email ID:</label>
            <input type="email" id="emailId" name="emailId" required><br>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select><br>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required><br>

            <label for="role">Role:</label>
            <input type="text" id="role" name="role" required><br>
			<center>
			<button onclick="window.location.href='/user/openusereditprofile'">Edit Profile</button>
			</center>
        </form>
    </div>
</body>
</html>
