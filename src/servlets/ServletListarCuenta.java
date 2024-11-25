package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import negocio.NegocioCuentas;

/**
 * Servlet implementation class ServletListarCuenta
 */
@WebServlet("/ServletListarCuenta")
public class ServletListarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("LLEGA AL SERVLET");
		System.out.print("LLEGA O NO????");
        if (request.getParameter("Param") != null) {
            NegocioCuentas negCue = new NegocioCuentas();
            try {
            	System.out.print("entra a lista");
                List<Cuenta> listaCuenta = negCue.obtenerCuentaTodos();

                if (listaCuenta != null && !listaCuenta.isEmpty()) {
                    System.out.println("Contenido de listaCuenta:");
                    for (Object cuenta : listaCuenta) {
                        System.out.println(cuenta);
                    }
                    request.setAttribute("listaCuenta", listaCuenta); 

                } else {
                    request.setAttribute("Mensaje", "No se encontraron cuentas.");
                }

                
                RequestDispatcher rd = request.getRequestDispatcher("ListarCuenta.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener la lista de cuentas: " + e.getMessage());
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
