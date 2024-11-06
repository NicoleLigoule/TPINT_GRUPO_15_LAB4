<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PagoPrestamo</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/PagoPrestamo.css">
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
        <span class="username">USUARIO XXXX</span>
    </nav>

    <div class="main-container">
        <aside class="sidebar" id="sidebar">
            <ul>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Transferencias</a>
                   
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Solicitudes de préstamos</a>
                    
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Pago de préstamos</a>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Información personal</a>
                </li>
            </ul>
        </aside>

        <div class="content">
            <div class="form-card">
                <h2>Pago de Préstamos</h2>
                <p>Seleccione el préstamo y la cuota que desea pagar:</p>
                
                <form action="ProcederPago.jsp" method="post">
                    <table class="loan-table">
                        <thead>
                            <tr>
                                <th>Número de Préstamo</th>
                                <th>Importe de la Cuota</th>
                                <th>Cuotas Restantes</th>
                                <th>Pagar Cuota</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>123456</td>
                                <td>$500.00</td>
                                <td>12</td>
                                <td>
                                    <button type="submit" name="pago" value="123456" class="pay-button">Pagar</button>
                                </td>
                            </tr>
                            <tr>
                                <td>789101</td>
                                <td>$650.00</td>
                                <td>8</td>
                                <td>
                                    <button type="submit" name="pago" value="789101" class="pay-button">Pagar</button>
                                </td>
                            </tr>
                            <tr>
                                <td>112131</td>
                                <td>$300.00</td>
                                <td>15</td>
                                <td>
                                    <button type="submit" name="pago" value="112131" class="pay-button">Pagar</button>
                                </td>
                            </tr>
                            <tr>
                                <td>141516</td>
                                <td>$450.00</td>
                                <td>10</td>
                                <td>
                                    <button type="submit" name="pago" value="141516" class="pay-button">Pagar</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <script src="JS/MenuAdm.js"></script>
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }

        function toggleSubmenu(event) {
            const menuItem = event.target.parentElement;
            menuItem.classList.toggle('active');
        }
    </script>
</body>
</html>
