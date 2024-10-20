<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deleted Airlines</title>
    <link rel="stylesheet" type="text/css" href="/CSS/deleted_airline.css">
</head>
<body>
    <h1>Deleted Airlines</h1>
	<div class="search-form">
	    <form method="get" action="/filterDeletedAirlines">
	        <input type="text" name="searchTerm" placeholder="Search by Airline Name or Number..." required>
	        <input type="submit" value="Filter">
	    </form>
	</div>


    <table>
        <thead>
            <tr>
                <th>Airline Name</th>
                <th>Airline Number</th>
                <th>Model Number</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, Object>> deletedAirlines = (List<Map<String, Object>>) request.getAttribute("deletedAirlines");
                if (deletedAirlines != null && !deletedAirlines.isEmpty()) {
                    for (Map<String, Object> airline : deletedAirlines) {
            %>
            <tr>
                <td><%= airline.get("airline_name") %></td>
                <td><%= airline.get("airline_number") %></td>
                <td><%= airline.get("model_number") %></td>
                <td>
                    <form action="/restoreAirline" method="post" style="display:inline;">
                        <input type="hidden" name="airlineId" value="<%= airline.get("id") %>">
						<input type="hidden" name="airlineName" value="<%= airline.get("airline_name") %>">
                        <input type="submit" value="Add Back">
                    </form>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No deleted airlines found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
	<a href="/openDisplayAirlinePage">Back to Airlines</a>
	

</body>
</html>
