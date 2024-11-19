<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Css/Login.css" type="text/Css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nombre del Banco</title>
    <style>
        .error-mensaje {
            color: red;
            font-size: 14px;
            margin-top: 10px;
            text-align: center;
        }
    </style>

</head>
<body>
	<nav>
	<div class="nav-content">
		<a href="#Login.jsp"> <img src="img/png_logo.png" class="img_logo"
			alt="Logo UTN">
		</a>

		<h1>BANCO UTN</h1>
	</div>
	</nav>
	<div class="container">
		<h2>Cambio de contraseña</h2>
		<%
            String errorMensaje = (String) request.getAttribute("errorMessage");
            if (errorMensaje != null) {
        %>
            <p class="error-mensaje"><%= errorMensaje %></p>
        <%
            }
        %>
		<form action="${pageContext.request.contextPath}/ServletCambioContrasena"
			method="post">
			<label for="usuario">Ingrese su contraseña</label> <input type="text" id="usuario"
				name="usuario" placeholder="contraseña" required> <label
				for="contrasena">Repita la contraseña</label> <input type="password"
				id="contrasena" name="contrasena" placeholder="Contraseña" required>

			<button type="submit">Aceptar</button>
		</form>

	</div>
</body>

</html>
