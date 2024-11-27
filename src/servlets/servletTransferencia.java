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
        // Obtener el usuario desde la sesión
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
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                String cbuCuentaDestino = request.getParameter("cbu");
                double monto = Double.parseDouble(request.getParameter("monto"));
                String detalle = request.getParameter("detalle");

                // Obtener la fecha y hora actuales con el formato deseado
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaHoraTransferencia = ahora.format(formatter); // Formato deseado

                // Llamar al negocio para realizar la transferencia
                transferenciaNegocio.realizarTransferencia(cuentaOrigen, cbuCuentaDestino, monto, detalle);

                // Establecer los atributos de sesión
                HttpSession session = request.getSession();
                session.setAttribute("cbuDestino", cbuCuentaDestino);
                session.setAttribute("importe", String.valueOf(monto));
                session.setAttribute("fechaHoraTransferencia", fechaHoraTransferencia); // Guardar la fecha y hora
                session.setAttribute("detalle", detalle); // Guardar el detalle

                // Redirigir a la página de transferencia exitosa
                RequestDispatcher rd = request.getRequestDispatcher("transferenciaExitosa.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error en la transferencia: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                rd.forward(request, response);
            }
        }
    }
}
