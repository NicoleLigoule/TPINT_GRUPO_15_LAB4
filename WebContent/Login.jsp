<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Css/Login.css" type="text/Css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nombre del Banco</title>

</head>
<body>
    <nav>
        <div class="nav-content">
            <a href="#Login.jsp">
            	<img src="img/png_logo.png" class="img_logo" alt="Logo UTN">
            </a>
            
            <h1>BANCO UTN</h1>
        </div>
    </nav>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="login.jsp" method="post">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
            
            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required>
            
            <button type="submit">Ingresar</button>
        </form>
        <div class="links">
            <a href="#">¿Olvidó su contraseña?</a>
            <a href="#">¿No posee aún una cuenta?</a>
        </div>
    </div>
</body>

</html>
