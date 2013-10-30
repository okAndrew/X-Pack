<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="video-player-inside">
	<video>
		<source type="application/x-mpegurl" src="${videoSRC }"></source>
		<source type="video/webm" src="${videoSRC }"></source>
		<source type="video/mp4" src="${videoSRC }"></source>
		<source type="video/ogg" src="${videoSRC }"></source>
	</video>
</div>