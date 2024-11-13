<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ModificarContrasenia</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/ModificarContraseniaCli.css">
<body>
    <nav class="navbar">
        <button class="hamburger" onclick="toggleSidebar()">
            <div class="line"></div>
            <div class="line"></div>
            <div class="line"></div>
        </button>
        <a href="${pageContext.request.contextPath}/Login.jsp">
            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
        </a>
        <span class="username"><%= usuario.getUsuarioUs() %></span>
    </nav>
    <div class="container">
        <h2>Cambiar Contraseña</h2>
        <form action="login.jsp" method="post">
                <label for="usuario">Usuario:  XXXXXX</label>

	            <label for="contrasena">Contraseña</label>
	            <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required>
            
            <label for="contrasenaRE">Repita Contraseña</label>
            <input type="password" id="contrasenaRE" name="contrasenaRE" placeholder="Repita Contraseña" required>
            
            <button type="submit">Guardar</button>
        </form>

    </div>
</body>

</html>