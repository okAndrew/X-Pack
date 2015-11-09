function initTeamPage() {
	var team = [
			{
				name : "Roma",
				photo : "res/img/team/korol.png",
				about : "Roman Korol likes to spent his time playing a Prince of Persia. He likes to be called the King of Persia"
			},
			{
				name : "Andrew",
				photo : "res/img/team/kravchuk.png",
				about : "Andrew went to the neighbour to make 'git push'. He likes to watch interesting video. His fovourite song is 'What Does the Fox Say'. His best friend is a captain."
			},
			{
				name : "Natalia",
				photo : "res/img/team/romanenko.jpg",
				about : "Natalia is the prettiest girl in our group. She usually stend up at 7 for making 'git commit'"
			},
			{
				name : "Sergiy",
				photo : "res/img/team/savruk.jpg",
				about : "Sergiy is a captain. He likes to drink Captain Morgan. He also likes to play tennis. In tennis most of all he likes... tennis"
			},
			{
				name : "Roma",
				photo : "res/img/team/tretyak.png",
				about : "He shouted that he won't write the code without taking 'medicine'"
			},
			{
				name : "Sergiy",
				photo : "res/img/team/trofim.png",
				about : "Every day I helped my captain to sum up the findings. Sergiy likes to eat. His best dish is pizza with tomatoes. "
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