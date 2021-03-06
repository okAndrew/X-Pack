function bytesToSize(bytes) {
	var sizes = [ 'Bytes', 'KB', 'MB', 'GB', 'TB' ];

	if (bytes == 0) {
		return 'n/a';
	}

	var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));

	return (bytes / Math.pow(1024, i)).toFixed(2) + sizes[i];
};

/*
 * 
 */
function checkPage(page) {
	var temp = parseInt(page);
	if (temp != NaN && temp >= 0) {
		return temp;
	}

	return 0;
}

/*
 * 
 */
function changePerPage(param) {
	window.perPage = checkCount(param);
	window.location.href = generateLink(window.page);
}

/*
 * 
 */
function checkCount(perPage) {
	var temp = parseInt(perPage);
	if (temp != NaN && temp > 0 && temp < 100) {
		return temp;
	}

	return 10;
}

/*
 * 
 */
function checkOrderBy(orderBy) {
	var temp = new String(orderBy).toLocaleLowerCase();
	if (temp != "") {
		return orderBy;
	}

	return "id";
}

/*
 * 
 */
function checkSort(sort) {
	var temp = new String(sort).toLocaleLowerCase();
	if (temp == "asc" || temp == "desc") {
		return temp;
	}

	return "asc";
}

/*
 * 
 */
function changeSort(sort) {
	var temp = new String(sort);
	if (sort == "asc") {
		temp = "desc";
	} else {
		temp = "asc";
	}

	return temp;
}

/*
 * 
 */
function changeOrderBy(order) {
	window.sort = changeSort(window.sort);
	window.orderBy = order;
	window.location.href = generateLink(window.page);
}

/*
 * 
 */
function render() {
	var pageStart = window.page - 2;
	var pageEnd = window.page + 2;

	if (pageStart < 0) {
		pageStart = 0;
	}

	if (pageEnd > window.pageCount - 1) {
		pageEnd = window.pageCount - 1;
	}
	
	if (window.page == 0) {
		backward = "<li class='disabled'><a><span class='glyphicon glyphicon-backward'></span></a></li>";
		step_backward = "<li class='disabled'><a><span class='glyphicon glyphicon-step-backward'></span></a></li>";
	} else {
		backward = "<li><a href='" + generateLink(0) + "'><span class='glyphicon glyphicon-backward'></span></a></li>";
		step_backward = "<li><a href='" + generateLink(window.page - 1) + "'><span class='glyphicon glyphicon-step-backward'></span></a></li>";
	}

	$('#paginator').append(backward);
	$('#paginator').append(step_backward);

	for ( var i = pageStart, j = i + 1; i <= pageEnd; i++, j++) {
		if (window.page == i) {
			link = "<li class='active'>";
		} else {
			link = "<li>";
		}
		link += "<a href=" + generateLink(i);
		link += ">" + j + "</a></li>";

		if (window.page == i) {
			$('#paginator').append(link);
		} else {
			$('#paginator').append(link);
		}
	}

	if (window.page == window.pageCount - 1) {
		step_forward = "<li class='disabled'><a><span class='glyphicon glyphicon-step-forward'></span></a></li>";
		forward = "<li class='disabled'><a><span class='glyphicon glyphicon-forward'></span></a></li>";
	} else {
		step_forward = "<li><a href=" + generateLink(window.page + 1) + "'><span class='glyphicon glyphicon-step-forward'></span></a></li>";
		forward = "<li><a href='" + generateLink(window.pageCount - 1) + "'><span class='glyphicon glyphicon-forward'></span></a></li>";
	}
	
	$('#paginator').append(step_forward);
	$('#paginator').append(forward);
}

/*
 * 
 */
function generateLink(p) {
	var temp = "";
	
	if (p != null) {
		temp += "http://localhost:8080/dreamhost/" + window.linkVar + "?page=" + p;
		temp += "&count=" + window.perPage;
		temp += "&orderby=" + window.orderBy;
		temp += "&sop=" + window.sort;
	}

	return temp;
}