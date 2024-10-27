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
        margin: 0;
        padding-top: 60px; 
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        box-sizing: border-box;
    }

    nav {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #a05d98;
        padding: 15px 0;
        text-align: center;
        color: white;
        box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        z-index: 1000;
    }

    nav img {
        vertical-align: middle;
        width: 40px;
        height: auto;
        margin-right: 10px;
    }

    nav h1 {
        display: inline;
        font-size: 18px;
        color: white;
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

    h2 {
        font-size: 20px;
        color: #333;
    }

    label {
        font-size: 14px;
        color: #333;
        text-align: left;
        display: block;
        margin-top: 10px;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
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
        margin-top: 15px;
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
    <nav>
        <img src="logo.png" alt="Logo UTN">
        <h1>BANCO UTN</h1>
    </nav>
    <div class="container">
        <h2>Iniciar Sesión</h2>
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
