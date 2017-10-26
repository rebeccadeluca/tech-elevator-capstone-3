<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section>
	<c:url var="submitSurvey" value="/parkSurvey"/>
	<form id="survey-form" action=${submitSurvey} method="POST"}>
		<label for="">Favorite National Park</label>
		<select name="parkCode">
			<c:forEach var="park" items="${parks}">
				<option value="${park.code}"> ${park.name} </option>
			</c:forEach>
		</select>
		<br><br>
		<label for="text">Your Email</label>
		<input type="text" name="email">
		<br><br>
		<label for="">State of Residence</label>
		<select name="state">
			<c:forEach var="state" items="${states}">
				<option value="${state}"> ${state} </option>
			</c:forEach>
		</select>
		<br><br>
		<label for="activityLevel">Activity Level</label>
		<div style="display:inline-block" id="radio-buttons">
		<input type="radio" name="activityLevel" value="inactive"> inactive
		<input type="radio" name="activityLevel" value="sedentary"> sedentary
		<input type="radio" name="activityLevel" value="active"> active
		<input type="radio" name="activityLevel" value="extremely active"> extremely active
		</div>
		<br>
		<div id="submit-button">
			<input type="submit" value="Submit">
		</div>
	</form>
</section>