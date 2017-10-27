<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp" />

<section style="padding: 60px;">
<c:forEach var="park" items="${parks}">
<div class="row">
	<div class="col-md-3">
		<c:url value="/img/parks/${park.imageName}" var="parkImage"/>
		<c:url value="/parkDetail" var="detailPage">
			<c:param name="parkCode" value="${park.code}"/>
		</c:url>
		<a href="${detailPage}">
		<img style="height: 20vw; width:auto" class="img-responsive center-block" src="${parkImage}" alt="${park.name}"/></a>
	</div>
	<div class="col-md-9">
		<a href="${detailPage}">
		<h1 class="francois-font"><c:out value="${park.name}"/></h1></a><br>
		<p><c:out value="${park.summary}"/></p>
	</div>
</div>
<br>
<br>
</c:forEach>


</section>