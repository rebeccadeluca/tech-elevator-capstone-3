<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<c:import url="/WEB-INF/jsp/common/Header.jsp" />

<section>
<c:forEach var="park" items="${parks}">
<div class="row">
	<div class="col-md-3">
		<c:url value="/img/parks/${park.imageName}" var="parkImage"/>
		<img class="img-responsive center-block" src="${parkImage}" alt="${park.name}"/> 
	</div>
	<div class="col-md-9">
		<h1><c:out value="${park.name}"/></h1><br>
		<h3><c:out value="${park.summary}"/></h3>
	</div>
</div>
<br>
<br>
</c:forEach>


</section>