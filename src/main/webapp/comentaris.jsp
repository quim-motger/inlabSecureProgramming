<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
LLISTAR
<c:forEach items="${comentaris}" var="c">
<p>${c.comentari}</p>
${c.autor} - ${c.data}<hr>
</c:forEach>
<c:if test="${not empty username}">
Afegir comentari de ${username}
<form method="POST">
<textarea name="comentari"></textarea>
<input type="hidden" name="autor" value="${username}">
<input type="submit" value="Comentar">
</form>
</c:if>
<c:if test="${empty username}">
<a href="login">Login per comentar</a>
</c:if>
</body>
</html>