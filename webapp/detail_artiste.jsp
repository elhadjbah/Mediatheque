<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>

   	<a href="./lister_album.jsp">Lister Album !</a>
   	<a href="./lister_artiste.jsp">Lister Artiste !</a>
   	
	<h1>Détail Artiste</h1>
     
     <p>
     	<h3>ID : </h3>${ artiste.id }
     	<h3>NOM : </h3>${ artiste.nom }
     	<h3>NATIONALITE : </h3>${ artiste.nationalite }
     </p>
     
     <a href="ListerArtiste">Aller vers la gestions des artistes !</a><br/><br/>
 
</body>
</html>