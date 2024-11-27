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

        // Paginaci�n
        int page = 1; // P�gina por defecto
        int limit = 5; // N�mero de movimientos por p�gina

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Manejar el error si el par�metro no es v�lido
                page = 1;
            }
        }

        if (cuentaSeleccionada != null) {
            try {
                int numeroCuenta = Integer.parseInt(cuentaSeleccionada);

                List<Movimiento> listaMovimientos;
                int totalMovimientos;

                // Obtener movimientos seg�n los par�metros de cuenta y tipo de movimiento
                if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) {
                    listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuentaYTipoPaginado(numeroCuenta, tipoMovimiento, page, limit);
                    totalMovimientos = negocioMovimiento.contarMovimientosPorCuentaYTipo(numeroCuenta, tipoMovimiento);
                } else {
                    listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuentaPaginado(numeroCuenta, page, limit);
                    totalMovimientos = negocioMovimiento.contarMovimientosPorCuenta(numeroCuenta);
                }

                if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
                    request.setAttribute("listaMovimientos", listaMovimientos);
                    int totalPaginas = (int) Math.ceil((double) totalMovimientos / limit);
                    request.setAttribute("totalPaginas", totalPaginas);
                    request.setAttribute("paginaActual", page);
                } else {
                    request.setAttribute("MensajeMovimientos", "No se encontraron movimientos para la cuenta seleccionada.");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("MensajeMovimientos", "El n�mero de cuenta proporcionado no es v�lido.");
            }
        } else {
            request.setAttribute("MensajeMovimientos", "No se seleccion� ninguna cuenta.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("Movimientos.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
