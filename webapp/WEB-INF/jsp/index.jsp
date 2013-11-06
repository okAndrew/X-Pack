
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Home</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1><fmt:message key="Hello,_Team" bundle="${lang}" /></h1>
			<p><fmt:message key="At_DreamHost_we've_been_happily_hosting_our_clients'_dreams_(and_websites)_since_April_1997._That_was_when_four_Computer_Science_undergraduates_at_Harvey_Mudd_College_in_Claremont,_CA_launched_this_company_with_no_capital_apart_from_a_single_Pentium_100_web_server_(Destro_was_her_name),_using_shared_bandwidth_on_a_T1_line_that_a_friend_gave_us_at_no_cost._By_necessity_we_had_to_be_frugal,_but_even_with_our_less_than_ample_resources_we_always_did_our_best_to_provide_a_quality_service_at_a_reasonable_price." bundle="${lang}" /></p>
			<p>
				<a class="btn btn-primary btn-lg"><fmt:message key="Learn_more" bundle="${lang}" /> &raquo;</a>
			</p>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<h2><fmt:message key="Web_Hosting" bundle="${lang}" /></h2>
				<p><fmt:message key="Cost-effective_web_hosting_for_WordPress_bloggers,_website_designers,_e-commerce_and_small_business" bundle="${lang}" /></p>
				<p>
					<a class="btn btn-default" href="#"><fmt:message key="View_details" bundle="${lang}" /> &raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2><fmt:message key="Dedicated_Hosting" bundle="${lang}" /></h2>
				<p><fmt:message key="High-performance,_business_class_web_hosting_services_that_are_ideal_for_enterprise_or_advanced_users." bundle="${lang}" /></p>
				<p>
					<a class="btn btn-default" href="#"><fmt:message key="View_details" bundle="${lang}" /> &raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2><fmt:message key="Virtual_Private_Server" bundle="${lang}" /></h2>
				<p><fmt:message key="Web_hosting_resources_allocated_only_to_you,_for_improved_performance_and_scalability_as_your_website_grows." bundle="${lang}" /></p>
				<p>
					<a class="btn btn-default" href="#"><fmt:message key="View_details" bundle="${lang}" /> &raquo;</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>