<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.CuotasXPrestamo" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>


<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Obtén el objeto prestamo desde los atributos del request o session
    Prestamo prestamo = (Prestamo) request.getAttribute("Prestamo_seleccionado");
    if (prestamo == null) {
        out.println("<p>Error: No se recibió ningún préstamo.</p>");
        return;
    }

    // Contar cuotas impagas
    int cuotasImpagas = 0;
    float monto = 0;

    if (prestamo.getCuotas() != null && prestamo.getDetalle() != null) {
    	monto = prestamo.getDetalle().getImporteXCuotasDt().floatValue();
        for (CuotasXPrestamo cuota : prestamo.getCuotas()) {
            if (!cuota.getPagada()) {
                cuotasImpagas++;                
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pago Prestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/PagoPrestamo.css">
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
            <div class="form-card">
                <h2>Pago de Préstamo</h2>
                <%
                    if (cuotasImpagas > 0) {
                %>
                    <div class="loan-card">
                        <h2>Préstamo #<%= prestamo.getIdPrestamoPt() %></h2>
                        <p><strong>Número de Cuenta:</strong> <%= prestamo.getNumeroDeCuentaCuPt() %></p>
                        <p><strong>Monto Total:</strong> $<%= prestamo.getMontoRestante() %></p>
                        <p><strong>Cuotas Pendientes:</strong> <%= cuotasImpagas %></p>
                        <p><strong>Precio por Cuota:</strong> $<%= monto != 0 ? monto : "N/A" %></p>
                    </div>

                    <form id="form-pago" action="ServletPagoPrestamo" method="post">
                        <h3>Detalles del Pago</h3>
                        <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamoPt() %>">
                        
                        
                        <p><strong>Monto a Pagar:</strong> $<%= monto != 0 ? monto : "N/A" %></p>
                        
                        <label for="cuentaPago">Seleccionar cuenta para el pago:</label>
                        <select name="cuentaPago" id="cuentaPago" required>
                            <%
                                // Suponiendo que tienes una lista de cuentas del usuario
                                List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas_usuario");
                                if (cuentas != null) {
                                    for (Cuenta cuenta : cuentas) {
                            %>
                                        <option value="<%= cuenta.getNumeroDeCuentaCu() %>">Cuenta #<%= cuenta.getNumeroDeCuentaCu() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>

                        <button type="submit">Pagar</button>
                    </form>
                <%
                    } else {
                %>
                    <p>Felicidades, no tiene cuotas impagas.</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
