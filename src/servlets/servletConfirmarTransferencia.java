package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.NegocioTransferencia;

/**
 * Servlet implementation class ConfirmarTransferencia
 */
@WebServlet("/servletConfirmarTransferencia")
public class servletConfirmarTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletConfirmarTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("entro al servlet confirmar transferencia");

        // Obtener los datos de la sesión
        HttpSession session = request.getSession();
        request.setAttribute("cuentaOrigen", session.getAttribute("cuentaOrigen"));
        request.setAttribute("cbuDestino", session.getAttribute("cbuDestino"));
        request.setAttribute("importe", session.getAttribute("importe"));
        request.setAttribute("detalle", session.getAttribute("detalle"));
        
        // Redirigir a la página de confirmación
        RequestDispatcher dispatcher = request.getRequestDispatcher("ConfirmarTransferencia.jsp");
        dispatcher.forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Verificar si el botón "RealizarBtn" fue presionado
            if (request.getParameter("ConfirmarBtn") != null) {
                // Obtener datos confirmados
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                String cbuDestino = request.getParameter("cbuDestino");
                double importe = Double.parseDouble(request.getParameter("importe"));
                String detalle = request.getParameter("detalle");

                // Obtener la fecha y hora actuales
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaHoraTransferencia = ahora.format(formatter);

                // Simular transferencia (aquí llama a la lógica real)
                NegocioTransferencia transferenciaNegocio = new NegocioTransferencia();
                transferenciaNegocio.realizarTransferencia(cuentaOrigen, cbuDestino, importe, detalle);

                // Guardar los datos en la sesión para mostrar en la página de éxito
                HttpSession session = request.getSession();
                session.setAttribute("cbuDestino", cbuDestino);
                session.setAttribute("importe", importe);
                session.setAttribute("fechaHoraTransferencia", fechaHoraTransferencia);
                session.setAttribute("detalle", detalle);

                // Redirigir a la página de éxito
                response.sendRedirect("transferenciaExitosa.jsp");
            } else {
                // Si no se presionó el botón, redirigir a la página de transferencia
                response.sendRedirect("Transferencia.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al confirmar la transferencia: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("ConfirmarTransferencia.jsp");
            rd.forward(request, response);
        }
    }


}
