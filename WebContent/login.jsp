<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>R2Genda</title>
</head>
<body>
<h1>R2Genda controle de agendamentos</h1>

<form action="login" method="post">
	<label>Usuário</label>
	<input name="usuario" type="text" placeholder="Informe seu usuário" />
	<label>Senha</label>
	<input name="senha" type="password" placeholder="Informe sua senha" />
	<input type="submit" value="Logar" />
</form>

<% String erro = (String) request.getAttribute("erro");

if (erro != null) { %>
	
	<h1><% out.print(erro); %></h1>
	
<% } %>

</body>
</html>