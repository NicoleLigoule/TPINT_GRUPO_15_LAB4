package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.MovimientoDaoImpl;
import entidades.Movimiento;

/**
 * Servlet implementation class servletHistorialMovimiento
 */
@WebServlet("/servletHistorialMovimiento")
public class servletHistorialMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletHistorialMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("Llega al Servlet historial movimiento");
        if (request.getParameter("Param") != null) {
            MovimientoDaoImpl movimientoDao = new MovimientoDaoImpl();
            try {
                System.out.print("Obtiene movimientos");
                List<Movimiento> listaMovimientos = movimientoDao.obtenerMovimientos();

                if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
                    System.out.println("Contenido de listaMovimientos:");
                    for (Movimiento movimiento : listaMovimientos) {
                        System.out.println(movimiento);
                    }
                    request.setAttribute("listaMovimientos", listaMovimientos);
                } else {
                    request.setAttribute("Mensaje", "No se encontraron movimientos.");
                }

                RequestDispatcher rd = request.getRequestDispatcher("Movimientos.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener la lista de movimientos: " + e.getMessage());
            }
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
