<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
	<h1>Liste des artistes</h1>
     <table class="table table-hover mt-4" border="1">
        <thead>
            <tr>
            <th scope="col">ID</th>
            <th scope="col">NOM</th>
            <th scope="col">NATIONALITE </th>
            <th scope="col">ACTION </th>
            </tr>
        </thead>
        <tbody>
		    <div>
		      <p style="color: green;"> ${ successMessage } </p>
		    </div>
		    
		    <div>
		      <p style="color: red;"> ${ errorMessage } </p>
		    </div>
		    
		    <c:forEach var="artiste" items="${ artites }">
            
            <tr class="table-primary">
                <td><c:out value="${ artiste.id }" /></td>
                <td><c:out value="${ artiste.nom }" /></td>
                <td><c:out value="${ artiste.nationalite }" /></td>
                <td>
                	<a href="ModifierArtiste?id=${artiste.id}"> Modifier </a><br>
                	<a href="ModifierArtiste?id=${artiste.id}&action=delete" />"> Supprimer </a><br>
                	<a href="detail_artiste.jsp?id=<c:out value="${artiste.id}" />"> <button>Detail</button> </a>
                </td>
            </tr>   
        </c:forEach>           
        </tbody>
      </table>
</body>
</html>