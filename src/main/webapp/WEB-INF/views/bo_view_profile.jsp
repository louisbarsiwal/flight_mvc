<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="flightmanagement.app.entities.BusinessOwnerRegistration"%>
<%@ page import="flightmanagement.app.utilities.ViewImage"%>


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
			<%
					BusinessOwnerRegistration businessOwnerRegistration = 
					(BusinessOwnerRegistration) request.getAttribute("businessOwnerRegistration");
				%>
			
</head>
<body>
    <h1>View Profile</h1>
	
	<form:form method="POST" action="/user/boUpdateProfile" modelAttribute="businessOwnerRegistration" enctype="multipart/form-data">
		
        <table>
			
			<tr>
				<center><p><img src="data:image/jpg;base64,
					<%= ViewImage.displayImage(businessOwnerRegistration.getImage()) %>" width="100" 
					height="150"></p><center>
			</tr>
			
			<tr>
			   <td>Profile Image:</td>
			      <td>
					<input type="file" name="profileImage" />
			       </td>
			    </tr>
				
        	<form:hidden path="boId" />
			
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
           
        </table>
        <br>
        <button type="submit">Save Changes</button>
    </form:form>
    
   

</body>
</html>
