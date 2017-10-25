<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section>
	<c:url value="img/parks/${park.imageName}" var="imageName"/>
	<img style="width: 50vw; height:auto" src="${imageName}"/>
	<h1>${park.name}</h1>
	<h4>${park.location}</h4>
	<p>${park.summary}</p>
	<p>Acreage: ${park.acreage} acres</p>
	<p>Elevation: ${park.elevation} feet</p>
	<p>Miles of Trail: ${park.milesOfTrail}</p>
	<p>Number of Campsites: ${park.campsites}</p>
	<p>Climate: ${park.climate}</p>
	<p>Year Founded: ${park.yearFounded}</p>
	<p>Number of Annual Visitors: ${park.visitors}</p>
	<p>Entry Fee: ${park.fee}</p>
	<p>Number of Animal Species: ${park.species}</p>
	<p>${park.quote} (${park.quoteSource})</p>
	<br>
	<br>
	
	<c:url value="/parkDetail" var="submitUnit">
		<c:param value="${park.code}" name="parkCode"/>
	</c:url>
	<form action="${submitUnit}" method="POST">
		<input type="radio" name="unit" value="fahrenheit" checked> Fahrenheit 
		<input type="radio" name="unit" value="celsius"> Celsius
		<input type="submit" value="Change units">			
	</form>
	
	<div class="row">
		<div class="col-md-3">
		<h4>Today</h4>
		<c:set value="${forecast.get(0)}" var="today"/>
			<c:url value="img/weather/${today.forecast}.png" var="weatherImage"/>
			<img src="${weatherImage}"/>		
			<p>High: ${today.getHigh(sessionScope.unit)} Low: ${today.getLow(sessionScope.unit)}</p>
			<ul>
			<c:forEach var="recommendation" items="${today.recommendation}">
				<li>${recommendation}</li>
			</c:forEach>
			</ul>
		</div>
		
		
		
		<c:forEach var="day" items="${forecast}" begin="1">
		<div class="col-md-2">
			<c:url value="img/weather/${day.forecast}.png" var="weatherImage"/>
			<img src="${weatherImage}"/>
			<br>
			High: ${day.getHigh(sessionScope.unit)}   Low: ${day.getLow(sessionScope.unit)}
		</div>	
		</c:forEach>
		

</section>