<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
	<a href="ListerArtiste">Aller vers la gestions des artistes !</a><br/><br/>
	<a href="./ajout_album.jsp">Ajouter Album !</a><br/><br/>
	<h1>Liste des albums</h1>
     <table class="table table-hover mt-4" border="1">
        <thead>
            <tr>
            <th scope="col">ID</th>
            <th scope="col">DESCRIPTION</th>
            <th scope="col">DATE SORTIE</th>
            <th scope="col">PRIX ACHAT </th>
            <th scope="col">DETAIL </th>
            <th scope="col">MODIFIER</th>
            <th scope="col">SUPPRIMER </th>
            </tr>
        </thead>
        <tbody>
		    <div>
		      <p style="color: green;"> ${ successMessage } </p>
		    </div>
		    
		    <div>
		      <p style="color: red;"> ${ errorMessage } </p>
		    </div>
		    
		    <c:forEach var="album" items="${ albums }">
            
            <tr class="table-primary">
                <td><c:out value="${ album.id }" /></td>
                <td><c:out value="${ album.description }" /></td>
                <td><c:out value="${ album.dateSortie }" /></td>
                <td><c:out value="${ album.prixAchat }" /></td>
                <td><a href="GestionAlbum?id=${album.id}&action=detail"><button> Detail</button> </a></td>
                <td><a href="GestionAlbum?id=${album.id}&action=modifier"><button> Modifier</button> </a></td>
                <td><a href="GestionAlbum?id=${album.id}&action=delete"><button>Supprimer</button> </a></td>  
            </tr>   
        </c:forEach>           
        </tbody>
      </table>
</body>
</html>