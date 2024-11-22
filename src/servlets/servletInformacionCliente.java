package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.ClienteCuentaDTO;
import entidades.Usuario;
import negocio.DDL;

/**
 * Servlet implementation class servletInformacionCliente
 */
@WebServlet("/servletInformacionCliente")
public class servletInformacionCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletInformacionCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("LLEGA AL SERVLET ");
		HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Obtener el CUIL del usuario
        String cuil = usuario.getCuilUs();
        System.out.print("Cuil usuario: "+ cuil);
        
        if(cuil != null)
        {
        	DDL ddl = new DDL();
        	ClienteCuentaDTO infoCliente = ddl.infoClienteCtas(cuil);
        	if (infoCliente != null)
        	{
        		                  
                 String nombreApellido = infoCliente.getNombre() + " " + infoCliente.getApellido();
                 System.out.print("nombreApellido: "+nombreApellido);
                 String correo = infoCliente.getCorreoElectronico();
                 String telefono = infoCliente.getTelefono();
                 String direccion = infoCliente.getDireccion();
                BigDecimal saldoCtaCorr = infoCliente.getSaldoCtaCorriente();                
                BigDecimal saldoCajaAhorro = infoCliente.getSaldoCajaAhorro();
                Integer numeroCtaCorriente = infoCliente.getNumeroCuentaCorriente();
                Integer numeroCajaAhorro = infoCliente.getNumeroCajaAhorro();
                 
                 request.setAttribute("usuario", usuario);
                 request.setAttribute("nombreApellido", nombreApellido);
                 request.setAttribute("correo", correo);
                 request.setAttribute("telefono", telefono);
                 request.setAttribute("direccion", direccion);
                 request.setAttribute("saldoCtaCorr", saldoCtaCorr);
                 request.setAttribute("saldoCajaAhorro", saldoCajaAhorro);
                 request.setAttribute("numeroCtaCorriente", numeroCtaCorriente);
                 request.setAttribute("numeroCajaAhorro", numeroCajaAhorro);
        	}
        }
        
        

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("InicioCliente.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
