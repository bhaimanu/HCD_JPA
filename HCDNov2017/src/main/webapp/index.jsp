<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src ="./webjars/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src ="./javascript.js"></script>
	<meta charset="UTF-8">
	<title>Home</title>
	<script type="text/javascript">

		</script>
</head>
<body>
	
	<div id="login">
	<div id="message"></div>
	
	<h1>Hello World! Test using JSP</h1>
	
	
<form id="Loginform">	
  User ID:<br>
  <input type="text" name="USER_ID" >
  <br>
  Password:<br>
  <input type="password"  name="PASS">
  <br>	
</form>
    <button id ="LoginApplication">Login </button>

<form action="./RegisterCourse.jsp">
    <button type="submit" >Register Course</button>
</form>

<form action="./RegisterUser.jsp">
    <button type="submit" >Register User</button>
</form>
<button type="submit" >Register</button>
    <button id ="ShowAllemployee" >Show All users</button>
    <button id ="ShowAllCourses" >Show All Courses</button>
    
    
<div id="AllCoursesArea">
</div>
    
	<div id="AllUsersArea">
	</div>

</div>

<div style="display:none;" id="ShowCourse">

<form id="ChangeCourse">
  Name: <input type="text" name="COURSE_NAME">
  <br>
  
  Begin Date: <input type="date" name="BEGIN_DATE" default >
  <br>
  End Date: <input type="date" name="END_DATE">
  
</form>

</div>


<div style="display:none;" id="ShowUser">
<div id="USER_NAME"> </div> 
 
<form id="ChangeForm">	
  
  
<div>
  User ID: <input  type="text" id="USER_ID_NEW" name="USER_ID_NEW">
</div>
  <div style="display:none;" id="password_hide">
  Password:<input type="password" name="PASS_NEW" id="PASS_NEW">
  <br> Name: <input  name="USER_NAME_NEW" id="USER_NAME_NEW">
</div>
<div>
  Email: <input type="text" id="EMAIL_NEW" name="EMAIL_NEW">
  </div>
</form>

<div  id="buttonChangeDiv">
    <button id ="EditProfile" >Edit</button>
</div>

<div  id="buttonBook">
    <button id ="Book" >Book</button>
</div>

<div id="Bookings">

</div>


<div id="AllCoursesAreaBook">

</div>


<form id="ChangeFormBook">	
  
  
<div id="ChangeFormDiv" style="display:none;">
  User ID: <input  type="text" id="UserIdBook" name="UserIdBook">
  Course ID: <input  type="text" id="CourseIdBook" name="CourseIdBook">	
</div>
</form>


<div style="display:none;"   id="buttonChangeDivSave">
    <button id ="SaveChangeProfile" >Save</button>
</div>

 </div>

</body>
</html>
