package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.Usuario;
import negocio.NegocioCuentas;
import negocio.NegocioTransferencia;

@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NegocioTransferencia transferenciaNegocio;

    public servletTransferencia() {
        super();
        this.transferenciaNegocio = new NegocioTransferencia(); // Instancia de negocio
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario desde la sesi�n
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        System.out.println("entro al servlet transferencia");
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String cuil = usuario.getCuilUs(); // Obtener CUIL del usuario logueado
        NegocioCuentas negocioCuentas = new NegocioCuentas();
        List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);

        if (cuentas == null || cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas para el CUIL: " + cuil);
        }

        request.setAttribute("cuentas", cuentas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        if (request.getParameter("RealizarBtn") != null) {
            try {
                // Capturar los datos ingresados en el formulario
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                String cbuCuentaDestino = request.getParameter("cbu");
                double monto = Double.parseDouble(request.getParameter("monto"));
                String detalle = request.getParameter("detalle");

                // Guardar los datos en la sesi�n
                HttpSession session = request.getSession();
                session.setAttribute("cuentaOrigen", cuentaOrigen);
                session.setAttribute("cbuDestino", cbuCuentaDestino);
                session.setAttribute("importe", monto);
                session.setAttribute("detalle", detalle);

                // Redirigir al servlet de confirmaci�n
                response.sendRedirect("servletConfirmarTransferencia");

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al procesar los datos: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                rd.forward(request, response);
            }
        }
    }



}
