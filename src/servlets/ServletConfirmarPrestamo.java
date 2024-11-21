package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConfirmarPrestamo
 */
@WebServlet("/ServletConfirmarPrestamo")
public class ServletConfirmarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConfirmarPrestamo() {
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
       
        String cuentaDestino = request.getParameter("cuenta_destino");
        double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
        int plazoPago = Integer.parseInt(request.getParameter("plazo_pago"));


        double tasaInteres = 0.05; 
        double montoConInteres = importeSolicitado * Math.pow(1 + tasaInteres, plazoPago);
        double montoPorCuota = montoConInteres / plazoPago;

        
        request.setAttribute("cuentaDestino", cuentaDestino);
        request.setAttribute("importeSolicitado", importeSolicitado);
        request.setAttribute("montoConInteres", montoConInteres);
        request.setAttribute("plazoPago", plazoPago);
        request.setAttribute("montoPorCuota", montoPorCuota);

        
        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
    }

}
