<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>

<script>
function validateForm() {
  var nom = document.getElementById("descrtion").value;
  var nationalite = document.getElementById("nationalite").value;
  
  if (nom == "" || date == "" || prixachat="") {
    alert("Veuillez remplir tous les champs requis.");
    return false;
  }
}
</script>

</head>
<body>
	<h1>Ajout album</h1>
    <form method="post" action="CreationAlbum" onsubmit="return validateForm()">
        <p>
            <label for="description">Descrtion : </label>
            <input type="text" name="description" id="description" required/>
            <span id="nom_error" style="color:red"></span>
        </p>
        <p>
            <label for="date_sortie">Date de sortie : </label>
            <input type="date" name="date_sortie" id="date_sortie" required/>
            <span id="nationalite_error" style="color:red"></span>
        </p>
        
        <p>
            <label for="prix_achat">Prix achat : </label>
            <input type="double" name="prix_achat" id="prix_achat" required/>
            <span id="nationalite_error" style="color:red"></span>
        </p>
        
        <input type="submit" value="Ajouter" />
    </form>
    
</body>
</html>
