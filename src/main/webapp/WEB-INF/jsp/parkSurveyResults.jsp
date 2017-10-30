<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section id="survey-results">
	<c:choose>
		<c:when test="${sessionScope.surveyDisplayMode.equals('everyone')}">
			<h1 id = "everyone"> Survey Results for Everyone</h1>
		</c:when>
		<c:when test="${sessionScope.surveyDisplayMode.equals('demographics')}">
			<h1 id="demographics"> Survey Results for People Similar To You</h1>
		</c:when>
	</c:choose>
	<c:url value="parkSurveyResults" var="submitForm"/>
	<form action="${submitForm}" method="POST">
		<input type="radio" name="displayMode" value="demographics"> people like me
		<input type="radio" name="displayMode" value="everyone"> everyone
		<input type="submit" name="submit" value="submit">
	</form>
	<br>
	
	<table>
		<tr>
		<td></td>
		<td><h3>Park</h3></td>
		<td><h3>Votes</h3></td>
		</tr>
	<c:forEach var="parkCode" items="${surveyResults.getAllParkCodes()}">
	<tr>
	<td>
		<c:url value="img/parks/${parkCode.toLowerCase()}.jpg" var="imageURL"/>
		<img src="${imageURL}"></td>
		<td>${surveyResults.getName(parkCode)}</td>
		<td>${surveyResults.getCountForParkCode(parkCode).toString()}</td>
		</tr>
		<br>
	</c:forEach>
	</table>
</section>