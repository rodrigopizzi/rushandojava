<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>R2Genda :: Home</title>
</head>
<body>

<div style="float: right;">
<form action="logout" method="post">
	<p>Bem vindo <%=session.getAttribute("usuario") %>
		<button type="submit">Logout</button>
	</p>
</form>
</div>

<h1>R2Genda</h1>

<hr />

<br />

<button>Cadastrar</button>
<button>Alterar</button>
<button>Excluir</button>

</body>
</html>