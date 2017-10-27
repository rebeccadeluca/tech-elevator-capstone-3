<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section>
	<h1> Survey Results</h1>
	<c:url value="parkSurveyResults" var="submitForm"/>
	<form action="${submitForm}" method="POST">
		<input type="radio" name="activityLevel" value="demographics"> people like me
		<input type="radio" name="activityLevel" value="everyone"> everyone
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