<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Test</title>

		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
		<script>
	    $(document).bind("mobileinit", function() {
	    	$.mobile.page.prototype.options.addBackBtn = true;
	    });
	  	</script>
		<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	</head>
	<body>
	
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>Home</h1>
		</div><!-- /header -->
	
		<div data-role="content">	
			<c:if test="${not empty gameGroups}">
				<h2>Spielgruppen</h2>
				
				<ul data-role="listview" data-inset="true">
					<c:forEach items="${gameGroups}" var="gameGroup">
						<c:url value="games/${gameGroup.id}" var="gameGroupUrl"/>
						<li><a href="${gameGroupUrl}">${gameGroup.name}</a></li>
					</c:forEach>
				</ul>
			</c:if>
			
			<c:if test="${not empty gameGroup}">
				<h2>${gameGroup.name}</h2>
	
				<ul data-role="listview" data-inset="true">
					<c:forEach var="game" items="${games}">
					<c:url value="game/${game.id}" var="gameUrl"/>
					<c:choose>
					<c:when test="${game.gameStatus == 'UPCOMMING'}">
						<li>
							<div style="float:right; margin-top:10px;">
								<input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><span>:</span><input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><a href="index.html" data-role="button" data-icon="check" data-iconpos="notext" style="display:inline-block; float:right; margin-top:4px;">Save</a> 
							</div>
							<h1 style="display:inline-block;">${game.teamHome.name} - ${game.teamAway.name}</h1><br/>
							<p style="display:inline-block;"><fmt:formatDate value="${game.kickOff}" type="both" timeStyle="short" dateStyle="short" /></p>
						</li>
					</c:when>
					<c:when test="${game.gameStatus == 'RUNNING'}">
						<li data-theme="a">
							<a href="${gameUrl}">
								<h1>${game.teamHome.name} - ${game.teamAway.name}</h1>
								<p><fmt:formatDate value="${game.kickOff}" type="both" timeStyle="short" dateStyle="short" /></p>
								<div class="ui-li-count" style="right:80px;">RUNNING: ${game.scoreHome} : ${game.scoreAway}</div>
								<span class="ui-li-count">+ ?</span>
							</a>
						</li>
					</c:when>
					<c:when test="${game.gameStatus == 'FINISHED'}">
						<li data-theme="b">
							<a href="${gameUrl}">
								<h1>${game.teamHome.name} - ${game.teamAway.name}</h1>
								<p><fmt:formatDate value="${game.kickOff}" type="both" timeStyle="short" dateStyle="short" /></p>
								<div class="ui-li-count" style="right:80px;">FINISHED: ${game.scoreHome} : ${game.scoreAway}</div>
								<span class="ui-li-count">+ ?</span>
							</a>
						</li>
					</c:when>
					</c:choose>
					</c:forEach>
				</ul>
			</c:if>
				

			<c:if test="${not empty game}">
			
			<div class="ui-grid-b">
				<div class="ui-block-a" style="text-align:center;"><img src="../../../images/team/normal/${game.teamHome.externalTeamId}.png"/><h3>${game.teamHome.name}</h3></div>
				<div class="ui-block-b" style="text-align:center;"><h1>${game.scoreHome} : ${game.scoreAway} </h1>${game.gameStatus}</div>
				<div class="ui-block-c" style="text-align:center;"><img src="../../../images/team/normal/${game.teamAway.externalTeamId}.png"/><h3>${game.teamAway.name}</h3></div>
			</div><!-- /grid-b -->

			</c:if>			
				<h2>Top 5</h2>
			
				<ol data-role="listview" data-inset="true">
					<li><a href="index.html">The Godfather<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Inception<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">The Good, the Bad and the Ugly<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Pulp Fiction<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Schindler's List<span class="ui-li-count">100</span></a></li>
				</ol>
				
		</div><!-- /content -->
	
	
	</div><!-- /page -->

</body>
</html>