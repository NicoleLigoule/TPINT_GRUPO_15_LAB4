package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.DDL;
import entidades.Cuenta;
import entidades.InteresesXCantidadDeMeses;
import entidades.Usuario;

@WebServlet("/ServletSolicitarPrestamo")
public class ServletSolicitarPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DDL desplegable;

    public void init() throws ServletException {
        desplegable = new DDL();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        // Obtener usuario de la sesión
//        HttpSession session = request.getSession();
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//
//        if (usuario == null) {
//            response.sendRedirect("Login.jsp"); // Redirigir si no hay sesión
//            return;
//        }
//
//        // Consultar cuentas asociadas al CUIL del usuario  obtenerIntereses
//        String cuil = usuario.getCuilUs();
//        List<Cuenta> cuentas = desplegable.obtenercuentaUsurio(cuil);
//        ArrayList<InteresesXCantidadDeMeses> intereses= desplegable.obtenerIntereses();
//        // Pasar la lista de cuentas al JSP
//        request.setAttribute("cuentas", cuentas);
//        request.setAttribute("intereses", intereses);
//        // Redirigir al JSP para la solicitud de préstamo
//        request.getRequestDispatcher("SolicitarPrestamo.jsp").forward(request, response);
//   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String cuil = usuario.getCuilUs();
        try {
            List<Cuenta> cuentas = desplegable.obtenercuentaUsurio(cuil);
            ArrayList<InteresesXCantidadDeMeses> intereses = desplegable.obtenerIntereses();
            request.setAttribute("plazosPago", intereses);

            if (cuentas == null || cuentas.isEmpty()) {
                request.setAttribute("error", "No se encontraron cuentas asociadas.");
            } else {
                request.setAttribute("cuentas", cuentas);
            }

            if (intereses == null || intereses.isEmpty()) {
                request.setAttribute("error", "No se encontraron opciones de intereses.");
            } else {
                request.setAttribute("intereses", intereses);
            }
        } catch (Exception e) {
            // Manejo de excepción
            request.setAttribute("error", "Ocurrió un error al recuperar los datos.");
            e.printStackTrace(); // Esto es solo para depuración. Considera quitar en producción.
        }

        // Redirigir al JSP
        request.getRequestDispatcher("SolicitarPrestamo.jsp").forward(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
