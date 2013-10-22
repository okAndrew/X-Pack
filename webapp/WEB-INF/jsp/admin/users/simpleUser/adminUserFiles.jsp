<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png">

<title>Dream Host</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />


<!-- check if exists in js file!!!!! if exists-remove else - move to res/js -->
<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

</head>
<body>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>

	<div class="files-admin-user">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Files" bundle="${lang}" />
			</div>
			<form action="userEmployeeController">
				<div class="panel-body">
					<ul class="nav nav-pills">
						<li><button type="submit" class="btn btn-default"
								name="action" value="delete">
								<fmt:message key="Delete" bundle="${lang}" />
							</button></li>
						<li><div class="input-group" style="width: 300px;">
								<input name="searchtext" type="text" class="form-control"><span
									class="input-group-btn"><button type="submit"
										class="btn btn-default" name="action" value="search">
										<fmt:message key="Search" bundle="${lang}" />
									</button></span>
							</div></li>
					</ul>

					<table class="table table-condensed">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
										key="All" bundle="${lang}" /></th>
								<th><fmt:message key="File_name" bundle="${lang}" /></th>
								<th><fmt:message key="Date" bundle="${lang}" /></th>
								<th><fmt:message key="Size" bundle="${lang}" /></th>
								<th><fmt:message key="Type" bundle="${lang}" /></th>
								<th><fmt:message key="Path" bundle="${lang}" /></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${currentFolder.idUpper!=0}">
								<a href="adminUserFiles"><fmt:message
										key="Back_to_all_files" bundle="${lang}" /></a>
							</c:if>
							<c:forEach items="${folders}" var="folder">
								<tr>
									<td><label class="checkbox-inline"> <input
											type="checkbox" name="folders" value="${folder.id}">
									</label></td>
									<td><a href="userfoldernav?folderId=${folder.id}">${folder.name}</a>
								</tr>
							</c:forEach>
							<c:forEach items="${files}" var="file">
								<tr>

									<td><label class="checkbox-inline"> <input
											type="checkbox" name="files" value="${file.id}">
									</label></td>
									<td><c:out value="${file.nameIncome}" /></td>
									<td><c:out value="${file.date}" /></td>
									<td><c:out value="${file.size}" /></td>
									<td><c:out value="${file.type}" /></td>
									<td><c:out value="${file.path}" /></td>
								</tr>
							</c:forEach>


						</tbody>
					</table>

				</div>
				<c:if test="${message != null}">
					<div class="alert alert-block">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning!</h4>
						<h5>${message}</h5>
					</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>