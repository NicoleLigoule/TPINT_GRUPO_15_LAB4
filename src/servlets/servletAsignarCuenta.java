package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abml.abmlCuenta;
import entidades.Cuenta;

/**
 * Servlet implementation class servletAsignarCuenta
 */
@WebServlet("/servletAsignarCuenta")
public class servletAsignarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAsignarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		abmlCuenta abmlCu = new abmlCuenta();
		int numeroCuenta = 0;
		String cuilCliente = request.getParameter("cuilCliente");
		if(request.getParameter("numeroCuenta") != null &&
				cuilCliente != null) {
			numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
			System.out.print("cuil y numero de cuenta obtenidos exitosamente" + cuilCliente +" | "+ numeroCuenta);
			
			Cuenta cuenta = abmlCu.obtenerCuenta(numeroCuenta);
			cuenta.setCuilCliCu(cuilCliente);
			
			if(abmlCu.editarCuenta(cuenta)) {      
                response.sendRedirect("AsignarCuenta.jsp?status=success");
            } else {
                request.setAttribute("Error", "Error al agregar Cuenta a Cliente.");
                RequestDispatcher rd = request.getRequestDispatcher("AsignarCuenta.jsp");
                rd.forward(request, response);
            }   
    	}
		
	}

}
