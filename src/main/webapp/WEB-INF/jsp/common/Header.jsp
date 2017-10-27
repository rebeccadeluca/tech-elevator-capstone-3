<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:100,400,400i,600,800" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Francois One' rel='stylesheet'>
    <c:url value="/css/npgeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/" var="home" />
    		<c:url value="/img/newlogo.png" var="logoSrc" />
        <a href="${home}">
        		<img style="height: 25%" src="${logoSrc}" alt="National Park Geek" />
        </a>
    </header>
    <nav>
        <ul>
        		<c:url value="parkSurvey" var="survey"/>
            <li><a href="${home}">Home</a></li>
            <li><a href="${survey}">Survey</a></li>
        </ul>
    </nav>
    <br>
    