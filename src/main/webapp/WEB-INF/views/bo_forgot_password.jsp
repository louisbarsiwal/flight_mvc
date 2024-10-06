<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Form</title>
	<script>
		        window.onload = function() {
		            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
		            if (message) {
		                alert(message);
		            }
		        };
		    </script>
	<link rel="stylesheet" type="text/css" href="/CSS/user_login.css">
</head>
<body>
    <div class="form-container">
        <form action="/user/forgotPassword" method="post"> <!-- Change 'yourActionUrl' to your actual action URL -->
            <div class="form-group">
				<label for="username">User Name:</label>
				    <input type="text"id="username" name="username" required />
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="password" required />
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required />
            </div>
            <button type="submit" class="save-button">Save</button>
        </form>
    </div>
</body>
</html>
