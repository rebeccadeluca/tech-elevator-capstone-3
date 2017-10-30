<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp"/>

<section id="park-detail" style="text-align: center;  width: auto">
	<c:url value="img/parks/${park.imageName}" var="imageName"/>
	<img style="width: 50vw; height:auto;" src="${imageName}"/>
	<h1 style="margin: 25px;" class="francois-font">${park.name}</h1>
	<h2 style="margin: 25px;">${park.location}</h2>
	<p style="text-align: center; width: auto">${park.summary}</p>
	<ul class="flex-container">
		<li class="flex-item">
			<p><span class="extra-bold">Acreage:</span> ${park.acreage} acres</p>
			<p><span class="extra-bold">Elevation:</span> ${park.elevation} feet</p>
			<p><span class="extra-bold">Miles of Trail:</span> ${park.milesOfTrail}</p>
		</li>
		<li class="flex-item">
			<p><span class="extra-bold">Number of Campsites:</span> ${park.campsites}</p>
			<p><span class="extra-bold">Climate:</span> ${park.climate}</p>
			<p><span class="extra-bold">Year Founded:</span> ${park.yearFounded}</p>
		</li>
		<li class="flex-item">
			<p><span class="extra-bold">Annual Visitors:</span> ${park.visitors}</p>
			<p><span class="extra-bold">Entry Fee:</span> ${park.fee}</p>
			<p><span class="extra-bold">Species of Animals:</span> ${park.species}</p>
		</li>
	</ul>
	<br>
	<p style="text-align: center; width: auto"><em>"${park.quote}"</em></p> 
	<p style="text-align: center; width: auto">-${park.quoteSource}</p>
	<br>
	<br>
	
	
	
	
	
	
	<h1 class="francois-font">5-Day Weather Forecast for ${park.name}</h1>
	<hr style="max-width: 500px;">
	<div style="font-size: 0.7em; text-align: left; padding-left: 25px;">
	<c:url value="/parkDetail" var="submitUnit">
		<c:param value="${park.code}" name="parkCode"/>
	</c:url>
	<form action="${submitUnit}" method="POST">
		<input type="radio" name="unit" value="fahrenheit" checked> Fahrenheit 
		<input type="radio" name="unit" value="celsius"> Celsius
		<input id="submit" type="submit" value="Change units">			
	</form>
	</div>
	
	<div class="row" style="font-size: 0.85em;">
		<div class="col-md-3">
		<h2 style="text-align: center" class="francois-font">Today</h2>
		<c:choose>
			<c:when test='${sessionScope.unit.equals("fahrenheit")}'>
				<c:set value="F" var="temp"/>
			</c:when>
			<c:when test='${sessionScope.unit.equals("celsius")}'>
				<c:set value="C" var="temp"/>
			</c:when>
		</c:choose>
		<c:set value="${forecast.get(0)}" var="today"/>
			<c:url value="img/weather/${today.forecast}.png" var="weatherImage"/>
			<img src="${weatherImage}"/>		
			
			<p id="${temp}"><span class="extra-bold">High:</span> ${today.getHigh(sessionScope.unit) } ${temp} <span class="extra-bold">Low:</span> ${today.getLow(sessionScope.unit)}  ${temp}</p>
			<ul>
			<c:forEach var="recommendation" items="${today.recommendation}">
				<li style="text-align: left;">${recommendation}</li>
			</c:forEach>
			</ul>
		</div>
		
		
		
		<c:forEach var="day" items="${forecast}" begin="1">
		<div id="week-forecast" class="col-md-2">
		<br>
		<br>
			<c:url value="img/weather/${day.forecast}.png" var="weatherImage"/>
			<img src="${weatherImage}"/>
			<br>
			<span class="extra-bold">High:</span> ${day.getHigh(sessionScope.unit)} ${temp} <br>
			<span class="extra-bold">Low:</span> ${day.getLow(sessionScope.unit)}  ${temp}
		</div>	
		</c:forEach>
		

</section>