<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>

<script>
function validateForm() {
  var nom = document.getElementById("nom").value;
  var nationalite = document.getElementById("nationalite").value;
  
  if (nom == "" || nationalite == "") {
    alert("Veuillez remplir tous les champs requis.");
    return false;
  }
}
</script>

</head>
<body>
	<h1>Ajout artiste</h1>
    <form method="post" action="CreationArtiste" onsubmit="return validateForm()">
        <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" required/>
            <span id="nom_error" style="color:red"></span>
        </p>
        <p>
            <label for="nationalite">Nationalite : </label>
            <input type="text" name="nationalite" id="nationalite" required/>
            <span id="nationalite_error" style="color:red"></span>
        </p>
        
        <input type="submit" />
    </form>
    
</body>
</html>
