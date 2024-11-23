package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrestamoDao;
import daoImpl.InteresesXCantidadDeMesesDaoImpl;
import daoImpl.PrestamoDaoImpl;
import entidades.InteresesXCantidadDeMeses;

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
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       
//        String cuentaDestino = request.getParameter("cuenta_destino");
//        double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
//        int plazoPago = Integer.parseInt(request.getParameter("plazo_pago"));
//
//
//        double tasaInteres = 0.05; 
//        double montoConInteres = importeSolicitado * Math.pow(1 + tasaInteres, plazoPago);
//        double montoPorCuota = montoConInteres / plazoPago;
//
//        
//        request.setAttribute("cuentaDestino", cuentaDestino);
//        request.setAttribute("importeSolicitado", importeSolicitado);
//        request.setAttribute("montoConInteres", montoConInteres);
//        request.setAttribute("plazoPago", plazoPago);
//        request.setAttribute("montoPorCuota", montoPorCuota);
//
//        
//        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
//    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String cuentaDestino = request.getParameter("cuenta_destino");
	        double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
	        String plazoPago = request.getParameter("plazo_pago");
	        int plazoPagoMeses = 1;
	        
	        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();	        
	        boolean plazoValido = prestamoDao.comprobarPlazoExistente(plazoPago);
	        
	        InteresesXCantidadDeMesesDaoImpl plazoDao = new InteresesXCantidadDeMesesDaoImpl();
	        InteresesXCantidadDeMeses interes = null; 
	        
	        if(plazoValido) {
	        	interes = plazoDao.obtenerUno(plazoPago);
		        plazoPagoMeses = interes.getMeses();

	        }
	        
	        
	        
	        // Cálculos del préstamo
	        double tasaInteres = 0.05;
	        double montoConInteres = importeSolicitado * Math.pow(1 + tasaInteres, plazoPagoMeses);
	        double montoPorCuota = montoConInteres / plazoPagoMeses;


	     // Verificar si el plazoPago existe en la tabla 'interesxcantidaddmeses'
	     

	     if (!plazoValido) {
	         request.setAttribute("mensajeError", "El plazo de pago no es válido.");
	         request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
	         return;
	     }

	     // Si el plazo es válido, guardar el préstamo
	     boolean guardado = prestamoDao.guardarPrestamo(cuentaDestino, importeSolicitado, montoConInteres, interes, montoPorCuota);

	        if (guardado) {
	            // Si se guarda correctamente, mostrar mensaje de éxito
	            request.setAttribute("mensajeConfirmacion", "Préstamo confirmado y guardado exitosamente.");
	        } else {
	            // Si hay un error al guardar, mostrar mensaje de error
	            request.setAttribute("mensajeError", "Error al guardar el préstamo en la base de datos.");
	        }

	        // Establecer atributos para pasar al JSP de confirmación
	        request.setAttribute("cuenta_destino", cuentaDestino);
	        request.setAttribute("importe_solicitado", importeSolicitado);
	        request.setAttribute("monto_con_interes", montoConInteres);
	        request.setAttribute("plazo_pago", plazoPago);
	        request.setAttribute("monto_por_cuota", montoPorCuota);

	        // Redirigir a la página de confirmación
	        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);

	    } catch (NumberFormatException e) {
	        request.setAttribute("mensajeError", "Hubo un error al procesar los datos. Verifique los valores ingresados.");
	        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
	    } catch (Exception e) {
	        request.setAttribute("mensajeError", "Hubo un problema al procesar la solicitud de préstamo.");
	        request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
	    }
	}
	
}
