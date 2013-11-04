function initTeamPage() {
	var team = [
			{
				name : "Roma",
				photo : "res/img/team/korol.png",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			},
			{
				name : "Andrew",
				photo : "res/img/team/kravchuk.png",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			},
			{
				name : "Natalya",
				photo : "res/img/team/romanenko.jpg",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			},
			{
				name : "Sergiy",
				photo : "res/img/team/savruk.jpg",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			},
			{
				name : "Roma",
				photo : "res/img/team/tretyak.png",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			},
			{
				name : "Sergiy",
				photo : "res/img/team/trofim.png",
				about : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi. Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin. Pellentesque tristique elementum felis eu fermentum. Maecenas eu."
			} ];

	var colors = [ {
		c : "#f5f5f5"
	}, {
		c : "#f56545"
	}, {
		c : "#fb2"
	}, {
		c : "#ee2"
	}, {
		c : "#b5c5c5"
	}, {
		c : "#6cd"
	}, {
		c : "#98e3ca"
	}, {
		c : "#bbe535"
	} ];

	var min = 0;
	var max = colors.length - 1;

	for ( var i = 0; i < team.length; i++) {
		var n = Math.floor(Math.random() * (max - min + 1)) + min;
		var start = "<div class='col-md-4' style='padding: 0px;'><div class='panel-person' id='person' style='background-color: "
				+ colors[n].c + "'><div class='panel-body'>";
		var img = "<img class='avatar' src='" + team[i].photo + "'/>";
		var name = "<h1>" + team[i].name + "</h1>";
		var about = "<p>" + team[i].about + "</p>";
		var end = "</div></div></div>";
		$('#row').append(start + img + name + about + end);
	}
}