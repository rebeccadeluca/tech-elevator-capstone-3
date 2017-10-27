<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section>
	<c:choose>
		<c:when test="${sessionScope.surveyDisplayMode.equals('everyone')}">
			<h1 id = "everyone"> Survey Results for Everyone</h1>
		</c:when>
		<c:when test="${sessionScope.surveyDisplayMode.equals('demographics')}">
			<h1 id="demographics"> Survey Results for Your Demographic</h1>
		</c:when>
	</c:choose>
	<h1> Survey Results </h1>
	<c:url value="parkSurveyResults" var="submitForm"/>
	<form action="${submitForm}" method="POST">
		<input type="radio" name="displayMode" value="demographics"> people like me
		<input type="radio" name="displayMode" value="everyone"> everyone
	</form>
	<br>
	<c:forEach var="parkCode" items="${surveyResults.getAllParkCodes()}">
		<c:url value="img/parks/${parkCode.toLowerCase()}.jpg" var="imageURL"/>
		<img src="${imageURL}">
		${surveyResults.getName(parkCode)}
		${surveyResults.getCountForParkCode(parkCode).toString()}
		<br>
	</c:forEach>
</section>