package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.TipoDeCuenta;
import negocio.DDL;

/**
 * Servlet implementation class servletsSolicitarCuenta
 */
@WebServlet("/servletsSolicitarCuenta")
public class servletsSolicitarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletsSolicitarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null)
		{
		DDL lista=new DDL();
		ArrayList<TipoDeCuenta> listaSeg =lista.TipoDecuenta();            
        
		request.setAttribute("listaTCuentas", listaSeg);
		RequestDispatcher rd = request.getRequestDispatcher("Admin/AgregarCuenta.jsp");   
        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
