<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<jsp:root version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util">
	<tiles:importAttribute />
	<li>
	<spring:url value="/start/user/${score.userId}" var="user_url"/>
		<a href="${user_url}">
			${score.username}<span class="ui-li-count">
			<c:if test="${score.totalScore > 0}">
				+ 
			</c:if>
			${score.totalScore}</span>
		</a>
	</li>
</jsp:root>