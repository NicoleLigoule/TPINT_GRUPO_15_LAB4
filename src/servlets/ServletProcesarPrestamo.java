package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioPrestamo;

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
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String cuentaDestino = request.getParameter("cuenta_destino");
        double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
        double montoConInteres = Double.parseDouble(request.getParameter("monto_con_interes"));
        double montoPorCuota = Double.parseDouble(request.getParameter("monto_por_cuota"));        
        String plazoPagoIXM = request.getParameter("plazo_pago_IXM");
        String motivo = request.getParameter("motivo");
        NegocioPrestamo neg=new NegocioPrestamo();

        
        boolean exito = neg.procesarPrestamo(cuentaDestino, importeSolicitado, montoConInteres, plazoPagoIXM, motivo);

        if(exito) {
        	
        	//redireccionas aca mismo con un mensaje de exito
            request.setAttribute("mensajeConfirmacion", "Préstamo confirmado y guardado exitosamente.");
        }else {
        	//redireccionas aca mismo con un mensaje de Fallo al procesar prestamos
            request.setAttribute("mensajeError", "Error al guardar el préstamo en la base de datos.");
        }
        
        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
        
    }




}
