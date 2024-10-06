<%@ page import="flightmanagement.app.entities.FlightManagerRegistration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Flight Manager Access Control</title>
    <link rel="stylesheet" type="text/css" href="/CSS/access_control.css">
</head>
<body>
    <h2>Flight Manager Access Control</h2>

    <%
        // Get the list of flight managers from the request attribute
        List<?> flightManagerList = (List<?>) request.getAttribute("flightManagerRegistration");

        // Debugging: Print the flightManagerList to console
        System.out.println("Flight Manager List: " + flightManagerList);

        List<FlightManagerRegistration> flightManagerRegistrations = new ArrayList<>();
        if (flightManagerList != null) {
            for (Object obj : flightManagerList) {
                if (obj instanceof FlightManagerRegistration) {
                    flightManagerRegistrations.add((FlightManagerRegistration) obj);
                }
            }
        }

        // Display the success message if it exists
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) { 
    %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <%
        // Only show the form if flight managers exist
        if (!flightManagerRegistrations.isEmpty()) {
    %>
        <form action="/user/submitAccess" method="POST">
            <label for="flightManager">Select Flight Manager</label>
            <select id="flightManager" name="flightManager" required>
                <option value="" disabled selected>Choose a Flight Manager</option>
                <% for (FlightManagerRegistration flightManager : flightManagerRegistrations) { %>
                    <option value="<%= flightManager.getFmId() %>">
                        <%= flightManager.getFirstName() + " " + flightManager.getLastName() %>
                    </option>
                <% } %>
            </select>


            <div class="radio-group">
                <label>
                    <input type="radio" name="access" value="grant" required>
                    Grant Access
                </label>
                <label>
                    <input type="radio" name="access" value="revoke">
                    Revoke Access
                </label>
            </div>

            <button type="submit">Submit</button>
        </form>
    <%
        } else {
    %>
        <p>No flight managers found.</p>
    <%
        }
    %>
</body>
</html>
