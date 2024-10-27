<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nombre del Banco</title>
<style>
    body {
        background-color: #924b8b;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background-color: #d69ecf;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-bottom: 20px;
    }

    .header img {
        width: 50px;
        height: auto;
    }

    .header h1 {
        font-size: 14px;
        color: white;
        margin: 0;
    }

    h1 {
        font-size: 20px;
        color: #333;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border-radius: 5px;
        border: 1px solid #ccc;
        font-size: 14px;
    }

    button {
        background-color: #333;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        width: 100%;
    }

    button:hover {
        background-color: #555;
    }

    .links {
        display: flex;
        justify-content: space-between;
        font-size: 12px;
        margin-top: 10px;
        color: #333;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="logo.png" alt="Logo UTN">
            <h1>NOMBRE DEL BANCO</h1>
        </div>
        <h1>Iniciar Sesión</h1>
        <form action="login.jsp" method="post">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Usuario" required >
            
            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required >
            
            <button type="submit">Ingresar</button>
        </form>
        <div class="links">
            <a href="#">¿Olvidó su contraseña?</a>
            <a href="#">¿No posee aún una cuenta?</a>
        </div>
    </div>
</body>
</html>
