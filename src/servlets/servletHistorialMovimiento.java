package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Usuario;
import negocio.NegocioCuentas;
import negocio.NegocioMovimiento;

@WebServlet("/servletHistorialMovimiento")
public class servletHistorialMovimiento extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletHistorialMovimiento() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String cuil = usuario.getCuilUs();
        NegocioMovimiento negocioMovimiento = new NegocioMovimiento();
        NegocioCuentas negocioCuentas = new NegocioCuentas();

        List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);

        if (cuentas != null && !cuentas.isEmpty()) {
            request.setAttribute("cuentas", cuentas);
        } else {
            request.setAttribute("MensajeCuentas", "No se encontraron cuentas asociadas.");
        }

        String cuentaSeleccionada = request.getParameter("cuentaSeleccionada");
        String tipoMovimiento = request.getParameter("tipoMovimiento");

        if (cuentaSeleccionada != null) {
            try {
                int numeroCuenta = Integer.parseInt(cuentaSeleccionada);

                List<Movimiento> listaMovimientos;
                if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) {
                    listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuentaYTipo(numeroCuenta, tipoMovimiento);
                } else {
                    listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuenta(numeroCuenta);
                }

                if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
                    request.setAttribute("listaMovimientos", listaMovimientos);
                } else {
                    request.setAttribute("MensajeMovimientos", "No se encontraron movimientos para la cuenta seleccionada.");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("MensajeMovimientos", "El número de cuenta proporcionado no es válido.");
            }
        } else {
            request.setAttribute("MensajeMovimientos", "No se seleccionó ninguna cuenta.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("Movimientos.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
