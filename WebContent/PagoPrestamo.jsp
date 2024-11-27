<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.CuotasXPrestamo" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.util.Pair" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
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
            <div class="form-card">
                <h2>Pago de Préstamos</h2>

                <%
                    List<Pair<Integer, ArrayList<Prestamo>>> prestamoXCuenta = (List<Pair<Integer, ArrayList<Prestamo>>>) request.getAttribute("prestamosXCuenta");
                    boolean hayCuotasImpagas = false; // Para controlar si hay al menos una cuota impaga
                
                    if (prestamoXCuenta != null && !prestamoXCuenta.isEmpty()) {
                        for (Pair<Integer, ArrayList<Prestamo>> pair : prestamoXCuenta) {
                            ArrayList<Prestamo> prestamos = pair.getValue();
                            for (Prestamo prestamo : prestamos) {
                                int cuotasImpagas = 0;

     
                                if (prestamo.getCuotas() != null) {
                                    for (CuotasXPrestamo cuota : prestamo.getCuotas()) {
                                        if (!cuota.getPagada()) {
                                            cuotasImpagas = cuotasImpagas+1;
                                            hayCuotasImpagas = true;
                                        }
                                    }
                                }
                                System.out.println("Cuotas impagas: " + cuotasImpagas);  // Para depurar
                                if (cuotasImpagas > 0) {
                %>
                                   
                                   <div class="loan-card" onclick="seleccionarPrestamo(<%= prestamo.getIdPrestamoPt() %>, 
                                   '<%= prestamo.getMontoRestante() %>', '<%= cuotasImpagas %>', 
                                   '<%= prestamo.getDetalle().getImporteXCuotasDt() %>')">
                                    
                                    
                                    
                                        <h2>Préstamo #<%= prestamo.getIdPrestamoPt() %></h2>
                                        <p><strong>Número de Cuenta:</strong> <%= prestamo.getNumeroDeCuentaCuPt() %></p>
                                        <p><strong>Monto Total:</strong> $<%= prestamo.getMontoRestante() %></p>
                                        <p><strong>Cuotas Pendientes:</strong> <%= cuotasImpagas %></p>
                                        <p><strong>Precio por Cuota:</strong> $<%= prestamo.getDetalle().getImporteXCuotasDt() %></p>
                                    </div>
                <%
                                }
                            }
                        }
                    }

                    if (!hayCuotasImpagas) {
                %>
                        <p>Felicidades, no tiene cuotas impagas.</p>
                <%
                    }
                %>

                <form id="form-pago" action="RealizarPago.jsp" method="post" style="display:none;">
                    <h3>Detalles del Préstamo Seleccionado</h3>
                    <input type="hidden" name="idPrestamo" id="idPrestamo">
                    <p><strong>Monto Total:</strong> <span id="montoTotal"></span></p>
                    <p><strong>Cuotas Pendientes:</strong> <span id="cuotasPendientes"></span></p>
                    <p><strong>Precio por Cuota:</strong> <span id="precioCuota"></span></p>

                    <label for="cuentaPago">Seleccionar cuenta para el pago:</label>
                    <select name="cuentaPago" id="cuentaPago" required>
                        <%
                            // Suponiendo que hay una lista de cuentas del usuario
                            List<Integer> cuentas = (List<Integer>) request.getAttribute("cuentasUsuario");
                            if (cuentas != null) {
                                for (Integer cuenta : cuentas) {
                        %>
                                    <option value="<%= cuenta %>">Cuenta #<%= cuenta %></option>
                        <%
                                }
                            }
                        %>
                    </select>

                    <button type="submit">Pagar</button>
                </form>
            </div>
        </div>
    </div>


    <script>
        function seleccionarPrestamo(idPrestamo, montoTotal, cuotasPendientes, precioCuota) {
            document.getElementById('form-pago').style.display = 'block';
            document.getElementById('idPrestamo').value = idPrestamo;
            document.getElementById('montoTotal').textContent = `$${montoTotal}`;
            document.getElementById('cuotasPendientes').textContent = cuotasPendientes;
            document.getElementById('precioCuota').textContent = `$${precioCuota}`;
        }
    </script>
</body>
</html>
