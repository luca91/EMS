$(document).ready(function(){
	  $("#btn1").click(function(){
	    $("#ajax-parse").append("<b>Appended text</b>.");
	  });
	  
	  $(".p2").click(function(){
	  	$("#ajax-parse").load("eventList.html");
	  });	  
	  
	  $(".p3").click(function(){
	  	$("#ajax-parse").empty();
	  });
});