<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="images/favicon.png">

    <title>Dream Host</title>

    <!-- Bootstrap core CSS -->
    <link href="res/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="res/css/navbar.css" rel="stylesheet">

      <script src="res/js/html5shiv.js"></script>
      <script src="res/js/respond.min.js"></script>
  </head>

  <body>
  <jsp:include page="adminUserHeader.jsp"></jsp:include>
    <div class="container">
      <!-- Static navbar -->
      <div class="navbar navbar-default">
        <div class="navbar-collapse collapse">
         here will be included page from UI but without "menu.jsp"!!!!!
        </div><!--/.nav-collapse -->
      </div>
    </div> <!-- /container -->
	
	<div class="container">
		<c:if test="${param.p != null}">
			<jsp:include page="admin/${param.p}.jsp"></jsp:include>
		</c:if>
	</div>
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="res/js/jquery.js"></script>
    <script src="res/js/bootstrap.min.js"></script>
  </body>
</html>