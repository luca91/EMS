<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${systemUser.role == 'admin' }">
		<% System.out.println("admin"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='#users' id="listusers">Users Management</a></li>
		<li><a href='#events' id="listevents">Events Management</a></li>
		<li><a href='#groups' id="listgroups">Groups Management</a></li>
		<li><a href='#participants' id="listparticip">Participants Management</a></li>
		<li><a href='<c:url value="/private/badgeList.html"/>'>Badges</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'event_mng' }">
		<% System.out.println("event_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>		
		<li><a href='#events' id="listevents">Events Management</a></li>
		<li><a href='#groups' id="listgroups">Groups Management</a></li>
		<li><a href='#participants' id="listparticip">Participants Management</a></li>
		<li><a href='<c:url value="/private/badgeList.html"/>'>Badges</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'group_mng' }">
		<% System.out.println("group_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>		
		<li><a href='#groups' id="listgroups">Groups Management</a></li>
		<li><a href='#participants' id="listparticip">Participants Management</a></li>
	</c:when>
</c:choose>
<!-- HISTORY + BACK BUTTON experimentation-->
<script type="text/javascript">
$(function(){
    
	// Keep a mapping of url-to-container for caching purposes.
	var cache = {
	    // If url is '' (no fragment), display this div's content.
	    '': $('.col-3')
	  };
	
	// Fetch the elements
    var $ajaxparse = $('#ajax-parse'),
    	$menu = $('#menu'),
        $users = $('#listusers'),
        $events = $('#listevents'),
        $groups = $('#listgroups'),
        $participants = $('#listparticip');
   
    // Get the tabs
    var $tabs = $ajaxparse.children();
   
    // Hide all our tabs initially
    //$tabs.hide();
   
    // Fetch our original document title
    var document_title = document.title;
   
    // Define our update menu function - as this is used a lot
    var updateMenu = function(state){
        // Update out tab menu
        $menu.children('li:has(a[href="#'+state+'"])').addClass('active').siblings('.active').removeClass('active');
    };
   
    
    // Bind a handler for ALL hash/state changes
    $.History.bind(function(state){
        // Update the current element to indicate which state we are now on
        $current.text('Our current state is: ['+state+']');
        // Update the page's title with our current state on the end
        document.title = document_title + ' | ' + state;
    });
   
    // Bind a handler for state: users
    $.History.bind('/users',function(state){
        // Update Menu
        updateMenu(state);
        // Show users tab, hide the other tabs
        $tabs.hide();
        $users.stop(true,true).fadeIn(200);
        //$(users).trigger('click');
        //window.location = $(users).attr("href");
        $(users).trigger('click').attr("href");
    });

    // Bind a handler for state: events
    $.History.bind('/events',function(state){
        // Update Menu
        updateMenu(state);
        // Show events tab, hide the other tabs
        $tabs.hide();
        $events.stop(true,true).fadeIn(200);
        //$(events).trigger('click');
        //window.location = $(events).attr("href");
        $(events).trigger('click').attr("href");
    });
   
    // Bind a handler for state: groups
    $.History.bind('/groups',function(state){
        // Update Menu
        updateMenu(state);
        // Show groups tab, hide the other tabs
        $tabs.hide();
        $groups.stop(true,true).fadeIn(200);
        //$(groups).trigger('click');
        //window.location = $(groups).attr("href");
        $(groups).trigger('click').attr("href");
    });
    
 // Bind a handler for state: participants
    $.History.bind('/participants',function(state){
        // Update Menu
        updateMenu(state);
        // Show participants tab, hide the other tabs
        $tabs.hide();
        $participants.stop(true,true).fadeIn(200);
        //$(participants).trigger('click');
        //window.location = $(participants).attr("href");
        $(participants).trigger('click').attr("href");
        //$.History.go('/participants');
    });
 
    
});
</script>		