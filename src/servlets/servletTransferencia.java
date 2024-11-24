package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.TransferenciaDaoImpl;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletTransferencia() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si se necesita verificar un par�metro de la URL (como 'Param'), se puede manejar aqu�
        System.out.println("Entro al servlet transferencia");
        String param = request.getParameter("Param");
        if (param != null) {
            System.out.println("El par�metro Param recibido es: " + param);
        }

        // Aqu� redirigimos a la p�gina de la transferencia si es necesario
        RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificamos si se ha presionado el bot�n "Realizar Transferencia"
    
        if(request.getParameter("RealizarBtn") != null) {
            try {
                // Obtener los par�metros del formulario (cuentas, monto y detalle)
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                int cuentaDestino = Integer.parseInt(request.getParameter("cuentaDestino"));
                double monto = Double.parseDouble(request.getParameter("monto"));
                String detalle = request.getParameter("detalle");

                // Validaci�n de los par�metros
                if (cuentaOrigen <= 0 || cuentaDestino <= 0 || monto <= 0) {
                    // Si los par�metros no son v�lidos, enviamos un mensaje de error
                    request.setAttribute("error", "Par�metros inv�lidos para la transferencia.");
                    RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                    rd.forward(request, response);
                    return;
                }

                // Realizar la transferencia (usando TransferenciaDaoImpl)
                TransferenciaDaoImpl transferenciaDao = new TransferenciaDaoImpl();
                transferenciaDao.realizarTransferencia(cuentaOrigen, cuentaDestino, monto, detalle);
                System.out.println("Transferencia realizada");
                System.out.println("Cuenta Origen: " + cuentaOrigen);
                System.out.println("Cuenta Destino: " + cuentaDestino);
                System.out.println("Monto: " + monto);
                System.out.println("Detalle: " + detalle);

                // Si la transferencia fue exitosa, redirigir a la p�gina de �xito
                response.sendRedirect("transferenciaExitosa.jsp");

            } catch (Exception e) {
                // Si ocurre un error, se maneja la excepci�n
                e.printStackTrace();
                // Enviar mensaje de error y redirigir a la p�gina de transferencia
                request.setAttribute("error", "Ha ocurrido un error en la transferencia.");
                RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                rd.forward(request, response);
            }
        } else {
            // Si no se presion� el bot�n de transferencia, redirigir a la p�gina de transferencia
            RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
            rd.forward(request, response);
        }
    }
    }



