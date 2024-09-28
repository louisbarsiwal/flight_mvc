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
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" value="${user.fullName}" required><br>

            <label for="mobileNumber">Mobile Number:</label>
            <input type="text" id="mobileNumber" name="mobileNumber" value="${user.mobileNumber}" required><br>

            <label for="emailId">Email ID:</label>
            <input type="email" id="emailId" name="emailId" value="${user.emailId}" required><br>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="male" ${user.gender == 'male' ? 'selected' : ''}>Male</option>
                <option value="female" ${user.gender == 'female' ? 'selected' : ''}>Female</option>
                <option value="other" ${user.gender == 'other' ? 'selected' : ''}>Other</option>
            </select><br>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${user.dob}" required><br>

            <label for="role">Role:</label>
            <input type="text" id="role" name="role" value="${user.role}" required><br>

            <input type="submit" value="Save Details">
        </form>
    </div>
</body>
</html>
