<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>404 Not found</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	function startIntro() {
		$('#intro').fadeOut(1000);
		setTimeout(function() {
			$('body').css('background', 'url(res/img/errorSW/background.png)')
			var music = document.getElementById('music')
			if (music.canPlayType)
				music.play()
			$('#logodiv').fadeIn(500);
			setTimeout(startCrawl, 1000);
		}, 1500)
	}

	function startCrawl() {
		$('#logodiv').hide("scale", {}, 15000);
		setTimeout(function() {
			$('#text').show()
			var now = 800;
			var translateY = setInterval(function() {
				if ($.browser.mozilla)
					var prefix = '-moz-'
				else
					var prefix = '-webkit-'
				$('#text').css(
						prefix + 'transform',
						'rotateX(55deg) translateY(' + now
								+ 'px) translateZ(300px)');
				now = now - 2;
				if (now == -1800) {
					clearInterval(translateY);
					$('#text').fadeOut(2000)
					$('#theend').delay(2000).fadeIn(2000);
				}
			}, 50);
		}, 9000)
	}

	$(function() {
		setTimeout(function() {
			$('#intro').fadeIn(1000);
		}, 500)
		setTimeout(startIntro, 6000);
	})
</script>

<link rel="stylesheet" href="res/css/errorSW.css" type="text/css">
<style type="text/css">
#crawl {
	width: 1000px;
	margin: auto;
	-webkit-perspective: 800px;
	-webkit-perspective-origin: center 350px;
	-moz-perspective: 800px;
	-moz-perspective-origin: center 350px;
}

#text {
	display: none;
	margin: auto;
	width: 600px;
	text-align: justify;
	-webkit-transform: rotateX(55deg) translateY(800px) translateZ(300px);
	-webkit-backface-visibility: visible;
	-moz-transform: rotateX(55deg) translateY(800px) translateZ(300px);
	-moz-backface-visibility: visible;
}
</style>
</head>
<body>
	<h3 id="intro">A long time ago your website page went far, far
		away...</h3>
	<audio id="music" preload="auto" autobuffer controls>
		<source src="http://dl.dropbox.com/u/16979719/sw_music.ogg"></source>
	</audio>
	<div id="logodiv">
		<img src="res/img/errorSW/starwars-logo2.png" width="100%" />
	</div>
	<div id="crawl">
		<div id="text">
			<h2>EPISODE 404</h2>
			<h2>NO HOPE</h2>
			<p>Go back to the DreamHost home page.</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Suspendisse vel velit euismod, dignissim orci eu, rhoncus mi.
				Vivamus luctus mi nec nunc ultricies, ac aliquet nulla sollicitudin.
				Pellentesque tristique elementum felis eu fermentum. Maecenas eu.</p>
		</div>
	</div>
	<div id="theend">THE END</div>
	<div id="bg"></div>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-30034642-1' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>