package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletProcesarPrestamo
 */
@WebServlet("/ServletProcesarPrestamo")
public class ServletProcesarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProcesarPrestamo() {
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
        
//        String cuentaDestino = request.getParameter("cuenta_destino");
//        double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
//        double montoConInteres = Double.parseDouble(request.getParameter("monto_con_interes"));
//        String plazoPago = request.getParameter("plazo_pago");
//        double montoPorCuota = Double.parseDouble(request.getParameter("monto_por_cuota"));
//
//        
//        boolean exito = procesarPrestamo(cuentaDestino, importeSolicitado, montoConInteres, plazoPago, montoPorCuota);
//        
//        if (exito) {
//        	request.setAttribute("mensaje", "prestamo cargado con exito");
//        	request.setAttribute("status", "success");
//        	
//        	request.setAttribute("cuenta_destino", "success");
//        	request.setAttribute("importe_solicitado", "success");
//        	request.setAttribute("monto_con_interes", "success");
//        	request.setAttribute("plazo_pago", "success");
//        	request.setAttribute("monto_por_cuota", "success");
//        	
//        	request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
////            response.sendRedirect("InicioCliente.jsp?status=success");
//        } else {
//
//            response.sendRedirect("ConfirmarPrestamo.jsp?status=error");
//        }
    }

    private boolean procesarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago, double montoPorCuota) {
    	//aca se carga en la db
    	System.out.print("procesarPrestamo::SE PROCESA EL PRESTAMO");
        return true; 
    }


}
