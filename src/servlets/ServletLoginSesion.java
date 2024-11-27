package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import negocio.NegocioUsuarios;

import negocio.NegocioClientes;

/**
 * Servlet implementation class ServletLoginSesion
 */
@WebServlet("/ServletLoginSesion")
public class ServletLoginSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegocioUsuarios negUsr;
	
    public void init() throws ServletException {
    	negUsr = new NegocioUsuarios(); 
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginSesion() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioUs = request.getParameter("usuario");
        String contraseniaUs = request.getParameter("contrasena");

        Usuario usuario = negUsr.validarUsuario(usuarioUs, contraseniaUs);

        if (usuario != null && usuario.isEstadoUs()) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            
            if (usuario.isRolUs()) { 
                response.sendRedirect("MenuAdm.jsp");
            } else { 
                //response.sendRedirect("InicioCliente.jsp");
                response.sendRedirect("servletInformacionCliente"); // Redirige al servlet
            }
        } else {
            request.setAttribute("errorMessage", "Usuario o contrase�a incorrectos.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
	

}
