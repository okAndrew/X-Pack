<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<style type="text/css">
.avatar {
	display: block;
	max-width: 150px;
	max-height: 150px;
	border-radius: 50%;
	margin-left: auto;
    margin-right: auto;
}
div .panel-body h1 {
	font-family: 'Lobster', cursive;
	text-align: center;
}
</style>
<script type="text/javascript">
var team = [
	{name: "Roma", photo: "res/img/team/korol.png", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."},
	{name: "Andrew", photo: "res/img/team/kravchuk.png", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."},
	{name: "Natalya", photo: "res/img/team/romanenko.jpg", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."},
	{name: "Sergiy", photo: "res/img/team/savruk.jpg", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."},
	{name: "Roma", photo: "res/img/team/tretyak.png", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."},
	{name: "Sergiy", photo: "res/img/team/trofim.png", about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."}];

var colors = [
	{c: "#f5f5f5"},
	{c: "#f56545"},
	{c: "#fb2"},
	{c: "#ee2"},
	{c: "#b5c5c5"},
	{c: "#6cd"},
	{c: "#98e3ca"},
	{c: "#bbe535"}];
	
var min = 0;
var max = colors.length - 1;
	
$('#mydiv').children('input').each(function () {
    alert(this.value);
});

$(document).ready(function () {
	for (var i = 0; i < team.length; i++) {
		var n = Math.floor(Math.random() * (max - min + 1)) + min;
		var start = "<div class='col-md-4'><div class='panel panel-default' id='person' style='background-color: " + colors[n].c +"'><div class='panel-body'>";
		var img = "<img class='avatar' src='" + team[i].photo + "'/>";
		var name = "<h1>" + team[i].name + "</h1>";
		var about = "<p>" + team[i].about + n + "</p>";
		var end = "</div></div></div>";
        $('#row').append(start + img + name + about + end);
	}
});
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="row" id="row">
			</div>
		</div>
	</div>
</body>
</html>