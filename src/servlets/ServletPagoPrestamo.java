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
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // Nos llevamos el pago del prestamo y actualizamos cuotas y monto faltante
      
      
        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

      
        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamoPorCuenta(idCuenta);

    
        for (Prestamo prestamo : prestamos) {
        	
        	// HACER OTRA ENTIDAD PARA PRESTAMOSINFO
//            int cuotasPagadas = prestamo.obtenerCuotasPagadas(prestamo.getIdPrestamoPt());
//            
//            
//            prestamo.setCuotasPagadas(cuotasPagadas);
//            prestamo.setMontoRestante(calcularMontoRestante(prestamo));

            System.out.println("Prestamo ID: " + prestamo.getIdPrestamoPt() +
//                    ", Cuotas Pagadas: " + cuotasPagadas +
                    ", Monto Restante: " + prestamo.getMontoRestante());
        }

      
        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("/listaPrestamos.jsp").forward(request, response);
    }

    private double calcularMontoRestante(Prestamo prestamo) {
       
//        double total = prestamo.getMontoTotal();
//        double pagado = prestamo.getMontoPagado();
//        return total - pagado;
    	return 0;
    }

    
    
}
