package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
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
        // Obt�n el par�metro de la cuenta desde el request
        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

        // Obt�n la lista de pr�stamos por cuenta
        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamoPorCuenta(idCuenta);

        // Procesa cada pr�stamo y calcula las cuotas pagadas y el monto restante
        for (Prestamo prestamo : prestamos) {
        	
        	// HACER OTRA ENTIDAD PARA PRESTAMOSINFO
            int cuotasPagadas = PrestamoDao.obtenerCuotasPagadas(prestamo.getIdPrestamoPt()); // Calcula cuotas pagadas
            
            
            prestamo.setCuotasPagadas(cuotasPagadas);
            prestamo.setMontoRestante(calcularMontoRestante(prestamo)); // M�todo de c�lculo

            // Debugging/log (puedes eliminarlo si no es necesario)
            System.out.println("Prestamo ID: " + prestamo.getIdPrestamoPt() +
                    ", Cuotas Pagadas: " + cuotasPagadas +
                    ", Monto Restante: " + prestamo.getMontoRestante());
        }

        // Guarda la lista en el request y redirige a una p�gina JSP
        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("/listaPrestamos.jsp").forward(request, response);
    }

    private double calcularMontoRestante(Prestamo prestamo) {
        // L�gica para calcular el monto restante basado en las cuotas pagadas
        double total = prestamo.getMontoTotal();
        double pagado = prestamo.getMontoPagado();
        return total - pagado; // L�gica simplificada
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener par�metros del formulario
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            double monto = Double.parseDouble(request.getParameter("monto"));

            // Llamar a la capa de negocio
            boolean exito = prestamoNegocio.realizarPagoPrestamo(idPrestamo, monto);

            // Redirigir seg�n el resultado
            String mensaje;
            if (exito) {
                mensaje = "El pago se realiz� con �xito.";
            } else {
                mensaje = "El pago no se pudo realizar. Verifique los datos.";
            }

            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("PagoPrestamo.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en los datos enviados.");
        }
    }
}
