<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>View Profile</title>
    <link href="../css/view_profile.css" rel="stylesheet"/>
	
	<script>
		        window.onload = function() {
		            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
		            if (message) {
		                alert(message);
		            }
		        };
		    </script>
</head>
<body>
    <h1>View Profile</h1>

	<form:form method="POST" action="/user/fmUpdateProfile" modelAttribute="flightManagerRegistration" enctype="multipart/form-data">

        <table>
        	<form:hidden path="flightManagerId" />
			
			<tr>
			    <td>User Name:</td>
			    <td>
			        <form:input path="username" disabled="true" htmlEscape="false" />
			    </td>
			</tr>


			
			
            <tr>
                <td>First Name:</td>
                <td>
                    <form:input path="firstName" />
                </td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td>
                    <form:input path="lastName" />
                </td>
            </tr>
            <tr>
                <td>Email ID:</td>
                <td>
                    <form:input path="emailId" />
                </td>
            </tr>
            <tr>
                <td>Mobile No:</td>
                <td>
                    <form:input path="mobileNo" />
                </td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td>
                    <form:input path="dateOfBirth" type="date" />
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
            <tr>
                <td>Profile Image:</td>
                <td>
                    <input type="file" name="profileImage" />
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">Save Changes</button>
    </form:form>
    
   

</body>
</html>
