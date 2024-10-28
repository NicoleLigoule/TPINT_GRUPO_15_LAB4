<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Css/AgregarCli.css" type="text/Css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
            <input type="text" id="Fecha" name="Fecha" placeholder="AAAA/MM/DD" required >
            
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