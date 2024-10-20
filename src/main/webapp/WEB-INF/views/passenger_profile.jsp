<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>View Profile</title>
    <link rel="stylesheet" type="text/css" href="/CSS/passenger_profile.css">
    <script>
        window.onload = function() {
            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
            if (message) {
                alert(message);
            }
        };

        // Main function to validate the form
        function validateForm(event) {
            event.preventDefault(); // Prevent form submission if validation fails

            // Get field values
            const firstName = document.getElementById('firstName').value.trim();
            const lastName = document.getElementById('lastName').value.trim();
            const email = document.getElementById('emailId').value.trim();
            const mobile = document.getElementById('mobileNo').value.trim();
            const age = document.getElementById('age').value.trim();
            const gender = document.querySelector('input[name="gender"]:checked'); // Get selected gender

            // Regular expressions for validation
            const namePattern = /^[A-Za-z\s]{3,20}$/; // Alphabets, 3-20 characters
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // Standard email format
            const mobilePattern = /^\d{10}$/; // Exactly 10 digits

            // Check for empty fields
            if (!firstName) {
                alert("First Name is required.");
                return false;
            }
            if (!lastName) {
                alert("Last Name is required.");
                return false;
            }
            if (!email) {
                alert("Email ID is required.");
                return false;
            }
            if (!mobile) {
                alert("Mobile Number is required.");
                return false;
            }
            if (!age) {
                alert("Age is required.");
                return false;
            }
            if (!gender) {
                alert("Please select a gender.");
                return false;
            }

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

            // Age validation
            if (age < 0 || age > 120) {
                alert("Please enter a valid age between 0 and 120.");
                return false;
            }

            // If all validations pass, submit the form
            document.getElementById('profileForm').submit(); // Submit the form
        }
    </script>
</head>
<body>
    <h1>View Profile</h1>

    <form:form id="profileForm" method="POST" action="/user/passengerUpdateProfile" modelAttribute="passengerRegistration" enctype="multipart/form-data" onsubmit="validateForm(event)">
        <table>
            <form:hidden path="passenger_Id" />
            <tr>
                <td>User Name:</td>
                <td>
                    <form:input path="username" disabled="true" htmlEscape="false" />
                </td>
            </tr>
            <tr>
                <td>First Name</td>
                <td>
                    <form:input id="firstName" path="firstName" />
                </td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>
                    <form:input id="lastName" path="lastName" />
                </td>
            </tr>
            <tr>
                <td>Email ID</td>
                <td>
                    <form:input id="emailId" path="emailId" />
                </td>
            </tr>
            <tr>
                <td>Mobile No</td>
                <td>
                    <form:input id="mobileNo" path="mobileNo" />
                </td>
            </tr>
            <tr>
                <td>Age</td>
                <td>
                    <form:input id="age" path="age" type="number" />
                </td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                    <form:radiobutton path="gender" value="male" /> Male
                    <form:radiobutton path="gender" value="female" /> Female
                    <form:radiobutton path="gender" value="other" /> Other
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">Save Changes</button>
    </form:form>
</body>
</html>
