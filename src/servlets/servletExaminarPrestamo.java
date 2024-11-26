package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Prestamo;
import negocio.NegocioPrestamo;

/**
 * Servlet implementation class servletExaminarPrestamo
 */
@WebServlet("/servletExaminarPrestamo")
public class servletExaminarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletExaminarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int IDPRestamo=-1;
        NegocioPrestamo ddlPrestamo = new NegocioPrestamo();
        String IDPRestam = request.getParameter("PrestamoSelec");
        if (IDPRestam != null && !IDPRestam.isEmpty()) {
        	IDPRestamo= Integer.parseInt(IDPRestam);
        }
        
        try {
        	System.out.print("entra al prestmao");
          Prestamo Prestamo = ddlPrestamo.obtenerPrestamo(IDPRestamo);
                request.setAttribute("Prestamo", Prestamo); 
                    System.out.println(Prestamo.toString());
                
            RequestDispatcher rd = request.getRequestDispatcher("ExaminarPrestamo.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al obtener la lista de prestamos: " + e.getMessage());
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
