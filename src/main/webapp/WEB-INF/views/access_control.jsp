<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flight Manager Access Control</title>
    <link rel="stylesheet" type="text/css" href="/CSS/access_control.css">
</head>
<body>
    <h2>Flight Manager Access Control</h2>

    <!-- Display success or error messages -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    
    <% 
        List<Map<String, Object>> flightManagers = (List<Map<String, Object>>) request.getAttribute("flightManagerRegistration");
        
        if (flightManagers != null && !flightManagers.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
					<th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                    <th>Status</th> <!-- Add a new header for Status -->
                    <th>Actions</th> <!-- Add column for Grant/Revoke buttons -->
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Map<String, Object> flightManager : flightManagers) {
                %>
                    <tr>
						<td><%= flightManager.get("flightManager_id") %></td>
                        <td><%= flightManager.get("first_name") %></td>
                        <td><%= flightManager.get("last_name") %></td>
                        <td><%= flightManager.get("user_name") %></td>
                        <td><%= flightManager.get("status") != null ? flightManager.get("status") : "Not Set" %></td> <!-- Show status -->
                        <td>
							<form action="${pageContext.request.contextPath}/user/grantAccess" method="post">
								<input type="hidden" name="flightManagerId" value="<%= flightManager.get("flightManager_id") %>" />
								                    <button type="submit">Grant Access</button>
							</form>

							<form action="${pageContext.request.contextPath}/user/revokeAccess" method="post">
								<input type="hidden" name="flightManagerId" value="<%= flightManager.get("flightManager_id") %>" />
								                   <button type="submit">Revoke Access</button>
							</form>

                        </td>
                    </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    <% 
        } else {
    %>
        <p>No flight managers found.</p>
    <% 
        } 
    %>
</body>
</html>
