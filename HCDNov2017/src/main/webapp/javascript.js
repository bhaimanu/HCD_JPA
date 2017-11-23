/**
 * 
 */

jQuery(document).on('click', '.CourseList', function() {
    //alert( $(this).text() );
    //alert( $(this).attr("id"));
});

jQuery(document).on('click', '.CourseListCancel', function() {
	$("#CourseIdBook").val($(this).attr("id"));
	$("#UserIdBook").val($("#USER_ID_NEW").val());
	
	var variables = $( "#ChangeFormBook" ).serialize();
	//$("#ChangeFormDiv").prop('disabled', true);
	
	
	$.post("./CancelParticipant",
			variables,
	        function(data,status){
		alert(data);
					$.post("./GetAllBookings",
							$( "#Loginform" ).serialize(),
							function(data,status){
						
						 try {
							 
							 alert(data);
								var obj = JSON.parse(data);
								 }
								 catch(e) {
								        alert(e); // error in the above string (in this case, yes)!
								    }
								//alert(obj);
							        if ((obj) && (obj.length !=0 ))
							        	{
							        	$("#Bookings").empty();

							        	$("#Bookings").append("<h3> Bookings (Click to Delete bookings) </h3> ");
								        $("#Bookings").append("<ul>");
								        for(i=0;i<obj.length;i++){
								        //	alert(obj[i]);
								        	//$("#AllCoursesArea").append("<li>");
								        	var innerHtml = "<li> <a class='CourseListCancel' id='" + 
								        	obj[i].CourseId + "'>" + obj[i].CourseName + obj[i].BeginDate 
								        	+ obj[i].EndDate + "</a> </li>";
								        //	alert(innerHtml);
								        	$("#Bookings").append(innerHtml);
								        	
							        	}
							        	$("#Bookings").append("</ul>");
							        }
						
					});

		});
	
});

jQuery(document).on('click', '.CourseListBook', function() {
	$("#CourseIdBook").val($(this).attr("id"));
	$("#UserIdBook").val($("#USER_ID_NEW").val());
	
	var variables = $( "#ChangeFormBook" ).serialize();
	//$("#ChangeFormDiv").prop('disabled', true);
	
	
	$.post("./BookParticipant",
			variables,
	        function(data,status){
		 try {
							// alert(data);
								var obj = JSON.parse(data);
								alert(data);
							        if ((obj) && (obj.length !=0 ))
							        	{
							        	$("#Bookings").empty();

							        	$("#Bookings").append("<h3> Bookings (Click to Delete bookings) </h3> ");
								        $("#Bookings").append("<ul>");
								        for(i=0;i<obj.length;i++){
								        //	alert(obj[i]);
								        	//$("#AllCoursesArea").append("<li>");
								        	var innerHtml = "<li> <a class='CourseListCancel' id='" + 
								        	obj[i].CourseId + "'>" + obj[i].CourseName + obj[i].BeginDate 
								        	+ obj[i].EndDate + "</a> </li>";
								        //	alert(innerHtml);
								        	$("#Bookings").append(innerHtml);
								        	
							        	}
							        	$("#Bookings").append("</ul>");
							        }
											 }
								 catch(e) {
									 	alert(e); // error in the above string (in this case, yes)!
								        alert(data);
								 }
								
		});
	
//	alert(variables);
});

