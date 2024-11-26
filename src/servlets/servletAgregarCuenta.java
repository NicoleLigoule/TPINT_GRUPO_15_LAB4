package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.TipoDeCuenta;
import negocio.DDL;
import negocio.NegocioCuentas;
	
	@WebServlet("/servletAgregarCuenta")
	public class servletAgregarCuenta extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    public servletAgregarCuenta() {
	        super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	System.out.print("LLEGA AL SERVLET");
	        if (request.getParameter("Param") != null) {
	            DDL lista = new DDL();
	            try {
	                ArrayList<TipoDeCuenta> listaSeg = lista.TipoDecuenta();
	                if (listaSeg != null && !listaSeg.isEmpty()) {
	                    request.setAttribute("listaTCuentas", listaSeg);
	                    RequestDispatcher rd = request.getRequestDispatcher("AgregarCuenta.jsp");
	                    rd.forward(request, response);
	                } else {
	                    response.getWriter().println("No se encontraron tipos de cuenta.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                response.getWriter().println("Error al obtener los tipos de cuenta: " + e.getMessage());
	            }
	        }
	    }
	    
	    
	    
	    
	    
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("ENTRO AL SERVLET");

	        if (request.getParameter("agregarBtn") != null) {
	            Cuenta cuenta = new Cuenta();
	            
	            // Obtener y establecer tipo de cuenta
	            String tipoCuenta = request.getParameter("tipoCuenta");
	            if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
	                try {
	                    cuenta.setIdTipoCuenta(Integer.parseInt(tipoCuenta));
	                    System.out.println("Tipo de Cuenta: " + tipoCuenta);
	                } catch (NumberFormatException e) {
	                    System.out.println("Error: Tipo de cuenta no válido.");
	                    request.setAttribute("Error", "Tipo de cuenta no válido.");
	                    request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
	                    return;
	                }
	            }

	            

	            // Obtener y validar fecha de apertura
	            String fechaAperturaStr = request.getParameter("fechaApertura");
	            if (fechaAperturaStr != null && !fechaAperturaStr.isEmpty()) {
	                try {
	                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                    LocalDate fechaApertura = LocalDate.parse(fechaAperturaStr, formatter);
	                    cuenta.setFechaCreacionCu(fechaApertura);
	                    System.out.println("Fecha de Apertura: " + fechaApertura);
	                } catch (Exception e) {
	                    System.out.println("Error: Fecha de apertura no válida.");
	                    request.setAttribute("Error", "Fecha de apertura no válida.");
	                    request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
	                    return;
	                }
	            }

	            // Obtener y establecer el estado de la cuenta (checkbox)
	            String estadoCheckbox = request.getParameter("estado");
	            cuenta.setEstadoCu(estadoCheckbox != null); // true si está marcado, false si no lo está
	            System.out.println("Estado de la Cuenta: " + (estadoCheckbox != null));

	            // Llamada a la lógica de negocio para agregar cuenta
	            NegocioCuentas negCue = new NegocioCuentas();
	            boolean insert = negCue.agregarCuenta(cuenta);

	            // Verificar si la inserción fue exitosa
	            if (insert) {
	                System.out.println("Cuenta agregada exitosamente.");
	                response.sendRedirect("AgregarCuenta.jsp?status=success");
	            } else {
	                System.out.println("Error al agregar cuenta.");
	                request.setAttribute("Error", "Error al agregar Cuenta.");
	                RequestDispatcher rd = request.getRequestDispatcher("AgregarCuenta.jsp");
	                rd.forward(request, response);
	            }
	        }
	    }

	}
