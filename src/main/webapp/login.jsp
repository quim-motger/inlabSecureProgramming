<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<c:if test="${not empty login_error}">
			<div class="alert alert-danger">Username o password incorrecte</div>
		</c:if>
		<form action="<c:url value="login"/>" method="POST" class="form-signin">
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" name="username" class="form-control">
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" name="password" class="form-control">
			</div>
			<button class="btn btn-lg btn-primary pull-right" type="submit">Login</button>
		</form>
	</div>
</body>
</html>