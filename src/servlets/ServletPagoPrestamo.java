package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            double monto = Double.parseDouble(request.getParameter("monto"));

          
            boolean exito = prestamoNegocio.realizarPagoPrestamo(idPrestamo, monto);

       
            String mensaje;
            if (exito) {
                mensaje = "El pago se realizó con éxito.";
            } else {
                mensaje = "El pago no se pudo realizar. Verifique los datos.";
            }

            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("PagoPrestamo.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en los datos enviados."); // cambiar
        }
    }
}
