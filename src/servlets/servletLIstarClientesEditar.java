package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDaoImpl;
import daoImpl.LocalidadDaoImpl;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import negocio.DDL;


/**
 * Servlet implementation class servletLIstarClientesEditar
 */
@WebServlet("/servletLIstarClientesEditar")
public class servletLIstarClientesEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLIstarClientesEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		if (request.getParameter("cuil") != null) {
//			ClienteDaoImpl clienteDao = new ClienteDaoImpl();
//			Cliente cliente = clienteDao.obtenerUnoPorCuil(request.getParameter("cuil"));
//			request.setAttribute("clienteSeleccionado", cliente);
//			request.getRequestDispatcher("servletEditarCliente?param=1").forward(request, response);
//		}
	}
}


