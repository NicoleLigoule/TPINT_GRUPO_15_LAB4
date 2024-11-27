<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Prestamo, entidades.CuotasXPrestamo, entidades.Usuario, java.util.List, entidades.Cuenta" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Prestamo prestamo = (Prestamo) request.getAttribute("Prestamo_seleccionado");
    if (prestamo == null) {
        out.println("<p>Error: No se recibió ningún préstamo.</p>");
        return;
    }

    int cuotasImpagas = 0;
    float monto = 0;
    float montoTotal = 0;

    if (prestamo.getCuotas() != null && prestamo.getDetalle() != null) {
        monto = prestamo.getDetalle().getImporteXCuotasDt().floatValue();
        
        for (CuotasXPrestamo cuota : prestamo.getCuotas()) {
            if (!cuota.getPagada()) {
                cuotasImpagas++;
            }
        }
        montoTotal = monto * cuotasImpagas;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pago Préstamo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/PagoPrestamo.css">
</head>
<body>
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}/Login.jsp">
            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
        </a>
        <span class="username"><%= usuario.getUsuarioUs() %></span>
        <jsp:include page="SubMenu_Cliente.jsp" />
    </nav>

    <div class="main-container">

        <div class="content">
            <h2>Pago de Prestamo</h2>
            <% if (cuotasImpagas > 0) { %>
                <p><strong>Prestamo #<%= prestamo.getIdPrestamoPt() %></strong></p>
                <p><strong>Numero de Cuenta:</strong> <%= prestamo.getNumeroDeCuentaCuPt() %></p>
                 <p><strong>Monto Pedido:</strong> $<%= prestamo.getDetalle().getImporteCInteresDt() %></p>
                  <p><strong>Monto Restante a pagar:</strong> $<%= montoTotal %></p>
                <p><strong>Cuotas Pendientes:</strong> <%= cuotasImpagas %></p>
                <br>
            </div>
            <div class="content">
               <h2>Detalles del Pago</h2>
                 
				 <p><strong>Monto a Pagar:</strong> $<%= monto %></p>
				 
                <form action="ServletPagoPrestamo" method="post">
                    <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamoPt() %>">
                    <label for="cuentaPago">Seleccionar cuenta para el pago:</label>
                    <br>
                    <select name="cuentaPago" id="cuentaPago" required>
                        <% List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas_usuario");
                        if (cuentas != null) {
                            for (Cuenta cuenta : cuentas) { %>
                                <option value="<%= cuenta.getNumeroDeCuentaCu() %>">Cuenta #<%= cuenta.getNumeroDeCuentaCu() %></option>
                        <% } } %>
                    </select>
                    <br>
                    <button type="submit">Pagar</button>
                </form>
            <% } else { %>
                <p>¡Felicidades! No tienes cuotas impagas.</p>
            <% } %>
            <% 
			    String mensajeExito = (String) request.getAttribute("Mensaje_exito");
			    String mensajeError = (String) request.getAttribute("Mensaje_error");
			%>
			
			<% if (mensajeExito != null) { %>
			    <div class="alert alert-success">
			        <p><%= mensajeExito %></p>
			    </div>
			<% } else if (mensajeError != null) { %>
			    <div class="alert alert-danger">
			        <p><%= mensajeError %></p>
			    </div>
			<% } %>
        </div>
    </div>
</body>
</html>