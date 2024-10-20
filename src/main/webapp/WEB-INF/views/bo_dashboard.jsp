<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Management System</title>
<style>
	h1, p {text-align: center;}
</style>
</head>
<body>
				
			
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/CSS/bo_dashboard.css">
</head>
<body>
	<!--h1>Flight  Application</h1-->
<div class="header" id="header">
  <button class="openbtn" onclick="openNav()">☰</button>
  <span id="companyName">TravelWings</span>
   <div class="profile-icon" onclick="toggleDropdown()">
     <img src="/images/profile-icon.png" alt="Profile Icon">
     <div id="dropdown" class="dropdown-content">
       <a href="/user/openBoLoginPage">Business Owner</a>
       <a href="/">Back to HomePage</a>
    </div>
  </div>
</div>
 
<div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <a href="/user/openBoViewProfilePage">VIEW PROFILE</a>
  
  <a href="/airline/openAddAirlinePage">ADD AIRLINES</a>
  <a href="/flight/openAddFlightPage">ADD FLIGHTS</a>
  <a href="/openDeletedAirlinePage">DELETED AIRLINES</a>
  <a href="/openDeletedFlightPage">DELETED FLIGHTS</a>
  <a href="/openDisplayAirlinePage">DISPLAY AIRLINES</a>
  <a href="/openDisplayFlightPage">DISPLAY FLIGHTS</a>
 
  <a href="/user/openAccessControlPage">FLIGHT MANAGERS</a>
  <a href="/user/openDisplayPassengers">PASSENGERS LIST</a>
  
  <a href="/">LOGOUT</a>
  
 

</div>
 

</div>
 
<script>
function openNav() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("header").style.marginLeft = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}
 
function closeNav() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("header").style.marginLeft = "0";
  document.getElementById("main").style.marginLeft = "0";
}
 
function toggleDropdown() {
  document.getElementById("dropdown").classList.toggle("show");
}
 

 
window.onclick = function(event) {
  if (!event.target.matches('.profile-icon img')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

</script>
</body>
</html>