jQuery(document).ready(function(){

	
	$("#LoginApplication").click(function(){
			$.post("./Login",
					$( "#Loginform" ).serialize(),
					function(data,status){
				try {
				var obj = JSON.parse(data);    
				if( obj && obj[0] && obj[0].USERNAME)
					{
		        	$("#ShowUser").css("display","block");
				    $("#login").css("display","none");
				    
				    $("#USER_NAME").append("<h1> Welcome " + obj[0].USERNAME + "</h1>")
			    	$("#USER_ID_NEW").prop('disabled', true);
			    	$('#USER_ID_NEW').val(obj[0].USERID);
			    	$("#EMAIL_NEW").prop('disabled', true);
			    	$('#USER_NAME_NEW').val(obj[0].USERNAME);
			    	$('#EMAIL_NEW').val(obj[0].EMAIL);
					$.post("./GetAllBookings",
							$( "#Loginform" ).serialize(),
							function(data,status){
						
						 try {
							// alert(data);
								var obj = JSON.parse(data);
								//alert(obj);
							        if ((obj) && (obj.length !=0 ))
							        	{
							        	$("#Bookings").empty();

							        	$("#Bookings").append("<h3> Bookings (Click to Delete bookings) </h3> ");
								        $("#Bookings").append("<ul>");
								        for(i=0;i<obj.length;i++){
								        //	alert(obj[i]);
								        	//$("#AllCoursesArea").append("<li>");
								        	var innerHtml = "<li> <a class='CourseListCancel' id='" + 
								        	obj[i].CourseId + "'>" + obj[i].CourseName + obj[i].BeginDate 
								        	+ obj[i].EndDate + "</a> </li>";
								        //	alert(innerHtml);
								        	$("#Bookings").append(innerHtml);
								        	
							        	}
							        	$("#Bookings").append("</ul>");
							        }
											 }
								 catch(e) {
									 	alert(e); // error in the above string (in this case, yes)!
								        alert(data);
								 }
						
						
					});

					
					}
				else{
					$("#message").append("<h1>" + data +"</h1>")
				}
				}
				catch(e) {
					$("#message").append("<h1>" + data +"</h1>");
					// error in the above string (in this case, yes)!
			    
				};
				
			    });
		}); 
		$("#EditProfile").click(function(){
			$("#password_hide").css("display","block");
			$("#buttonChangeDiv").css("display","none");
			$("#buttonChangeDivSave").css("display","block");
		    $("#EMAIL_NEW").prop('disabled', false);
	    	
		}); 
		

		$("#EditProfile").click(function(){
			$("#password_hide").css("display","block");
			$("#buttonChangeDiv").css("display","none");
			$("#buttonChangeDivSave").css("display","block");
		    $("#EMAIL_NEW").prop('disabled', false);
	    	
		}); 
		
		
		$("#buttonChangeDivSave").click(function(){
			$("#USER_ID_NEW").prop('disabled', false);
			var variables = $( "#ChangeForm" ).serialize().replace(/_NEW/g, "");
			$("#USER_ID_NEW").prop('disabled', true);
			//alert(variables);
			$.post("./SaveEmployeeDetails",
					variables,
			        function(data,status){
				alert(data);
				});
		});
		$("#RegisterSaveUser").click(function(){
			
			var variables = $( "#Register" ).serialize();
			//alert(variables);
			$("#USER_ID_NEW").prop('disabled', true);
			//alert(variables);
			$.post("./RegisterUser",
					variables,
			        function(data,status){
				alert(data);
				});
		});
	
		$("#RegisterSaveCourse").click(function(){
			
			var variables = $( "#Register" ).serialize();
			//alert(variables);
			$("#USER_ID_NEW").prop('disabled', true);
			//alert(variables);
			$.post("./RegisterCourse",
					variables,
			        function(data,status){
				alert(data);
				});
		});
	
		
		
$("#buttonBook").click(function(){
			
			$.get("./GetAllCourses", function(data, status){
				//alert(data);
				 try {
					 
				 
				var obj = JSON.parse(data);
				 }
				 catch(e) {
				        alert(e); // error in the above string (in this case, yes)!
				    }
				//alert(obj);
			        if ((obj) && (obj.length !=0 ))
			        	{
			        	$("#AllCoursesAreaBook").empty();
			        	$("#AllCoursesAreaBook").append("<h3> Click on course to book </h3> ");
				        $("#AllCoursesAreaBook").append("<ul>");
				        for(i=0;i<obj.length;i++){
				        //	alert(obj[i]);
				        	//$("#AllCoursesArea").append("<li>");
				        	var innerHtml = "<li> <a class='CourseListBook' id='" + 
				        	obj[i].CourseId + "'>" + obj[i].CourseName + obj[i].BeginDate 
				        	+ obj[i].EndDate + "</a> </li>";
				        //	alert(innerHtml);
				        	$("#AllCoursesAreaBook").append(innerHtml);
				        	
			        	}
			        	$("#AllUsersArea").append("</ul>");
			        }
			    });
			  //  
			    
		 });
		
		$("#ShowAllCourses").click(function(){
			
			$.get("./GetAllCourses", function(data, status){
				//alert(data);
				 try {
					 
				 
				var obj = JSON.parse(data);
				 }
				 catch(e) {
				        alert(e); // error in the above string (in this case, yes)!
				    }
				//alert(obj);
			        if ((obj) && (obj.length !=0 ))
			        	{
			        	$("#AllCoursesArea").empty();
			        	$("#AllCoursesArea").append("<ul>");
				        for(i=0;i<obj.length;i++){
				        //	alert(obj[i]);
				        	//$("#AllCoursesArea").append("<li>");
				        	var innerHtml = "<li> <a class='CourseList' id='" + 
				        	obj[i].CourseId + "'>" + obj[i].CourseName + "</a> </li>";
				        	//alert(innerHtml);
				        	$("#AllCoursesArea").append(innerHtml);
				        	
				        	//$("#AllCoursesArea").append("</li>");
			        	}
			        	$("#AllUsersArea").append("</ul>");
			        }
			    });
			  //  
			    
		 });
		
		$("#ShowAllemployee").click(function(){
			
			$.get("./GetAllUsers", function(data, status){
			//	alert(data);
				var obj = JSON.parse(data);
			        if ((obj) && (obj.length !=0 ))
			        	{
			        	$("#AllUsersArea").empty();
			        	$("#AllUsersArea").append("<ul>");
				        for(i=0;i<obj.length;i++){
				        	$("#AllUsersArea").append("<li>" + obj[i].USERID +"</li>")
				        	
			        	}
			        	$("#AllUsersArea").append("</ul>");
			        }
			    });
			  //  
			    
		 });	
		
	});

