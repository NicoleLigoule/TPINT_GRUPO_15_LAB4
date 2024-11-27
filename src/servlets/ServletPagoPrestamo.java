package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.Prestamo;
import entidades.Usuario;
import javafx.util.Pair;
import negocio.NegocioCuentas;
import entidades.Prestamo;
import entidades.Usuario;
import negocio.NegocioPrestamo;

@WebServlet("/ServletPagoPrestamo")
public class ServletPagoPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioPrestamo prestamoNegocio;

    public ServletPagoPrestamo() {
        super();
        this.prestamoNegocio = new NegocioPrestamo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.print("doGet ServletPagoPrestamo");
    	
    	
        if (request.getParameter("Param") != null) {
            try {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
                NegocioCuentas negocioCuentas = new NegocioCuentas();
                ArrayList<Pair<Integer, ArrayList<Prestamo>>> prestamoXCuenta = new ArrayList<Pair<Integer, ArrayList<Prestamo>>>();
                
                if (usuario == null) {
                    response.sendRedirect("Login.jsp");
                    return;
                }
                
                String cuil = usuario.getCuilUs();
                System.out.println("PagoPrestamo | CUIL del usuario: " + cuil);
                
               
                List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);
                
                if (cuentas == null || cuentas.isEmpty()) {
                    System.out.println("PagoPrestamo | No se encontraron cuentas para el CUIL: " + cuil);
                } else {
                    for (Cuenta cuenta : cuentas) {
                    	ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) negocioPrestamo.obtenerPrestamoPorCuentaConDetalle(cuenta.getNumeroDeCuentaCu());
                     
                        prestamoXCuenta.add(new Pair<>(cuenta.getNumeroDeCuentaCu(), listaPrestamos));
                    }
                    
                   request.setAttribute("PrestamosXCuenta", prestamoXCuenta);
                }
                
                System.out.println("Lista de prestamos: " + prestamoXCuenta);

                RequestDispatcher rd = request.getRequestDispatcher("PagoPrestamo_Seleccionar.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("error al obtener los datos: " + e.getMessage());
            }
        }else if (request.getParameter("idPrestamo") != null) {
//        	traer prestamo y meterlo en atributte, despues llamar al jsp que sigue
        	
        	NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
        	Prestamo pr = negocioPrestamo.PrestamoCargado(Integer.parseInt(request.getParameter("idPrestamo")));
        	request.setAttribute("Prestamo_seleccionado", pr);
        	
        	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        	if (usuario == null) {
                response.sendRedirect("Login.jsp");
                return;
            }
            
            String cuil = usuario.getCuilUs();
            NegocioCuentas negocioCuentas = new NegocioCuentas();
            List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);
        	

            request.setAttribute("cuentas_usuario", cuentas);
        	
        	request.getRequestDispatcher("PagoPrestamo.jsp").forward(request, response);
        	
        	
        	
        }
        
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int numCuenta = Integer.parseInt(request.getParameter("cuentaPago"));
    	int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
    	
    	NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
    	
    	if(negocioPrestamo.pagarCuota(idPrestamo, numCuenta)) {
    		request.setAttribute("Mensaje_exito", "Se realizo el pago con exito de la cuota");
    	}else {
    		request.setAttribute("Mensaje_error", "Fallo al pagar la cuota");
    	}
    	
    	
    	request.getRequestDispatcher("PagoPrestamo.jsp").forward(request, response);
    	
    	
    }

   

    
    
}
