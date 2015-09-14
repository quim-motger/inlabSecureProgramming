<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Curs Programació Segura</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Comentaris</h1>
		<c:forEach items="${comentaris}" var="c">
			<div class="well">
				<p>${c.comentari}</p>
				<i>Publicat per ${c.autor} el dia ${c.data}</i>
			</div>
		</c:forEach>
		<c:if test="${not empty username}">
			<form method="POST">
				<div class="form-group">
					<label for="comentari">Afegir comentari en nom de ${username}</label>
					<textarea name="comentari" class="form-control"></textarea>
					<input type="hidden" name="autor" value="${username}">
				</div>
				<button class="btn btn-lg btn-primary pull-right" type="submit">Comentar</a>
			</form>
		</c:if>
		<c:if test="${empty username}">
			<a class="btn btn-lg btn-primary pull-right" href="login">Comentar (requereix login)</a>
		</c:if>
	</div>
</body>
</html>