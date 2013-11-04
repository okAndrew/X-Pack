<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
		<p><fmt:message key="At_DreamHost_we've_been_happily_hosting_our_clients'_dreams_(and_websites)_since_April_1997._That_was_when_four_Computer_Science_undergraduates_at_Harvey_Mudd_College_in_Claremont,_CA_launched_this_company_with_no_capital_apart_from_a_single_Pentium_100_web_server_(Destro_was_her_name),_using_shared_bandwidth_on_a_T1_line_that_a_friend_gave_us_at_no_cost._By_necessity_we_had_to_be_frugal,_but_even_with_our_less_than_ample_resources_we_always_did_our_best_to_provide_a_quality_service_at_a_reasonable_price." bundle="${lang}"/></p>
		<p><fmt:message key="Those_early_days_forced_us_to_get_creative_and_use_innovative_solutions_to_meet_the_needs_of_our_customers._It's_a_habit_that's_stuck_with_us_as_we've_decided_to_build_much_of_our_own_technology,_and_really_differentiates_us_from_the_competition._Our_technology_is_built_by_some_of_the_industry's_most_clever_developers_on_stable_systems_that_come_together_to_create_one_amazing_user_experience._Designed_to_be_easy-to-use,_everything_at_DreamHost_is_built_with_the_customer_in_mind." bundle="${lang}" /></p>
		<p><fmt:message key="So_far,_we've_hit_the_nail_on_the_head._Over_the_last_decade_or_so,_we've_grown_to_more_than_1500_servers,_state-of-the-art_data_centers,_and_a_full_time_staff_of_more_than_100_employees._We_now_host_more_than_1_million_domains._We're_focused_solely_on_providing_a_quality_customer_experience_backed_by_the_absolute_best_web_hosting_technology_in_the_industry._It's_a_never-ending_quest,_but_it's_one_we're_happy_to_take_on." bundle="${lang}" /></p>
		<p><fmt:message key="We_pledge_to_always_take_your_business_seriously._We're_always_looking_for_ways_to_diversify_our_offerings,_improve_reliability,_and_improve_our_overall_quality_of_service._After_all,_your_ideas_put_you_online,_but_we're_the_ones_that_help_keep_you_there." bundle="${lang}" /></p>
			</div>
		</div>
	</div>
</body>
</html>