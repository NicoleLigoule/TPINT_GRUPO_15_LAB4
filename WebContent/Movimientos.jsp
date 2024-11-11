<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Movimientos.css">
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
                    <a href="#" onclick="toggleSubmenu(event)">Solicitudes de prestamos</a>
                </li>
                
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Pago de prestamos</a>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Informacion personal</a>
                </li>
            </ul>
        </aside>

    
        <aside class="sidebar" id="sidebar">
            <ul>
                <li><a href="#">Transferencias</a></li>
                <li><a href="#">Solicitudes de prestamos</a></li>
                <li><a href="#">Pago de prestamos</a></li>
                <li><a href="#">Información personal</a></li>
            </ul>
        </aside>

        <section class="content">
            <!-- movimientos -->
            <div class="movements-list">
                <h3>Hoy</h3>
                <div class="movement-item">
                    <div class="movement-icon"></div>
                    <div class="movement-details">
                        <p><strong>Claudio Nuñez</strong></p>
                        <p>Transferencia enviada</p>
                        <p>Caja de ahorro</p>
                    </div>
                    <div class="movement-amount-negative">- $17,278.40 </div>
                    <div class="movement-time"> 18:28</div>
                </div>
             
            	<h3>3 de noviembre</h3>
                <div class="movement-item">
                    <div class="movement-icon"></div>
                    <div class="movement-details">
                        <p><strong>Tomas Munguia</strong></p>
                        <p>Ingreso de dinero</p>
                        <p>Transferencia externa</p>
                    </div>
                    <div class="movement-amount-positive">+ $220,000.00 </div>
                    <div class="movement-time"> 14:34</div>
                </div>

                <h3>2 de noviembre</h3>
                <div class="movement-item">
                    <div class="movement-icon"></div>
                    <div class="movement-details">
                        <p><strong>Matias Ferrero</strong></p>
                        <p>Transferencia enviada</p>
                        <p>Cuenta Corriente</p>
                    </div>
                    <div class="movement-amount-negative">- $9,200.00 </div>
                    <div class="movement-time"> 19:49</div>
                </div>
                
            	<h3>1 de noviembre</h3>
                  <div class="movement-item">
                    <div class="movement-icon"></div>
                    <div class="movement-details">
                        <p><strong>Daniel Rios</strong></p>
                        <p>Transferencia enviada</p>
                        <p>Caja de ahorro</p>
                    </div>
                    <div class="movement-amount-negative">- $55,450.40 </div>
                    <div class="movement-time"> 21:40</div>
                </div>
                
            	<h3>1 de noviembre</h3>
                <div class="movement-item">
                    <div class="movement-icon"></div>
                    <div class="movement-details">
                        <p><strong>Dante Luongo</strong></p>
                        <p>Ingreso de dinero</p>
                        <p>Transferencia externa</p>
                    </div>
                    <div class="movement-amount-positive">+ $70,000.00 </div>
                    <div class="movement-time"> 18:20</div>
                </div>
            </div>

            <!-- paginacion -->
            <div class="pagination">
                <span>1</span>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">Siguiente ></a>
            </div>
        </section>
    </div>

    <script src="JS/MenuAdm.js"></script>
</body>
</html>
