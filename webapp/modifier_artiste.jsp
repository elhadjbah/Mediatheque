<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Modifier un artiste</title>
</head>
<body>
	<h1>Modifier un artiste</h1>
	<form action="ModifierArtiste" method="post">
		<label for="id">ID :</label>
		<input type="text" id="id" name="id" value="${artiste.id }" readonly><br>
		<label for="nom">Nom :</label>
		<input type="text" id="nom" name="nom" value="${artiste.nom }"><br>
		<label for="nationalite">Nationalité :</label>
		<input type="text" id="nationalite" name="nationalite" value="${artiste.nationalite }"><br>
		<input type="submit" value="Modifier">
	</form>
</body>
</html>