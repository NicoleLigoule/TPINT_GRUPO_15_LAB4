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
        // Si se necesita verificar un parámetro de la URL (como 'Param'), se puede manejar aquí
        System.out.println("Entro al servlet transferencia");
        String param = request.getParameter("Param");
        if (param != null) {
            System.out.println("El parámetro Param recibido es: " + param);
        }

        // Aquí redirigimos a la página de la transferencia si es necesario
        RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificamos si se ha presionado el botón "Realizar Transferencia"
    
        if(request.getParameter("RealizarBtn") != null) {
            try {
                // Obtener los parámetros del formulario (cuentas, monto y detalle)
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                int cuentaDestino = Integer.parseInt(request.getParameter("cuentaDestino"));
                double monto = Double.parseDouble(request.getParameter("monto"));
                String detalle = request.getParameter("detalle");

                // Validación de los parámetros
                if (cuentaOrigen <= 0 || cuentaDestino <= 0 || monto <= 0) {
                    // Si los parámetros no son válidos, enviamos un mensaje de error
                    request.setAttribute("error", "Parámetros inválidos para la transferencia.");
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

                // Si la transferencia fue exitosa, redirigir a la página de éxito
                response.sendRedirect("transferenciaExitosa.jsp");

            } catch (Exception e) {
                // Si ocurre un error, se maneja la excepción
                e.printStackTrace();
                // Enviar mensaje de error y redirigir a la página de transferencia
                request.setAttribute("error", "Ha ocurrido un error en la transferencia.");
                RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                rd.forward(request, response);
            }
        } else {
            // Si no se presionó el botón de transferencia, redirigir a la página de transferencia
            RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
            rd.forward(request, response);
        }
    }
    }



