<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천하는 음악</title>
<style>
	* {
		text-align: center;
	}
	.align-center {
		margin: 0px auto;
	}
	.recommend-music-title {
		margin: 50px;
	}
	.music-list {
		padding: 10px;
		width: 80%;
	}
	.music-container {
		display: flex;
		justify-content: space-around;
		margin: 20px 0px;
		min-width: 800px;
	}
	.music {
		flex-grow: 1;
		margin: 12px;
	}
	.music-img {
		max-width: 100%;
		height: auto;
	}
	.music-title {
		
	}
	.music-artist {
		font-size: 0.8em;
		color: #AAAAAA !important;
	}
	.hover-cursor {
		cursor: pointer;
	}
	@media (orientation: portrait) {
      	
    }
</style>
</head>
<body>
	<div class="recommend-music-title">추천하는 음악</div>
	
	<div class="align-center music-list">
		<% 
		for (int i = 0; i < 15; i++) {
			if (i % 5 == 0) {
		%>
				<div class="music-container">
			<%
			}
			%>
			<div class="music">
				<img src="../sample/holding_onto_gravity.jpg" class="music-img hover-effect" onclick="location.href=''">
				<div class="music-title">
					<span onclick="location.href=''" class="hover-cursor">title</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href=''" class="hover-cursor">artist</span>
				</div>
			</div>
		<%
			if ((i + 1) % 5 == 0) {
		%>
				</div>
		<%
			}
		}
		%>
	</div>
</body>
</html>