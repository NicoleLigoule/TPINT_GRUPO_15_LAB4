package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCuentas;
import negocio.NegocioPrestamo;

/**
 * Servlet implementation class ServletReportePrestamos
 */
@WebServlet("/ServletReportePrestamos")
public class ServletReportePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportePrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {	NegocioPrestamo Neg =new NegocioPrestamo();
			double P_PretamosAprobados = Neg.PorcentajeCreditosAprobados();
	        request.setAttribute("POrcentajesAporbados", P_PretamosAprobados); 
	        
	        double REn_PretamosAprobados = Neg.RendimientosCreditosAprobados();
	        request.setAttribute("RendimientosAporbados", REn_PretamosAprobados);
	        
	        double REnMEnsual_PretamosAprobados = Neg.RendimientosMEnsualesCreditosAprobados();
	        request.setAttribute("RendimientosMEnsualAporbados", REnMEnsual_PretamosAprobados);
	        
	        double REnAnual_PretamosPagados = Neg.RendimientosAnualesCreditosPagados();
	        request.setAttribute("RendimientosAnualPagado", REnAnual_PretamosPagados);
	        
			RequestDispatcher rd = request.getRequestDispatcher("ReportePrestamos.jsp");
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
