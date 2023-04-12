<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Modifier un artiste</title>
</head>
<body>
	<h1>Modifier un album</h1>
	<form action="GestionAlbum" method="post">
		<label for="id">ID :</label>
		<input type="text" id="id" name="id" value="${album.id }" readonly><br>
		<label for="description">Description :</label>
		<input type="text" id="description" name="description" value="${album.description }"><br>
		
		<label for="date_sortie">Date de sortie :</label>
		<input type="text" id="date_sortie" name="date_sortie" value="${album.dateSortie }"><br>
		
		<label for="prix_achat">Prix achat :</label>
		<input type="text" id="prix_achat" name="prix_achat" value="${album.prixAchat }"><br>
		<input type="submit" value="Modifier">
	</form>
</body>
</html>