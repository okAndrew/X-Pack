<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link href="res/css/style.css" rel="stylesheet" />
<style>

#effectName {
	width: 350px;
	height: 100px;
	padding: 0.4em;
	position: absolute;
	display: none;
}
#effectDescription {
	width: 350px;
	height: 100px;
	padding: 0.4em;
	position: absolute;
	display: none;
}
</style>
<script>

	$(function() {
		var div;
		function runEffect(div) {
			var selectedEffect = "clip";
			var options = {};
			$("#effect"+div).show(selectedEffect, options, 500, callback);
		}
		;
		function callback() {
		}

		;
		$("#buttonName").click(function() {
			div="Name";
			if ($("#effect"+div).is(':visible')) {
				$("#effect"+div).removeAttr("style").fadeOut();
			} else {
				runEffect(div);
				return false;
			}
		});
		$("#buttonDescription").click(function() {
			div="Description";
			if ($("#effect"+div).is(':visible')) {
				$("#effect"+div).removeAttr("style").fadeOut();
			} else {
				runEffect(div);
				return false;
			}
		});
		$("#effectDescription").hide();
		$("#effectName").hide();
	});
</script>
<div class="modal fade" id="addTariffModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Add_new_tariff" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="employeeControllerTariffs" method="post">
								<div id="errorinfo" class="alert alert-danger"
									style="display: none;">
									<c:if test="${message != null}">
											${message}
									</c:if>
								</div>
								<table class="table">
									<tbody>
										<tr>
											<td><fmt:message key="Name" bundle="${lang}" /></td>
											<td><input type="text" name="name"
												class="form-control first" placeholder="name"
												autofocus="autofocus" /><a href="#" id="buttonName"
												class="ui-state-default ui-corner-all"><span id="span1"
													class="glyphicon glyphicon-plus"></span>Add
													translation</a></td>
											<td>
												<div id="effectName" class="ui-widget-content ui-corner-all">
													<input type="text" name="name_UA"
														class="form-control midle" placeholder="Ukrainian" /> <input
														type="text" name="name_RU"
														class="form-control midle" placeholder="Russian" />
												</div>
											</td>
										</tr>

										<tr>
											<td><fmt:message key="Max_Capacity" bundle="${lang}" /></td>
											<td><input type="text" name="maxCapacity"
												class="form-control midle" placeholder="Max Capacity(MB)" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Price" bundle="${lang}" /></td>
											<td><input type="text" name="price"
												class="form-control midle" placeholder="Price" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Position" bundle="${lang}" /></td>
											<td><input type="text" name="position"
												class="form-control midle" placeholder="Position" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Description" bundle="${lang}" /></td>
											<td><input type="text" name="description"
												id="description" class="form-control midle"
												placeholder="Description" /> <a href="#" id="buttonDescription"
												class="ui-state-default ui-corner-all"><span id="span1"
													class="glyphicon glyphicon-plus"></span>Add
													translation</a></td>
											<td>
												<div id="effectDescription" class="ui-widget-content ui-corner-all">
													<input type="text" name="description_UA"
														class="form-control midle" placeholder="Ukrainian" /> <input
														type="text" name="description_RU"
														class="form-control midle" placeholder="Russian" />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<button type="submit" class="btn btn-primary" name="action"
									value="addTariff">
									<fmt:message key="Add_new_tariff" bundle="${lang}" />
								</button>
							</form>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->