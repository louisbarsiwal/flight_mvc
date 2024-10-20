<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Passengers</title>
    <link rel="stylesheet" type="text/css" href="/CSS/passengers_list.css">
</head>
<body>
    <div class="table-container">
        <h2>Passengers List</h2>
		<div class="search-form">
				    <form method="get" action="/user/filterPassengers">
				        <input type="text" name="searchTerm" placeholder="Search by passenger_Id or first_name ..." required>
				        <input type="submit" value="Filter">
				    </form>
				</div>
        <table>
            <thead>
                <tr>
                    <th>passenger_Id</th>
                    <th>first_name</th>
                    <th>last_name</th>
                    <th>email</th>
                    <th>mobile_no</th> 
                    <th>age</th> 
                    <th>gender</th> 
                    <th>username</th> 
					
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Map<String, Object>> passengers = (List<Map<String, Object>>) request.getAttribute("passengers");
                    if (passengers != null) {
                        for (Map<String, Object> passenger : passengers) {
                %>
                    <tr>
                        <td><%= passenger.get("passenger_Id") %></td>
                        <td><%= passenger.get("first_name") %></td>
                        <td><%= passenger.get("last_name") %></td>
						<td><%= passenger.get("email") %></td>
						<td><%= passenger.get("mobile_no") %></td>
						<td><%= passenger.get("age") %></td>
						<td><%= passenger.get("gender") %></td>
						<td><%= passenger.get("username") %></td>
												
																								
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No Passengers found.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>

	</div>


</body>
</html>
