<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/article/boardStyles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="script.js"></script>
<script type="text/javascript">
function isLogin() {
	var artist = '${artistId}';
	if(artist != ''){
		document.getElementById("login").style.display = "none";
		document.getElementById("logout").style.display = "block";
	}
	else {
		document.getElementById("logout").style.display = "none";
		document.getElementById("login").style.display = "block";
	}
}
</script>
<style>
.like {
	border: 1px solid #ccc;
	border-radius : 4px;
	padding-top : 3px;
	padding-bottom : 3px;
	padding-left : 20px;
	padding-right : 20px;
}
</style>
<title>HOME</title>
</head>
<body onload='isLogin()'>
	<div id='menu'>
		<ul>
			<li class='active'><a href='#'>Home</a></li>
			<li><a href="<c:url value='/article/articleMain' />">Article</a></li>
			<li><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li><a
				href="<c:url value='/mypage'>
						 	<c:param name='artistId' value='${artistId}'/>
						 </c:url>">My
					Page</a></li>
			<button id='logout'
				onclick="location.href='<c:url value='/artist/logout' />' ">Logout</button>
			<button id='login'
				onClick="location.href='<c:url value='/artist/login/form' />' ">Login</button>

		</ul>
	</div>
	<br>
	<br>
	<div id='home' align="center">
		<input id="tab1" type="radio" name="tabs" checked value="all">
		<label for="tab1">전체 인기차트</label> <input id="tab2" type="radio"
			name="tabs" value="rock"> <label for="tab2">ROCK 인기차트</label>
		<input id="tab3" type="radio" name="tabs" value="rNb"> <label
			for="tab3">R&B 인기차트</label> <input id="tab4" type="radio" name="tabs"
			value="pop"> <label for="tab4">POP 인기차트</label> <input
			id="tab5" type="radio" name="tabs" value="edm"> <label
			for="tab5">EDM 인기차트</label> <input id="tab6" type="radio" name="tabs"
			value="hiphop"> <label for="tab6">HIP-HOP 인기차트</label> <input
			id="tab7" type="radio" name="tabs" value="etc"> <label
			for="tab7">기타장르 인기차트</label>


		<section id="content1" align="center">
			<div class="chart">
				<ol>
					<c:forEach var="all" items="${likeChartAll}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${all.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${all.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${all.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${all.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content2">
			<div class="chart">
				<ol>
					<c:forEach var="rock" items="${likeChartRock}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${rock.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${rock.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${rock.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${rock.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content3">
			<div class="chart">
				<ol>
					<c:forEach var="rNb" items="${likeChartRNb}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${rNb.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${rNb.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${rNb.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${rNb.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content4">
			<div class="chart">
				<ol>
					<c:forEach var="pop" items="${likeChartPop}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${pop.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${pop.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${pop.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${pop.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content5">
			<div class="chart">
				<ol>
					<c:forEach var="edm" items="${likeChartEdm}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${edm.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${edm.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${edm.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${edm.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content6">
			<div class="chart">
				<ol>
					<c:forEach var="hiphop" items="${likeChartHiphop}">
						<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${hiphop.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${hiphop.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${hiphop.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${hiphop.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>

		<section id="content7">
			<div class="chart">
				<ol>
					<c:forEach var="etc" items="${likeChartEtc}">
					<a
							href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${etc.musicId}'/>
						   </c:url>"><li>
								<strong>[ ${etc.nickname} ]</strong>
						</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong>${etc.musicName}</strong></li>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<li><strong  class='like'>${etc.likeCount}</strong></li> </a>
						</a>
					</c:forEach>
				</ol>
			</div>
		</section>
	</div>
</body>
</html>
