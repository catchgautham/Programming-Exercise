[10:35 AM, 3/12/2018] Anna: <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX calls using JQuery in JSP to call Servlet</title>
<script src="http://code.jquery.com/jquery-latest.js">
	        
</script>
<script>
$(document).ready(function() {
	$('#submit').click(function(event) {
		var username = $('#keyWord').val();
		$.get('ActionServlet', {
			keyWord : username
			}, function(responseText) {
				$('#result').text(responseText);
			});
		});
	});
</script>
</head>
<body>
	<form id="form1">
		<h1>Web Application</h1>
		Enter Keyword: <input type="text" id="keyWord" /> <input type="button"
			id="submit" value="Search" />    
		<div id="result"></div>
	</form>
</body>
</html>