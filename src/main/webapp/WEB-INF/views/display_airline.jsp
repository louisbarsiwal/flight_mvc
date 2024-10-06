<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Airlines</title>
    <link rel="stylesheet" type="text/css" href="/CSS/display_airline.css">
</head>
<body>
    <div class="table-container">
        <h2>Airline List</h2>
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
                    List<Map<String, Object>> airlines = (List<Map<String, Object>>) request.getAttribute("airlines");
                    if (airlines != null) {
                        for (Map<String, Object> airline : airlines) {
                %>
                    <tr>
                        <td><%= airline.get("airline_name") %></td>
                        <td><%= airline.get("airline_number") %></td>
                        <td><%= airline.get("model_number") %></td>
						<td>
							<form action="/deleteAirline" method="post" style="display:inline;">
							        <input type="hidden" name="airlineId" value="<%= airline.get("id") %>">
							        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this airline?');">
							    </form>
						</td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4">No airlines found.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
