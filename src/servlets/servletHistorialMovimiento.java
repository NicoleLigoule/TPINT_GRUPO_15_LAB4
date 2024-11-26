package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.MovimientoDaoImpl;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Usuario;
import negocio.NegocioCuentas;
import negocio.NegocioMovimiento;

/**
 * Servlet implementation class servletHistorialMovimiento
 */
@WebServlet("/servletHistorialMovimiento")
public class servletHistorialMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletHistorialMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String cuil = usuario.getCuilUs();
        System.out.println("CUIL del usuario: " + cuil); // Depuración

        NegocioMovimiento negocioMovimiento = new NegocioMovimiento();
        NegocioCuentas negocioCuentas = new NegocioCuentas();

        // Obtener las cuentas asociadas al usuario
        List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);

        if (cuentas != null && !cuentas.isEmpty()) {
            request.setAttribute("cuentas", cuentas); // Atributo para el JSP
            
        } else {
            request.setAttribute("MensajeCuentas", "No se encontraron cuentas asociadas.");
        }

        // Obtener la cuenta seleccionada
        String cuentaSeleccionada = request.getParameter("cuentaSeleccionada");

        if (cuentaSeleccionada != null) {
            try {
                int numeroCuenta = Integer.parseInt(cuentaSeleccionada);
                List<Movimiento> listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuenta(numeroCuenta);

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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
