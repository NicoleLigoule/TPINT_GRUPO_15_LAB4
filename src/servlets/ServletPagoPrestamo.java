package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.PrestamoNegocio;

@WebServlet("/ServletPagoPrestamo")
public class ServletPagoPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PrestamoNegocio prestamoNegocio;

    public ServletPagoPrestamo() {
        super();
        this.prestamoNegocio = new PrestamoNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirige a una página de error o a un formulario de pago
        response.sendRedirect("PagoPrestamo.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            double monto = Double.parseDouble(request.getParameter("monto"));

            // Llamar a la capa de negocio
            boolean exito = prestamoNegocio.realizarPagoPrestamo(idPrestamo, monto);

            // Redirigir según el resultado
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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en los datos enviados.");
        }
    }
}
