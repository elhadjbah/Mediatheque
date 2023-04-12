<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Artiste - <%= request.getAttribute("artiste").getNom() %></title>
</head>
<body>
    <h1><%= request.getAttribute("artiste").getNom() %></h1>
    <p>Nationalité : <%= request.getAttribute("artiste").getNationalite() %></p>
    <h2>Liste des albums</h2>
    <ul>
        <c:forEach items="${albums}" var="album">
            <li><a href="album?id=<%= album.getId() %>"><%= album.getDescription() %></a></li>
        </c:forEach>
    </ul>
</body>
</html>