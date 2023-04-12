<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
	<h1>Détail Artiste</h1>
     
     <p>
     	<h3>ID : </h3>${ album.id }
     	<h3>Description : </h3>${ album.description }
     	<h3>Date de sortie : </h3>${ album.dateSortie }
     	<h3>Prix achat : </h3>${ album.prixAchat }
     </p>
     <a href="GestionAlbum">Aller vers la gestions des albums !</a><br/><br/>
 
</body>
</html>