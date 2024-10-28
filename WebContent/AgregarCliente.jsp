<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
        background-color: #f5f5f5;
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
        background-color: #d69ecf;
        padding: 15px 0;
        text-align: center;
        color: #d69ecf;
        box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        z-index: 1000;
    }

    nav img {
        vertical-align: middle;
        width: 40px;
        height: auto;
        margin-right: 10px;

    }
    .img_logo{
     width: 60px;
     height: 60px;
  }
      nav h1 {
        display: inline;
        font-size: 18px;
        color: white;
        margin: 0;
    }
    .container {
        
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        width: 550px;
        text-align: center;
    }
        label {
        font-size: 14px;
        color: #333;
        text-align: left;
        display: block;
        margin-top: 10px;
    }
  button {
        background-color: #333;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        width: 40%;
        margin-top: 15px;
    }
   
</style>
</head>
<body>
	<nav>
        <img src="img/png_logo.png" class="img_logo" alt="Logo UTN">
        <h1>REGISTAR CLIENTE</h1>
    </nav>
        <div class="container">
        <h2>ALTA DE CLIENTE</h2>
        <form action="login.jsp" method="post">
            <label for="usuario">D.N.I</label>
            <input type="text" id="DNI" name="DNI" placeholder="DNI" required >
            
            <label for="CUIL">CUIL</label>
            <input type="CUIL" id="CUIL" name="CUIL" placeholder="CUIL" required >
            
            <label for="Nombre">Nombre</label>
            <input type="text" id="Nombre" name="Nombre" placeholder="Nombre" required >
            
            <label for="Apellido">Apellido</label>
            <input type="text" id="Apellido" name="Apellido" placeholder="Apellido" required >

            <label for="genero">Género:</label>
            <select id="genero" name="genero" required>
            <option value="" disabled selected>Seleccione su género</option>
            </select>
            
            <label for="Nacionalidad">Nacionalidad:</label>
            <input type="text" id="Nacionalidad" name="Nacionalidad" placeholder="Nacionalidad" required >
            
            <label for="Fecha">Fecha de Nacimiento</label>
            <input type="text" id="Fecha" name="Fecha" placeholder="DD/MM/AA" required >
            
            <label for="Dirección">Dirección</label>
            <input type="text" id="Dirección" name="Dirección" placeholder="Dirección" required >
            
            <label for="Provincia">Provincia</label>
            <select id="Provincia" name="Provincia" required>
            <option value="" disabled selected>Seleccione su Provincia</option>
            </select>
            <label for="Localidad">Localidad</label>
            <select id="Localidad" name="Localidad" required>
            <option value="" disabled selected>Seleccione su Localidad</option>
           </select>
             <label for="Mail">Mail</label>
            <input type="text" id="Mail" name="Mail" placeholder="Mail" required >
            
 			<label for="Telefono">Teléfono</label>
            <input type="text" id="Teléfono" name="Teléfono" placeholder="Teléfono" required >
            
            <label for="Telefono2">Teléfono2</label>
            <input type="text" id="Teléfono2" name="Teléfono2" placeholder="Teléfono2" required >
            
            <label for="Telefono"></label>

  <button type="submit">CANCELAR</button>  <button type="submit">ACEPTAR</button>
        </form>

    </div>
</body>
</html>